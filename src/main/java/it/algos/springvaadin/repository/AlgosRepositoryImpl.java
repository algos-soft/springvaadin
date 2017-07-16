package it.algos.springvaadin.repository;

import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;

import java.io.Serializable;

/**
 * Created by gac on 03/07/17
 * <p>
 * Implementazione generica (non fa nulla)
 * Le metodologie concrete specifiche, possono essere di vari tipi:
 * - Spring Boot JDBC usa JdbcTemplate (basso livello, architettura più semplice, uso più macchinoso)
 * - Spring Boot JPA usa le Repository (alto livello, architettura automatica molto più complessa, uso facile ed elegante)
 * Ognuna può implementare concretamente solo i metodi che utilizza
 */
public class AlgosRepositoryImpl implements AlgosRepository {

    /**
     * Returns whether a table exists.
     *
     * @return true if an table exists, {@literal false} otherwise
     */
    @Override
    public boolean exists() {
        return false;
    }// end of method

    /**
     * Creazione iniziale di una tavola
     */
    @Override
    public void creaTable() {

    }// end of method


    /**
     * Non fa nulla. Tenuta solo per implementare tutti i metodi dell'interfaccia CrudRepository
     * Rimanda a save(AlgosEntity entity)
     */
    @Override
    public AlgosEntity save(Object entity) {
        return save(((AlgosEntity) entity));
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
    public AlgosEntity save(AlgosEntity entity) {
        return null;
    }// end of method

    /**
     * Saves all given entities.
     *
     * @param entities
     *
     * @return the saved entities
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public Iterable save(Iterable entities) {
        return null;
    }// end of method

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
    }// end of method

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public Iterable findAll() {
        return null;
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
    }// end of method

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @Override
    public long count() {
        return 0;
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
    }// end of method

    /**
     * Deletes a given entity.
     *
     * @param entity
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(Object entity) {
    }// end of method

    /**
     * Deletes the given entities.
     *
     * @param entities
     *
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public void delete(Iterable entities) {
    }// end of method

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {

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
        return 0;
    }// end of method

}// end of class
