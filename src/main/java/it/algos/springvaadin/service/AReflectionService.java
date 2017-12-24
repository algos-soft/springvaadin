package it.algos.springvaadin.service;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.entity.ACEntity;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.ACost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 22:11
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class AReflectionService {


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public AArrayService array;

    @Autowired
    public AAnnotationService annotation;


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public ATextService text;


    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    public Object getPropertyValue(final Class<?> clazz, final String publicFieldName) {
        Object value = null;

        try { // prova ad eseguire il codice
            value = clazz.getDeclaredField(publicFieldName).get(null);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return value;
    }// end of method


    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    public Resource getPropertyRes(final Class<?> clazz, final String publicFieldName) {
        Resource value = null;
        Object obj = getPropertyValue(clazz, publicFieldName);

        if (obj != null && obj instanceof Resource) {
            value = (Resource) obj;
        }// end of if cycle

        return value;
    }// end of method


    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    public String getPropertyStr(final Class<?> clazz, final String publicFieldName) {
        String value = "";
        Object obj = getPropertyValue(clazz, publicFieldName);

        if (obj != null && obj instanceof String) {
            value = (String) obj;
        }// end of if cycle

        return value;
    }// end of method


    /**
     * Field dichiarato di una Entity
     *
     * @param entityClazz     classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    public Field getField(final Class<? extends AEntity> entityClazz, final String publicFieldName) {
        Field field = null;

        try { // prova ad eseguire il codice
            field = entityClazz.getDeclaredField(publicFieldName);
        } catch (Exception unErrore) { // intercetta l'errore
            log.warn(unErrore.toString() + " getField()");
        }// fine del blocco try-catch

        return field;
    }// end of method


    /**
     * Fields dichiarati nella Entity
     * Solo la entityClazz indicata
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields da considerare per List e Form
     */
    public List<Field> getFieldsEntityOnly(Class<? extends AEntity> entityClazz) {
        ArrayList<Field> fieldsList = new ArrayList<>();
        Field[] fieldsArray = entityClazz.getDeclaredFields();

        for (Field field : fieldsArray) {
            if (!field.getName().equals(ACost.PROPERTY_SERIAL)) {
                fieldsList.add(field);
            }// end of if cycle
        }// end of for cycle

        return fieldsList;
    }// end of method


    /**
     * All field names di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutte i fieldNames editabili, elencati in ordine di inserimento nella AEntity
     */
    public List<String> getFieldsNameEntityOnly(final Class<? extends AEntity> entityClazz) {
        List<String> nameList = null;
        List<Field> fieldsList = this.getFieldsEntityOnly(entityClazz);

        if (array.isValid(fieldsList)) {
            nameList = new ArrayList<>();
            for (Field field : fieldsList) {
                nameList.add(field.getName());
            }// end of for cycle
        }// end of if cycle

        return nameList;
    }// end of method


    /**
     * All field names di una EntityClass
     * Compresa la entityClazz
     * Comprese tutte le superclassi (fino a AEntity e AEntity)
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutte i fieldNames editabili, elencati in ordine di inserimento nella AEntity
     */
    @Deprecated
    public List<String> getFieldsNameAll(final Class<? extends AEntity> entityClazz) {
        List<String> nameList = null;
        List<Field> fieldsList = this.getFieldsAllSuperclasses(entityClazz);

        if (array.isValid(fieldsList)) {
            nameList = new ArrayList<>();
            for (Field field : fieldsList) {
                nameList.add(field.getName());
            }// end of for cycle
        }// end of if cycle

        return nameList;
    }// end of method


    /**
     * Fields dichiarati nella Entity
     * Compresa la entityClazz
     * Comprese tutte le superclassi (fino a AEntity e AEntity)
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields da considerare per List e Form
     */
    public List<Field> getFieldsAllSuperclasses(Class<? extends AEntity> entityClazz) {
        return getFields(entityClazz, null, true, false);
    }// end of method


    /**
     * Fields per le Campi del Form dichiarati nella Entity
     * Considera solo quelli della listaNomi indicata Tutti, se listaNomi=null
     *
     * @param entityClazz da cui estrarre i fields
     * @param listaNomi   dei fields da considerare. Tutti, se listaNomi=null
     *
     * @return lista di fields da considerare per Form
     */
    @SuppressWarnings("all")
    public List<Field> getFormFields(Class<? extends AEntity> entityClazz, List<String> listaNomi) {
        List<Field> fieldsList = new ArrayList<>();
        List<Field> fieldsTmp = this.getFieldsAllSuperclasses(entityClazz);

        for (Field field : fieldsTmp) {
            if (listaNomi.contains(field.getName())) {
                fieldsList.add(field);
            }// end of if cycle
        }// end of for cycle

        return fieldsList;
    }// end of method


    /**
     * Fields dichiarati nella Entity
     * Compresa la entity
     * Comprese tutte le superclassi (fino a ACompanyEntity e AEntity)
     *
     * @param entityClazz   da cui estrarre i fields
     * @param listaNomi     dei fields da considerare. Tutti, se listaNomi=null
     * @param addKeyID      flag per aggiungere (per primo) il field keyId
     * @param addKeyCompany flag per aggiungere (per secondo) il field keyCompany
     *
     * @return lista di fields da considerare per List e Form
     */
    public List<Field> getFields(Class<? extends AEntity> entityClazz, List<String> listaNomi, boolean addKeyID, boolean addKeyCompany) {
        ArrayList<Field> fieldsList = new ArrayList<>();
        Class<?> clazz = entityClazz;
        ArrayList<Field> fieldsTmp = new ArrayList<>();
        Field[] fieldsArray = null;
        Field fieldKeyId = null;
        Field fieldCompany = null;
        Field fieldOrdine = null;
        String fieldName;
        boolean useCompany = annotation.isCompanyFieldVisible(entityClazz);

        //--recupera tutti i fields della entity e di tutte le superclassi
        while (clazz != Object.class) {
            try { // prova ad eseguire il codice
                fieldsArray = clazz.getDeclaredFields();
                for (Field field : fieldsArray) {
                    if (field.getName().equals(ACost.PROPERTY_ID)) {
                        fieldKeyId = field;
                    }// end of if cycle
                    if (field.getName().equals(ACost.PROPERTY_COMPANY)) {
                        fieldCompany = field;
                    }// end of if cycle
                    if (field.getName().equals(ACost.PROPERTY_ORDINE)) {
                        fieldOrdine = field;
                    }// end of if cycle
                    if (!ACost.ESCLUSI.contains(field.getName())) {
                        fieldsTmp.add(field);
                    }// end of if cycle
                }// end of for cycle
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
            clazz = clazz.getSuperclass();
        }// end of while cycle

        //--controlla che i fields siano quelli richiesti
        //--se la lista dei nomi dei fields è nulla, li prende tutti
        //--controlla che i field siano visibili per il livello di developer/buttonAdmin/buttonUser corrente
        if (fieldsTmp != null && fieldsTmp.size() > 0) {
            if (listaNomi != null && listaNomi.size() > 0) {
                for (String nome : listaNomi) {
                    for (Field field : fieldsTmp) {
                        fieldName = field.getName();
                        if (text.isValid(fieldName) && !fieldName.equals(ACost.PROPERTY_SERIAL)) {
                            if (fieldName.equals(nome)) {
                                fieldsList.add(field);
                            }// end of if cycle
                        }// end of if cycle
                    }// end of for cycle
                }// end of for cycle
            } else {
                for (Field field : fieldsTmp) {
                    fieldName = field.getName();
                    if (text.isValid(fieldName) && !fieldName.equals(ACost.PROPERTY_SERIAL)) {
                        fieldsList.add(field);
                    }// end of if cycle
                }// end of for cycle
            }// end of if/else cycle
        }// end of if cycle


        //--se la entity è di tipo ACEntity, aggiunge (all'inizio) il field di riferimento
        //--se esiste il field ''ordine'', company viene messo dopo ordine
        if (addKeyCompany && ACEntity.class.isAssignableFrom(entityClazz)) {
            if (fieldCompany != null) {
                if (fieldOrdine != null) {
                    fieldsList.add(fieldsList.indexOf(fieldOrdine) + 1, fieldCompany);
                } else {
                    fieldsList.add(0, fieldCompany);
                }// end of if/else cycle
            } else {
                log.warn("Non ho trovato il field company");
            }// end of if/else cycle
        }// end of if cycle


        //--se il flag booleano addKeyID è true, aggiunge (all'inizio) il field keyId
        //@todo RIMETTERE
        if (addKeyID) {
//            if (addKeyID || LibSession.isDeveloper()) {
            if (fieldKeyId != null) {
                fieldsList.add(0, fieldKeyId);
            } else {
                log.error("Non ho trovato il field keyId");
            }// end of if/else cycle
        }// end of if cycle

        return fieldsList;
    }// end of method


    /**
     * Fields per le Columns della List (Grid) dichiarati nella Entity
     * Compresa la entity
     * Comprese tutte le superclassi (fino a ACompanyEntity e AEntity)
     *
     * @param entityClazz   da cui estrarre i fields
     * @param listaNomi     dei fields da considerare. Tutti, se listaNomi=null
     * @param addKeyCompany flag per aggiungere (per secondo) il field keyCompany
     *
     * @return lista di fields da considerare per List
     */
    public List<Field> getListColumns(Class<? extends AEntity> entityClazz, List<String> listaNomi, boolean addKeyCompany) {
        ArrayList<Field> fieldsList = new ArrayList<>();
        boolean addKeyID = annotation.isListShowsID(entityClazz);
        List<Field> fieldsTmp = this.getFields(entityClazz, listaNomi, addKeyID, addKeyCompany);

        for (Field field : fieldsTmp) {
            if (annotation.isColumnVisibile(field)) {
                fieldsList.add(field);
            }// end of if cycle
        }// end of for cycle

        return fieldsList;
    }// end of method


}// end of class
