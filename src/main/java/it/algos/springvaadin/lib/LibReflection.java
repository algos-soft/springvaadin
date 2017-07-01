package it.algos.springvaadin.lib;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
    }// end of method

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
    }// end of method

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
    }// end of method


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
    }// end of method


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
    }// end of method

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
    }// end of method

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
    }// end of method

}// end of static class
