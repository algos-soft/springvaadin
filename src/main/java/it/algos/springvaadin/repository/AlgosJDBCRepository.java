package it.algos.springvaadin.repository;

import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.lib.LibSql;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by gac on 02/07/17
 * <p>
 * Implementazione concreta che utilizza JdbcTemplate (basso livello, architettura più semplice, uso più macchinoso)
 * Questa Repository è alternativa a AlgosJPARepository//@todo da sviluppare
 * <p>
 * Le sottoclassi specifiche devono regolare alcuni parametri (nel metodo @PostConstruct)
 * Le sottoclassi specifiche possono implementare ulteriori metodi non di uso generalizzato
 * (sconsigliato perché obbliga un casting dell'interfaccia)
 */
@Lazy
@SpringComponent
public class AlgosJDBCRepository extends AlgosRepositoryImpl {


    //--template per eseguire le Query
    //--viene iniettato automaticamente nel costruttore
    protected JdbcTemplate jdbcTemplate;


    //--tavola di riferimento
    //--regolata nella sottoclasse concreta
    protected String tableName;


    //--query per la creazione iniziale della tavola
    //--regolata nella sottoclasse concreta
    protected String createQuery;


    //--mappatura del modello dati
    //--regolata nella sottoclasse concreta
    protected RowMapper rowMapper;


    /**
     * Costruttore nel quale SpringBoot inietta automaticamente il riferimento a JdbcTemplate
     */
    @Autowired
    public AlgosJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }// fine del metodo costruttore Autowired


    /**
     * Indicates a method to be invoked AFTER a bean has been created and dependency injection is complete.
     * Used to perform any initialization work necessary.
     * Performing the initialization in a constructor is not suggested
     * Possono esserci diversi metodi con @PostConstruct ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    public void inizia() {
        //--indispensabile passare dalla sottoclasse, se non altro per il nome della tavola
        regolaParametri();

//        //--test di esistenza per invocare la creazione della tavola
//        if (nonEsiste()) {
//            creaTable();
//        }// end of if cycle
//
//        //--test per popolare (opzionale) la tavola specifica se è vuota
//        if (vuota()) {
//            creaDatiIniziali();
//        }// end of if cycle
    }// end of method


//    /**
//     * Controlla l'esistenza della tavola
//     * Usa un metodo qualsiasi
//     * Se va in errore, la tavola non esiste
//     */
//    private boolean nonEsiste() {
//        boolean nonEsiste = false;
//
//        String query = "SELECT * FROM " + tableName + " LIMIT 1;";
//
//        try { // prova ad eseguire il codice
//            jdbcTemplate.execute(query);
//        } catch (Exception unErrore) { // intercetta l'errore
//            nonEsiste = true;
//        }// fine del blocco try-catch
//
//        return nonEsiste;
//    }// end of method


//    /**
//     * Controlla se la tavola contiene dei records
//     */
//    private boolean vuota() {
//        return count() == 0;
//    }// end of method


    /**
     * Regolazione specifica dei parametri generali
     */
    protected void regolaParametri() {
    }// end of method


    /**
     * Creazione iniziale di una tavola
     */
    @Override
    public void creaTable() {
        jdbcTemplate.execute(createQuery);
    }// end of method


    /**
     * Returns whether a table exists.
     *
     * @return true if an table exists, {@literal false} otherwise
     */
    @Override
    public boolean exists() {
        boolean esiste = false;

        String query = "SELECT 1 FROM " + tableName + " LIMIT 1;";

        try { // prova ad eseguire il codice
            jdbcTemplate.execute(query);
            esiste = true;
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return esiste;
    }// end of method


    /**
     * Returns the number of entity available.
     *
     * @return the number of entity
     */
    @Override
    public long count() {
        String query = "select count(*) from " + tableName;
        return jdbcTemplate.queryForObject(query, Long.class);
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entity
     */
    @Override
    public Iterable findAll() {
        String query = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(query, rowMapper);
    }// end of method

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal null} if none found
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public Object findOne(Serializable id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        return (AlgosModel) jdbcTemplate.queryForObject(query, new Object[]{id}, rowMapper);
    }// end of method

    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entity
     *
     * @return the saved entity
     */
    @Override
    public Object save(Object entity) {

        if (entity != null && entity instanceof AlgosModel && ((AlgosModel) entity).getId() != null && ((AlgosModel) entity).getId() > 0) {
            return update((AlgosModel) entity);
        } else {
            return insert((AlgosModel) entity);
        }// end of if/else cycle

    }// end of method

    /**
     * Creazione di una nuova entity
     * Utilizza la mappa della sottoclasse
     */
    public int insert(AlgosModel entityBean) {
        String query = LibSql.getQueryInsert(tableName, new String[]{"ordine", "titolo", "descrizione", "modifica"});
        Versione vers = (Versione) entityBean;

//        LinkedHashMap<String, Object> map = this.getBeanMap(entityBean);
//        String campi = StringUtils.join(map.keySet(), ",");
//        String valori = LibText.repeat("?", ",", map.size());
//        campi = LibText.setTonde(campi);
//        valori = " values" + LibText.setTonde(valori);
//        String query = "INSERT INTO " + tableName + campi + valori;

//        return jdbcTemplate.update(query, map.values().toArray());
        jdbcTemplate.update(query, new Object[]{vers.getOrdine(), vers.getTitolo(), vers.getDescrizione(), vers.getModifica()});
        return 0;
    }// end of method


    public AlgosModel update(AlgosModel entityBean) {
        String query = "UPDATE " + tableName + " SET ";
        String campi = "";
        LinkedHashMap<String, Object> map = this.getBeanMap(entityBean);

        for (String campo : map.keySet()) {
            campi += campo + "=?, ";
        }// end of for cycle
        campi = LibText.levaCoda(campi, ",");

        query += campi + " WHERE id=?";
        map.put("id", entityBean.getId());
        jdbcTemplate.update(query, map.values().toArray());

        return null;//@todo occhio
    }// end of method

    protected LinkedHashMap<String, Object> getBeanMap(AlgosModel entityBean) {
        return null;
    }// end of method

}// end of class
