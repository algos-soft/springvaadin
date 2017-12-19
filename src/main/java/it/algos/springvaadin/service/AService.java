package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.enumeration.EAFormButton;
import it.algos.springvaadin.enumeration.EAListButton;
import it.algos.springvaadin.lib.ACost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 07:36
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public abstract class AService implements IAService {


    @Autowired
    public AAnnotationService annotation;


    @Autowired
    public AReflectionService reflection;


    @Autowired
    public ASessionService session;


    @Autowired
    public AArrayService array;


    //--la repository dei dati viene iniettata dal costruttore della sottoclasse concreta
    public MongoRepository repository;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    public Class<? extends AEntity> entityClass;

    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AService(MongoRepository repository) {
        this.repository = repository;
    }// end of Spring constructor


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
     * Returns all entities of the type.
     * <p>
     * Senza filtri
     * Ordinati per ID
     * <p>
     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
     * L'annotation standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
     * Eseguo qui la conversione, che rimane trasparente al resto del programma
     *
     * @return all entities
     */
    @Override
    public List<? extends AEntity> findAll() {
        return repository.findAll();
    }// end of method

    /**
     * Colonne visibili (e ordinate) nella Grid
     * Sovrascrivibile
     * La colonna key ID normalmente non si visualizza
     * 1) Se questo metodo viene sovrascritto, si utilizza la lista della sottoclasse specifica (con o senza ID)
     * 2) Se la classe AEntity->@AIList(columns = ...) prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova AEntity->@AIList, usa tutti i campi della AEntity (senza ID)
     * 4) Se trova AEntity->@AIList(showsID = true), questo viene aggiunto, indipendentemente dalla lista
     * 5) Vengono visualizzati anche i campi delle superclassi della classe AEntity
     * Ad esempio: company della classe ACompanyEntity
     *
     * @return lista di fields visibili nella Grid
     */
    @Override
    public List<Field> getListFields() {
        List<Field> listaField = null;
        List<String> listaNomi = null;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        //--rimanda ad un metodo separato per poterlo sovrascrivere
        listaNomi = this.getListFieldsName();

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        listaField = reflection.getListColumns(entityClass, listaNomi, displayCompany());

//        //--il developer vede tutto (si potrebbe migliorare)
//        if (LibSession.isDeveloper()) {
//            listaNomi = LibReflection.getListVisibleColumnNames(entityClass, true, true);
//        }// end of if cycle

        return listaField;
    }// end of method


    /**
     * Nomi dei fields da considerare per estrarre i Java Reflected Field dalle @Annotation della Entity
     * Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
     * Sovrascrivibile
     *
     * @return nomi dei fields, oppure null se non esiste l'Annotation specifica @AIForm() nella Entity
     */
    protected List<String> getListFieldsName() {
        return annotation.getListColumns(entityClass);
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
        List<Field> listaField = null;
        List<String> listaNomi = null;

        //--Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        //--rimanda ad un metodo separato per poterlo sovrascrivere
        listaNomi = this.getFormFieldsName();

        //--Se non trova nulla, usa tutti i campi
        //--Nel Form il developer vede SEMPRE la keyId, non Enabled
        //--rimanda ad un metodo separato per poterlo sovrascrivere
        listaField = this.getFormFields(listaNomi);

        return listaField;
    }// end of method


    /**
     * Nomi dei fields da considerare per estrarre i Java Reflected Field dalle @Annotation della Entity
     * Se la classe AEntity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
     * Sovrascrivibile
     *
     * @return nomi dei fields, oppure null se non esiste l'Annotation specifica @AIForm() nella Entity
     */
    protected List<String> getFormFieldsName() {
        return annotation.getFormFieldsName(entityClass);
    }// end of method


    /**
     * Java Reflected Fields estratti dalle @Annotation della Entity
     * Se la lista nomi è nulla, usa tutte le properties (fields)
     * Nel Form il developer vede SEMPRE la keyId, non Enabled
     * Sovrascrivibile
     *
     * @param listaNomi per estrarre i Java Reflected Fields dalle @Annotation della Entity
     *
     * @return fields, oppure null se non esiste l'Annotation specifica @AIForm() nella Entity
     */
    protected List<Field> getFormFields(List<String> listaNomi) {
        List<Field> listaFields = null;

        if (session.isDeveloper()) {
            listaFields = reflection.getFieldsAllSuperclasses(entityClass);
        } else {
            if (!listaNomi.contains(ACost.PROPERTY_NOTE)) {
                listaNomi = array.add(listaNomi, ACost.PROPERTY_NOTE);
            }// end of if cycle
            listaFields = reflection.getFormFields(entityClass, listaNomi);
        }// end of if/else cycle

        //@todo RIMETTERE
//        //--controllo per l'uso delle properties creazione e modifica
//        if (pref.isFalse(Cost.KEY_USE_PROPERTY_CREAZIONE_AND_MODIFICA)) {
//            Field fieldCreazione = null;
//            Field fieldModifica = null;
//
//            for (Field field : listaProperties) {
//                if (field.getName().equals(Cost.PROPERTY_CREAZIONE)) {
//                    fieldCreazione = field;
//                }// end of if cycle
//                if (field.getName().equals(Cost.PROPERTY_MODIFICA)) {
//                    fieldModifica = field;
//                }// end of if cycle
//            }// end of for cycle
//
//            if (fieldCreazione != null) {
//                listaProperties.remove(fieldCreazione);
//            }// end of if cycle
//            if (fieldModifica != null) {
//                listaProperties.remove(fieldModifica);
//            }// end of if cycle
//        }// end of if cycle

        return listaFields;
    }// end of method


    /**
     * Flag.
     * Deve essere true il flag useMultiCompany
     * La Entity deve estendere ACompanyEntity
     * L'buttonUser collegato deve essere developer
     */
    public boolean displayCompany() {

//        //--Deve essere true il flag useMultiCompany
//        if (!LibParams.useMultiCompany()) {
//            return false;
//        }// end of if cycle
//
//        //--La Entity deve estendere ACompanyEntity
//        if (!ACompanyEntity.class.isAssignableFrom(entityClass)) {
//            return false;
//        }// end of if cycle
//
//        //--L'buttonUser collegato deve essere developer
//        if (!LibSession.isDeveloper()) {
//            return false;
//        }// end of if cycle

        return false;
    }// end of static method


    /**
     * Lista di bottoni presenti nella toolbar (footer) della view AList
     * Legge la enumeration indicata nella @Annotation della AEntity
     *
     * @return lista (type) di bottoni visibili nella toolbar della view AList
     */
    public List<EAButtonType> getListTypeButtons() {
        EAListButton listaBottoni = annotation.getListBotton(entityClass);
        EAButtonType[] matrice = null;

        if (listaBottoni != null) {
            switch (listaBottoni) {
                case standard:
                    matrice = new EAButtonType[]{EAButtonType.create, EAButtonType.edit, EAButtonType.delete, EAButtonType.search};
                    break;
                case noSearch:
                    matrice = new EAButtonType[]{EAButtonType.create, EAButtonType.edit, EAButtonType.delete};
                    break;
                case noCreate:
                    matrice = new EAButtonType[]{EAButtonType.edit, EAButtonType.delete};
                    break;
                case edit:
                    matrice = new EAButtonType[]{EAButtonType.edit};
                    break;
                case show:
                    matrice = new EAButtonType[]{EAButtonType.show};
                    break;
                case noButtons:
                    matrice = new EAButtonType[]{};
                    break;
                default:
                    log.warn("Switch - caso non definito");
                    break;
            } // end of switch statement
        }// end of if cycle

        return Arrays.asList(matrice);
    }// end of method


    /**
     * Lista di bottoni presenti nella toolbar (footer) della view AForm
     * Legge la enumeration indicata nella @Annotation della AEntity
     *
     * @return lista (type) di bottoni visibili nella toolbar della view AForm
     */
    public List<EAButtonType> getFormTypeButtons() {
        EAFormButton listaBottoni = annotation.getFormBotton(entityClass);
        EAButtonType[] matrice = null;

        if (listaBottoni != null) {
            switch (listaBottoni) {
                case standard:
                    matrice = new EAButtonType[]{EAButtonType.annulla, EAButtonType.revert, EAButtonType.registra};
                    break;
                case show:
                    matrice = new EAButtonType[]{EAButtonType.annulla};
                    break;
                case conferma:
                    matrice = new EAButtonType[]{EAButtonType.annulla, EAButtonType.revert, EAButtonType.conferma};
                    break;
                default:
                    log.warn("Switch - caso non definito");
                    break;
            } // end of switch statement
        }// end of if cycle

        return Arrays.asList(matrice);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * Senza properties per compatibilità con la superclasse
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public AEntity newEntity() {
        return null;
    }// end of method


    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     * <p>
     * Controlla se l'applicazione usa le company - flag  AlgosApp.USE_MULTI_COMPANY=true
     * Controlla se la collection (table) usa la company
     *
     * @param entityBean da salvare
     *
     * @return the saved entity
     */
    public AEntity save(AEntity entityBean) throws Exception {

        if (entityBean.creazione == null) {
            entityBean.creazione = LocalDateTime.now();
        }// end of if cycle
        entityBean.modifica = LocalDateTime.now();

        return (AEntity) repository.save(entityBean);
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
    @Override
    public boolean delete(AEntity entityBean) {
        repository.delete(entityBean.getId());

        //@todo aggiungere controllo se il record è stato cancellato
        return true;
    }// end of method

}// end of class
