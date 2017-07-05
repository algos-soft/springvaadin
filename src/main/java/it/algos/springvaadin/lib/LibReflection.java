package it.algos.springvaadin.lib;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractField;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 21/12/16.
 * .
 */
public abstract class LibReflection {

    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
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
    public static Object getPropertyValus(final Class<?> clazz, final String publicFieldName) {
        Object value = "";

        try { // prova ad eseguire il codice
            value = clazz.getDeclaredField(publicFieldName).get(null);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return value;
    }// end of static method


    /**
     * Tutte le property di una classe
     *
     * @param clazz classe su cui operare la riflessione
     */
    public static List<Field> getAllFields(final Class<?> clazz) {
        Field[] array = null;

        try { // prova ad eseguire il codice
            array = clazz.getDeclaredFields();
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return (ArrayList<Field>) (ArrayList<?>) (LibArray.fromObj(array));
    }// end of static method

    /**
     * Tutte le property di una classe
     *
     * @param clazz classe su cui operare la riflessione
     */
    public static List<String> getAllPropertyName(final Class<?> clazz) {
        List<String> lista = new ArrayList();
        Field[] array = null;
        String fieldName = "";

        try { // prova ad eseguire il codice
            array = clazz.getDeclaredFields();
            for (Field field : array) {
                fieldName = field.getName();
                if (LibText.isValid(fieldName) && !fieldName.equals(Cost.PROPERTY_EXCLUDED)) {
                    lista.add(field.getName());
                }// end of if cycle
            }// end of for cycle
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return lista;
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
     * Property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    public static Field getField(final Class<?> clazz, final String publicFieldName) {
        Field field = null;

        try { // prova ad eseguire il codice
            field = clazz.getDeclaredField(publicFieldName);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return field;
    }// end of static method


    /**
     * Properties di una entity
     *
     * @param entity da esaminare
     *
     * @return tutte le properties, elencate in ordine alfabetico
     */
    public static String[] getAllProperties(AlgosModel entity) {
        String[] propertyNames = null;

        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(entity);
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
    public static List<String> getProperties(AlgosModel entity) {
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
     * Table di una entity. Inserita (opzionale) come Annotation.
     *
     * @param entity da esaminare
     *
     * @return tableName dichiarato nella Annotation @Table
     */
    public static String getTable(AlgosModel entity) {
        return getTable(entity.getClass());
    }// end of static method


    /**
     * Table di una classe entity. Inserita (opzionale) come Annotation.
     *
     * @param clazz da esaminare
     *
     * @return tableName dichiarato nella Annotation @Table
     */
    public static String getTable(Class<?> clazz) {
        String tableName = "";
        Table table = clazz.getAnnotation(Table.class);

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
    public static Method getMethod(AlgosModel entity, String propertyName) {
        Method method = null;
        String methodNameGet = "get" + LibText.primaMaiuscola(propertyName);
        String methodNameIs = "is" + LibText.primaMaiuscola(propertyName);

        try { // prova ad eseguire il codice
            method = entity.getClass().getMethod(methodNameGet);
        } catch (Exception unErrore) { // intercetta l'errore
            try { // prova ad eseguire il codice
                method = entity.getClass().getMethod(methodNameIs);
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
     * @return tutti i metodi getter, in ordine alfabetico
     */
    public static List<Method> getMethods(AlgosModel entity) {
        List<Method> methods = null;
        List<String> propertyNames = getProperties(entity);

        if (propertyNames != null && propertyNames.size() > 0) {
            methods = new ArrayList<>();
            for (String name : propertyNames) {
                methods.add(getMethod(entity, name));
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
    public static LinkedHashMap<String, Object> getBeanMap(AlgosModel entity) {
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
     * Array dei valori di una entity
     *
     * @param entity da esaminare
     *
     * @return arry dei valori, in ordine alfabetico delle chiavi della mappa
     */
    public static Object[] getArgs(AlgosModel entity) {
        Object[] args = null;
        LinkedHashMap<String, Object> mappa = LibReflection.getBeanMap(entity);

        if (mappa != null && mappa.size() > 0) {
            args = LibReflection.getArgs(mappa);
        }// end of if cycle

        return args;
    }// end of static method


    /**
     * Array dei valori di una mappa
     *
     * @param mappa da esaminare
     *
     * @return arry dei valori, in ordine alfabetico delle chiavi della mappa
     */
    public static Object[] getArgs(LinkedHashMap<String, Object> mappa) {
        Object[] args = null;
        int k = 0;

        if (mappa != null && mappa.size() > 0) {
            args = new Object[mappa.size()];

            for (String property : mappa.keySet()) {
                args[k] = mappa.get(property);
                k++;
            }// end of for cycle

        }// end of if cycle

        return args;
    }// end of static method

}// end of static class
