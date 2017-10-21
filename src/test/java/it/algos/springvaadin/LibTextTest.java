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

    private String sorgente = "";
    private String stringaUno = "";
    private String stringaDue = "";
    private String previsto = "";
    private String ottenuto = "";
    private boolean previstoBooleano;
    private boolean ottenutoBooleano;

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

    /**
     * Controlla la validità di un indirizzo email
     */
    @Test
    public void isEmail() {
        previstoBooleano = true;
        sorgente = "gac@algos.it";
        ottenutoBooleano = LibText.isValid(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "gacalgosit";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "gac@algosit";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "@algos.it";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);
    }// end of single test

    @Test
    public void isNumber() {
        previstoBooleano = true;
        sorgente = "27";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "0";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        previstoBooleano = false;
        sorgente = "dodici";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "37x65";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);
    }// end of single test

    @Test
    public void isNumberNotNull() {
        previstoBooleano = true;
        sorgente = "27";
        ottenutoBooleano = LibText.isNumberNotNull(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        previstoBooleano = false;
        sorgente = "dodici";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);

        sorgente = "0";
        ottenutoBooleano = LibText.isNumberNotNull(sorgente);
        assertEquals(ottenutoBooleano, previstoBooleano);
    }// end of single test

}// end of class
