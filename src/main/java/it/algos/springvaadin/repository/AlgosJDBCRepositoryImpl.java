package it.algos.springvaadin.repository;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * Created by gac on 02/07/17
 * <p>
 * Implementazione concreta e completa dell'interfaccia AlgosJDBCRepository (che deriva da AlgosRepositoryOld)
 * Deve garantire l'implementazione di tutti i metodi dell'interfaccia
 * Le sottoclassi Repository regolano alcuni parametri (nel metodo @PostConstruct)
 * Le sottoclassi specifiche possono implementare ulteriori metodi non di uso generalizzato
 * (sconsigliato perché obbliga un casting dell'interfaccia)
 */
@Lazy
@SpringComponent
public class AlgosJDBCRepositoryImpl implements AlgosJDBCRepository {


    //--template per eseguire le Query
    //--viene iniettato automaticamente nel costruttore
    protected JdbcTemplate jdbcTemplate;


    //--tavola di riferimento
    //--regolata nella sottoclasse concreta
    protected String tableName;


    /**
     * Costruttore nel quale SpringBoot inietta automaticamente il riferimento a JdbcTemplate
     */
    @Autowired
    public AlgosJDBCRepositoryImpl(JdbcTemplate jdbcTemplate) {
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

        //--test di esistenza per invocare il metodo specifico di creazione della tavola
        if (nonEsiste()) {
            creaTable();
        }// end of if cycle

        //--test per popolare (opzionale) la tavola specifica se è vuota
        if (vuota()) {
            creaDatiIniziali();
        }// end of if cycle
    }// end of method


    /**
     * Controlla l'esistenza della tavola
     * Usa un metodo qualsiasi
     * Se va in errore, la tavola non esiste
     */
    private boolean nonEsiste() {
        boolean nonEsiste = false;

        String query = "SELECT * FROM " + tableName + " LIMIT 1;";

        try { // prova ad eseguire il codice
            jdbcTemplate.execute(query);
        } catch (Exception unErrore) { // intercetta l'errore
            nonEsiste = true;
        }// fine del blocco try-catch

        return nonEsiste;
    }// end of method


    /**
     * Controlla se la tavola contiene dei records
     */
    private boolean vuota() {
        return count() == 0;
    }// end of method


    /**
     * Regolazione specifica dei parametri generali
     */
    protected void regolaParametri() {
    }// end of method


    /**
     * Creazione specifica della tavola
     */
    protected void creaTable() {
    }// end of method


    /**
     * Creazione specifica dei dati iniziali
     */
    protected void creaDatiIniziali() {
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
     * Returns whether an entity with the given id exists.
     *
     * @param serializable must not be {@literal null}.
     *
     * @return true if an entity with the given id exists, {@literal false} otherwise
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public boolean exists(Serializable serializable) {
        return false;
    }


    /**
     * Returns all instances of the type.
     *
     * @return all entity
     */
    @Override
    public Iterable findAll() {
        String query = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(Versione.class));//@todo errore
    }// end of method

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param iterable
     *
     * @return
     */
    @Override
    public Iterable findAll(Iterable iterable) {
        return null;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param serializable must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal null} if none found
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @Override
    public Object findOne(Serializable serializable) {
        return null;
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     *
     * @return the saved entity
     */
    @Override
    public Object save(Object entity) {
        return null;
    }

    /**
     * Saves all given entity.
     *
     * @param entities
     *
     * @return the saved entity
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public Iterable save(Iterable entities) {
        return null;
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param serializable must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public void delete(Serializable serializable) {

    }

    /**
     * Deletes a given entity.
     *
     * @param entity
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(Object entity) {

    }

    /**
     * Deletes the given entity.
     *
     * @param entities
     *
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public void delete(Iterable entities) {

    }

    /**
     * Deletes all entity managed by the repository.
     */
    @Override
    public void deleteAll() {

    }

}// end of class
