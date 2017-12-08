package it.algos.springvaadin;

import com.vaadin.ui.Label;
import it.algos.springvaadin.enumeration.EAFirstChar;
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

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 14:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ATextServiceTest {


    private String sorgente = "";
    private String previsto = "";
    private String ottenuto = "";
    private boolean previstoBooleano;
    private boolean ottenutoBooleano;
    private String tag = "";
    private String oldTag = "";
    private String newTag = "";


    @InjectMocks
    private ATextService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(service);
    }// end of method


    @SuppressWarnings("javadoc")
    /**
     * Null-safe, short-circuit evaluation.
     *
     * @param stringa in ingresso da controllare
     *
     * @return vero se la stringa è vuota o nulla
     */
    @Test
    public void isEmpty() {
        sorgente = "Modifica scheda";
        previstoBooleano = false;
        ottenutoBooleano = service.isEmpty(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "";
        previstoBooleano = true;
        ottenutoBooleano = service.isEmpty(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = true;
        ottenutoBooleano = service.isEmpty(null);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Null-safe, short-circuit evaluation.
     *
     * @param stringa in ingresso da controllare
     *
     * @return vero se la stringa esiste è non è vuota
     */
    @Test
    public void isValid() {
        sorgente = "Modifica scheda";
        previstoBooleano = true;
        ottenutoBooleano = service.isValid(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "";
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        ottenutoBooleano = service.isValid(null);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Controlla che sia una stringa e che sia valida.
      *
      * @param obj in ingresso da controllare
      *
      * @return vero se la stringa esiste è non è vuota
      */
    @Test
    public void isValidObj() {
        Object obj = new Label();
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(obj);
        assertEquals(previstoBooleano, ottenutoBooleano);

        Object objString = "";
        previstoBooleano = false;
        ottenutoBooleano = service.isValid(objString);
        assertEquals(previstoBooleano, ottenutoBooleano);

        Object objStringFull = "a";
        previstoBooleano = true;
        ottenutoBooleano = service.isValid(objStringFull);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
      * Forza il primo carattere della stringa al carattere maiuscolo
      * <p>
      * Se la stringa è nulla, ritorna un nullo
      * Se la stringa è vuota, ritorna una stringa vuota
      * Elimina spazi vuoti iniziali e finali
      *
      * @param testo in ingresso
      *
      * @return test formattato in uscita
      */
    @Test
    public void primaMaiuscola() {
        sorgente = "TUTTO MAIUSCOLO ";
        previsto = "TUTTO MAIUSCOLO";
        ottenuto = service.primaMaiuscola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = " tutto minuscolo";
        previsto = "Tutto minuscolo";
        ottenuto = service.primaMaiuscola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = " afRodiSiacHo ";
        previsto = "AfRodiSiacHo";
        ottenuto = service.primaMaiuscola(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Forza il primo carattere della stringa al carattere minuscolo
     * <p>
     * Se la stringa è nulla, ritorna un nullo
     * Se la stringa è vuota, ritorna una stringa vuota
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testo in ingresso
     *
     * @return test formattato in uscita
     */
    @Test
    public void primaMinuscola() {
        sorgente = "tutto minuscolo ";
        previsto = "tutto minuscolo";
        ottenuto = service.primaMinuscola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = " TUTTO MAIUSCOLO";
        previsto = "tUTTO MAIUSCOLO";
        ottenuto = service.primaMinuscola(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = " AfRodiSiacHo ";
        previsto = "afRodiSiacHo";
        ottenuto = service.primaMinuscola(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina dal testo il tagIniziale, se esiste
     * <p>
     * Esegue solo se il testo è valido
     * Se tagIniziale è vuoto, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn     ingresso
     * @param tagIniziale da eliminare
     *
     * @return test ridotto in uscita
     */
    @Test
    public void levaTesta() {
        sorgente = "Non Levare questo inizio ";
        tag = "Non";
        previsto = "Levare questo inizio";
        ottenuto = service.levaTesta(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Non Levare questo inizio ";
        tag = "";
        previsto = "Non Levare questo inizio";
        ottenuto = service.levaTesta(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Non Levare questo inizio ";
        tag = "NonEsisteQuestoTag";
        previsto = "Non Levare questo inizio";
        ottenuto = service.levaTesta(sorgente, tag);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Elimina dal testo il tagFinale, se esiste
     * <p>
     * Esegue solo se il testo è valido
     * Se tagIniziale è vuoto, restituisce il testo
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn   ingresso
     * @param tagFinale da eliminare
     *
     * @return test ridotto in uscita
     */
    @Test
    public void levaCoda() {
        sorgente = " Levare questa fine Non ";
        tag = "Non";
        previsto = "Levare questa fine";
        ottenuto = service.levaCoda(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Non Levare questa fine ";
        tag = "";
        previsto = "Non Levare questa fine";
        ottenuto = service.levaCoda(sorgente, tag);
        assertEquals(previsto, ottenuto);

        sorgente = "Non Levare questa fine ";
        tag = "NonEsisteQuestoTag";
        previsto = "Non Levare questa fine";
        ottenuto = service.levaCoda(sorgente, tag);
        assertEquals(previsto, ottenuto);
    }// end of single test


    @SuppressWarnings("javadoc")
    /**
     * Sostituisce nel testo tutte le occorrenze di oldTag con newTag.
     * Esegue solo se il testo è valido
     * Esegue solo se il oldTag è valido
     * newTag può essere vuoto (per cancellare le occorrenze di oldTag)
     * Elimina spazi vuoti iniziali e finali
     *
     * @param testoIn ingresso da elaborare
     * @param oldTag  da sostituire
     * @param newTag  da inserire
     *
     * @return testo modificato
     */
    @Test
    public void sostituisce() {
        sorgente = "Devo sostituire tutte le t con p";
        oldTag = "t";
        newTag = "p";
        previsto = "Devo sospipuire puppe le p con p";
        ottenuto = service.sostituisce(sorgente, oldTag, newTag);
        assertEquals(previsto, ottenuto);

        sorgente = "Devo sostituire oldTagtutte le oldTag con newTag";
        oldTag = "oldTag";
        newTag = "newTag";
        previsto = "Devo sostituire newTagtutte le newTag con newTag";
        ottenuto = service.sostituisce(sorgente, oldTag, newTag);
        assertEquals(previsto, ottenuto);

        sorgente = "Devo oldTag cancoldTagellare tutte le oldTag";
        oldTag = "oldTag";
        newTag = "";
        previsto = "Devo  cancellare tutte le";
        ottenuto = service.sostituisce(sorgente, oldTag, newTag);
        assertEquals(previsto, ottenuto);
    }// end of single test


}// end of class
