package it.algos.springvaadin;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.service.AArrayService;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 10:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class AArrayServiceTest {


    private boolean previstoBooleano;
    private boolean ottenutoBooleano;


    private final static String[] stringArray = {"primo", "secondo", "quarto", "quinto", "1Ad", "terzo", "a10"};
    private final static List<String> stringList = LibArray.fromString(stringArray);
    private final static Object[] objArray = {new Label("Alfa"), new Button()};
    private final static List<Object> objList = Arrays.asList(objArray);
    private List<String> prevista;
    private List<String> ottenuta;


    @InjectMocks
    private AArrayService service;


    @InjectMocks
    private ATextService textService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(service);
        service.text = textService;
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Controlla la validità dell'array
     * Deve essitere (not null)
     * Deve avere degli elementi (size > 0)
     * Il primo elemento deve essere valido
     *
     * @param array (List) in ingresso da controllare
     *
     * @return vero se l'array soddisfa le condizioni previste
     */
    @Test
    public void isValid() {
        previstoBooleano = true;
        ottenutoBooleano = service.isValid(stringList);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        ottenutoBooleano = service.isValid((List) null);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        ottenutoBooleano = service.isValid(new ArrayList());
        assertEquals(previstoBooleano, ottenutoBooleano);

        String[] stringArray = {"", "secondo", "quarto", "quinto", "1Ad", "terzo", "a10"};
        List<String> lista = LibArray.fromString(stringArray);
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(lista);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = true;
        ottenutoBooleano = service.isValid(objList);
        assertEquals(previstoBooleano, ottenutoBooleano);

        Object[] objArray = {null, new Button()};
        List<Object> objList = Arrays.asList(objArray);
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(objList);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Controlla la validità dell'array
      * Deve essitere (not null)
      * Deve avere degli elementi (length > 0)
      * Il primo elemento deve essere una stringa valida
      *
      * @param array (String[]) in ingresso da controllare
      *
      * @return vero se l'array soddisfa le condizioni previste
      */
    @Test
    public void isValid2() {
        previstoBooleano = true;
        ottenutoBooleano = service.isValid(stringArray);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        ottenutoBooleano = service.isValid((String[]) null);
        assertEquals(previstoBooleano, ottenutoBooleano);

        String[] stringArray = {"", "secondo", "quarto", "quinto", "1Ad", "terzo", "a10"};
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(stringArray);
        assertEquals(previstoBooleano, ottenutoBooleano);

        String[] stringArray2 = {};
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(stringArray2);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


}// end of class
