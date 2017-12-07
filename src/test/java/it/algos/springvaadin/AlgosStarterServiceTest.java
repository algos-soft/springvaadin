package it.algos.springvaadin;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 27-set-2017
 * Time: 20:13
 */
@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
public class AlgosStarterServiceTest {

//    @InjectMocks
//    private AlgosStartService service;
//
//    /**
//     * Recupera i parametri in ingresso.
//     * 1) /demo
//     * 2) /demo=
//     * 3) /company=demo
//     * 4) /?company=demo
//     * 5) /?company=demo&buttonUser=gac
//     * 6) http://localhost:8090?demo
//     */
//    @Test
//    public void getParams() {
//        String uriSorgente;
//        Map<String, String> queryParamsOttenuti;
//
//        uriSorgente = "https://stackoverflow.com?param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//
//        uriSorgente = "http://localhost:8090?param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//
//        uriSorgente = "?param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//
//        uriSorgente = "param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//
//        uriSorgente = "/demo";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 1);
//        assertTrue(queryParamsOttenuti.containsKey("demo"));
//        assertEquals(queryParamsOttenuti.get("demo"), null);
//
//        uriSorgente = "http://localhost:8090?demo";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 1);
//        assertTrue(queryParamsOttenuti.containsKey("demo"));
//        assertEquals(queryParamsOttenuti.get("demo"), null);
//
//        uriSorgente = "/company=demo";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 1);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//
//        uriSorgente = "/?company=demo";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 1);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//
//        uriSorgente = "/?company=demo&buttonUser=gac";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//
//        uriSorgente = "/?buttonUser=gac&company=demo";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//
//        uriSorgente = "pippoz&v-1506692572130";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//    }// end of single test
//
//
//    /**
//     * Recupera la sigla della company come parametro in ingresso.
//     * Il nome della company, contenuto nell'URI, è recuperato se presente nella forma:
//     * 1) /demo
//     * 2) /demo=
//     * 3) /company=demo
//     * 4) /?company=demo
//     * 5) /?company=demo&buttonUser=gac
//     * 6) http://localhost:8090?demo
//     */
//    @Test
//    public void getCompanyName() {
//        String uriSorgente;
//        String siglaOttenuta;
//
//        uriSorgente = "https://stackoverflow.com?param1=value1&param2=&param3=value3&param3";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "");
//
//        uriSorgente = "company=pippoz";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "pippoz");
//
//        uriSorgente = "/company=pippoz";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "pippoz");
//
//        uriSorgente = "http://localhost:8090?company=demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/param1=value1";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "");
//
//        uriSorgente = "demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "demo=";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/demo=";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "company=demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/company=demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "?company=demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/?company=demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "/?company=demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "?company=demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "http://localhost:8090?demo";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "demo");
//
//        uriSorgente = "pippoz&v-1506692572130";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "pippoz");
//
//        uriSorgente = "?pippoz&v-1506692572130";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "pippoz");
//
//        uriSorgente = "v-1506692572130";
//        siglaOttenuta = service.getSiglaCompany(uriSorgente);
//        assertEquals(siglaOttenuta, "");
//    }// end of single test
//
//    /**
//     * Recupera i parametri in ingresso.
//     * 1) /demo
//     * 2) /demo=
//     * 3) /company=demo
//     * 4) /?company=demo
//     * 5) /?company=demo&buttonUser=gac
//     * 6) http://localhost:8090?demo
//     */
//    @Test
//    public void getParams2() {
//        String uriSorgente;
//        Map<String, String> queryParamsOttenuti;
//
//
//        uriSorgente = "http://localhost:8090?param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//        assertEquals(queryParamsOttenuti.get("param2"), "");
//        assertEquals(queryParamsOttenuti.get("param3"), "value3");
//
//        uriSorgente = "?param1=value1&param2=&param3=value3&param3";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//        assertEquals(queryParamsOttenuti.get("param2"), "");
//        assertEquals(queryParamsOttenuti.get("param3"), "value3");
//
//        uriSorgente = "param1=value1&param2=&param3=value3&param3";
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 3);
//        assertTrue(queryParamsOttenuti.containsKey("param1"));
//        assertTrue(queryParamsOttenuti.containsValue("value1"));
//        assertEquals(queryParamsOttenuti.get("param1"), "value1");
//        assertEquals(queryParamsOttenuti.get("param2"), "");
//        assertEquals(queryParamsOttenuti.get("param3"), "value3");
//
//        uriSorgente = "/demo&buttonUser=gac";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//        assertTrue(queryParamsOttenuti.containsKey("demo"));
//        assertTrue(queryParamsOttenuti.containsKey("buttonUser"));
//        assertTrue(queryParamsOttenuti.containsValue("gac"));
//        assertEquals(queryParamsOttenuti.get("company"), null);
//        assertEquals(queryParamsOttenuti.get("buttonUser"), "gac");
//
//        uriSorgente = "/company=demo&buttonUser=gac";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsKey("buttonUser"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertTrue(queryParamsOttenuti.containsValue("gac"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//        assertEquals(queryParamsOttenuti.get("buttonUser"), "gac");
//
//        uriSorgente = "/?company=demo&buttonUser=gac";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//        assertTrue(queryParamsOttenuti.containsKey("company"));
//        assertTrue(queryParamsOttenuti.containsKey("buttonUser"));
//        assertTrue(queryParamsOttenuti.containsValue("demo"));
//        assertTrue(queryParamsOttenuti.containsValue("gac"));
//        assertEquals(queryParamsOttenuti.get("company"), "demo");
//        assertEquals(queryParamsOttenuti.get("buttonUser"), "gac");
//
//        uriSorgente = "pippoz&v-1506692572130";
//        queryParamsOttenuti = service.getParams(uriSorgente);
//        assertNotNull(queryParamsOttenuti);
//        assertEquals(queryParamsOttenuti.size(), 2);
//    }// end of single test
//
//
//    /**
//     * Recupera la sigla dell'Utente come parametro in ingresso.
//     * Il nickname del User, contenuto nell'URI, è recuperato se presente nella forma:
//     * 1) /buttonUser=gac
//     * 2) /buttonUser=gac
//     * 3) /admin=gac
//     * 4) /developer=gac
//     * 5) /?buttonUser=gac
//     * 6) /?buttonUser=gac
//     * 7) /?admin=gac
//     * 8) /?developer=gac
//     * 9) /company=demo&buttonUser=gac
//     * 10) /?company=demo&buttonUser=gac
//     * 11) http://localhost:8090?demo&user=gac
//     * 12) /demo&buttonUser=gac
//     * 13) /?demo&buttonUser=gac
//     * 14) /buttonUser=gac&demo
//     * 15) /?buttonUser=gac&demo
//     */
//    @Test
//    public void getSiglaUtente() {
//        String uriSorgente;
//        String siglaOttenuta;
//
//        uriSorgente = "gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "");
//
//        uriSorgente = "buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "admin=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "developer=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "?buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "?buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "?admin=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "?developer=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "company=demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "?company=demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "http://localhost:8090?demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "demo&buttonUser=gac";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//
//        uriSorgente = "buttonUser=gac&demo";
//        siglaOttenuta = service.getSiglaUtente(uriSorgente);
//        assertEquals(siglaOttenuta, "gac");
//    }// end of single test
//
//
//    /**
//     * Recupera il ruolo dell'buttonUser come parametro in ingresso.
//     * Il ruolo dell'buttonUser, contenuto nell'URI, è recuperato se presente nella forma:
//     * 1) /buttonUser=gac
//     * 2) /buttonUser=gac
//     * 3) /admin=gac
//     * 4) /developer=gac
//     * 5) /?buttonUser=gac
//     * 6) /?buttonUser=gac
//     * 7) /?admin=gac
//     * 8) /?developer=gac
//     * 9) /company=demo&buttonUser=gac
//     * 10) /?company=demo&buttonUser=gac
//     * 11) http://localhost:8090?demo&user=gac
//     * 12) /demo&buttonUser=gac
//     * 13) /?demo&buttonUser=gac
//     * 14) /buttonUser=gac&demo
//     * 15) /?buttonUser=gac&demo
//     */
//    @Test
//    public void getRoleUtente() {
//        String uriSorgente;
//        EARoleType ruoloOttenuto;
//        EARoleType ruoloPrevisto;
//
//        uriSorgente = "gac";
//        ruoloPrevisto = null;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "admin=gac";
//        ruoloPrevisto = EARoleType.admin;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "developer=gac";
//        ruoloPrevisto = EARoleType.developer;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "?buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "?buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "?admin=gac";
//        ruoloPrevisto = EARoleType.admin;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "?developer=gac";
//        ruoloPrevisto = EARoleType.developer;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "company=demo&buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "?company=demo&buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "http://localhost:8090?demo&buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "demo&buttonUser=gac";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//
//        uriSorgente = "buttonUser=gac&demo";
//        ruoloPrevisto = EARoleType.user;
//        ruoloOttenuto = service.getRoleUtente(uriSorgente);
//        assertEquals(ruoloOttenuto, ruoloPrevisto);
//    }// end of single test

}// end of test class
