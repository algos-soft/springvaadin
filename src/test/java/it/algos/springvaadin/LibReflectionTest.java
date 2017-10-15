package it.algos.springvaadin;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.entity.AEntity;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by gac on 05/07/17
 * .
 */
public class LibReflectionTest {


    private static Versione vers;
    private String sorgente = "";
    private String previsto = "";
    private String ottenuto = "";
    private Class<? extends AEntity> entityClazz = Versione.class;

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

//        vers.setId(id);
        vers.setOrdine(ordine);
//        vers.setTitolo(titolo);
        vers.setDescrizione(descrizione);
//        vers.setModifica(modifica);
    } // end of setup statico iniziale della classe


    /**
     * Fields dichiarati nella Entity
     *
     * @param entityClazz   da cui estrarre i fields
     * @param listaNomi     dei fields da considerare. Tutti, se listaNomi=null
     * @param addKeyID      flag per aggiungere (per primo) il field keyId
     * @param addKeyCompany flag per aggiungere (per secondo) il field keyCompany
     *
     * @return lista di fields da considerare per List e Form
     */
    @Test
    public void getFields() {
        List<Field> fieldsList;
        ArrayList<String> listaNomi = new ArrayList<>();
        listaNomi.add("ordine");
        listaNomi.add("descrizione");

        fieldsList = LibReflection.getFields(entityClazz);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 6);
        assertEquals(fieldsList.get(0).getName(), "id");
        assertEquals(fieldsList.get(1).getName(), "company");
        assertEquals(fieldsList.get(2).getName(), "ordine");
        assertEquals(fieldsList.get(3).getName(), "titolo");
        assertEquals(fieldsList.get(4).getName(), "descrizione");
        assertEquals(fieldsList.get(5).getName(), "modifica");

        fieldsList = LibReflection.getFields(entityClazz, false, false);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 4);
        assertEquals(fieldsList.get(0).getName(), "ordine");
        assertEquals(fieldsList.get(1).getName(), "titolo");
        assertEquals(fieldsList.get(2).getName(), "descrizione");
        assertEquals(fieldsList.get(3).getName(), "modifica");


        fieldsList = LibReflection.getFields(entityClazz, true, false);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 5);
        assertEquals(fieldsList.get(0).getName(), "id");
        assertEquals(fieldsList.get(1).getName(), "ordine");
        assertEquals(fieldsList.get(2).getName(), "titolo");
        assertEquals(fieldsList.get(3).getName(), "descrizione");
        assertEquals(fieldsList.get(4).getName(), "modifica");


        fieldsList = LibReflection.getFields(entityClazz, false, true);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 5);
        assertEquals(fieldsList.get(0).getName(), "company");
        assertEquals(fieldsList.get(1).getName(), "ordine");
        assertEquals(fieldsList.get(2).getName(), "titolo");
        assertEquals(fieldsList.get(3).getName(), "descrizione");
        assertEquals(fieldsList.get(4).getName(), "modifica");


        fieldsList = LibReflection.getFields(entityClazz, true, true);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 6);
        assertEquals(fieldsList.get(0).getName(), "id");
        assertEquals(fieldsList.get(1).getName(), "company");
        assertEquals(fieldsList.get(2).getName(), "ordine");
        assertEquals(fieldsList.get(3).getName(), "titolo");
        assertEquals(fieldsList.get(4).getName(), "descrizione");
        assertEquals(fieldsList.get(5).getName(), "modifica");


        fieldsList = LibReflection.getFields(entityClazz, listaNomi, false, false);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 2);
        assertEquals(fieldsList.get(0).getName(), "ordine");
        assertEquals(fieldsList.get(1).getName(), "descrizione");


        fieldsList = LibReflection.getFields(entityClazz, listaNomi, true, false);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 3);
        assertEquals(fieldsList.get(0).getName(), "id");
        assertEquals(fieldsList.get(1).getName(), "ordine");
        assertEquals(fieldsList.get(2).getName(), "descrizione");


        fieldsList = LibReflection.getFields(entityClazz, listaNomi, false, true);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 3);
        assertEquals(fieldsList.get(0).getName(), "company");
        assertEquals(fieldsList.get(1).getName(), "ordine");
        assertEquals(fieldsList.get(2).getName(), "descrizione");

        fieldsList = LibReflection.getFields(entityClazz, listaNomi, true, true);
        assertNotNull(fieldsList);
        assertEquals(fieldsList.size(), 4);
        assertEquals(fieldsList.get(0).getName(), "id");
        assertEquals(fieldsList.get(1).getName(), "company");
        assertEquals(fieldsList.get(2).getName(), "ordine");
        assertEquals(fieldsList.get(3).getName(), "descrizione");
    }// end of single test


    /**
     * All field names di una EntityClass
     *
     * @param entityClazz su cui operare la riflessione
     * @param useID       per comprendere anche il field key ID
     * @param useCompany  per comprendere anche il field company
     *
     * @return tutte i fieldNames, elencati in ordine di inserimento nella AEntity
     */
    @Test
    public void getAllFieldNames() {
        List<String> fieldNames;

        fieldNames = LibReflection.getAllFieldNames(entityClazz);
        assertNotNull(fieldNames);
        assertEquals(fieldNames.size(), 4);
        assertEquals(fieldNames.get(0), "ordine");
        assertEquals(fieldNames.get(1), "titolo");
        assertEquals(fieldNames.get(2), "descrizione");
        assertEquals(fieldNames.get(3), "modifica");


        fieldNames = LibReflection.getAllFieldNames(entityClazz, false, false);
        assertNotNull(fieldNames);
        assertEquals(fieldNames.size(), 4);
        assertEquals(fieldNames.get(0), "ordine");
        assertEquals(fieldNames.get(1), "titolo");
        assertEquals(fieldNames.get(2), "descrizione");
        assertEquals(fieldNames.get(3), "modifica");


        fieldNames = LibReflection.getAllFieldNames(entityClazz, true, false);
        assertNotNull(fieldNames);
        assertEquals(fieldNames.size(), 5);
        assertEquals(fieldNames.get(0), "id");
        assertEquals(fieldNames.get(1), "ordine");
        assertEquals(fieldNames.get(2), "titolo");
        assertEquals(fieldNames.get(3), "descrizione");
        assertEquals(fieldNames.get(4), "modifica");


        fieldNames = LibReflection.getAllFieldNames(entityClazz, false, true);
        assertNotNull(fieldNames);
        assertEquals(fieldNames.size(), 5);
        assertEquals(fieldNames.get(0), "company");
        assertEquals(fieldNames.get(1), "ordine");
        assertEquals(fieldNames.get(2), "titolo");
        assertEquals(fieldNames.get(3), "descrizione");
        assertEquals(fieldNames.get(4), "modifica");

        fieldNames = LibReflection.getAllFieldNames(entityClazz, true, true);
        assertNotNull(fieldNames);
        assertEquals(fieldNames.size(), 6);
        assertEquals(fieldNames.get(0), "id");
        assertEquals(fieldNames.get(1), "company");
        assertEquals(fieldNames.get(2), "ordine");
        assertEquals(fieldNames.get(3), "titolo");
        assertEquals(fieldNames.get(4), "descrizione");
        assertEquals(fieldNames.get(5), "modifica");
    }// end of single test


    /**
     * Field property di una EntityClass
     *
     * @param entityClazz     su cui operare la riflessione
     * @param publicFieldName property name
     */
    @Test
    public void getField() {
        Field field;
        String publicFieldName = "titolo";
        previsto = "titolo";

        field = LibReflection.getField(entityClazz, publicFieldName);
        assertNotNull(field);
        ottenuto = field.getName();
        assertEquals(ottenuto, previsto);
    }// end of single test



    /**
     * Properties di una entity. Tutte.
     * Compresa la entity
     * Compresa la superclasse AEntity
     *
     * @param entity da esaminare
     *
     * @return tutte le properties, anche della superclasse, elencate in ordine alfabetico
     */
    @Test
    public void getAllPropertyNames() {
       List risultato;

        risultato = LibReflection.getAllPropertyNames(vers);
        assert risultato != null;
        assertEquals(risultato.size(), 9);
    }// end of single test

    /**
     * Properties di una entity. Solo i campi dichiarati.
     * Compresa la entity
     * Compresa la superclasse AEntity
     * Escluso 'callbacks'
     * Escluso 'class'
     *
     * @param entity da esaminare
     *
     * @return properties, elencate in ordine alfabetico
     */
    @Test
    public void getPropertyNames() {
        List risultato;

        risultato = LibReflection.getPropertyNames(vers);
        assert risultato != null;
        assertEquals(risultato.size(), 7);
    }// end of single test



    /**
     * Metodo getter di una classe entity.
     *
     * @param entity       da esaminare
     * @param propertyName per estrarre il metodo
     *
     * @return method pubblico
     */
    @Test
    public void getMethod() {
        Method metodoPrevisto = null;
        Method metodoOttenuto = null;
        String metodoPrevistoName = "";
        Class metodoPrevistoTipo = null;
        String metodoOttenutoName = "";
        Class metodoOttenutoTipo = null;

        metodoPrevistoName = "getDescrizione";
        metodoPrevistoTipo = String.class;
        metodoOttenuto = LibReflection.getMethod(vers, "descrizione");
        assert metodoOttenuto != null;
        metodoOttenutoName = metodoOttenuto.getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);
        metodoOttenutoTipo = metodoOttenuto.getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);

        metodoPrevistoName = "getModifica";
        metodoPrevistoTipo = LocalDateTime.class;
        metodoOttenuto = LibReflection.getMethod(vers, "modifica");
        assert metodoOttenuto != null;
        metodoOttenutoName = metodoOttenuto.getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);
        metodoOttenutoTipo = metodoOttenuto.getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);
    }// end of single test


    /**
     * Metodo getter di una classe entity.
     *
     * @param entity       da esaminare
     * @param propertyName per estrarre il metodo
     *
     * @return method pubblico
     */
    @Test
    public void getMethod2() {
        Method metodoPrevisto = null;
        Method metodoOttenuto = null;
        String metodoPrevistoName = "";
        Class metodoPrevistoTipo = null;
        String metodoOttenutoName = "";
        Class metodoOttenutoTipo = null;

        metodoPrevistoName = "getDescrizione";
        metodoPrevistoTipo = String.class;
        metodoOttenuto = LibReflection.getMethod(entityClazz, "descrizione");
        assert metodoOttenuto != null;
        metodoOttenutoName = metodoOttenuto.getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);
        metodoOttenutoTipo = metodoOttenuto.getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);

        metodoPrevistoName = "getModifica";
        metodoPrevistoTipo = LocalDateTime.class;
        metodoOttenuto = LibReflection.getMethod(entityClazz, "modifica");
        assert metodoOttenuto != null;
        metodoOttenutoName = metodoOttenuto.getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);
        metodoOttenutoTipo = metodoOttenuto.getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);
    }// end of single test

    /**
     * Metodi getter di una classe entity.
     *
     * @param entity da esaminare
     *
     * @return tutti i metodi getter, in ordine alfabetico
     */
