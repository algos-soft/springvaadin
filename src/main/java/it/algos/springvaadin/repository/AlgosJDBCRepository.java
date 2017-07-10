package it.algos.springvaadin.repository;

import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

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
        String query = LibSql.getQueryCount(tableName);
        return jdbcTemplate.queryForObject(query, Long.class);
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entity
     */
    @Override
    public Iterable findAll() {
        String query = LibSql.getQueryFindAll(tableName);
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
    public AlgosEntity findOne(Serializable id) {
        String query = LibSql.getQueryFindOne(tableName);
        return (AlgosEntity) jdbcTemplate.queryForObject(query, new Object[]{id}, rowMapper);
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
    public AlgosEntity save(AlgosEntity entity) {

        if (entity != null && entity instanceof AlgosEntity && ((AlgosEntity) entity).getId() != null && ((AlgosEntity) entity).getId() > 0) {
            return update((AlgosEntity) entity);
        } else {
            return insert((AlgosEntity) entity);
        }// end of if/else cycle

    }// end of method


    /**
     * Creazione di una nuova entity
     *
     * @param entity da registrare/creare
     *
     * @return entity DOPO la registrazione (the save operation might have changed the entity instance completely)
     */
    public AlgosEntity insert(AlgosEntity entity) {
        AlgosEntity entityCreated = null;
        SimpleJdbcInsert insert;
        Number key;

        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName).usingGeneratedKeyColumns(Cost.PROPERTY_ID);
        key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));

        if (key != null) {
            entityCreated = findOne(key);
        }// end of if cycle

        return entityCreated;
    }// end of method


    /**
     * Modifica di una entity esistente
     * parameter values from bean properties of a given JavaBean object
     *
     * @param entity da registrare/modificare
     *
     * @return entity DOPO la registrazione (the save operation might have changed the entity instance completely)
     */
    public AlgosEntity update(AlgosEntity entity) {
        AlgosEntity entityUpdated = entity;
        int ritorno;
        long key = entity.getId();
        String query = LibSql.getQueryUpdate(entity);
        Object[] values = LibReflection.getValues(entity);

        ritorno = jdbcTemplate.update(query, values);

        if (ritorno == 1) {
            entityUpdated = findOne(key);
        }// end of if cycle

        return entityUpdated;
    }// end of method

    /**
     * Deletes the entity with the given id.
     *
     * @param serializable must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public void delete(Serializable serializable) {
        String query = LibSql.getQueryDelete(tableName);
        jdbcTemplate.update(query, new Object[]{serializable});
    }// end of method


    /**
     * Recupera il valore massimo della property (numerica) indicata
     *
     * @param propertyName
     *
     * @return max value if a property exists, zero otherwise
     */
    @Override
    public int getMax(String propertyName) {
        int valore = 0;
        String query = LibSql.getQueryMax(tableName, propertyName);
        List<Map<String, Object>> lista = jdbcTemplate.queryForList(query);
        Number value = 0;

        if (lista != null && lista.size() == 1) {
            if (lista.get(0) != null) {
                if (lista.get(0).containsKey(propertyName)) {
                    valore = (Integer) lista.get(0).get(propertyName);
                }// end of if cycle
            }// end of if cycle
        }// end of if cycle

        return valore;
    }// end of method

}// end of class
