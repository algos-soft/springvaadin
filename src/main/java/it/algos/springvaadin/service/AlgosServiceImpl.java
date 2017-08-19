package it.algos.springvaadin.service;

import com.mongodb.DuplicateKeyException;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAnnotation;
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
 * Implementazione standard dell'interfaccia AlgosService
 */
public abstract class AlgosServiceImpl implements AlgosService {


    //--la repository dei dati viene iniettata dal costruttore della sottoclasse concreta
    protected MongoRepository repository;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AlgosEntity> entityClass;

    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AlgosServiceImpl(MongoRepository repository) {
        this.repository = repository;
    }// end of Spring constructor


//    /**
//     * Creazione in memoria di una nuova entity che NON viene salvata
//     * Eventuali regolazioni iniziali delle property
//     */
//    public AlgosEntity newEntity() {
//        return null;
//    }// end of method


    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entityBean da salvare
     *
     * @return the saved entity
     */
    public AlgosEntity save(AlgosEntity entityBean) throws Exception {
        return (AlgosEntity) repository.save(entityBean);
    }// end of method


//    /**
//     * Returns all entities of the type.
//     * <p>
//     * Senza filtri
//     * Ordinati per ID
//     * <p>
//     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
//     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
//     * Eseguo qui la conversione, che rimane trasparente al resto del programma
//     *
//     * @return all entities
//     */
//    @Override
//    public List findAll() {
//        return null;
//    }// end of method


    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @Override
    public int count() {
        return (int) repository.count();
    }// end of method


    /**
     * Deletes a given entity.
     *
     * @param entityBean must not be null
     *
     * @return true, se la entity è stata effettivamente cancellata
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public boolean delete(AlgosEntity entityBean) {
        repository.delete(entityBean.getId());

        if (repository.findOne(entityBean.getId()) == null) {
            Notification.show("Delete", "Cancellato il record: " + entityBean, Notification.Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Delete", "Non sono riuscito a cancellare il record: " + entityBean, Notification.Type.WARNING_MESSAGE);
        }// end of if/else cycle

        return true;
    }// end of method

    /**
     * Deletes all entities of the collection.
     */
    @Override
    public boolean deleteAll() {
        repository.deleteAll();
        return repository.count() == 0;
    }// end of method

    /**
     * Colonne visibili (e ordinate) nella Grid
     * Può essere modificato nella sottoclasse
     * La colonna ID normalmente non si visualizza
     * <p>
     * 1) Se questo metodo viene sovrascritto nella sottoclasse specifica, si utilizza quella lista (con o senza ID)
     * 2) Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
     *
     * @return lista di colonne visibili nella Grid
     */
    @Override
    public List<String> getListColumns() {
        List<String> lista = null;
        boolean showsID = false;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        lista = LibAnnotation.getListColumns(entityClass);

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        if (lista == null) {
            showsID = LibAnnotation.isListShowsID(entityClass);
            lista = LibReflection.getAllFieldName(entityClass, showsID);
        }// end of if cycle

        return lista;
    }// end of method

    /**
     * Fields visibili (e ordinati) nel Form
     * Può essere modificato nella sottoclasse
     * Il campo key ID normalmente non si visualizza
     * <p>
     * 1) Se questo metodo viene sovrascritto nella sottoclasse specifica, si utilizza quella lista (con o senza ID)
     * 2) Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
     *
     * @return lista di fields visibili nel Form
     */
    @Override
    public List<String> getFormFields() {
        List<String> lista = null;
        boolean showsID = false;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        lista = LibAnnotation.getFormFields(entityClass);

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        if (lista == null) {
            showsID = LibAnnotation.isFormShowsID(entityClass);
            lista = LibReflection.getAllFieldName(entityClass, showsID);
        }// end of if cycle

        return lista;
    }// end of method


    public void setEntityClass(Class<? extends AlgosEntity> entityClass) {
        this.entityClass = entityClass;
    }// end of method

}// end of class
