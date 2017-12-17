package it.algos.springvaadin.service;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.annotation.AIForm;
import it.algos.springvaadin.annotation.AIList;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.*;
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


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public ATextService text;


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public AArrayService array;


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public ASessionService session;


    /**
     * Get the specific annotation of the class.
     * View classes
     *
     * @param viewClazz the view class
     *
     * @return the Annotation for the specific class
     */
    public SpringView getSpringView(final Class<? extends IAView> viewClazz) {
        return viewClazz.getAnnotation(SpringView.class);
    }// end of method


    /**
     * Get the specific annotation of the class.
     * Entity classes
     *
     * @param entityClazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public AIList getAIList(final Class<? extends AEntity> entityClazz) {
        return entityClazz.getAnnotation(AIList.class);
    }// end of method


    /**
     * Get the specific annotation of the class.
     * Entity classes
     *
     * @param entityClazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public AIForm getAIForm(final Class<? extends AEntity> entityClazz) {
        return entityClazz.getAnnotation(AIForm.class);
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
     * Get the specific annotation of the field.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the Annotation for the specific field
     */
    public AIField getAIField(final Field reflectionJavaField) {
        if (reflectionJavaField != null) {
            return reflectionJavaField.getAnnotation(AIField.class);
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
     * Fields visibili (e ordinati) nel Form
     *
     * @param clazz the entity class
     *
     * @return lista di fields visibili nel Form
     */
    @SuppressWarnings("all")
    public List<String> getFormFieldsName(final Class<? extends AEntity> clazz) {
        List<String> lista = null;
        String[] fields = null;
        AIForm annotation = this.getAIForm(clazz);

        if (annotation != null) {
            fields = annotation.fields();
        }// end of if cycle

        if (array.isValid(fields)) {
            lista = Arrays.asList(fields);
        }// end of if cycle

        return lista;
    }// end of method


    /**
     * Get the accessibility status of the class for the developer login.
     * Viene usata come default, se manca il valore specifico del singolo field
     * La Annotation @AIForm ha un suo valore di default per la property @AIForm.fieldsDev()
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param clazz the entity class
     *
     * @return accessibilità del Form
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFormAccessibilityDev(Class clazz) {
        EAFieldAccessibility formAccessibility = null;
        AIForm annotation = this.getAIForm(clazz);

        if (annotation != null) {
            formAccessibility = annotation.fieldsDev();
        }// end of if cycle

        return formAccessibility != null ? formAccessibility : EAFieldAccessibility.allways;
    }// end of method


    /**
     * Get the accessibility status of the class for the admin login.
     * Viene usata come default, se manca il valore specifico del singolo field
     * La Annotation @AIForm ha un suo valore di default per la property @AIForm.fieldsAdmin()
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param clazz the entity class
     *
     * @return accessibilità del Form
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFormAccessibilityAdmin(Class clazz) {
        EAFieldAccessibility formAccessibility = null;
        AIForm annotation = this.getAIForm(clazz);

        if (annotation != null) {
            formAccessibility = annotation.fieldsAdmin();
        }// end of if cycle

        return formAccessibility != null ? formAccessibility : EAFieldAccessibility.showOnly;
    }// end of method


    /**
     * Get the accessibility status of the class for the user login.
     * Viene usata come default, se manca il valore specifico del singolo field
     * La Annotation @AIForm ha un suo valore di default per la property @AIForm.fieldsUser()
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param clazz the entity class
     *
     * @return accessibilità del Form
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFormAccessibilityUser(Class clazz) {
        EAFieldAccessibility formAccessibility = null;
        AIForm annotation = this.getAIForm(clazz);

        if (annotation != null) {
            formAccessibility = annotation.fieldsUser();
        }// end of if cycle

        return formAccessibility != null ? formAccessibility : EAFieldAccessibility.never;
    }// end of method


    /**
     * Get the name (column) of the property.
     * Se manca, usa il nome del Field
     * Se manca, usa il nome della property
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the name (column) of the field
     */
    public String getColumnName(final Field reflectionJavaField) {
        String name = "";
        AIColumn annotation = this.getAIColumn(reflectionJavaField);

        if (annotation != null) {
            name = annotation.name();
        }// end of if cycle

        if (text.isEmpty(name)) {
            name = this.getFormFieldName(reflectionJavaField);
        }// end of if cycle

        return name;
    }// end of method


    /**
     * Get the type (column) of the property.
     * Se manca, usa il type del Field
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the type for the specific column
     */
    public EAFieldType getColumnType(final Field reflectionJavaField) {
        EAFieldType type = null;
        AIColumn annotation = this.getAIColumn(reflectionJavaField);

        if (annotation != null) {
            type = annotation.type();
        }// end of if cycle

        if (type == EAFieldType.ugualeAlField) {
            type = this.getFormType(reflectionJavaField);
        }// end of if cycle

        return type;
    }// end of method


    /**
     * Get the visibility of the column.
     * Di default true
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the visibility of the column
     */
    public boolean isColumnVisibile(final Field reflectionJavaField) {
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
     * Get the width of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the column expressed in int
     */
    @SuppressWarnings("all")
    public int getColumnWith(final Field reflectionJavaField) {
        int width = 0;
        AIColumn annotation = this.getAIColumn(reflectionJavaField);

        if (annotation != null) {
            width = annotation.width();
        }// end of if cycle

        return width;
    }// end of method


    /**
     * Get the type (field) of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the type for the specific column
     */
    public EAFieldType getFormType(final Field reflectionJavaField) {
        EAFieldType type = null;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            type = annotation.type();
        }// end of if cycle

        return type;
    }// end of method


    /**
     * Get the name (field) of the property.
     * Se manca, usa il nome della property
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the name (rows) of the field
     */
    public String getFormFieldName(final Field reflectionJavaField) {
        String name = null;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            name = annotation.name();
        }// end of if cycle

        if (text.isEmpty(name)) {
            name = reflectionJavaField.getName();
        }// end of if cycle

        return text.primaMaiuscola(name);
    }// end of method


    /**
     * Get the status focus of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return status of field
     */
    public boolean isFocus(Field reflectionJavaField) {
        boolean status = true;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            status = annotation.focus();
        }// end of if cycle

        return status;
    }// end of method


    /**
     * Get the width of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the field expressed in em
     */
    public int getWidth(Field reflectionJavaField) {
        int widthInt = 0;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            widthInt = annotation.widthEM();
        }// end of if cycle

        return widthInt;
    }// end of method


    /**
     * Get the widthEM of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the field expressed in em
     */
    public String getWidthEM(Field reflectionJavaField) {
        String width = "";
        int widthInt = this.getWidth(reflectionJavaField);
        String tag = "em";

        if (widthInt > 0) {
            width = widthInt + tag;
        }// end of if cycle

        return width;
    }// end of method


    /**
     * Get the status required of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return status of field
     */
    public boolean isRequired(Field reflectionJavaField) {
        boolean status = false;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            status = annotation.required();
        }// end of if cycle

        return status;
    }// end of method


    /**
     * Get the accessibility status of the field for the developer login.
     * La Annotation @AIField ha un suo valore di default per la property @AIField.dev()
     * Se il field lo prevede (valore di default) ci si rifà al valore generico del Form
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return accessibilità del field
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFieldAccessibilityDev(Field reflectionJavaField) {
        EAFieldAccessibility fieldAccessibility = null;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            fieldAccessibility = annotation.dev();
        }// end of if cycle

        if (fieldAccessibility == EAFieldAccessibility.asForm) {
            fieldAccessibility = this.getFormAccessibilityDev(reflectionJavaField.getClass());
        }// end of if cycle

        return fieldAccessibility != null ? fieldAccessibility : EAFieldAccessibility.allways;
    }// end of method


    /**
     * Get the accessibility status of the field for the admin login.
     * La Annotation @AIField ha un suo valore di default per la property @AIField.admin()
     * Se il field lo prevede (valore di default) ci si rifà al valore generico del Form
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return accessibilità del field
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFieldAccessibilityAdmin(Field reflectionJavaField) {
        EAFieldAccessibility fieldAccessibility = null;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            fieldAccessibility = annotation.admin();
        }// end of if cycle

        if (fieldAccessibility == EAFieldAccessibility.asForm) {
            fieldAccessibility = getFormAccessibilityAdmin(reflectionJavaField.getClass());
        }// end of if cycle

        return fieldAccessibility != null ? fieldAccessibility : EAFieldAccessibility.showOnly;
    }// end of method


    /**
     * Get the accessibility status of the field for the user login.
     * La Annotation @AIField ha un suo valore di default per la property @AIField.user()
     * Se il field lo prevede (valore di default) ci si rifà al valore generico del Form
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return accessibilità del field
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFieldAccessibilityUser(Field reflectionJavaField) {
        EAFieldAccessibility fieldAccessibility = null;
        AIField annotation = this.getAIField(reflectionJavaField);

        if (annotation != null) {
            fieldAccessibility = annotation.user();
        }// end of if cycle

        if (fieldAccessibility == EAFieldAccessibility.asForm) {
            fieldAccessibility = getFormAccessibilityUser(reflectionJavaField.getClass());
        }// end of if cycle

        return fieldAccessibility != null ? fieldAccessibility : EAFieldAccessibility.never;
    }// end of method


    /**
     * Get the accessibility status of the field for the current login.
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return accessibilità del field
     */
    @SuppressWarnings("all")
    public EAFieldAccessibility getFieldAccessibility(Field reflectionJavaField) {
        EAFieldAccessibility fieldAccessibility = EAFieldAccessibility.never;

        if (session.isDeveloper()) {
            fieldAccessibility = getFieldAccessibilityDev(reflectionJavaField);
        } else {
            if (session.isAdmin()) {
                fieldAccessibility = getFieldAccessibilityAdmin(reflectionJavaField);
            } else {
                if (session.isUser()) {
                    fieldAccessibility = getFieldAccessibilityUser(reflectionJavaField);
                }// end of if cycle
            }// end of if/else cycle
        }// end of if/else cycle

        return fieldAccessibility;
    }// end of method


    /**
     * Get the status of visibility for the field of ACompanyEntity.
     * <p>
     * Controlla se l'applicazione usa le company - flag  AlgosApp.USE_MULTI_COMPANY=true
     * Controlla se la collection (table) usa la company
     * Controlla se l'buttonUser collegato è un developer
     *
     * @param clazz the entity class
     *
     * @return status - default true
     */
    public boolean isCompanyFieldVisible(final Class<? extends AEntity> clazz) {
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


    /**
     * Tipo di lista (EAListButton) indicata nella AEntity class per la view AList
     *
     * @return valore della enumeration
     */
    @SuppressWarnings("all")
    public EAListButton getListBotton(final Class<? extends AEntity> clazz) {
        EAListButton listaNomi = EAListButton.standard;

        //@todo RIMETTERE

//        if (LibSession.isDeveloper()) {
//            listaNomi = getListBottonDev(clazz);
//        } else {
//            if (LibSession.isAdmin()) {
//                listaNomi = getListBottonAdmin(clazz);
//            } else {
//                if (true) {
//                    listaNomi = getListBottonUser(clazz);
//                }// end of if cycle
//            }// end of if/else cycle
//        }// end of if/else cycle

        return listaNomi;
    }// end of method


    /**
     * Tipo di lista (EAFormButton) indicata nella AEntity class per la view AForm
     *
     * @return valore della enumeration
     */
    @SuppressWarnings("all")
    public EAFormButton getFormBotton(final Class<? extends AEntity> clazz) {
        EAFormButton listaNomi = EAFormButton.standard;

        //@todo RIMETTERE

//        if (LibSession.isDeveloper()) {
//            listaNomi = getListBottonDev(clazz);
//        } else {
//            if (LibSession.isAdmin()) {
//                listaNomi = getListBottonAdmin(clazz);
//            } else {
//                if (true) {
//                    listaNomi = getListBottonUser(clazz);
//                }// end of if cycle
//            }// end of if/else cycle
//        }// end of if/else cycle

        return listaNomi;
    }// end of method


}// end of class
