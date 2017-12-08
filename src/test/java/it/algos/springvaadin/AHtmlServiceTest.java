package it.algos.springvaadin;

import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import it.algos.springvaadintest.application.SpringvaadinApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 13:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class AHtmlServiceTest {


    private String sorgente = "";
    private String previsto = "";
    private String ottenuto = "";
    private String tag = "";


    @InjectMocks
    private AHtmlService service;


    @InjectMocks
    private ATextService text;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(service);
        service.text = text;
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Carattere rosso e bold
     *
     * @param testoIn ingresso da 'incapsulare' nei tags
     *
     * @return testo composto; null se testoIn è vuoto/null
     */
    @Test
    public void setRossoBold() {
        sorgente = "Modifica scheda";
        previsto = "<strong style=\"color: red;\">" + sorgente + "</strong>";
        ottenuto = service.setRossoBold(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.setRossoBold(sorgente);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = service.setRossoBold(null);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Carattere verde e bold
      *
      * @param testoIn ingresso da 'incapsulare' nei tags
      *
      * @return testo composto; null se testoIn è vuoto/null
      */
    @Test
    public void setVerdeBold() {
        sorgente = "Modifica scheda";
        previsto = "<strong style=\"color: green;\">" + sorgente + "</strong>";
        ottenuto = service.setVerdeBold(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.setVerdeBold(sorgente);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = service.setVerdeBold(null);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Carattere blu e bold
     *
     * @param testoIn ingresso da 'incapsulare' nei tags
     *
     * @return testo composto; null se testoIn è vuoto/null
     */
    @Test
    public void setBluBold() {
        sorgente = "Modifica scheda";
        previsto = "<strong style=\"color: blue;\">" + sorgente + "</strong>";
        ottenuto = service.setBluBold(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.setBluBold(sorgente);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = service.setBluBold(null);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Carattere  bold
     *
     * @param testoIn ingresso da 'incapsulare' nei tags
     *
     * @return testo composto
     */
    @Test
    public void setBold() {
        sorgente = "Modifica scheda";
        previsto = "<strong>" + sorgente + "</strong>";
        ottenuto = service.setBold(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.setBold(sorgente);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = service.setBold(null);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina la parte di testo successiva al tag indicato (se esiste).
     * <p>
     * Elimina a partire dalla fine del tag
     * Esegue solo se il testo è valido
     * Se manca il tag, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     * @param tag     finale dopo il quale eliminare
     *
     * @return testo troncato
     */
    @Test
    public void levaDopo() {
        sorgente = "Elimina dopo il. Questo non lo vedo più";
        tag = ".";
        previsto = "Elimina dopo il.";
        ottenuto = service.levaDopo(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il punto Questo non lo vedo più";
        tag = "punto";
        previsto = "Elimina dopo il punto";
        ottenuto = service.levaDopo(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        tag = "punto";
        previsto = "";
        ottenuto = service.levaDopo(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il punto Questo non lo vedo più";
        tag = "";
        previsto = "Elimina dopo il punto Questo non lo vedo più";
        ottenuto = service.levaDopo(sorgente, tag);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina la parte di testo successiva al tag <ref> (se esiste).
     * <p>
     * Elimina a partire dall'inizio del tag
     * Esegue solo se il testo è valido
     * Se manca il tag, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     *
     * @return testo troncato
     */
    @Test
    public void levaDopoRef() {
        tag = service.ref;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoRef(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoRef(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoRef(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Elimina la parte di testo successiva al tag <!-- (se esiste).
      * <p>
      * Elimina a partire dall'inizio del tag
      * Esegue solo se il testo è valido
      * Se manca il tag, restituisce il testo
      * Elimina spazi vuoti iniziali e finali
      *
      * @param testoIn ingresso
      *
      * @return testo troncato
      */
    @Test
    public void levaDopoNote() {
        tag = service.note;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoNote(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoNote(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoNote(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Elimina la parte di testo successiva al tag {{ (se esiste).
      * <p>
      * Elimina a partire dall'inizio del tag
      * Esegue solo se il testo è valido
      * Se manca il tag, restituisce il testo
      * Elimina spazi vuoti iniziali e finali
      *
      * @param testoIn ingresso
      *
      * @return testo troncato
      */
    @Test
    public void levaDopoGraffe() {
        tag = service.graffe;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoGraffe(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoGraffe(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoGraffe(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina la parte di testo successiva al tag -virgola- (se esiste).
     * <p>
     * Elimina a partire dall'inizio del tag
     * Esegue solo se il testo è valido
     * Se manca il tag, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     *
     * @return testo troncato
     */
    @Test
    public void levaDopoVirgola() {
        tag = service.virgola;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoVirgola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoVirgola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoVirgola(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Elimina la parte di testo successiva al tag -aperta parentesi- (se esiste).
      * <p>
      * Elimina a partire dall'inizio del tag
      * Esegue solo se il testo è valido
      * Se manca il tag, restituisce il testo
      * Elimina spazi vuoti iniziali e finali
      *
      * @param testoIn ingresso
      *
      * @return testo troncato
      */
    @Test
    public void levaDopoParentesi() {
        tag = service.parentesi;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoParentesi(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoParentesi(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoParentesi(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina la parte di testo successiva al tag -punto interrogativo- (se esiste).
     * <p>
     * Elimina a partire dall'inizio del tag
     * Esegue solo se il testo è valido
     * Se manca il tag, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso
     *
     * @return testo troncato
     */
    @Test
    public void levaDopoInterrogativo() {
        tag = service.interrogativo;

        sorgente = "Elimina dopo il " + tag + " Questo non lo vedo più";
        previsto = "Elimina dopo il";
        ottenuto = service.levaDopoInterrogativo(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "Elimina dopo il tag Questo non lo vedo più";
        previsto = "Elimina dopo il tag Questo non lo vedo più";
        ottenuto = service.levaDopoInterrogativo(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "";
        previsto = "";
        ottenuto = service.levaDopoInterrogativo(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Elimina dal testo la stringa indicata.
      * <p>
      * Esegue solo se il testo è valido
      * Se la stringa non è valida, restituisce il testo
      *
      * @param testoIn    in ingresso
      * @param subStringa da eliminare
      *
      * @return testo ridotto
      */
    @Test
    public void levaTesto() {
        tag = "pippoz";

        sorgente = "Elimina " + tag + "tutte " + tag + "volte che lo " + tag + "trova";
        previsto = "Elimina tutte volte che lo trova";
        ottenuto = service.levaTesto(sorgente, tag);
        assertEquals(previsto, ottenuto);
    }// end of single test


}// end of test class
