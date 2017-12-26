package it.algos.springvaadin;

import com.vaadin.server.Resource;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.login.ALogin;
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
public class AReflectionServiceTest extends ATest {


    @InjectMocks
    private AReflectionService service;


    @InjectMocks
    public AAnnotationService annotation;


    @InjectMocks
    public ATextService text;


    @InjectMocks
    public AArrayService array;


    @InjectMocks
    public ALogin login;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(annotation);
        MockitoAnnotations.initMocks(text);
        MockitoAnnotations.initMocks(array);
        MockitoAnnotations.initMocks(login);
        array.text = text;
        annotation.array = array;
        service.annotation = annotation;
        service.text = text;
        annotation.reflection = service;
        service.array = array;
        service.login = login;
        AlgosApp.USE_MULTI_COMPANY = true;
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
     * Fields dichiarati nella Entity, da usare come columns della Grid (List)
     * Se listaNomi è nulla, usa tutti i campi (senza ID, senza note, senza creazione, senza modifica)
     * Comprende la entity e tutte le superclassi (fino a ACEntity e AEntity)
     * Se la company è prevista (AlgosApp.USE_MULTI_COMPANY, login.isDeveloper() e entityClazz derivata da ACEntity),
     * la posiziona come prima colonna a sinistra
     *
     * @param entityClazz da cui estrarre i fields
     * @param listaNomi   dei fields da considerare. Tutti, se listaNomi=null
     *
     * @return lista di fields visibili nella Grid
     */
    @Test
    public void getListFields() {
        String[] stringArray = {"ordine", "code"};
        ottenutoList = LibArray.fromString(stringArray);

        previstoIntero = 2;
        ottenutoFieldList = service.getListFields(ROLE_ENTITY_CLASS, ottenutoList);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        previstoIntero = 2;
        ottenutoFieldList = service.getListFields(ROLE_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.user);
        previstoIntero = 4;
        ottenutoFieldList = service.getListFields(USER_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_NICKNAME;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_PASSWORD;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ROLE;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ENABLED;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.admin);
        previstoIntero = 4;
        ottenutoFieldList = service.getListFields(USER_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_NICKNAME;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_PASSWORD;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ROLE;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ENABLED;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.developer);
        previstoIntero = 5;
        ottenutoFieldList = service.getListFields(USER_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_COMPANY;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NICKNAME;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_PASSWORD;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ROLE;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ENABLED;
        reflectionJavaField = ottenutoFieldList.get(4);
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
        String[] stringArray = {"ordine", "code"};
        ottenutoList = LibArray.fromString(stringArray);

        previstoIntero = 2;
        ottenutoFieldList = service.getFormFields(ROLE_ENTITY_CLASS, ottenutoList);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.admin);
        previstoIntero = 3;
        ottenutoFieldList = service.getFormFields(ROLE_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NOTE;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.developer);
        previstoIntero = 6;
        ottenutoFieldList = service.getFormFields(ROLE_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_KEY;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ORDINE;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CODE;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NOTE;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CREAZIONE;
        reflectionJavaField = ottenutoFieldList.get(4);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_MODIFICA;
        reflectionJavaField = ottenutoFieldList.get(5);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.admin);
        previstoIntero = 5;
        ottenutoFieldList = service.getFormFields(USER_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_NICKNAME;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_PASSWORD;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ROLE;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ENABLED;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NOTE;
        reflectionJavaField = ottenutoFieldList.get(4);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);


        login.setTypeLogged(EARoleType.developer);
        previstoIntero = 9;
        ottenutoFieldList = service.getFormFields(USER_ENTITY_CLASS, null);
        ottenutoIntero = ottenutoFieldList.size();
        assertEquals(previstoIntero, ottenutoIntero);

        previsto = FIELD_NAME_KEY;
        reflectionJavaField = ottenutoFieldList.get(0);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_COMPANY;
        reflectionJavaField = ottenutoFieldList.get(1);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NICKNAME;
        reflectionJavaField = ottenutoFieldList.get(2);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_PASSWORD;
        reflectionJavaField = ottenutoFieldList.get(3);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ROLE;
        reflectionJavaField = ottenutoFieldList.get(4);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_ENABLED;
        reflectionJavaField = ottenutoFieldList.get(5);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_NOTE;
        reflectionJavaField = ottenutoFieldList.get(6);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_CREAZIONE;
        reflectionJavaField = ottenutoFieldList.get(7);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);

        previsto = FIELD_NAME_MODIFICA;
        reflectionJavaField = ottenutoFieldList.get(8);
        ottenuto = reflectionJavaField.getName();
        assertEquals(previsto, ottenuto);
    }// end of single test


//    @SuppressWarnings("javadoc")
//    /**
//     * All field names di una EntityClass
//     *
//     * @param entityClazz su cui operare la riflessione
//     *
//     * @return tutte i fieldNames editabili, elencati in ordine di inserimento nella AEntity
//     */
//    @Test
//    public void getFieldsNameEntityOnly() {
//        List<String> listaNomiOttenuta = service.getFieldsNameEntityOnly(ROLE_ENTITY_CLASS);
//        assertNotNull(listaNomiOttenuta);
//        previstoIntero = 2;
//        ottenutoIntero = listaNomiOttenuta.size();
//        assertEquals(previstoIntero, ottenutoIntero);
//    }// end of single test

}// end of class
