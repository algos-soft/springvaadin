package it.algos.springvaadin.service;

import com.vaadin.ui.Notification;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.entity.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 07/07/17
 * Implementazione standard dell'annotation AlgosService
 */
@Slf4j
public abstract class AlgosServiceImpl implements AlgosService {


    //--la repository dei dati viene iniettata dal costruttore della sottoclasse concreta
    protected MongoRepository repository;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AEntity> entityClass;

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
//    public AEntity newEntity() {
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
    public AEntity save(AEntity entityBean) throws Exception {
        return (AEntity) repository.save(entityBean);
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
        return (AEntity) repository.findOne(serializable);
    }// end of method

//    /**
//     * Returns all entities of the type.
//     * <p>
//     * Senza filtri
//     * Ordinati per ID
//     * <p>
//     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
//     * L'annotation standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
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
    public boolean delete(AEntity entityBean) {
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
     * 4) Se la classe Entity->@Annotation prevede esplicitamente l'ID, questo viene aggiunto, indipendentemente dalla lista
     *
     * @return lista di fields visibili nel Form
     */
    @Override
    @Deprecated
    public List<String> getFormFields() {
        List<String> lista = null;
        boolean showsID = false;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        lista = LibAnnotation.getFormFields(entityClass);

        //--Se la classe Entity->@Annotation prevede esplicitamente l'ID, questo viene aggiunto, indipendentemente dalla lista
        showsID = LibAnnotation.isFormShowsID(entityClass);
        if (lista != null && showsID) {
            ArrayList<String> listaTmp = new ArrayList<>();
            listaTmp.add(Cost.PROPERTY_ID);
            listaTmp.addAll(lista);
            lista = listaTmp;
        }// end of if cycle

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        if (lista == null) {
            lista = LibReflection.getAllFieldName(entityClass, showsID);
        }// end of if cycle


        log.error(displayCompany() + "");

        return lista;
    }// end of method

    /**
     * Fields visibili nel Form
     * Sovrascrivibile
     * Il campo key ID normalmente non viene visualizzato
     * 1) Se questo metodo viene sovrascritto, si utilizza la lista della sottoclasse specifica (con o senza ID)
     * 2) Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova AEntity->@Annotation, usa tutti i campi della AEntity (senza ID)
     * 4) Se trova AEntity->@Annotation @AIForm(showsID = true), questo viene aggiunto, indipendentemente dalla lista
     * 5) Vengono visualizzati anche i campi delle superclassi della classe AEntity
     * Ad esempio: company della classe ACompanyEntity
     * <p>
     *
     * @return lista di fields da visualizzare nel Form
     */
    @Override
    public List<Field> getFields() {
        List<String> listaNomi = null;

        //--Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        listaNomi = LibAnnotation.getFormFields(entityClass);

        //--Se trova AEntity->@Annotation @AIForm(showsID = true), questo viene aggiunto, indipendentemente dalla lista
        //--Se non trova AEntity->@Annotation, usa tutti i campi della AEntity (con o senza ID)
        return LibReflection.getFields(
                entityClass, listaNomi,
                LibAnnotation.isFormShowsID(entityClass),
                displayCompany());
    }// end of method


    /**
     * Flag.
     * Deve essere true il flag useMultiCompany
     * La Entity deve estendere ACompanyEntity
     * L'utente collegato deve essere developer
     */
    @Override
    public boolean displayCompany() {

        //--Deve essere true il flag useMultiCompany
        if (!LibParams.useMultiCompany()) {
            return false;
        }// end of if cycle

        //--La Entity deve estendere ACompanyEntity
        if (!ACompanyEntity.class.isAssignableFrom(entityClass)) {
            return false;
        }// end of if cycle

//        //--L'utente collegato deve essere developer
//        if (!LibSession.isDeveloper()) {
//            return false;
//        }// end of if cycle

        return true;
    }// end of static method


    public void setEntityClass(Class<? extends AEntity> entityClass) {
        this.entityClass = entityClass;
    }// end of method


}// end of class
