package it.algos.springvaadin.lib;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.annotation.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.ACompanyRequired;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.FieldAccessibility;
import it.algos.springvaadin.form.FormButton;
import it.algos.springvaadin.list.ListButton;
import it.algos.springvaadin.login.ARoleType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 12/07/17
 * Libreria di metodo per gestire le interfacce specifiche di Springvaadin: AIColumn e AIField
 */
public abstract class LibAnnotation {


    /**
     * Get a map of all annotations of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotations for the specific field
     */
    @SuppressWarnings("all")
    public static Map getMap(final Class<? extends AEntity> clazz, final String publicFieldName) {
        Map mappa = null;
        Annotation[] annotations = getAll(clazz, publicFieldName);

        if (annotations != null) {
            mappa = new HashMap();

            for (Annotation ann : annotations) {
                mappa.put(ann.annotationType().getSimpleName(), ann);
            }// end of for cycle
        }// end of if cycle

        return mappa;
    }// end of static method

    /**
     * Get all annotations of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotations for the specific field
     */
    @SuppressWarnings("all")
    public static Annotation[] getAll(final Class<? extends AEntity> clazz, final String publicFieldName) {
        Annotation[] annotations = null;
        Field javaField = LibReflection.getField(clazz, publicFieldName);

        if (javaField != null) {
            annotations = javaField.getAnnotations();
        }// end of if cycle

        return annotations;
    }// end of static method


    /**
     * Get the field annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static AIField getField(final Class<? extends AEntity> clazz, final String publicFieldName) {
        AIField annotation = null;
        String tag = "AIField";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            annotation = (AIField) mappa.get(tag);
        }// end of if cycle

        return annotation;
    }// end of static method

    /**
     * Get the column annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific column
     */
    @SuppressWarnings("all")
    public static AIColumn getColumn(final Class<? extends AEntity> clazz, final String publicFieldName) {
        AIColumn annotation = null;
        String tag = "AIColumn";
        Map mappa = getMap(clazz, publicFieldName);

        //--recupera l'annotation della entity corrente
        mappa = getMap(clazz, publicFieldName);


        if (mappa != null && mappa.containsKey(tag)) {
            annotation = (AIColumn) mappa.get(tag);
        }// end of if cycle

        return annotation;
    }// end of static method


    /**
     * Get the type (field) of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the type for the specific column
     */
    @SuppressWarnings("all")
    public static AFieldType getTypeField(final Class<? extends AEntity> clazz, final String publicFieldName) {
        AFieldType type = null;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            type = fieldAnnotation.type();
        }// end of if cycle

