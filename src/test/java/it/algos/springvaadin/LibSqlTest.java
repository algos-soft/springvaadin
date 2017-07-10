package it.algos.springvaadin;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.LibSql;
import it.algos.springvaadin.lib.LibText;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    private static Versione vers;

    /**
     * SetUp iniziale eseguito solo una volta alla creazione della classe
     */
    @BeforeClass
    public static void setUpInizialeStaticoEseguitoSoloUnaVoltaAllaCreazioneDellaClasse() {
        vers = mock(Versione.class);
        long id = 27;
        int ordine = 45;
        String titolo = "Prova";
        String descrizione = "Testo lungo";
        LocalDateTime modifica = LocalDateTime.now();

        vers.setId(id);
        vers.setOrdine(ordine);
        vers.setTitolo(titolo);
        vers.setDescrizione(descrizione);
        vers.setModifica(modifica);
    } // end of setup statico iniziale della classe

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
     * Get the query string for COUNT
     *
     * @param tableName da conteggiare
     *
     * @return stringa della query
     */
    @Test
    public void getQueryCount() {
        String tableName = "versione";

        previsto = "SELECT COUNT(*) FROM versione";
        ottenuto = LibSql.getQueryCount(tableName);
        assertEquals(ottenuto, previsto);
    }// end of single test


    /**
     * Get the query string for SELECT ALL entities
     *
     * @param tableName da cui estrarre le entities
     *
     * @return stringa della query
     */
    @Test
    public void getQueryFindAll() {
        String tableName = "versione";

        previsto = "SELECT * FROM versione";
        ottenuto = LibSql.getQueryFindAll(tableName);
        assertEquals(ottenuto, previsto);
    }// end of static method


    /**
     * Get the query string for SELECT one entity
     *
     * @param tableName da cui estrarre la entity
     * @param id        key della entity da recuperare
     *
     * @return stringa della query
     */
    @Test
    public void getQueryFindOne() {
        String tableName = "versione";

        previsto = "SELECT * FROM versione WHERE id=?";
        ottenuto = LibSql.getQueryFindOne(tableName);
        assertEquals(ottenuto, previsto);
    }// end of single test


    /**
     * Get the query string for INSERT
     *
     * @param tableName   in cui inserire la nuova entity
     * @param columnsName delle property
     *
     * @return stringa della query
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

    /**
     * Get the query string for INSERT
     *
     * @param tableName   in cui inserire la nuova entity
     * @param columnsName delle property
     *
     * @return stringa della query
     */
    @Test
    public void getQueryInsert2() {
        String tableName = "versione";
        ArrayList<String> columns = new ArrayList();

        columns.add("ordine");
        previsto = "INSERT INTO versione (ordine) VALUES (?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("titolo");
        previsto = "INSERT INTO versione (ordine, titolo) VALUES (?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("descrizione");
        previsto = "INSERT INTO versione (ordine, titolo, descrizione) VALUES (?,?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("modifica");
        previsto = "INSERT INTO versione (ordine, titolo, descrizione, modifica) VALUES (?,?,?,?)";
        ottenuto = LibSql.getQueryInsert(tableName, columns);
        assertEquals(ottenuto, previsto);
    }// end of single test

    /**
     * Get the query string for UPDATE
     *
     * @param tableName   in cui modificare la entity
     * @param columnsName delle property
     *
     * @return stringa della query
     */
    @Test
    public void getQueryUpdate() {
        String tableName = "versione";
        String[] columns;

        columns = new String[]{"ordine"};
        previsto = "UPDATE versione SET ordine=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo"};
        previsto = "UPDATE versione SET ordine=?, titolo=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo", "descrizione"};
        previsto = "UPDATE versione SET ordine=?, titolo=?, descrizione=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns = new String[]{"ordine", "titolo", "descrizione", "modifica"};
        previsto = "UPDATE versione SET ordine=?, titolo=?, descrizione=?, modifica=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);
    }// end of single test

    /**
     * Get the query string for UPDATE
     *
     * @param tableName   in cui modificare la entity
     * @param columnsName delle property
     *
     * @return stringa della query
     */
    @Test
    public void getQueryUpdate2() {
        String tableName = "versione";
        ArrayList<String> columns = new ArrayList();

        columns.add("ordine");
        previsto = "UPDATE versione SET ordine=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("titolo");
        previsto = "UPDATE versione SET ordine=?, titolo=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("descrizione");
        previsto = "UPDATE versione SET ordine=?, titolo=?, descrizione=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);

        columns.add("modifica");
        previsto = "UPDATE versione SET ordine=?, titolo=?, descrizione=?, modifica=? WHERE id=?";
        ottenuto = LibSql.getQueryUpdate(tableName, columns);
        assertEquals(ottenuto, previsto);
    }// end of single test


//    @Test
//    public void getQueryUpdate3() {
//        String tableName = "versione";
//
//        previsto = "UPDATE versione SET descrizione=?, modifica=?, ordine=?, titolo=? WHERE id=?";
//        ottenuto = LibSql.getQueryUpdate(tableName,vers);
//        assertEquals(ottenuto, previsto);
//    }// end of single test


    /**
     * Get the query string for DELETE
     *
     * @param tableName in cui cancellare la entity
     *
     * @return stringa della query
     */
    @Test
    public void getQueryDelete() {
        String tableName = "versione";

        previsto = "DELETE FROM versione WHERE id=?";
        ottenuto = LibSql.getQueryDelete(tableName);
        assertEquals(ottenuto, previsto);
    }// end of single test


    /**
     * Get the max value for propertyName (numeric)
     *
     * @param tableName    da esaminare
     * @param propertyName da esaminare
     *
     * @return stringa della query
     */
    @Test
    public void getQueryMax() {
        String tableName = "versione";
        String propertyName = "ordine";

        previsto = "SELECT * FROM versione ORDER BY ordine DESC LIMIT 1";
        ottenuto = LibSql.getQueryMax(tableName, propertyName);
        assertEquals(ottenuto, previsto);
    }// end of single test


}// end of class
