package it.algos.springvaadin.service;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 07/07/17
 * .
 */
@Service
public abstract class AlgosServiceImpl implements AlgosService {

//    private AlgosEntity entity;
//
//    /**
//     * Costruttore @Autowired (nella superclasse)
//     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
//     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
//     * Si usa un @Qualifier(), per avere la sottoclasse specifica
//     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
//     */
//    @Autowired //@todo in realtà funziona anche senza @Autowired. Non capisco :-(
//    public AlgosServiceImpl(AlgosEntity entity) {
//        this.entity = entity;
////        this.repository = repository;
//    }// end of Spring constructor
//
//
//    /**
//     * Indicates a method to be invoked AFTER a bean has been created and dependency injection is complete.
//     * Used to perform any initialization work necessary.
//     * Performing the initialization in a constructor is not suggested
//     * Possono esserci diversi metodi con @PostConstruct ma l'ordine con cui vengono chiamati NON è garantito
//     */
//    @PostConstruct
//    public void inizia() {
////        //--test di esistenza per invocare il metodo specifico di creazione della tavola
////        if (!esisteTable()) {
////            repository.creaTable();
////        }// end of if cycle
//
//        //--test per popolare (opzionale) la tavola specifica se è vuota
////        if (vuota()) {
////            creaDatiIniziali();
////        }// end of if cycle
//    }// end of method
//
//
//    protected boolean esisteTable() {
//        return false;
////        return repository.exists();
//    }// end of method
//
//
//    protected boolean vuota() {
//        return count() == 0;
//    }// end of method
//
//
//    protected void creaDatiIniziali() {
//    }// end of method
//
//
//    @Override
//    public int count() {
//        int totaleInt = 0;
////        Long totaleLong = repository.count();
////
////        if (totaleLong != null && totaleLong > 0) {
////            totaleInt = totaleLong.intValue();
////        }// end of if cycle
//
//        return totaleInt;
//    }// end of method
//
//    /**
//     * Recupera il singolo bean
//     *
//     * @param id
//     */
//    @Override
//    public AlgosEntity findById(long id) {
//        return (AlgosEntity) repository.findOne(id);
//    }// end of method
//
//    /**
//     * Returns all instances of the type.
//     * <p>
//     * Senza filtri
//     * Ordinati per ID
//     * <p>
//     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
//     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
//     * Eseguo qui la conversione, che rimane trasparente al resto del programma
//     *
//     * @return all entity
//     */
//    @Override
//    public List<AlgosEntity> findAll() {
//        List<AlgosEntity> target = new ArrayList();
//        Iterable iterable = repository.findAll();
//
//        if (iterable != null) {
//            if (iterable instanceof List) {
//                target = (List<AlgosEntity>) iterable;
//            } else {
//                final List<AlgosEntity> targetFinal = new ArrayList();
//                iterable.forEach(it -> targetFinal.add((AlgosEntity) it));
//                target = targetFinal;
//            }// end of if/else cycle
//        }// end of if cycle
//
//        return target;
//    }// end of method
//
//    /**
//     * Creazione di un nuovo bean
//     * Utilizza la mappa della sottoclasse
//     *
//     * @param entity
//     */
//    @Override
//    public AlgosEntity save(AlgosEntity entity) {
//        return (AlgosEntity)repository.save(entity);
//    }// end of method
//
//    /**
//     * Cancella il singolo bean
//     *
//     * @param entityBean
//     */
//    @Override
//    public boolean delete(AlgosEntity entityBean) {
//       repository.delete(entityBean.getId());
//       return false;
//    }// end of method
//
//
//    @Override
//    public Class<? extends AlgosEntity> getEntityClass() {
//        return null;
//    }// end of method
//
//    /**
//     * Returns whether a table exists.
//     *
//     * @return true if an table exists, {@literal false} otherwise
//     */
//    @Override
//    public boolean exists() {
//        return false;
//    }// end of method
//
//    /**
//     * Creazione iniziale di una tavola
//     */
//    @Override
//    public boolean creaTable() {
//        return false;
//    }// end of method
//
//    /**
//     * Creazione iniziale di una entity
//     * Vuota, coi valori di default
//     */
//    @Override
//    public AlgosEntity newEntity() {
//        return null;
//    }// end of method
//
//
//    /**
//     * Saves all given entities.
//     *
//     * @param entities
//     *
//     * @return the saved entities
//     *
//     * @throws IllegalArgumentException in case the given entity is {@literal null}.
//     */
//    @Override
//    public List<AlgosEntity> save(List<AlgosEntity> entities) {
//        return null;
//    }// end of method
//
//    /**
//     * Returns whether an entity with the given id exists.
//     *
//     * @param serializable must not be {@literal null}.
//     *
//     * @return true if an entity with the given id exists, {@literal false} otherwise
//     *
//     * @throws IllegalArgumentException if {@code id} is {@literal null}
//     */
//    @Override
//    public boolean exists(Serializable serializable) {
//        return false;
//    }// end of method
//
//    /**
//     * Returns all instances of the type with the given IDs.
//     * <p>
//     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
//     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
//     * Eseguo qui la conversione, che rimane trasparente al resto del programma
//     *
//     * @param iterable
//     *
//     * @return
//     */
//    @Override
//    public Iterable findAll(Iterable iterable) {
//        return null;
//    }// end of method
//
//    /**
//     * Deletes the entity with the given id.
//     *
//     * @param id key, must not be {@literal null}.
//     *
//     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
//     */
//    @Override
//    public boolean delete(long id) {
//        return false;
//    }// end of method
//
//    /**
//     * Deletes the given entities.
//     *
//     * @param entities
//     *
//     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
//     */
//    @Override
//    public boolean delete(Iterable entities) {
//        return false;
//    }// end of method
//
//    /**
//     * Deletes all entities managed by the repository.
//     */
//    @Override
//    public boolean deleteAll() {
//        return false;
//    }// end of method
//
//
//    /**
//     * Ordine standard di presentazione delle colonne nella grid
//     * Può essere modificato
//     * La colonna ID normalmente non si visualizza
//     */
//    public List<String> getListColumns() {
//        return LibReflection.getAllFieldName(entity.getClass());
//    }// end of method
//
//
//    /**
//     * Ordine standard di presentazione dei fields nel form
//     * Può essere modificato
//     * La colonna ID normalmente non si visualizza
//     */
//    public List<String> getFormFields() {
//        return LibReflection.getAllFieldName(entity.getClass());
//    }// end of method
//
//
//    /**
//     * Recupera il valore massimo della property (numerica) indicata
//     */
//    public int getMax(String propertyName) {
//        return 0;
////        return repository.getMax(propertyName);
//    }// end of method

}// end of class
