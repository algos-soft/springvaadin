package it.algos.springvaadin.service;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.Cost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.util.ArrayList;
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


    @Autowired
    public AAnnotationService annotation;


    @Autowired
    private ATextService text;


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
                    if (field.getName().equals(Cost.PROPERTY_ID)) {
                        fieldKeyId = field;
                    }// end of if cycle
                    if (field.getName().equals(Cost.PROPERTY_COMPANY)) {
                        fieldCompany = field;
                    }// end of if cycle
                    if (field.getName().equals(Cost.PROPERTY_ORDINE)) {
                        fieldOrdine = field;
                    }// end of if cycle
                    if (!Cost.ESCLUSI.contains(field.getName())) {
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
                        if (text.isValid(fieldName) && !fieldName.equals(Cost.PROPERTY_SERIAL)) {
                            if (fieldName.equals(nome)) {
                                fieldsList.add(field);
                            }// end of if cycle
                        }// end of if cycle
                    }// end of for cycle
                }// end of for cycle
            } else {
                for (Field field : fieldsTmp) {
                    fieldName = field.getName();
                    if (text.isValid(fieldName) && !fieldName.equals(Cost.PROPERTY_SERIAL)) {
                        fieldsList.add(field);
                    }// end of if cycle
                }// end of for cycle
            }// end of if/else cycle
        }// end of if cycle


        //@todo RIMETTERE
//        //--se la entity è di tipo ACompanyEntity, aggiunge (all'inizio) il field di riferimento
//        //--se esiste il field ''ordine'', company viene messo dopo ordine
//        if (addKeyCompany && ACompanyEntity.class.isAssignableFrom(entityClazz)) {
//            if (fieldCompany != null) {
//                if (fieldOrdine != null) {
//                    fieldsList.add(fieldsList.indexOf(fieldOrdine) + 1, fieldCompany);
//                } else {
//                    fieldsList.add(0, fieldCompany);
//                }// end of if/else cycle
//            } else {
//                log.warn("Non ho trovato il field company");
//            }// end of if/else cycle
//        }// end of if cycle


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
