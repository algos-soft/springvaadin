package it.algos.springvaadin.service;

import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gac on 07/07/17
 * <p>
 * Contratto d'interfaccia con i metodi che il Service rende disponibili all'applicazione,
 * in particolare ad AlgosPresenter
 * Implementati nella classe concreta AlgosServiceImpl
 */
public interface AlgosService {


    /**
     * Returns whether a table exists.
     *
     * @return true if an table exists, {@literal false} otherwise
     */
    public boolean exists();


    /**
     * Creazione iniziale di una tavola
     */
    public boolean creaTable();


    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entity
     *
     * @return the saved entity
     */
    public AlgosEntity save(AlgosEntity entity);


    /**
     * Saves all given entities.
     *
     * @param entities
     *
     * @return the saved entities
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public List<AlgosEntity> save(List<AlgosEntity> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal null} if none found
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public AlgosEntity findById(long id);


    /**
     * Returns whether an entity with the given id exists.
     *
     * @param serializable must not be {@literal null}.
     *
     * @return true if an entity with the given id exists, {@literal false} otherwise
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public boolean exists(Serializable serializable);

    /**
     * Returns all instances of the type.
     * <p>
     * Senza filtri
     * Ordinati per ID
     * <p>
     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
     * Eseguo qui la conversione, che rimane trasparente al resto del programma
     *
     * @return all entities
     */
    public List<AlgosEntity> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     * <p>
     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
     * Eseguo qui la conversione, che rimane trasparente al resto del programma
     *
     * @param iterable
     *
     * @return
     */
    public Iterable findAll(Iterable iterable);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    public int count();


    /**
     * Deletes the entity with the given id.
     *
     * @param id key, must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */

    public boolean delete(long id);

    /**
     * Deletes a given entity.
     *
     * @param entity must not be null
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */

    public boolean delete(AlgosEntity entity);


    /**
     * Deletes the given entities.
     *
     * @param entities
     *
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    public boolean delete(Iterable entities);


    /**
     * Deletes all entities managed by the repository.
     */
    public boolean deleteAll();


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getListColumns();

    /**
     * Ordine standard di presentazione dei fields nel form
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getFormFields();

    public Class<? extends AlgosEntity> getEntityClass();

}// end of interface
