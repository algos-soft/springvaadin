package it.algos.springvaadin.service;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosMongoRepository;
import it.algos.springvaadin.repository.AlgosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 07/07/17
 * Implementazione standar dell'interfaccia AlgosService
 * Deve sempre esistere la sottoclasse concreta
 */
public abstract class AlgosServiceImpl implements AlgosService {


    protected MongoRepository repository;


    /**
     * Costruttore @Autowired
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AlgosServiceImpl(MongoRepository repository) {
        this.repository = repository;
    }// end of Spring constructor


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     */
    public AlgosEntity newEntity() {
        return null;
    }// end of method


    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entityBean da salvare
     *
     * @return the saved entity
     */
    public AlgosEntity save(AlgosEntity entityBean) {
        return (AlgosEntity) repository.save(entityBean);
    }// end of method


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
    @Override
    public List findAll() {
        return null;
    }// end of method


    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @Override
    public int count() {
        return 0;
    }// end of method


    /**
     * Deletes a given entity.
     *
     * @param entityBean must not be null
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public boolean delete(AlgosEntity entityBean) {
        return false;
    }// end of method


    /**
     * Deletes all entities of the collection.
     */
    @Override
    public boolean deleteAll() {
        return false;
    }// end of method

    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    @Override
    public List<String> getListColumns() {
        return null;
    }// end of method

    /**
     * Ordine standard di presentazione dei fields nel form
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    @Override
    public List<String> getFormFields() {
//        return LibReflection.getAllFieldName(entity.getClass());
        return null;
    }// end of method


}// end of class
