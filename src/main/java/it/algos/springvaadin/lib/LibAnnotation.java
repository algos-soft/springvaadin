package it.algos.springvaadin.lib;

import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosEntity;

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
    public static boolean getRequired(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
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
    public static boolean getEnabled(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean status = true;
        AIField fieldAnnotation = getField(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            status = fieldAnnotation.enabled();
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
     * Get the presence of @NotNull Annotation.
     *
     * @param clazz           the entity class
     * @param publicFieldName the name of the property
     *
     * @return the presence of the @NotNull Annotation
     */
    @SuppressWarnings("all")
    public static boolean getNull(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        boolean presente = false;
        String tag = "NotNull";
        Map mappa = getMap(clazz, publicFieldName);

        if (mappa != null && mappa.containsKey(tag)) {
            presente = true;
        }// end of if cycle

        return presente;
    }// end of static method


}// end of static class
