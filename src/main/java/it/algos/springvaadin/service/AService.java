package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;
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
public abstract class AService implements IAService {


    @Autowired
    public AAnnotationService annotation;


    @Autowired
    public AReflectionService reflection;


    //--la repository dei dati viene iniettata dal costruttore della sottoclasse concreta
    public MongoRepository repository;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AEntity> entityClass;

    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AService(MongoRepository repository) {
        this.repository = repository;
    }// end of Spring constructor


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
    public List<AEntity> findAll() {
        return repository.findAll();
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
     * @return lista di fields visibili nella Grid
     */
    @Override
    public List<Field> getListFields() {
        List<Field> listaField = null;
        List<String> listaNomi = null;

        //--Se la classe Entity->@Annotation prevede una lista specifica, usa quella lista (con o senza ID)
        listaNomi = annotation.getListColumns(entityClass);

        //--Se non trova nulla, usa tutti i campi (senza ID, a meno che la classe Entity->@Annotation preveda l'ID)
        listaField = reflection.getListColumns(entityClass, listaNomi, displayCompany());

//        //--il developer vede tutto (si potrebbe migliorare)
//        if (LibSession.isDeveloper()) {
//            listaNomi = LibReflection.getListVisibleColumnNames(entityClass, true, true);
//        }// end of if cycle

        return listaField;
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
        return null;
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

}// end of class
