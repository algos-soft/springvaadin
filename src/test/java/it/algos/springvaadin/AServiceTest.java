package it.algos.springvaadin;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleService;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 10-dic-2017
 * Time: 11:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class AServiceTest extends ATest {

    @InjectMocks
    private RoleService service;

    @InjectMocks
    private AAnnotationService annotation;

    @InjectMocks
    private AReflectionService reflection;

    @InjectMocks
    private ATextService text;

    @InjectMocks
    private AArrayService array;


    private Field reflectionJavaField;
    private String previsto = "";
    private String ottenuto = "";
    private boolean previstoBooleano;
    private boolean ottenutoBooleano;
    private int previstoIntero = 0;
    private int ottenutoIntero = 0;
    private List<Field> previstoList;
    private List<Field> ottenutoList;
    private EAFieldType previstoType;
    private EAFieldType ottenutoType;
    private final static String NAME_ORDINE = "ordine";
    private final static String NAME_CODE = "code";
    private final static String HEADER_ORDINE = "#";
    private final static String HEADER_CODE = "Code";
    private static Field FIELD_ORDINE;
    private static Field FIELD_CODE;
    private static Class<? extends AEntity> ROLE_ENTITY_CLASS = Role.class;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(service);
        MockitoAnnotations.initMocks(annotation);
        MockitoAnnotations.initMocks(reflection);
        MockitoAnnotations.initMocks(text);
        MockitoAnnotations.initMocks(array);
        annotation.text = text;
        annotation.array = array;
        annotation.reflection = reflection;
        annotation.reflection.array = array;
        reflection.text = text;
        reflection.annotation = annotation;
        array.text = text;
        service.annotation = annotation;
        service.annotation.reflection = reflection;
        service.reflection = reflection;
        service.entityClass = ROLE_ENTITY_CLASS;
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Returns all entities of the type.
     * <p>
     * Senza filtri
     * Ordinati per ID
     * <p>
     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
     * L'annotation standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
     * Eseguo qui la conversione, che rimane trasparente al resto del programma
     *
     * @return all entities
     */
    @Test
    public void findAll() {
        //@todo RIMETTERE
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Colonne visibili (e ordinate) nella Grid
     * Sovrascrivibile
     * La colonna key ID normalmente non si visualizza
     * 1) Se questo metodo viene sovrascritto, si utilizza la lista della sottoclasse specifica (con o senza ID)
     * 2) Se la classe AEntity->@AIList(columns = ...) prevede una lista specifica, usa quella lista (con o senza ID)
     * 3) Se non trova AEntity->@AIList, usa tutti i campi della AEntity (senza ID)
     * 5) Vengono visualizzati anche i campi delle superclassi della classe AEntity
     * Ad esempio: company della classe ACompanyEntity (se è previsto e se è un developer)
     *
     * @return lista di fields visibili nella Grid
     */
    @Test
    public void getListFields() {
        ottenutoList = service.getListFields();
        assertNotNull(ottenutoList);
        ottenutoIntero = ottenutoList.size();

        if (annotation.getAIList(ROLE_ENTITY_CLASS) != null) {
            previstoIntero = 2;
            assertEquals(previstoIntero, ottenutoIntero);

            previsto = FIELD_NAME_ORDINE;
            reflectionJavaField = ottenutoList.get(0);
            ottenuto = reflectionJavaField.getName();
            assertEquals(previsto, ottenuto);

            previsto = FIELD_NAME_CODE;
            reflectionJavaField = ottenutoList.get(1);
            ottenuto = reflectionJavaField.getName();
            assertEquals(previsto, ottenuto);
        } else {
            previstoIntero = 6;
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
        }// end of if/else cycle
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Lista di bottoni presenti nella toolbar (footer) della view AList
     * Legge la enumeration indicata nella @Annotation della AEntity
     *
     * @return lista (type) di bottoni visibili nella toolbar della view AList
     */
    @Test
    public void getListTypeButtons() {
        List<EATypeButton> ottenutoLista;
        EATypeButton[] matrice = new EATypeButton[]{EATypeButton.create, EATypeButton.edit, EATypeButton.delete, EATypeButton.search};
        List<EATypeButton> previstoLista = Arrays.asList(matrice);
        ottenutoLista = service.getListTypeButtons();
        assertEquals(previstoLista, ottenutoLista);
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Lista di bottoni presenti nella toolbar (footer) della view AForm
     * Legge la enumeration indicata nella @Annotation della AEntity
     *
     * @return lista (type) di bottoni visibili nella toolbar della view AForm
     */
    @Test
    public void getFormTypeButtons() {
        List<EATypeButton> ottenutoLista;
        EATypeButton[] matrice = new EATypeButton[]{EATypeButton.annulla, EATypeButton.revert, EATypeButton.registra};
        List<EATypeButton> previstoLista = Arrays.asList(matrice);
        ottenutoLista = service.getFormTypeButtons();
        assertEquals(previstoLista, ottenutoLista);
    }// end of method

}// end of class