        return type;
    }// end of static method


    /**
     * Get the type (column) of the property.
     * Se manca, usa il type del Field
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the type for the specific column
     */
    @SuppressWarnings("all")
    public static AFieldType getTypeColumn(final Class<? extends AEntity> clazz, final String publicFieldName) {
        AFieldType type = null;
        AIColumn columnAnnotation = getColumn(clazz, publicFieldName);

        if (columnAnnotation != null) {
            type = columnAnnotation.type();
        }// end of if cycle

        if (type == AFieldType.ugualeAlField) {
            type = getTypeField(clazz, publicFieldName);
        }// end of if cycle

        return type;
    }// end of static method

    /**
     * Get the status required of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isRequired(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.required();
        }// end of if cycle

        return status;
    }// end of static method

    /**
     * Get the status required of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isRequiredWild(final Class<? extends AEntity> clazz, final String publicFieldName) {
        return isNotEmpty(clazz, publicFieldName) || isNotNull(clazz, publicFieldName) || isRequired(clazz, publicFieldName);
    }// end of static method

//    /**
//     * Get the status enabled of the property.
//     *
//     * @param clazz           the entity class
//     * @param publicFieldName the name of the property
//     *
//     * @return status of field
//     */
//    @SuppressWarnings("all")
//    public static ATypeEnabled getTypeEnabled(final Class<? extends AEntity> clazz, final String publicFieldName) {
//        ATypeEnabled typeEnabled = ATypeEnabled.always;
//        AIField fieldAnnotation = getField(clazz, publicFieldName);
//
//        if (fieldAnnotation != null) {
//            typeEnabled = fieldAnnotation.typeEnabled();
//        }// end of if cycle
//
//        return typeEnabled;
//    }// end of static method

    /**
     * Get the status enabled of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static FieldAccessibility getFieldAccessibility(final Class<? extends AEntity> clazz, final String publicFieldName) {
        FieldAccessibility fieldAccessibility = FieldAccessibility.never;

        if (LibSession.isDeveloper()) {
            fieldAccessibility = getFieldAccessibilityDev(clazz, publicFieldName);
        } else {
            if (LibSession.isAdmin()) {
                fieldAccessibility = getFieldAccessibilityAdmin(clazz, publicFieldName);
            } else {
                if (true) {
                    fieldAccessibility = getFieldAccessibilityUser(clazz, publicFieldName);
                }// end of if cycle
            }// end of if/else cycle
        }// end of if/else cycle

        return fieldAccessibility;
    }// end of static method


    /**
     * Get the status enabled of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static FieldAccessibility getFieldAccessibilityDev(final Class<? extends AEntity> clazz, final String publicFieldName) {
        FieldAccessibility fieldAccessibility = FieldAccessibility.allways;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            fieldAccessibility = fieldAnnotation.dev();
        }// end of if cycle

        return fieldAccessibility;
    }// end of static method


    /**
     * Get the status enabled of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static FieldAccessibility getFieldAccessibilityAdmin(final Class<? extends AEntity> clazz, final String publicFieldName) {
        FieldAccessibility fieldAccessibility = FieldAccessibility.allways;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            fieldAccessibility = fieldAnnotation.admin();
        }// end of if cycle

        return fieldAccessibility;
    }// end of static method


    /**
     * Get the status enabled of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static FieldAccessibility getFieldAccessibilityUser(final Class<? extends AEntity> clazz, final String publicFieldName) {
        FieldAccessibility fieldAccessibility = FieldAccessibility.allways;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            fieldAccessibility = fieldAnnotation.user();
        }// end of if cycle

        return fieldAccessibility;
    }// end of static method

    /**
     * Get the status focus of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isFocus(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.focus();
        }// end of if cycle

        return status;
    }// end of static method

    /**
     * Get the status FirstCapital of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isFirstCapital(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.firstCapital();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the status allUpper of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isAllUpper(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.allUpper();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the status nullSelectionAllowed of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isNullSelectionAllowed(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.nullSelectionAllowed();
        }// end of if cycle

        return status;
    }// end of static method

    /**
     * Get the status allLower of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isAllLower(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.allLower();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the status onlyNumber of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isOnlyNumber(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.onlyNumber();
        }// end of if cycle

        return status;
    }// end of static method

    /**
     * Get the status onlyLetter of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return status of field
     */
    @SuppressWarnings("all")
    public static boolean isOnlyLetter(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.onlyLetter();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the name (field) of the property.
     * Se manca, usa il nome della property
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the name (column) of the field
     */
    @SuppressWarnings("all")
    public static String getNameField(final Class<? extends AEntity> clazz, final String publicFieldName) {
        String name = "";
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            name = fieldAnnotation.name();
        }// end of if cycle

        if (name.equals("")) {
            name = LibText.primaMaiuscola(publicFieldName);
        }// end of if cycle

        return name;
    }// end of static method

    /**
     * Get the name (column) of the property.
     * Se manca, usa il nome del Field
     * Se manca, usa il nome della property
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the name (column) of the field
     */
    @SuppressWarnings("all")
    public static String getNameColumn(final Class<? extends AEntity> clazz, final String publicFieldName) {
        String name = publicFieldName;
        AIColumn columnAnnotation = getColumn(clazz, publicFieldName);

        if (columnAnnotation != null) {
            name = columnAnnotation.name();
        }// end of if cycle

        if (name.equals("")) {
            name = getNameField(clazz, publicFieldName);
        }// end of if cycle

        return name;
    }// end of static method


    /**
     * Get the widthEM of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the width of the field expressed in em
     */
    @SuppressWarnings("all")
    public static String getWidthEM(final Class<? extends AEntity> clazz, final String publicFieldName) {
        String width = "";
        int widthInt = 0;
        String tag = "em";
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            widthInt = fieldAnnotation.widthEM();
            if (widthInt > 0) {
                width = widthInt + tag;
            }// end of if cycle
        }// end of if cycle

        return width;
    }// end of static method


    /**
     * Get the number of rows of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the number of rows of the field expressed in int
     */
    @SuppressWarnings("all")
    public static int getNumRows(final Class<? extends AEntity> clazz, final String publicFieldName) {
        int rows = 1;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            rows = fieldAnnotation.numRowsTextArea();
        }// end of if cycle

        return rows;
    }// end of static method


    /**
     * Get the width of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the width of the column expressed in int
     */
    @SuppressWarnings("all")
    public static int getColumnWith(final Class<? extends AEntity> clazz, final String publicFieldName) {
        int width = 0;
        AIColumn columnAnnotation = getColumn(clazz, publicFieldName);

        if (columnAnnotation != null) {
            width = columnAnnotation.width();
        }// end of if cycle

        return width;
    }// end of static method

    /**
     * Get the specific annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static Size getSize(final Class<? extends AEntity> clazz, final String publicFieldName) {
        Size annotation = null;
        String tag = "Size";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            annotation = (Size) mappa.get(tag);
        }// end of if cycle

        return annotation;
    }// end of static method


    /**
     * Get the existence of the Size annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the Size Annotation exists
     */
    @SuppressWarnings("all")
    public static boolean isSize(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean sizeEsiste = false;
        Size sizeAnnotation = getSize(clazz, publicFieldName);

        if (sizeAnnotation != null) {
            sizeEsiste = true;
        }// end of if cycle

        return sizeEsiste;
    }// end of static method


    /**
     * Get the message of the Size annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the specific message
     */
    @SuppressWarnings("all")
    public static String getSizeMessage(final Class<? extends AEntity> clazz, final String publicFieldName, boolean notEmpty) {
        String message = "";
        Size annotation = getSize(clazz, publicFieldName);
        String oppure = notEmpty ? "" : "vuoto oppure ";
        int min = LibAnnotation.getMin(clazz, publicFieldName);
        int max = LibAnnotation.getMax(clazz, publicFieldName);
        boolean maxEccessivo = max > 10000;
        String fieldName = LibText.primaMaiuscola(publicFieldName);
        fieldName = LibText.setRossoBold(fieldName);

        if (annotation != null) {
            message = annotation.message();
        }// end of if cycle

        if (message.equals("{javax.validation.constraints.Size.message}")) {
            message = fieldName + " non può essere vuoto";
        }// end of if cycle

        if (min == max) {
            message = fieldName + " deve essere " + oppure + "uguale a " + min + " caratteri";
        } else {
            if (maxEccessivo) {
                message = fieldName + " deve essere " + oppure + " di almeno " + min + " caratteri";
            } else {
                message = fieldName + " deve essere " + oppure + "compreso tra " + min + " e " + max + " caratteri";
            }// end of if/else cycle
        }// end of if/else cycle

        return message;
    }// end of static method


    /**
     * Get the min length of the string property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the min length of the string property
     */
    @SuppressWarnings("all")
    public static int getMin(final Class<? extends AEntity> clazz, final String publicFieldName) {
        int length = 0;
        Size annotation = getSize(clazz, publicFieldName);

        if (annotation != null) {
            length = annotation.min();
        }// end of if cycle

        return length;
    }// end of static method


    /**
     * Get the max length of the string property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the max length of the string property
     */
    @SuppressWarnings("all")
    public static int getMax(final Class<? extends AEntity> clazz, final String publicFieldName) {
        int length = 0;
        Size annotation = getSize(clazz, publicFieldName);

        if (annotation != null) {
            length = annotation.max();
        }// end of if cycle

        return length;
    }// end of static method


    /**
     * Get the specific annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static NotEmpty getNotEmpty(final Class<? extends AEntity> clazz, final String publicFieldName) {
        NotEmpty notEmpty = null;
        String tag = "NotEmpty";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            notEmpty = (NotEmpty) mappa.get(tag);
        }// end of if cycle

        return notEmpty;
    }// end of static method


    /**
     * Get the message of the NotEmpty annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the specific message
     */
    @SuppressWarnings("all")
    public static String getNotEmptyMessage(final Class<? extends AEntity> clazz, final String publicFieldName) {
        String message = "";
        NotEmpty notEmpty = getNotEmpty(clazz, publicFieldName);
        String fieldName = LibText.primaMaiuscola(publicFieldName);
        fieldName = LibText.setRossoBold(fieldName);

        if (notEmpty != null) {
            message = notEmpty.message();
        }// end of if cycle

        if (message.equals("{org.hibernate.validator.constraints.NotEmpty.message}")) {
            message = fieldName + " non può essere vuoto";
        }// end of if cycle

        return message;
    }// end of static method


    /**
     * Get the existence of the notEmpty annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the notEmpty Annotation exists
     */
    @SuppressWarnings("all")
    public static boolean isNotEmpty(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean nonVuota = false;
        NotEmpty notEmpty = getNotEmpty(clazz, publicFieldName);

        if (notEmpty != null) {
            nonVuota = true;
        }// end of if cycle

        return nonVuota;
    }// end of static method


    /**
     * Get the specific annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static NotNull getNotNull(final Class<? extends AEntity> clazz, final String publicFieldName) {
        NotNull notNull = null;
        String tag = "NotNull";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            notNull = (NotNull) mappa.get(tag);
        }// end of if cycle

        return notNull;
    }// end of static method


    /**
     * Get the message of the NotNull annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the specific message
     */
    @SuppressWarnings("all")
    public static String getNotNullMessage(final Class<? extends AEntity> clazz, final String publicFieldName) {
        String message = "";
        NotNull notNull = getNotNull(clazz, publicFieldName);
        String fieldName = LibText.primaMaiuscola(publicFieldName);
        fieldName = LibText.setRossoBold(fieldName);

        if (notNull != null) {
            message = notNull.message();
        }// end of if cycle

        if (message.equals("{javax.validation.constraints.NotNull.message}")) {
            message = fieldName + " non può essere nullo";
        }// end of if cycle

        return message;
    }// end of static method


    /**
     * Get the existence of the NotNull annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the NotNull Annotation exists
     */
    @SuppressWarnings("all")
    public static boolean isNotNull(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean nonVuota = false;
        NotNull notNull = getNotNull(clazz, publicFieldName);

        if (notNull != null) {
            nonVuota = true;
        }// end of if cycle

        return nonVuota;
    }// end of static method


    /**
     * Get the specific annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static Indexed getIndexed(final Class<? extends AEntity> clazz, final String publicFieldName) {
        Indexed indexed = null;
        String tag = "Indexed";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            indexed = (Indexed) mappa.get(tag);
        }// end of if cycle

        return indexed;
    }// end of static method


    /**
     * Get the value of unique field of Indexed annotation
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the Indexed Annotation exists and is true
     */
    @SuppressWarnings("all")
    public static boolean isUnico(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean unico = false;
        Indexed indexed = getIndexed(clazz, publicFieldName);

        if (indexed != null) {
            unico = indexed.unique();
        }// end of if cycle

        return unico;
    }// end of static method


    /**
     * Get the class of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the class for the specific column
     */
    @SuppressWarnings("all")
    public static Class getClass(final Class<? extends AEntity> clazz, final String publicFieldName) {
        Class linkClazz = null;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            linkClazz = fieldAnnotation.clazz();
        }// end of if cycle

        return linkClazz;
    }// end of static method


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public static AIList getAIList(final Class<? extends AEntity> clazz) {
        return clazz.getAnnotation(AIList.class);
    }// end of static method


    /**
     * Colonne visibili (e ordinate) nella Grid
     *
     * @param clazz the entity class
     *
     * @return lista di colonne visibili nella Grid
     */
    @SuppressWarnings("all")
    public static List<String> getListColumns(final Class<? extends AEntity> clazz) {
        List<String> lista = null;
        String[] columns = null;
        AIList listAnnotation = getAIList(clazz);

        if (listAnnotation != null) {
            columns = listAnnotation.columns();
        }// end of if cycle

        if (columns != null && columns.length > 0 && !columns[0].equals("")) {
            lista = Arrays.asList(columns);
        }// end of if cycle

        return lista;
    }// end of static method


    /**
     * Get the status listShowsID of the class.
     *
     * @param clazz the entity class
     *
     * @return status of class - default false
     */
    @SuppressWarnings("all")
    public static boolean isListShowsID(final Class<? extends AEntity> clazz) {
        boolean status = false;
        AIList listAnnotation = getAIList(clazz);

        if (listAnnotation != null) {
            status = listAnnotation.showsID();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the width of the property.
     *
     * @param clazz the entity class
     *
     * @return the width of the column expressed in int
     */
    @SuppressWarnings("all")
    public static int getListWidthID(final Class<? extends AEntity> clazz) {
        int width = 290;
        AIList listAnnotation = getAIList(clazz);

        if (listAnnotation != null) {
            width = listAnnotation.widthID();
        }// end of if cycle

        return width;
    }// end of static method


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public static AIForm getAIForm(final Class<? extends AEntity> clazz) {
        return clazz.getAnnotation(AIForm.class);
    }// end of static method


    /**
     * Fields visibili (e ordinati) nel Form
     *
     * @param clazz the entity class
     *
     * @return lista di fields visibili nel Form
     */
    @SuppressWarnings("all")
    public static List<String> getFormFields(final Class<? extends AEntity> clazz) {
        List<String> lista = null;
        String[] fields = null;
        AIForm formAnnotation = getAIForm(clazz);

        if (formAnnotation != null) {
            fields = formAnnotation.fields();
        }// end of if cycle

        if (fields != null && fields.length > 0 && !fields[0].equals("")) {
            lista = Arrays.asList(fields);
        }// end of if cycle

        return lista;
    }// end of static method


    /**
     * Get the status formShowsID of the class.
     *
     * @param clazz the entity class
     *
     * @return status of class - default false
     */
    @SuppressWarnings("all")
    public static boolean isFormShowsID(final Class<? extends AEntity> clazz) {
        boolean status = false;
        AIForm formAnnotation = getAIForm(clazz);

        if (formAnnotation != null) {
            status = formAnnotation.showsID();
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the width of the property.
     *
     * @param clazz the entity class
     *
     * @return the width of the field expressed in int
     */
    @SuppressWarnings("all")
    public static String getFormWithID(final Class<? extends AEntity> clazz) {
        String width = "";
        int widthInt = 0;
        String tag = "em";
        AIForm formAnnotation = getAIForm(clazz);

        if (formAnnotation != null) {
            widthInt = formAnnotation.widthIDEM();
            width = widthInt + tag;
        }// end of if cycle

        return width;
    }// end of static method


    /**
     * Get the specific annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the Annotation for the specific field
     */
    @SuppressWarnings("all")
    public static DBRef getDBRef(final Class<? extends AEntity> clazz, final String publicFieldName) {
        DBRef dbRef = null;
        String tag = "DBRef";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            dbRef = (DBRef) mappa.get(tag);
        }// end of if cycle

        return dbRef;
    }// end of static method

    /**
     * Get the existence of the DBRef annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the notEmpty Annotation exists
     */
    @SuppressWarnings("all")
    public static boolean isDBRef(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean usaDBRef = false;
        DBRef dbRef = getDBRef(clazz, publicFieldName);

        if (dbRef != null) {
            usaDBRef = true;
        }// end of if cycle

        return usaDBRef;
    }// end of static method

    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public static SpringView getSpringView(final Class<? extends View> clazz) {
        return clazz.getAnnotation(SpringView.class);
    }// end of static method


    /**
     * Get the name of the spring-view.
     *
     * @param clazz the entity class
     *
     * @return the name of the spring-view
     */
    @SuppressWarnings("all")
    public static String getNameView(final Class<? extends View> clazz) {
        String name = "";
        SpringView viewAnnotation = getSpringView(clazz);

        if (viewAnnotation != null) {
            name = viewAnnotation.name();
        }// end of if cycle

        return name;
    }// end of static method


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public static AIEntity getAIEntity(final Class<? extends AEntity> clazz) {
        return clazz.getAnnotation(AIEntity.class);
    }// end of static method


    /**
     * Get the status companyNotNull of the class.
     *
     * @param clazz the entity class
     */
    @SuppressWarnings("all")
    public static ACompanyRequired companyType(final Class<? extends AEntity> clazz) {
        ACompanyRequired companyRequired = ACompanyRequired.nonUsata;
        AIEntity entityAnnotation = getAIEntity(clazz);

        if (entityAnnotation != null) {
            companyRequired = entityAnnotation.company();
        }// end of if cycle

        return companyRequired;
    }// end of static method

    /**
     * Get the status of use of ACompanyEntity.
     * Controlla se l'applicazione usa le company - flag  AlgosApp.USE_MULTI_COMPANY=true
     * Controlla se la collection (table) usa la company
     *
     * @param clazz the entity class
     *
     * @return status - default true
     */
    @SuppressWarnings("all")
    public static boolean useCompany(final Class<? extends AEntity> clazz) {
        boolean status = true;

        if (!AlgosApp.USE_MULTI_COMPANY) {
            return false;
        }// end of if cycle

        if (LibAnnotation.companyType(clazz) == ACompanyRequired.nonUsata) {
            return false;
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the status of visibility for the field of ACompanyEntity.
     * Controlla se l'applicazione usa le company - flag  AlgosApp.USE_MULTI_COMPANY=true
     * Controlla se la collection (table) usa la company
     * Controlla se l'utente collegato è un developer
     *
     * @param clazz the entity class
     *
     * @return status - default true
     */
    @SuppressWarnings("all")
    public static boolean useCompanyFields(final Class<? extends AEntity> clazz) {
        boolean status = true;

        if (!AlgosApp.USE_MULTI_COMPANY) {
            return false;
        }// end of if cycle

        if (LibAnnotation.companyType(clazz) == ACompanyRequired.nonUsata) {
            return false;
        }// end of if cycle

        if (!LibSession.isDeveloper()) {
            return false;
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the status of visibility for the optional field of ACompanyEntity.
     * Definiti in Cost.COMPANY_OPTIONAL
     *
     * @param clazz the entity class
     *
     * @return status - default true
     */
    @SuppressWarnings("all")
    public static boolean useCompanyOptionalFields(final Class<? extends AEntity> clazz) {
        boolean status = true;

        if (!useCompanyFields(clazz)) {
            return false;
        }// end of if cycle

        if (LibAnnotation.companyType(clazz) == ACompanyRequired.facoltativa) {
            return false;
        }// end of if cycle

        if (LibAnnotation.companyType(clazz) == ACompanyRequired.facoltativa) {
            return false;
        }// end of if cycle

        return status;
    }// end of static method


    /**
     * Get the roleTypeVisibility of the property.
     * Se manca, usa il ruolo Guest
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the ARoleType of the field
     */
    @SuppressWarnings("all")
    public static ARoleType getFieldRoleType(final Class<? extends AEntity> clazz, final String publicFieldName) {
        ARoleType roleTypeVisibility = ARoleType.guest;

        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            roleTypeVisibility = fieldAnnotation.roleTypeVisibility();
        }// end of if cycle

        return roleTypeVisibility;
    }// end of static method

    /**
     * Get the visibility of the field.
     * Controlla il ruolo dell'utente connesso
     * Controlla il grado di accesso consentito
     * Di default true
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the field
     */
    @SuppressWarnings("all")
    public static boolean isFieldVisibile(final Class<? extends AEntity> clazz, final String publicFieldName, boolean nuovaEntity) {
        boolean visibile = true;

        visibile = isFieldVisibileRole(clazz, publicFieldName);
        if (visibile) {
            visibile = isFieldVisibileAccess(clazz, publicFieldName, nuovaEntity);
        }// end of if cycle

        return visibile;
    }// end of static method

    /**
     * Get the visibility of the field.
     * Controlla il ruolo dell'utente connesso
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the field
     */
    @SuppressWarnings("all")
    public static boolean isFieldVisibileRole(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean visibile = false;
        ARoleType roleTypeVisibility = getFieldRoleType(clazz, publicFieldName);

        switch (roleTypeVisibility) {
            case nobody:
                visibile = false;
                break;
            case developer:
                if (LibSession.isDeveloper()) {
                    visibile = true;
                }// end of if cycle
                break;
            case admin:
                if (LibSession.isAdmin()) {
                    visibile = true;
                }// end of if cycle
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
    }// end of static method


    /**
     * Get the visibility of the field.
     * Controlla il grado di accesso consentito
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the field
     */
    @SuppressWarnings("all")
    public static boolean isFieldVisibileAccess(final Class<? extends AEntity> clazz, final String publicFieldName, boolean nuovaEntity) {
        boolean visibile = true;
        FieldAccessibility fieldAccessibility = getFieldAccessibility(clazz, publicFieldName);

        switch (fieldAccessibility) {
            case allways:
                visibile = true;
                break;
            case newOnly:
                visibile = true;
                break;
            case showOnly:
                visibile = true;
                break;
            case never:
                visibile = false;
                break;
            default:
                visibile = true;
                break;
        } // end of switch statement

        return visibile;
    }// end of static method


    /**
     * Get the enabled state of the field.
     * Controlla la visibilità del field
     * Controlla il grado di accesso consentito
     * Di default true
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the field
     */
    @SuppressWarnings("all")
    public static boolean isFieldEnabled(final Class<? extends AEntity> clazz, final String publicFieldName, boolean nuovaEntity) {
        boolean enabled = true;
        boolean visibile = isFieldVisibileRole(clazz, publicFieldName);

        if (visibile) {
            enabled = isFieldEnabledAccess(clazz, publicFieldName, nuovaEntity);
        }// end of if cycle

        return enabled;
    }// end of static method


    /**
     * Get the enabled state of the field.
     * Controlla il grado di accesso consentito
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the field
     */
    @SuppressWarnings("all")
    public static boolean isFieldEnabledAccess(final Class<? extends AEntity> clazz, final String publicFieldName, boolean nuovaEntity) {
        boolean enabled = true;
        FieldAccessibility fieldAccessibility = getFieldAccessibility(clazz, publicFieldName);

        switch (fieldAccessibility) {
            case allways:
                enabled = true;
                break;
            case newOnly:
                enabled = nuovaEntity;
                break;
            case showOnly:
                enabled = false;
                break;
            case never:
                enabled = false;
                break;
            default:
                enabled = true;
                break;
        } // end of switch statement

        return enabled;
    }// end of static method

    /**
     * Get the roleTypeVisibility of the property.
     * Se manca, usa il ruolo Guest
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the ARoleType of the field
     */
    @SuppressWarnings("all")
    public static ARoleType getColumnRoleType(final Class<? extends AEntity> clazz, final String publicFieldName) {
        ARoleType roleTypeVisibility = ARoleType.guest;

        AIColumn fieldAnnotation = getColumn(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            roleTypeVisibility = fieldAnnotation.roleTypeVisibility();
        }// end of if cycle

        return roleTypeVisibility;
    }// end of static method

    /**
     * Get the visibility of the column.
     * Di default true
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the visibility of the column
     */
    @SuppressWarnings("all")
    public static boolean isColumnVisibile(final Class<? extends AEntity> clazz, final String publicFieldName) {
        boolean visibile = false;
        ARoleType roleTypeVisibility = getColumnRoleType(clazz, publicFieldName);

        switch (roleTypeVisibility) {
            case nobody:
                visibile = false;
                break;
            case developer:
                if (LibSession.isDeveloper()) {
                    visibile = true;
                }// end of if cycle
                break;
            case admin:
                if (LibSession.isAdmin()) {
                    visibile = true;
                }// end of if cycle
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
    }// end of static method


    /**
     * Get the roleTypeVisibility of the collection (modulo-table).
     * Se manca, usa il ruolo Guest
     *
     * @param clazz the entity class
     *
     * @return the ARoleType of the collection
     */
    @SuppressWarnings("all")
    public static ARoleType getEntityRoleType(final Class<? extends AEntity> clazz) {
        ARoleType roleTypeVisibility = ARoleType.guest;
        AIEntity entityAnnotation = getAIEntity(clazz);

        if (entityAnnotation != null) {
            roleTypeVisibility = entityAnnotation.roleTypeVisibility();
        }// end of if cycle

        return roleTypeVisibility;
    }// end of static method


    /**
     * Get the visibility of the collection (modulo-table).
     * Di default true
     *
     * @param clazz the entity class
     *
     * @return the visibility of the collection
     */
    @SuppressWarnings("all")
    public static boolean isEntityClassVisibile(final Class<? extends AEntity> clazz) {
        boolean visibile = false;
        ARoleType roleTypeVisibility = getEntityRoleType(clazz);

        switch (roleTypeVisibility) {
            case nobody:
                visibile = false;
                break;
            case developer:
                if (LibSession.isDeveloper()) {
                    visibile = true;
                }// end of if cycle
                break;
            case admin:
                if (LibSession.isAdmin()) {
                    visibile = true;
                }// end of if cycle
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
    }// end of static method


    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static ListButton getListBotton(final Class<? extends AEntity> clazz) {
        ListButton listaNomi = ListButton.standard;

        if (LibSession.isDeveloper()) {
            listaNomi = getListBottonDev(clazz);
        } else {
            if (LibSession.isAdmin()) {
                listaNomi = getListBottonAdmin(clazz);
            } else {
                if (true) {
                    listaNomi = getListBottonUser(clazz);
                }// end of if cycle
            }// end of if/else cycle
        }// end of if/else cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static ListButton getListBottonDev(final Class<? extends AEntity> clazz) {
        ListButton listaNomi = null;
        AIList annotation = getAIList(clazz);

        if (annotation != null) {
            listaNomi = annotation.dev();
        }// end of if cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static ListButton getListBottonAdmin(final Class<? extends AEntity> clazz) {
        ListButton listaNomi = null;
        AIList annotation = getAIList(clazz);

        if (annotation != null) {
            listaNomi = annotation.admin();
        }// end of if cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static ListButton getListBottonUser(final Class<? extends AEntity> clazz) {
        ListButton listaNomi = null;
        AIList annotation = getAIList(clazz);

        if (annotation != null) {
            listaNomi = annotation.user();
        }// end of if cycle

        return listaNomi;
    }// end of static method





    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static FormButton getFormBotton(final Class<? extends AEntity> clazz) {
        FormButton listaNomi = FormButton.standard;

        if (LibSession.isDeveloper()) {
            listaNomi = getFormBottonDev(clazz);
        } else {
            if (LibSession.isAdmin()) {
                listaNomi = getFormBottonAdmin(clazz);
            } else {
                if (true) {
                    listaNomi = getFormBottonUser(clazz);
                }// end of if cycle
            }// end of if/else cycle
        }// end of if/else cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static FormButton getFormBottonDev(final Class<? extends AEntity> clazz) {
        FormButton listaNomi = null;
        AIForm annotation = getAIForm(clazz);

        if (annotation != null) {
            listaNomi = annotation.dev();
        }// end of if cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static FormButton getFormBottonAdmin(final Class<? extends AEntity> clazz) {
        FormButton listaNomi = null;
        AIForm annotation = getAIForm(clazz);

        if (annotation != null) {
            listaNomi = annotation.admin();
        }// end of if cycle

        return listaNomi;
    }// end of static method

    /**
     * Bottoni visibili nella toolbar
     *
     * @param clazz the entity class
     *
     * @return lista di bottoni visibili nella toolbar
     */
    @SuppressWarnings("all")
    public static FormButton getFormBottonUser(final Class<? extends AEntity> clazz) {
        FormButton listaNomi = null;
        AIForm annotation = getAIForm(clazz);

        if (annotation != null) {
            listaNomi = annotation.user();
        }// end of if cycle

        return listaNomi;
    }// end of static method

}// end of static class
