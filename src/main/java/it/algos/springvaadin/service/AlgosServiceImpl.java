package it.algos.springvaadin.service;

import com.vaadin.ui.Notification;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.versione.Versione;
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


    /**
     * Controlla se la company è obbligatoria e se esiste
     */
    protected ACompanyEntity checkCompany(Company company, ACompanyEntity entity) {

        if (company == null) {
            company = LibSession.getCompany();
        }// end of if cycle

        if (company == null && LibAnnotation.isCompanyNotNull(Versione.class)) {
            log.warn("Entity non creata perché manca la Company (obbligatoria)");
            return null;
        }// end of if cycle

        entity.setCompany(company);

        return entity;
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
     * Sovrascrivibile
     * La colonna ID normalmente non si visualizza
     * 1) Se questo metodo viene sovrascritto, si utilizza la lista della sottoclasse specifica (con o senza ID)
     * 2) Se la classe AEntity->@AIList(columns = ...) prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova AEntity->@AIList, usa tutti i campi della AEntity (senza ID)
     * 4) Se trova AEntity->@AIList(showsID = true), questo viene aggiunto, indipendentemente dalla lista
     * 5) Vengono visualizzati anche i campi delle superclassi della classe AEntity
     * Ad esempio: company della classe ACompanyEntity
     *
     * @return lista di nomi di colonne visibili nella Grid
     */
    @Override
    public List<String> getListColumnNames() {
        List<String> listaNomi = null;
        boolean useID = false;
        boolean useCompany = false;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        listaNomi = LibAnnotation.getListColumns(entityClass);

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        if (listaNomi == null) {
            useID = LibAnnotation.isListShowsID(entityClass);
            useCompany = this.displayCompany();
            listaNomi = LibReflection.getAllFieldNames(entityClass, useID, useCompany);
        }// end of if cycle

        //--il developer vede tutto (si potrebbe migliorare)
        if (LibSession.isDeveloper()) {
            listaNomi = LibReflection.getAllFieldNames(entityClass, true, true);
        }// end of if cycle

        return listaNomi;
    }// end of method


    /**
     * Fields visibili (e ordinati) nel Form
     * Sovrascrivibile
     * Il campo key ID normalmente non viene visualizzato
     * 1) Se questo metodo viene sovrascritto, si utilizza la lista della sottoclasse specifica (con o senza ID)
     * 2) Se la classe AEntity->@AIForm(fields = ...) prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova AEntity->@AIForm, usa tutti i campi della AEntity (senza ID)
     * 4) Se trova AEntity->@AIForm(showsID = true), questo viene aggiunto, indipendentemente dalla lista
     * 5) Vengono visualizzati anche i campi delle superclassi della classe AEntity
     * Ad esempio: company della classe ACompanyEntity
     *
     * @return lista di fields visibili nel Form
     */
    @Override
    public List<Field> getFormFields() {
        List<String> listaNomi = null;
        boolean useID = false;
        boolean useCompany = false;

        //--Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        listaNomi = LibAnnotation.getFormFields(entityClass);

        //--Se trova AEntity->@AIForm(showsID = true), questo viene aggiunto, indipendentemente dalla lista
        //--Se non trova AEntity->@AIForm, usa tutti i campi della AEntity (con o senza ID)
        useID = LibAnnotation.isFormShowsID(entityClass);
        useCompany = this.displayCompany();

        //--il developer vede tutto (si potrebbe migliorare)
        if (LibSession.isDeveloper()) {
            listaNomi = LibReflection.getAllFieldNames(entityClass, true, true);
            useID = true;
            useCompany = true;
        }// end of if cycle

        return LibReflection.getFields(entityClass, listaNomi, useID, useCompany);
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

        //--L'utente collegato deve essere developer
        if (!LibSession.isDeveloper()) {
            return false;
        }// end of if cycle

        return true;
    }// end of static method


    public void setEntityClass(Class<? extends AEntity> entityClass) {
        this.entityClass = entityClass;
    }// end of method


}// end of class