//    @Test
    public void getMethods2() {
        List<Method> listaMetodiOttenuta;
        String metodoPrevistoName = "getOrdine";
        String metodoOttenutoName = "";
        Class metodoPrevistoTipo = LocalDateTime.class;
        Class metodoOttenutoTipo = null;

        listaMetodiOttenuta = LibReflection.getMethods(vers);
        assert listaMetodiOttenuta != null;
        assertEquals(listaMetodiOttenuta.size(), 4);

        metodoOttenutoName = listaMetodiOttenuta.get(2).getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);

        metodoOttenutoTipo = listaMetodiOttenuta.get(1).getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);
    }// end of single test


    /**
     * Metodi getter di una classe entity.
     *
     * @param entityClazz su cui operare la riflessione
     *
     * @return tutti i metodi getter,  elencati in ordine di inserimento nella AEntity
     */
//    @Test
    public void getMethods() {
        List<Method> listaMetodiOttenuta;
        String metodoPrevistoName = "getOrdine";
        String metodoOttenutoName = "";
        Class metodoPrevistoTipo = LocalDateTime.class;
        Class metodoOttenutoTipo = null;

        listaMetodiOttenuta = LibReflection.getMethods(Versione.class);
        assert listaMetodiOttenuta != null;
        assertEquals(listaMetodiOttenuta.size(), 5);

        metodoOttenutoName = listaMetodiOttenuta.get(1).getName();
        assertEquals(metodoOttenutoName, metodoPrevistoName);

        metodoOttenutoTipo = listaMetodiOttenuta.get(4).getReturnType();
        assertEquals(metodoOttenutoTipo, metodoPrevistoTipo);
    }// end of single test


}// end of test class
