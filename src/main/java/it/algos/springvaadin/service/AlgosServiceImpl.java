package it.algos.springvaadin.service;

import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.repository.AlgosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.algos.springvaadin.lib.LibReflection.getBeanMap;

/**
 * Created by gac on 07/07/17
 * .
 */
@Service
public abstract class AlgosServiceImpl implements AlgosService {

    private AlgosEntity entity;
    private AlgosRepository repository;

    /**
     * Costruttore @Autowired (nella superclasse)
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    @Autowired //@todo in realtà funziona anche senza @Autowired. Non capisco :-(
    public AlgosServiceImpl(AlgosEntity entity, AlgosRepository repository) {
        this.entity = entity;
        this.repository = repository;
    }// end of Spring constructor


    @Override
    public int count() {
        return 0;
    }

    /**
     * Recupera il singolo bean
     *
     * @param id
     */
    @Override
    public AlgosEntity findById(long id) {
        return null;
    }

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
     * @return all entity
     */
    @Override
    public List<AlgosEntity> findAll() {
        List<AlgosEntity> target = new ArrayList();
        Iterable iterable = repository.findAll();

        if (iterable != null) {
            if (iterable instanceof List) {
                target = (List<AlgosEntity>) iterable;
            } else {
                final List<AlgosEntity> targetFinal = new ArrayList();
                iterable.forEach(it -> targetFinal.add((AlgosEntity) it));
                target = targetFinal;
            }// end of if/else cycle
        }// end of if cycle

        return target;
    }// end of method

    /**
     * Creazione di un nuovo bean
     * Utilizza la mappa della sottoclasse
     *
     * @param entityBean
     */
    @Override
    public AlgosEntity save(AlgosEntity entityBean) {
        return null;
    }

    /**
     * Cancella il singolo bean
     *
     * @param entity
     */
    @Override
    public boolean delete(AlgosEntity entity) {
        return false;
    }


    @Override
    public Class<? extends AlgosEntity> getEntityClass() {
        return null;
    }

    /**
     * Returns whether a table exists.
     *
     * @return true if an table exists, {@literal false} otherwise
     */
    @Override
    public boolean exists() {
        return false;
    }

    /**
     * Creazione iniziale di una tavola
     */
    @Override
    public boolean creaTable() {
        return false;
    }

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
    public List<AlgosEntity> save(List<AlgosEntity> entities) {
        return null;
    }

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
    @Override
    public Iterable findAll(Iterable iterable) {
        return null;
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id key, must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public boolean delete(long id) {
        return false;
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     *
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public boolean delete(Iterable entities) {
        return false;
    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public boolean deleteAll() {
        return false;
    }


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getListColumns() {
        return LibReflection.getAllFieldName(entity.getClass());
    }// end of method


    /**
     * Ordine standard di presentazione dei fields nel form
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getFormFields() {
        return null;
    }// end of method

}// end of class
