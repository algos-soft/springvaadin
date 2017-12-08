package it.algos.springvaadin.service;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIList;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.view.IAView;
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
 * Time: 21:58
 * Classe di Libreria
 * Gestisce le interfacce @Annotation standard di Springs
 * Gestisce le interfacce specifiche di Springvaadin: AIColumn, AIField, AIEntity, AIForm, AIList
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class AAnnotationService {


    @Autowired
    public AArrayService array;


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public SpringView getSpringView(final Class<? extends IAView> clazz) {
        return clazz.getAnnotation(SpringView.class);
    }// end of method


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public AIList getAIList(final Class<? extends AEntity> clazz) {
        return clazz.getAnnotation(AIList.class);
    }// end of method


    /**
     * Get the specific annotation of the field.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the Annotation for the specific field
     */
    public AIColumn getAIColumn(final Field reflectionJavaField) {
        if (reflectionJavaField != null) {
            return reflectionJavaField.getAnnotation(AIColumn.class);
        } else {
            return null;
        }// end of if/else cycle
    }// end of method


    /**
     * Get the name of the spring-view.
     *
     * @param clazz the entity class
     *
     * @return the name of the spring-view
     */
    public String getViewName(final Class<? extends IAView> clazz) {
        String name = "";
        SpringView annotation = this.getSpringView(clazz);

        if (annotation != null) {
            name = annotation.name();
        }// end of if cycle

        return name;
    }// end of method


    /**
     * Get the status listShowsID of the class.
     *
     * @param clazz the entity class
     *
     * @return status of class - default false
     */
    public boolean isListShowsID(final Class<? extends AEntity> clazz) {
        boolean status = false;
        AIList annotation = this.getAIList(clazz);

        if (annotation != null) {
            status = annotation.showsID();
        }// end of if cycle

        return status;
    }// end of method


    /**
     * Colonne visibili (e ordinate) nella Grid
     *
     * @param clazz the entity class
     *
     * @return lista di colonne visibili nella Grid
     */
    public List<String> getListColumns(final Class<? extends AEntity> clazz) {
        List<String> lista = null;
        String[] columns = null;
        AIList annotation = this.getAIList(clazz);

        if (annotation != null) {
            columns = annotation.columns();
        }// end of if cycle

        if (array.isValid(columns)) {
            lista = Arrays.asList(columns);
        }// end of if cycle

        return lista;
    }// end of method


    /**
     * Get the visibility of the column.
     * Di default true
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the visibility of the column
     */
    public  boolean isColumnVisibile(final Field reflectionJavaField) {
        boolean visibile = false;
        EARoleType roleTypeVisibility = EARoleType.nobody;
        AIColumn annotation = this.getAIColumn(reflectionJavaField);

        if (annotation != null) {
            roleTypeVisibility = annotation.roleTypeVisibility();
        }// end of if cycle

        switch (roleTypeVisibility) {
            case nobody:
                visibile = false;
                break;
            case developer:
                //@todo RIMETTERE

//                if (LibSession.isDeveloper()) {
                    visibile = true;
//                }// end of if cycle
                break;
            case admin:
                //@todo RIMETTERE

                //                if (LibSession.isAdmin()) {
                    visibile = true;
//                }// end of if cycle
                break;
            case user:
                visibile = true;
                break;
            case guest:
                visibile = true;
                break;
            default:
                visibile = true;
                break;
        } // end of switch statement

        return visibile;
    }// end of method


    /**
     * Get the status of visibility for the field of ACompanyEntity.
     *
     * Controlla se l'applicazione usa le company - flag  AlgosApp.USE_MULTI_COMPANY=true
     * Controlla se la collection (table) usa la company
     * Controlla se l'buttonUser collegato è un developer
     *
     * @param clazz the entity class
     *
     * @return status - default true
     */
    public  boolean isCompanyFieldVisible(final Class<? extends AEntity> clazz) {
        boolean status = true;

        //@todo RIMETTERE

//        if (!AlgosApp.USE_MULTI_COMPANY) {
//            return false;
//        }// end of if cycle
//
//        if (LibAnnotation.companyType(clazz) == ACompanyRequired.nonUsata) {
//            return false;
//        }// end of if cycle
//
//        if (!LibSession.isDeveloper()) {
//            return false;
//        }// end of if cycle

        return status;
    }// end of method


}// end of class
