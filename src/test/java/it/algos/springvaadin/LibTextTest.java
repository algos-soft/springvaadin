package it.algos.springvaadin;

import it.algos.springvaadin.lib.LibText;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by gac on 04/07/17
 * .
 */
public class LibTextTest {

    protected String sorgente = "";
    protected String stringaUno = "";
    protected String stringaDue = "";
    protected String previsto = "";
    protected String ottenuto = "";

    /**
     * Aggiunge parentesi all'inizio ed alla fine della stringa.
     * Aggiunge SOLO se gia non esiste
     * Elimina spazi vuoti iniziali e finali
     *
     * @param stringaIn in ingresso
     *
     * @return stringa con tag aggiunti
     */
    @Test
    public void setTonde() {
        previsto = "(mario rossi)";

        sorgente = "mario rossi";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(ottenuto, previsto);

        sorgente = " mario rossi";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(ottenuto, previsto);

        sorgente = "(mario rossi)";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(ottenuto, previsto);

        sorgente = "(mario rossi";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(ottenuto, previsto);

        sorgente = "mario rossi)";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(ottenuto, previsto);
    }// end of single test


}// end of class
