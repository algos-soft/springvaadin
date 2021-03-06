package it.algos.springvaadin;

import com.vaadin.server.Resource;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleRepository;
import it.algos.springvaadin.entity.role.RoleService;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.lib.ACost;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


    @InjectMocks
    public Role role;


    @InjectMocks
    public RoleService roleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(annotation);
        MockitoAnnotations.initMocks(text);
        MockitoAnnotations.initMocks(array);
        MockitoAnnotations.initMocks(login);
        MockitoAnnotations.initMocks(role);
        MockitoAnnotations.initMocks(roleService);
        array.text = text;
        annotation.array = array;
        service.annotation = annotation;
        service.text = text;
        annotation.reflection = service;
        service.array = array;
        login.setTypeLogged(EARoleType.user);
        service.login = login;
        AlgosApp.USE_SECURITY = true;
        AlgosApp.USE_MULTI_COMPANY = true;
    }// end of method

    @SuppressWarnings("javadoc")
    /**
     * Nomi dei fields dichiarati nella Entity
     * Comprende la entity e tutte le superclassi (fino a ACEntity e AEntity)
     * Esclusa la property: PROPERTY_SERIAL
     * Non ordinati
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields della Entity e di tutte le supeclassi
     */
    @Test
    public void getAllFieldsName() {
        previstoIntero = 6;
        ottenutoList = service.getAllFieldsName(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();
        assertEquals(previstoIntero, ottenutoIntero);
    }// end of single test

    @SuppressWarnings("javadoc")
    /**
     * Nomi dei fields dichiarati nella Entity
     * Comprende la entity e tutte le superclassi (fino a ACEntity e AEntity)
     * Esclusa le properties: PROPERTY_SERIAL, PROPERTY_CREAZIONE, PROPERTY_MODIFICA
     * Non ordinati
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields della Entity e di tutte le supeclassi
     */
    @Test
    public void getAllFieldsNameNoCrono() {
        previstoIntero = 4;
        ottenutoList = service.getAllFieldsNameNoCrono(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();
        assertEquals(previstoIntero, ottenutoIntero);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Fields dichiarati nella Entity
     * Comprende la entity e tutte le superclassi (fino a ACEntity e AEntity)
     * Esclusa la property: PROPERTY_SERIAL
     * Non ordinati
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields della Entity e di tutte le supeclassi
     */
    @Test
    public void getAllFields() {
        int ottenutoSize;
        int previstoSize = 6;

        ottenutoFieldList = service.getAllFields(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoFieldList);
        ottenutoSize = ottenutoFieldList.size();
        assertEquals(previstoSize, ottenutoSize);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Fields dichiarati nella Entity
     * Comprende la entity e tutte le superclassi (fino a ACEntity e AEntity)
     * Esclusa le properties: PROPERTY_SERIAL, PROPERTY_CREAZIONE, PROPERTY_MODIFICA
     * Non ordinati
     *
     * @param entityClazz da cui estrarre i fields
     *
     * @return lista di fields della Entity e di tutte le supeclassi
     */
    @Test
    public void getAllFieldsNoCrono() {
        int ottenutoSize;
        int previstoSize = 4;

        ottenutoFieldList = service.getAllFieldsNoCrono(ROLE_ENTITY_CLASS);
        assertNotNull(ottenutoFieldList);
        ottenutoSize = ottenutoFieldList.size();
        assertEquals(previstoSize, ottenutoSize);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Valore della property statica di una classe
     *
     * @param clazz           classe su cui operare la riflessione
     * @param publicFieldName property statica e pubblica
     */
    @Test
    public void getPropertyValue() {
        String previstoNote = "Note seminascoste";
        String ottenutoNote;
        previstoIntero = 17;
        previsto = "PippozBelloz";
        Object value;
        Role entity = Role.builder().ordine(previstoIntero).code(previsto).build();
        entity.note = previstoNote;

        value = service.getPropertyValue(entity, FIELD_NAME_ORDINE);
        assertNotNull(value);
        assertTrue(value instanceof Integer);
        ottenutoIntero = (Integer) value;
        assertEquals(previstoIntero, ottenutoIntero);

        value = service.getPropertyValue(entity, FIELD_NAME_CODE);
        assertNotNull(value);
        assertTrue(value instanceof String);
        ottenuto = (String) value;
        assertEquals(previsto, ottenuto);

        value = service.getPropertyValue(entity, FIELD_NAME_NOTE);
        assertNotNull(value);
        assertTrue(value instanceof String);
        ottenutoNote = (String) value;
        assertEquals(previstoNote, ottenutoNote);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Mappa di tutti i valori delle properties di una classe
     *
     * @param entityBean oggetto su cui operare la riflessione
     */
    @Test
    public void getPropertyMap() {
        Map previstoMappa;
        Map ottenutoMappa;
        previstoIntero = 17;
        int ottenutoSize;
        int previstoSize = 6;
        previsto = "PippozBelloz";
        Object value;
        Role entity = Role.builder().ordine(previstoIntero).code(previsto).build();

        ottenutoMappa = service.getPropertyMap(entity);
        assertNotNull(ottenutoMappa);
        ottenutoSize = ottenutoMappa.size();
        assertEquals(previstoSize, ottenutoSize);

        value = ottenutoMappa.get(FIELD_NAME_ORDINE);
        assertNotNull(value);
        assertTrue(value instanceof Integer);
        ottenutoIntero = (Integer) value;
        assertEquals(previstoIntero, ottenutoIntero);

        value = ottenutoMappa.get(FIELD_NAME_CODE);
        assertNotNull(value);
        assertTrue(value instanceof String);
        ottenuto = (String) value;
        assertEquals(previsto, ottenuto);
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
        previstoIntero = 3;
        ottenutoFieldList = service.getFormFields(ROLE_ENTITY_CLASS, ottenutoList);
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

//        previsto = FIELD_NAME_NOTE;
//        reflectionJavaField = ottenutoFieldList.get(3);
//        ottenuto = reflectionJavaField.getName();
//        assertEquals(previsto, ottenuto);
//
//        previsto = FIELD_NAME_CREAZIONE;
//        reflectionJavaField = ottenutoFieldList.get(4);
//        ottenuto = reflectionJavaField.getName();
//        assertEquals(previsto, ottenuto);
//
//        previsto = FIELD_NAME_MODIFICA;
//        reflectionJavaField = ottenutoFieldList.get(5);
//        ottenuto = reflectionJavaField.getName();
//        assertEquals(previsto, ottenuto);


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


}// end of class
