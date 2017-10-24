package it.algos.springvaadin;

import it.algos.springvaadin.entity.preferenza.PrefType;
import it.algos.springvaadin.lib.LibByte;
import it.algos.springvaadin.lib.LibText;
import org.junit.Test;

import java.nio.charset.Charset;

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
        assertEquals(previsto, ottenuto);

        sorgente = " mario rossi";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "(mario rossi)";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "(mario rossi";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(previsto, ottenuto);

        sorgente = "mario rossi)";
        ottenuto = LibText.setTonde(sorgente);
        assertEquals(previsto, ottenuto);
    }// end of single test

    /**
     * Controlla la validità di un indirizzo email
     */
    @Test
    public void isEmail() {
        previstoBooleano = true;
        sorgente = "gac@algos.it";
        ottenutoBooleano = LibText.isValid(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "gacalgosit";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "gac@algosit";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "@algos.it";
        ottenutoBooleano = LibText.isWrongEmail(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test

    @Test
    public void isNumber() {
        previstoBooleano = true;
        sorgente = "27";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "0";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        sorgente = "dodici";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "37x65";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test

    @Test
    public void isNumberNotNull() {
        previstoBooleano = true;
        sorgente = "27";
        ottenutoBooleano = LibText.isNumberNotNull(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        previstoBooleano = false;
        sorgente = "dodici";
        ottenutoBooleano = LibText.isNumber(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);

        sorgente = "0";
        ottenutoBooleano = LibText.isNumberNotNull(sorgente);
        assertEquals(previstoBooleano, ottenutoBooleano);
    }// end of single test


    @Test
    public void getDescrizione() {
        String stringaVuota = "";
        String stringaUno = "uno";
        String stringaDue = "due";
        Object objUno = new Integer(37);
        Object objDue = new Integer(4);
        boolean boolUno = true;
        boolean boolDue = false;
        byte[] byteUno = PrefType.string.objectToBytes(stringaUno);
        byte[] byteDue = PrefType.string.objectToBytes(stringaDue);
        byte[] byteUnoInt = PrefType.integer.objectToBytes(37);
        byte[] byteDueInt = PrefType.integer.objectToBytes(4);
        byte[] byteBoolUno = PrefType.bool.objectToBytes(true);
        byte[] byteBoolDue = PrefType.bool.objectToBytes(false);

        previsto = " modificato da uno a due";
        ottenuto = LibText.getDescrizione(stringaUno, stringaDue);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da due a uno";
        ottenuto = LibText.getDescrizione(stringaDue, stringaUno);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(stringaUno, stringaUno);
        assertEquals(previsto, ottenuto);

        previsto = " cancellato uno";
        ottenuto = LibText.getDescrizione(stringaUno, stringaVuota);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(stringaVuota, stringaVuota);
        assertEquals(previsto, ottenuto);

        previsto = " aggiunto due";
        ottenuto = LibText.getDescrizione(stringaVuota, stringaDue);
        assertEquals(previsto, ottenuto);

        previsto = " aggiunto uno";
        ottenuto = LibText.getDescrizione(null, stringaUno);
        assertEquals(previsto, ottenuto);

        previsto = " cancellato uno";
        ottenuto = LibText.getDescrizione(stringaUno, null);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(null, null);
        assertEquals(previsto, ottenuto);

        previsto = " cancellato 37";
        ottenuto = LibText.getDescrizione(objUno, null);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(objUno, stringaUno);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da 37 a 4";
        ottenuto = LibText.getDescrizione(objUno, objDue);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(objUno, objUno);
        assertEquals(previsto, ottenuto);

        previsto = " cancellato 37";
        ottenuto = LibText.getDescrizione(objUno, null);
        assertEquals(previsto, ottenuto);

        previsto = " aggiunto 4";
        ottenuto = LibText.getDescrizione(null, objDue);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(boolUno, objUno);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(boolUno, boolUno);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da true a false";
        ottenuto = LibText.getDescrizione(boolUno, boolDue);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(byteUno, null);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(null, byteDue);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da uno a due";
        ottenuto = LibText.getDescrizione(byteUno, byteDue);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da uno a due";
        ottenuto = LibText.getDescrizione(byteUno, byteDue, PrefType.string);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(byteUnoInt, byteUnoInt, PrefType.integer);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da 37 a 4";
        ottenuto = LibText.getDescrizione(byteUnoInt, byteDueInt, PrefType.integer);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(byteBoolUno, byteBoolDue, PrefType.integer);
        assertEquals(previsto, ottenuto);

        previsto = " modificato da true a false";
        ottenuto = LibText.getDescrizione(byteBoolUno, byteBoolDue, PrefType.bool);
        assertEquals(previsto, ottenuto);

        previsto = "";
        ottenuto = LibText.getDescrizione(byteBoolUno, byteBoolUno, PrefType.bool);
        assertEquals(previsto, ottenuto);

    }// end of single test

}// end of class
