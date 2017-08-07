package it.algos.springvaadin.lib;

import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
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
    public static Map getMap(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static Annotation[] getAll(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static AIField getField(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static AIColumn getColumn(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AIColumn annotation = null;
        String tag = "AIColumn";
        Map mappa = getMap(clazz, publicFieldName);

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
    public static AFType getTypeField(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AFType type = null;
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
    public static AFType getTypeColumn(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AFType type = null;
        AIColumn columnAnnotation = getColumn(clazz, publicFieldName);

        if (columnAnnotation != null) {
            type = columnAnnotation.type();
        }// end of if cycle

        if (type == AFType.ugualeAlField) {
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
    public static boolean isRequired(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.required();
        }// end of if cycle

        return status;
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
    public static boolean isEnabled(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.enabled();
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
    public static boolean isFirstCapital(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.firstCapital();
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
    public static String getNameField(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static String getNameColumn(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        String name = "";
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
     * @return the width of the field expressed in int (to be converted)
     */
    @SuppressWarnings("all")
    public static int getWidthEM(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        int width = 0;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            width = fieldAnnotation.widthEM();
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
    public static Size getSize(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static boolean isSize(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static String getSizeMessage(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        String message = "";
        Size annotation = getSize(clazz, publicFieldName);

        if (annotation != null) {
            message = annotation.message();
        }// end of if cycle

        if (message.equals("{javax.validation.constraints.Size.message}")) {
            message = "";
        }// end of if cycle

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
    public static int getMin(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static int getMax(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static NotEmpty getNotEmpty(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static String getNotEmptyMessage(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        String message = "";
        NotEmpty notEmpty = getNotEmpty(clazz, publicFieldName);

        if (notEmpty != null) {
            message = notEmpty.message();
        }// end of if cycle

        if (message.equals("{org.hibernate.validator.constraints.NotEmpty.message}")) {
            message = "Il campo non può essere vuoto";
        }// end of if cycle

        return message;
    }// end of static method


    /**
     * Get the existence of the NotEmpty annotation of the property.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return true if the NotEmpty Annotation exists
     */
    @SuppressWarnings("all")
    public static boolean isNotEmpty(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static NotNull getNotNull(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static String getNotNullMessage(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        String message = "";
        NotNull notNull = getNotNull(clazz, publicFieldName);

        if (notNull != null) {
            message = notNull.message();
        }// end of if cycle

        if (message.equals("{javax.validation.constraints.NotNull.message}")) {
            message = "Il campo non può essere nullo";
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
    public static boolean isNotNull(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean nonVuota = false;
        NotNull notNull = getNotNull(clazz, publicFieldName);

        if (notNull != null) {
            nonVuota = true;
        }// end of if cycle

        return nonVuota;
    }// end of static method

}// end of static class
