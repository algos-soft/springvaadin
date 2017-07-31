package it.algos.springvaadin.service;

import it.algos.springvaadin.model.AlgosEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

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
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     */
    public AlgosEntity newEntity();


    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entityBean to be saved
     *
     * @return the saved entity
     */
    public AlgosEntity save(AlgosEntity entityBean);


    /**
     * Returns all entities of the type.
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
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    public int count();


    /**
     * Deletes a given entity.
     *
     * @param entityBean must not be null
     *
     * @return true, se la entity è stata effettivamente cancellata
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public boolean delete(AlgosEntity entityBean);


    /**
     * Deletes all entities of the collection.
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


    public void setEntityClass(Class<? extends AlgosEntity> entityClass);

}// end of interface

