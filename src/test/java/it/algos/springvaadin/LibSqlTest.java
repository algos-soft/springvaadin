package it.algos.springvaadin;

import it.algos.springvaadin.lib.LibSql;
import it.algos.springvaadin.lib.LibText;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gac on 04/07/17
 * .
 */
public class LibSqlTest {

    protected String sorgente = "";
    protected String previsto = "";
    protected String ottenuto = "";
    protected String stringaUno = "";
    protected String stringaDue = "";
    protected int numSorgente = 0;
    protected int numPrevisto = 0;
    protected int numOttenuto = 0;

    /**
     * Ripete n volte una stringa, con un separatore
     *
     * @param stringaBase    da ripetere
     * @param separatore     tra le ripetizioni
     * @param numRipetizioni della stringa base
     *
     * @return stringaBase ripetuta n volte, con n-1 separatori
     */
    @Test
    public void repeat() {
        String stringaBase = "?";
        String separatore = ",";
        int numRipetizioni = 4;
        previsto = "?,?,?,?";

        ottenuto = LibSql.repeat(stringaBase, separatore, numRipetizioni);
        assertEquals(ottenuto, previsto);
    }// end of single test

    /**
     * Ripete n volte una stringaBase='?', con un separatore=','
     * Aggiunge il tag VALUES
     * Inserisce il risultato dentro due parentesi tonde
     *
     * @param numRipetizioni del tag base
     *
     * @return stringaBase ripetuta n volte, con n-1 separatori; inserita dentro due parentesi tonde
     */
    @Test
    public void repeatValue() {
        int numRipetizioni = 4;
        previsto = "VALUES (?,?,?,?)";

        ottenuto = LibSql.repeatValue(numRipetizioni);
        assertEquals(ottenuto, previsto);
    }// end of single test


    /**
     * Get the query string for INSERT
     *
     * @param tableName   in cui inserire la nuova entity
     * @param columnsName delle property
     */
    @Test
    public void getQueryInsert() {
        String tableName = "versione";
        String[] columns;

        columns = new String[]{"ordine"};
        previsto = "INSERT INTO versione (ordine) VALUES (?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo"};
        previsto = "INSERT INTO versione (ordine, titolo) VALUES (?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo", "descrizione"};
        previsto = "INSERT INTO versione (ordine, titolo, descrizione) VALUES (?,?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo", "descrizione", "modifica"};
        previsto = "INSERT INTO versione (ordine, titolo, descrizione, modifica) VALUES (?,?,?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);
    }// end of single test

}// end of class
