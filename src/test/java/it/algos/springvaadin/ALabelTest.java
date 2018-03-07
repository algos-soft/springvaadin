package it.algos.springvaadin;

import com.vaadin.ui.Label;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.label.ALabel;
import it.algos.springvaadin.label.ALabelBold;
import it.algos.springvaadin.label.ALabelRosso;
import it.algos.springvaadin.label.ALabelRossoBold;
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 28-feb-2018
 * Time: 18:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ALabelTest extends ATest {

    @InjectMocks
    private AHtmlService html;

    @InjectMocks
    private ATextService text;

    private final static String TEXT_BASE = "Label base con html";
    private final static String TEXT_ROSSO = "Label rosso normale";
    private final static String TEXT_BOLD = "Label bold";
    private final static String HTML_BOLD = "<strong>Label bold</strong>";
    private final static String TEXT_ROSSO_BOLD = "Label rosso bold";
    private final static String HTML_ROSSO_BOLD = "<strong>Label rosso bold</strong>";

    private ALabel label;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(html);
        MockitoAnnotations.initMocks(text);
        html.text = text;
    }// end of method


    @Test
    public void aLabel() {
        previsto = TEXT_BASE;
        label = new ALabel(previsto);
        assertNotNull(label);
        assertTrue(label instanceof Label);
        ottenuto = label.toString();
        assertEquals(previsto, ottenuto);
        System.out.println("Label: " + ottenuto);
    }// end of method


    @Test
    public void aLabelRosso() {
        previsto = TEXT_ROSSO;
        label = new ALabelRosso(previsto);
        assertNotNull(label);
        assertTrue(label instanceof Label);
        ottenuto = label.toString();
        assertEquals(previsto, ottenuto);
        System.out.println("Label rosso: " + ottenuto);
    }// end of method


    @Test
    public void aLabelBold() {
        previsto = HTML_BOLD;
        label = new ALabelBold( TEXT_BOLD);
        assertNotNull(label);
        assertTrue(label instanceof Label);
        ottenuto = label.toString();
        assertEquals(previsto, ottenuto);
        System.out.println("Label bold: " + ottenuto);
    }// end of method


//    @Test
//    public void aLabelRossoBold() {
//        previsto = HTML_ROSSO_BOLD;
//        label = new ALabelRossoBold(html, TEXT_ROSSO_BOLD);
//        assertNotNull(label);
//        assertTrue(label instanceof Label);
//        ottenuto = label.toString();
//        assertEquals(previsto, ottenuto);
//        System.out.println("Label rosso bold: " + ottenuto);
//    }// end of method


}// end of class
