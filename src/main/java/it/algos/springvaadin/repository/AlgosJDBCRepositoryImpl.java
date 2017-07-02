package it.algos.springvaadin.repository;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * Created by gac on 02/07/17
 * <p>
 * Repository per utilizzare JdbcTemplate
 * La logica di selezione, controllo e manipolazione dei dati risiede nei Service
 * Nelle Repository c'è l'implementazione specifica di un collegamento al DB
 * <p>
 * Questa Repository è alternativa a AlgosJPARepository
 * Possono essere scambiate facilmente lasciando inalterati i Service
 *
 * @see https://spring.io/guides/gs/relational-data-access/
 */
//@SpringComponent
public class AlgosJDBCRepositoryImpl implements AlgosJDBCRepository {


    //--template per eseguire le Query
    //--viene iniettato automaticamente nel costruttore
    protected JdbcTemplate jdbcTemplate;


    //--tavola di riferimento
    //--regolata nella sottoclasse concreta
    protected String tableName;


    /**
     *
     */
    @Autowired
    public AlgosJDBCRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }// fine del metodo costruttore Autowired


    @PostConstruct
    public void inizia() {
        regolaParametri();

        if (nonEsiste()) {
            creaTable();
        }// end of if cycle

        if (vuota()) {
            creaDatiIniziali();
        }// end of if cycle
    }// end of method


    protected boolean nonEsiste() {
        boolean nonEsiste = false;

        String query = "SELECT * FROM " + tableName + " LIMIT 1;";

        try { // prova ad eseguire il codice
            jdbcTemplate.execute(query);
        } catch (Exception unErrore) { // intercetta l'errore
            nonEsiste = true;
        }// fine del blocco try-catch

        return nonEsiste;
    }// end of method


    protected boolean vuota() {
        return count() == 0;
    }// end of method


    protected void regolaParametri() {
    }// end of method


    protected void creaTable() {
    }// end of method


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
        return null;
    }

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
