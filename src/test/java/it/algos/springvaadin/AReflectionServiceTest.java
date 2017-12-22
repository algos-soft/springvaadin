package it.algos.springvaadin;

import com.vaadin.server.Resource;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.service.AAnnotationService;
import it.algos.springvaadin.service.AArrayService;
import it.algos.springvaadin.service.AReflectionService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 10-dic-2017
 * Time: 11:39
 */
@Slf4j
public class AReflectionServiceTest {


    @InjectMocks
    private AReflectionService service;


    @InjectMocks
    public AAnnotationService annotation;


    @InjectMocks
    public ATextService text;


    @InjectMocks
    public AArrayService array;


    private Field reflectionJavaField;
    private String previsto = "";
    private String ottenuto = "";
    private int previstoIntero = 0;
    private int ottenutoIntero = 0;
    private final static String FIELD_NAME_KEY = "id";
    private final static String FIELD_NAME_ORDINE = "ordine";
    private final static String FIELD_NAME_CODE = "code";
    private final static String FIELD_NAME_NOTE = "note";
    private final static String FIELD_NAME_CREAZIONE = "creazione";
    private final static String FIELD_NAME_MODIFICA = "modifica";
    private Field FIELD_ORDINE;
    private Field FIELD_CODE;
    private List<Field> ottenutoList;
    private static Class<? extends AEntity> ROLE_ENTITY_CLASS = Role.class;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(annotation);
        MockitoAnnotations.initMocks(text);
        MockitoAnnotations.initMocks(array);
        array.text = text;
        annotation.array = array;
        service.annotation = annotation;
        service.text = text;
        annotation.reflection = service;
        service.array = array;
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Test
    public void getPropertyValue() {
        Object value = service.getPropertyValue(ROLE_ENTITY_CLASS, FIELD_NAME_ORDINE);
        int a = 87;
        //@todo NON FUNZIONA
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Test
    public void getPropertyRes() {
        //@todo IMPLEMENTARE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Test
    public void getPropertyStr() {
        //@todo IMPLEMENTARE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Field dichiarato di una Entity
     *
     * @param entityClazz     classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Test
    public void getField() {
        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = service.getField(ROLE_ENTITY_CLASS, FIELD_NAME_ORDINE);
        assertNotNull(reflectionJavaField);
        assertEquals(int.class, reflectionJavaField.getType());
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = service.getField(ROLE_ENTITY_CLASS, FIELD_NAME_CODE);
        assertNotNull(reflectionJavaField);
        assertEquals(String.class, reflectionJavaField.getType());
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     /**
     * Fields dichiarati nella Entity
     * Solo la entityClazz indicata
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields da considerare per List e Form
     */
    @Test
    public void getFieldsEntityOnly() {
        previstoIntero = 2;
        ottenutoList = service.getFieldsEntityOnly(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Fields dichiarati nella Entity
     * Compresa la entityClazz
     * Comprese tutte le superclassi (fino a ACompanyEntity e AEntity)
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields da considerare per List e Form
     */
    @Test
    public void getFieldsAllSuperclasses() {
        previstoIntero = 6;
        ottenutoList = service.getFieldsAllSuperclasses(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_KEY;
        reflectionJavaField = ottenutoList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NOTE;
        reflectionJavaField = ottenutoList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CREAZIONE;
        reflectionJavaField = ottenutoList.get(4);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_MODIFICA;
        reflectionJavaField = ottenutoList.get(5);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Fields per le Campi del Form dichiarati nella Entity
     * Compresa la entity
     * Comprese tutte le superclassi (fino a ACompanyEntity e AEntity)
     *
     * @param entityClazz   da cui estrarre i fields
     * @param listaNomi     dei fields da considerare. Tutti, se listaNomi=null
     * @param addKeyID      flag per aggiungere (per primo) il field keyId
     * @param addKeyCompany flag per aggiungere (per secondo) il field keyCompany
     *
     * @return lista di fields da considerare per Form
     */
    @Test
    public void getFormFields() {
        List<String> listaNomi = annotation.getFormFieldsName(ROLE_ENTITY_CLASS);

        previstoIntero = 2;
        ottenutoList = service.getFormFields(ROLE_ENTITY_CLASS, listaNomi);
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * All field names di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutte i fieldNames editabili, elencati in ordine di inserimento nella AEntity
     */
    @Test
    public void getFieldsNameEntityOnly() {
        List<String> listaNomiOttenuta = service.getFieldsNameEntityOnly(ROLE_ENTITY_CLASS);
        assertNotNull(listaNomiOttenuta);
        previstoIntero = 2;
        ottenutoIntero = listaNomiOttenuta.size();
        assertEquals(previstoIntero, ottenutoIntero);
    }// end of single test

}// end of class
