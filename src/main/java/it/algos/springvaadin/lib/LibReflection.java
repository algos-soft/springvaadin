package it.algos.springvaadin.lib;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractField;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.model.AlgosEntity;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 21/12/16.
 * .
 */
public abstract class LibReflection {


    /**
     * Field property di una EntityClass
     *
     * @param entityClazz     su cui operare la riflessione
     * @param publicFieldName property name
     */
    public static Field getField(Class<? extends AlgosEntity> entityClazz, final String publicFieldName) {
        Field field = null;

        try { // prova ad eseguire il codice
            field = entityClazz.getDeclaredField(publicFieldName);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return field;
    }// end of static method


    /**
     * All fields properties di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     */
    public static List<Field> getAllFields(Class<? extends AlgosEntity> entityClazz) {
        List<Field> fieldsList = null;
        Field[] fieldsArray = null;
        String fieldName = "";

        try { // prova ad eseguire il codice
            fieldsArray = entityClazz.getDeclaredFields();
            fieldsList = new ArrayList<>();
            for (Field field : fieldsArray) {
                fieldName = field.getName();
                if (LibText.isValid(fieldName) && !fieldName.equals(Cost.PROPERTY_EXCLUDED)) {
                    fieldsList.add(field);
                }// end of if cycle
            }// end of for cycle
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return fieldsList;
    }// end of static method


    /**
     * All field names di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutte i fieldNames, elencati in ordine di inserimento nella AlgosEntity
     */
    public static List<String> getAllFieldName(final Class<? extends AlgosEntity> entityClazz) {
        List<String> nameList = null;
        List<Field> fieldsList = getAllFields(entityClazz);

        if (fieldsList != null && fieldsList.size() > 0) {
            nameList = new ArrayList<>();
            for (Field field : fieldsList) {
                nameList.add(field.getName());
            }// end of for cycle
        }// end of if cycle

        return nameList;
    }// end of static method


    /**
     * All field names di una Entity
     *
     * @param entityBean su cui operare la riflessione
     *
     * @return tutte i fieldNames, elencati in ordine alfabetico
     */
    public static List<String> getAllFieldNameAlfabetico(final AlgosEntity entityBean) {
        return LibArray.sort((ArrayList) getAllFieldName(entityBean.getClass()));
    }// end of static method


    /**
     * All field names di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutte i fieldNames, elencati in ordine alfabetico
     */
    public static List<String> getAllFieldNameAlfabetico(final Class<? extends AlgosEntity> entityClazz) {
        return LibArray.sort((ArrayList) getAllFieldName(entityClazz));
    }// end of static method


    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Deprecated
    public static String getPropertyStr(final Class<?> clazz, final String publicFieldName) {
        String value = "";
        Object obj = getPropertyValus(clazz, publicFieldName);

        if (obj != null && obj instanceof String) {
            value = (String) obj;
        }// end of if cycle

        return value;
    }// end of static method

    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Deprecated
    public static Resource getPropertyRes(final Class<?> clazz, final String publicFieldName) {
        Resource value = null;
        Object obj = getPropertyValus(clazz, publicFieldName);

        if (obj != null && obj instanceof Resource) {
            value = (Resource) obj;
        }// end of if cycle

        return value;
    }// end of static method

    /**
     * Valore statico del metodo 'getButtonMenu' di una view attiva
     *
     * @param clazz            classe su cui operare la riflessione
     * @param publicMethodName metodo statico e pubblico
     */
    public static Object getMethodButton(final Class<?> clazz, String publicMethodName) {
        Object value = null;
        Method method;

        try { // prova ad eseguire il codice
            method = clazz.getDeclaredMethod(publicMethodName);
            value = method.invoke(null);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return value;
    }// end of static method


    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Deprecated
    public static Object getPropertyValus(final Class<?> clazz, final String publicFieldName) {
        Object value = "";

        try { // prova ad eseguire il codice
            value = clazz.getDeclaredField(publicFieldName).get(null);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return value;
    }// end of static method


//    /**
//     * Property statica di una classe
//     *
//     * @param clazz           classe su cui operare la riflessione
//     * @param publicFieldName property statica e pubblica
//     */
//    public static Field getField(final Class<?> clazz, final String publicFieldName) {
//        Field field = null;
//
//        try { // prova ad eseguire il codice
//            field = clazz.getDeclaredField(publicFieldName);
//        } catch (Exception unErrore) { // intercetta l'errore
//        }// fine del blocco try-catch
//
//        return field;
//    }// end of method


    /**
     * Properties di una entity
     *
     * @param entity da esaminare
     *
     * @return tutte le properties, elencate in ordine alfabetico
     */
    @Deprecated
    public static String[] getAllProperties(AlgosEntity entity) {
        String[] propertyNames = null;

        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entity);
        propertyNames = bean.getReadablePropertyNames();

        return propertyNames;
    }// end of static method


    /**
     * Properties di una entity class
     *
     * @param entityClazz da esaminare
     *
     * @return tutte le properties, elencate in ordine alfabetico
     */
    @Deprecated
    public static String[] getAllProperties(Class<? extends AlgosEntity> entityClazz) {
        String[] propertyNames = null;

        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entityClazz);
        propertyNames = bean.getReadablePropertyNames();

        return propertyNames;
    }// end of static method


    /**
     * Properties di una entity. Solo i campi dichiarati.
     * Escluso 'callbacks'
     * Escluso 'class'
     *
     * @param entity da esaminare
     *
     * @return properties, elencate in ordine alfabetico
     */
    @Deprecated
    public static List<String> getProperties(AlgosEntity entity) {
        List<String> propertyNames = null;
        String[] propertyNamesAll = getAllProperties(entity);

        if (propertyNamesAll != null && propertyNamesAll.length > 0) {
            propertyNames = new ArrayList<>();
            for (String property : propertyNamesAll) {
                if (!property.equals("callbacks") && !property.equals("class") && !property.equals("id")) {
                    propertyNames.add(property);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return propertyNames;
    }// end of static method

    /**
     * Properties di una entity. Solo i campi dichiarati.
     * Escluso 'callbacks'
     * Escluso 'class'
     *
     * @param entityClazz da esaminare
     *
     * @return properties, elencate in ordine alfabetico
     */
    @Deprecated
    public static List<String> getProperties(Class<? extends AlgosEntity> entityClazz) {
        List<String> propertyNames = null;
        String[] propertyNamesAll = getAllProperties(entityClazz);

        Object alfa = entityClazz.getFields();
        Object beta = entityClazz.getDeclaredFields();

        if (propertyNamesAll != null && propertyNamesAll.length > 0) {
            propertyNames = new ArrayList<>();
            for (String property : propertyNamesAll) {
                if (!property.equals("callbacks") && !property.equals("class") && !property.equals("id")) {
                    propertyNames.add(property);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return propertyNames;
    }// end of static method


    /**
     * Table di una entity. Inserita (opzionale) come Annotation.
     *
     * @param entity su cui operare la riflessione
     *
     * @return tableName dichiarato nella Annotation @Table
     */
    public static String getTable(AlgosEntity entity) {
        return getTable(entity.getClass());
    }// end of static method


    /**
     * Table di una classe entity. Inserita (opzionale) come Annotation.
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tableName dichiarato nella Annotation @Table
     */
    public static String getTable(Class<? extends AlgosEntity> entityClazz) {
        String tableName = "";
        Table table = entityClazz.getAnnotation(Table.class);

        if (table != null) {
            tableName = table.name();
        }// end of if cycle

        return tableName;
    }// end of static method


    /**
     * Metodo getter di una classe entity.
     *
     * @param entity       da esaminare
     * @param propertyName per estrarre il metodo
     *
     * @return method pubblico
     */
    public static Method getMethod(AlgosEntity entity, String propertyName) {
        return getMethod(entity.getClass(), propertyName);
    }// end of static method


    /**
     * Metodo getter di una classe entity.
     *
     * @param entityClazz  su cui operare la riflessione
     * @param propertyName per estrarre il metodo
     *
     * @return method pubblico
     */
    public static Method getMethod(Class<? extends AlgosEntity> entityClazz, String propertyName) {
        Method method = null;
        String methodNameGet = "get" + LibText.primaMaiuscola(propertyName);
        String methodNameIs = "is" + LibText.primaMaiuscola(propertyName);

        try { // prova ad eseguire il codice
            method = entityClazz.getMethod(methodNameGet);
        } catch (Exception unErrore) { // intercetta l'errore
            try { // prova ad eseguire il codice
                method = entityClazz.getMethod(methodNameIs);
            } catch (Exception unErrore2) { // intercetta l'errore
            }// fine del blocco try-catch
        }// fine del blocco try-catch

        return method;
    }// end of static method


    /**
     * Metodi getter di una classe entity.
     *
     * @param entity da esaminare
     *
     * @return tutti i metodi getter,  elencati in ordine di inserimento nella AlgosEntity
     */
    public static List<Method> getMethods(AlgosEntity entity) {
        return getMethods(entity.getClass());
    }// end of static method

    /**
     * Metodi getter di una classe entity.
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutti i metodi getter,  elencati in ordine di inserimento nella AlgosEntity
     */
    public static List<Method> getMethods(Class<? extends AlgosEntity> entityClazz) {
        List<Method> methods = null;
        List<String> propertyNames = getAllFieldName(entityClazz);

        if (propertyNames != null && propertyNames.size() > 0) {
            methods = new ArrayList<>();
            for (String name : propertyNames) {
                methods.add(getMethod(entityClazz, name));
            }// end of for cycle
        }// end of if cycle

        return methods;
    }// end of static method


    /**
     * Mappatura dei valori di una entity.
     * Recupera le property
     * Aggiunge la key property
     * Recupera i valori
     *
     * @param entity da esaminare
     *
     * @return mappa dei valori attuali della entity, in ordine alfabetico
     */
    @Deprecated
    public static LinkedHashMap<String, Object> getBeanMap(AlgosEntity entity) {
        LinkedHashMap<String, Object> map = new LinkedHashMap();
        Method method = null;
        List<String> propertiesName = getProperties(entity);
        propertiesName.add(Cost.PROPERTY_ID);
        Object value = null;

        for (String property : propertiesName) {
            method = getMethod(entity, property);
            if (method != null) {
                try { // prova ad eseguire il codice
                    value = method.invoke(entity);
                    map.put(property, value);
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                }// fine del blocco try-catch
            }// end of if cycle
        }// end of for cycle

        return map;
    }// end of static method


    /**
     * Array dei valori validi di una entity
     *
     * @param entity da esaminare
     *
     * @return arry dei valori, in ordine alfabetico delle chiavi della mappa
     */
    @Deprecated
    public static Object[] getValues(AlgosEntity entity) {
        Object[] args = null;
        LinkedHashMap<String, Object> mappa = LibReflection.getBeanMap(entity);

        if (mappa != null && mappa.size() > 0) {
            args = LibReflection.getValues(mappa);
        }// end of if cycle

        return args;
    }// end of static method


    /**
     * Array dei valori validi di una mappa
     *
     * @param mappa da esaminare
     *
     * @return arry dei valori, in ordine alfabetico delle chiavi della mappa
     */
    @Deprecated
    public static Object[] getValues(LinkedHashMap<String, Object> mappa) {
        ArrayList lista = new ArrayList();
        Object value;

        if (mappa != null && mappa.size() > 0) {
            for (String property : mappa.keySet()) {
                value = mappa.get(property);
                if (value != null) {
                    lista.add(value);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return lista.toArray();
    }// end of static method


    /**
     * Valore di una property della entity.
     *
     * @param entityBean      da esaminare
     * @param publicFieldName da esaminare
     *
     * @return valore della property
     */
    public static Object getValue(AlgosEntity entityBean, String publicFieldName) {
        Object value = null;
        LinkedHashMap<String, Object> mappa = getBeanMap(entityBean);

        if (mappa != null && mappa.size() > 0) {
            if (mappa.containsKey(publicFieldName)) {
                value = mappa.get(publicFieldName);
            }// end of if cycle
        }// end of if cycle

        return value;
    }// end of static method


//    /**
//     * Get all the annotation of the property.
//     *
//     * @param attr the metamodel Attribute
//     */
//    @SuppressWarnings("all")
//    public static AIField getFieldAnnotation(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
//        AIField fieldAnnotation = null;
//        Annotation annotation = null;
//        Field javaField = null;
//        Object[] items = null;
//        javaField = LibReflection.getField(clazz, publicFieldName);
//
//        if (javaField != null) {
//            annotation = javaField.getAnnotation(AIField.class);
//        }// end of if cycle
//
//        if (annotation != null && annotation instanceof AIField) {
//            fieldAnnotation = (AIField) annotation;
//        }// end of if cycle
//
//        return fieldAnnotation;
//    }// end of static method
//
}// end of static class
