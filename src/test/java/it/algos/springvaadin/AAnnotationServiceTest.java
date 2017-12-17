package it.algos.springvaadin;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.annotation.AIForm;
import it.algos.springvaadin.annotation.AIList;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.enumeration.EAFieldAccessibility;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.enumeration.EAFormButton;
import it.algos.springvaadin.enumeration.EAListButton;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.service.*;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 09-dic-2017
 * Time: 14:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class AAnnotationServiceTest {


    @InjectMocks
    private AAnnotationService service;

    @InjectMocks
    private AReflectionService reflection;

    @InjectMocks
    public AArrayService array;

    @InjectMocks
    public ATextService text;

    @InjectMocks
    public ASessionService session;

    private Field reflectionJavaField;
    private String previsto = "";
    private String ottenuto = "";
    private boolean previstoBooleano;
    private boolean ottenutoBooleano;
    private int previstoIntero = 0;
    private int ottenutoIntero = 0;
    private List<String> ottenutoList;
    private EAFieldType previstoType;
    private EAFieldType ottenutoType;
    private EAFieldAccessibility previstaAccessibilità;
    private EAFieldAccessibility ottenutaAccessibilità;
    private final static String NAME_ORDINE = "ordine";
    private final static String NAME_CODE = "code";
    private final static String HEADER_ORDINE = "#";
    private final static String HEADER_CODE = "Code";
    private static Field FIELD_ORDINE;
    private static Field FIELD_CODE;
    private static Class<? extends IAView> ROLE_VIEW_CLASS_LIST = RoleList.class;
    private static Class<? extends IAView> ROLE_VIEW_CLASS_FORM = RoleForm.class;
    private static Class<? extends AEntity> ROLE_ENTITY_CLASS = Role.class;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(service);
        MockitoAnnotations.initMocks(reflection);
        MockitoAnnotations.initMocks(array);
        MockitoAnnotations.initMocks(text);
        MockitoAnnotations.initMocks(session);
        service.session = session;
        service.text = text;
        array.text = text;
        service.array = array;
        FIELD_ORDINE = reflection.getField(Role.class, NAME_ORDINE);
        FIELD_CODE = reflection.getField(Role.class, NAME_CODE);
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Get the specific annotation of the class.
     * View classes
     *
     * @param viewClazz the view class
     *
     * @return the Annotation for the specific class
     */
    @Test
    public void getSpringView() {
        SpringView ottenuto = service.getSpringView(ROLE_VIEW_CLASS_LIST);
        assertNotNull(ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the specific annotation of the class.
     * Entity classes
     *
     * @param entityClazz the entity class
     *
     * @return the Annotation for the specific class
     */
    @Test
    public void getAIList() {
        AIList ottenuto = service.getAIList(ROLE_ENTITY_CLASS);
        assertNotNull(ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the specific annotation of the class.
     * Entity classes
     *
     * @param entityClazz the entity class
     *
     * @return the Annotation for the specific class
     */
    @Test
    public void getAIForm() {
        AIForm ottenuto = service.getAIForm(ROLE_ENTITY_CLASS);
        assertNotNull(ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the specific annotation of the field.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the Annotation for the specific field
     */
    @Test
    public void getAIColumn() {
        AIColumn ottenuto = service.getAIColumn(FIELD_CODE);
        assertNotNull(ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the specific annotation of the field.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the Annotation for the specific field
     */
    @Test
    public void getAIField() {
        AIField ottenuto = service.getAIField(FIELD_CODE);
        assertNotNull(ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the name of the spring-view.
     *
     * @param clazz the entity class
     *
     * @return the name of the spring-view
     */
    @Test
    public void getViewName() {
        previsto = Cost.VIEW_ROL_LIST;
        ottenuto = service.getViewName(ROLE_VIEW_CLASS_LIST);
        assertEquals(previsto, ottenuto);

        previsto = Cost.VIEW_ROL_FORM;
        ottenuto = service.getViewName(ROLE_VIEW_CLASS_FORM);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the status listShowsID of the class.
     *
     * @param clazz the entity class
     *
     * @return status of class - default false
     */
    @Test
    public void isListShowsID() {
        previstoBooleano = false;
        ottenutoBooleano = service.isListShowsID(ROLE_ENTITY_CLASS);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Colonne visibili (e ordinate) nella Grid
     *
     * @param clazz the entity class
     *
     * @return lista di colonne visibili nella Grid
     */
    @Test
    public void getListColumns() {
        String[] stringArray = {"ordine", "code"};
        List<String> previstoList = LibArray.fromString(stringArray);

        ottenutoList = service.getListColumns(ROLE_ENTITY_CLASS);
        assertEquals(previstoList, ottenutoList);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Fields visibili (e ordinati) nel Form
     *
     * @param clazz the entity class
     *
     * @return lista di fields visibili nel Form
     */
    @Test
    public void getFormFieldsName() {
        String[] stringArray = {"ordine", "code"};
        List<String> previstoList = LibArray.fromString(stringArray);

        ottenutoList = service.getFormFieldsName(ROLE_ENTITY_CLASS);
        assertEquals(previstoList, ottenutoList);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the name (column) of the property.
     * Se manca, usa il nome del Field
     * Se manca, usa il nome della property
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the name (column) of the field
     */
    @Test
    public void getColumnName() {
        previsto = HEADER_ORDINE;
        ottenuto = service.getColumnName(FIELD_ORDINE);
        assertEquals(previsto, ottenuto);

        previsto = HEADER_CODE;
        ottenuto = service.getColumnName(FIELD_CODE);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the type (column) of the property.
     * Se manca, usa il type del Field
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the type for the specific column
     */
    @Test
    public void getColumnType() {
        previstoType = EAFieldType.integer;
        ottenutoType = service.getColumnType(FIELD_ORDINE);
        assertEquals(previstoType, ottenutoType);

        previstoType = EAFieldType.text;
        ottenutoType = service.getColumnType(FIELD_CODE);
        assertEquals(previstoType, ottenutoType);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the visibility of the column.
     * Di default true
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the visibility of the column
     */
    @Test
    public void isColumnVisibile() {
        //@todo RIMETTERE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the width of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the column expressed in int
     */
    @Test
    public void getColumnWith() {
        previstoIntero = 55;
        ottenutoIntero = service.getColumnWith(FIELD_ORDINE);
        assertEquals(previstoIntero, ottenutoIntero);

        previstoIntero = 210;
        ottenutoIntero = service.getColumnWith(FIELD_CODE);
        assertEquals(previstoIntero, ottenutoIntero);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the type (field) of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the type for the specific column
     */
    @Test
    public void getFormType() {
        previstoType = EAFieldType.integer;
        ottenutoType = service.getFormType(FIELD_ORDINE);
        assertEquals(previstoType, ottenutoType);

        previstoType = EAFieldType.text;
        ottenutoType = service.getFormType(FIELD_CODE);
        assertEquals(previstoType, ottenutoType);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the name (field) of the property.
     * Se manca, usa il nome della property
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the name (rows) of the field
     */
    @Test
    public void getFormFieldName() {
        previsto = text.primaMaiuscola(NAME_ORDINE);
        ottenuto = service.getFormFieldName(FIELD_ORDINE);
        assertEquals(previsto, ottenuto);

        previsto = text.primaMaiuscola(NAME_CODE);
        ottenuto = service.getFormFieldName(FIELD_CODE);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void isCompanyFieldVisible() {
        //@todo RIMETTERE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Tipo di lista (EAListButton) indicata nella AEntity class per la view AList
     *
     * @return valore della enumeration
     */
    @Test
    public void getListBotton() {
        EAListButton ottenutoListButton;
        EAListButton previstoListButton = EAListButton.standard;

        ottenutoListButton = service.getListBotton(ROLE_ENTITY_CLASS);
        assertEquals(previstoListButton, ottenutoListButton);
        //@todo RIMETTERE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Tipo di lista (EAFormButton) indicata nella AEntity class per la view AForm
     *
     * @return valore della enumeration
     */
    @Test
    public void getFormBotton() {
        EAFormButton ottenutoFormButton;
        EAFormButton previstoFormButton = EAFormButton.standard;

        ottenutoFormButton = service.getFormBotton(ROLE_ENTITY_CLASS);
        assertEquals(previstoFormButton, ottenutoFormButton);
        //@todo RIMETTERE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the status focus of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return status of field
     */
    @Test
    public void isFocus() {
        previstoBooleano = false;
        ottenutoBooleano = service.isFocus(FIELD_ORDINE);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = true;
        ottenutoBooleano = service.isFocus(FIELD_CODE);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the width of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the field expressed in em
     */
    @Test
    public void getWidth() {
        previstoIntero = 3;
        ottenutoIntero = service.getWidth(FIELD_ORDINE);
        assertEquals(previstoIntero, ottenutoIntero);

        previstoIntero = 12;
        ottenutoIntero = service.getWidth(FIELD_CODE);
        assertEquals(previstoIntero, ottenutoIntero);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the widthEM of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return the width of the field expressed in em
     */
    @Test
    public void getWidthEM() {
        previsto = "3em";
        ottenuto = service.getWidthEM(FIELD_ORDINE);
        assertEquals(previsto, ottenuto);

        previsto = "12em";
        ottenuto = service.getWidthEM(FIELD_CODE);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the status required of the property.
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return status of field
     */
    @Test
    public void isRequired() {
        previstoBooleano = false;
        ottenutoBooleano = service.isRequired(FIELD_ORDINE);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = true;
        ottenutoBooleano = service.isRequired(FIELD_CODE);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFormAccessibilityDev() {
        previstaAccessibilità = EAFieldAccessibility.allways;
        ottenutaAccessibilità = service.getFormAccessibilityDev(ROLE_ENTITY_CLASS);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFormAccessibilityAdmin() {
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFormAccessibilityAdmin(ROLE_ENTITY_CLASS);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFormAccessibilityUser() {
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFormAccessibilityUser(ROLE_ENTITY_CLASS);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFieldAccessibilityDev() {
        previstaAccessibilità = EAFieldAccessibility.showOnly;
        ottenutaAccessibilità = service.getFieldAccessibilityDev(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);

        previstaAccessibilità = EAFieldAccessibility.allways;
        ottenutaAccessibilità = service.getFieldAccessibilityDev(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFieldAccessibilityAdmin() {
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibilityAdmin(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);

        previstaAccessibilità = EAFieldAccessibility.showOnly;
        ottenutaAccessibilità = service.getFieldAccessibilityAdmin(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
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
    @Test
    public void getFieldAccessibilityUser() {
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibilityUser(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);

        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibilityUser(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Get the accessibility status of the field for the current login.
     * Se manca completamente l'annotation, inserisco qui un valore di default (per evitare comunque un nullo)
     *
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return accessibilità del field
     */
    @Test
    public void getFieldAccessibility() {
        //--developer
        session.setDeveloper(true);
        session.setAdmin(false);
        session.setUser(false);
        previstaAccessibilità = EAFieldAccessibility.showOnly;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
        previstaAccessibilità = EAFieldAccessibility.allways;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);

        //--admin
        session.setDeveloper(false);
        session.setAdmin(true);
        session.setUser(false);
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
        previstaAccessibilità = EAFieldAccessibility.showOnly;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);

        //--user
        session.setDeveloper(false);
        session.setAdmin(false);
        session.setUser(true);
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_ORDINE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
        previstaAccessibilità = EAFieldAccessibility.never;
        ottenutaAccessibilità = service.getFieldAccessibility(FIELD_CODE);
        assertEquals(previstaAccessibilità, ottenutaAccessibilità);
    }// end of single test

}// end of class
