package it.algos.springvaadin;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.lib.LibDate;
import it.algos.springvaadin.service.AlgosStartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

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
public class AlgosStarterServiceTest {


    /**
     * Recupera i parametri in ingresso.
     * 1) /demo
     * 2) /demo=
     * 3) /company=demo
     * 4) /?company=demo
     * 5) /?company=demo&user=gac
     * 6) http://localhost:8090?demo
     */
    @Test
    public void getParams() {
        String uriSorgente;
        Map<String, String> queryParamsOttenuti;

        uriSorgente = "https://stackoverflow.com?param1=value1&param2=&param3=value3&param3";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 3);
        assertTrue(queryParamsOttenuti.containsKey("param1"));
        assertTrue(queryParamsOttenuti.containsValue("value1"));
        assertEquals(queryParamsOttenuti.get("param1"),"value1");

        uriSorgente = "http://localhost:8090?param1=value1&param2=&param3=value3&param3";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 3);
        assertTrue(queryParamsOttenuti.containsKey("param1"));
        assertTrue(queryParamsOttenuti.containsValue("value1"));
        assertEquals(queryParamsOttenuti.get("param1"),"value1");

        uriSorgente = "?param1=value1&param2=&param3=value3&param3";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 3);
        assertTrue(queryParamsOttenuti.containsKey("param1"));
        assertTrue(queryParamsOttenuti.containsValue("value1"));
        assertEquals(queryParamsOttenuti.get("param1"),"value1");

        uriSorgente = "param1=value1&param2=&param3=value3&param3";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 3);
        assertTrue(queryParamsOttenuti.containsKey("param1"));
        assertTrue(queryParamsOttenuti.containsValue("value1"));
        assertEquals(queryParamsOttenuti.get("param1"),"value1");

        uriSorgente = "/demo";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 1);
        assertTrue(queryParamsOttenuti.containsKey("demo"));
        assertEquals(queryParamsOttenuti.get("demo"), null);

        uriSorgente = "http://localhost:8090?demo";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 1);
        assertTrue(queryParamsOttenuti.containsKey("demo"));
        assertEquals(queryParamsOttenuti.get("demo"), null);

        uriSorgente = "/company=demo";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 1);
        assertTrue(queryParamsOttenuti.containsKey("company"));
        assertTrue(queryParamsOttenuti.containsValue("demo"));
        assertEquals(queryParamsOttenuti.get("company"),"demo");

        uriSorgente = "/?company=demo";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 1);
        assertTrue(queryParamsOttenuti.containsKey("company"));
        assertTrue(queryParamsOttenuti.containsValue("demo"));
        assertEquals(queryParamsOttenuti.get("company"),"demo");

        uriSorgente = "/?company=demo&user=gac";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 2);
        assertTrue(queryParamsOttenuti.containsKey("company"));
        assertTrue(queryParamsOttenuti.containsValue("demo"));
        assertEquals(queryParamsOttenuti.get("company"),"demo");

        uriSorgente = "/?user=gac&company=demo";
        queryParamsOttenuti = AlgosStartService.getParams(uriSorgente);
        assertNotNull(queryParamsOttenuti);
        assertEquals(queryParamsOttenuti.size(), 2);
        assertTrue(queryParamsOttenuti.containsKey("company"));
        assertTrue(queryParamsOttenuti.containsValue("demo"));
        assertEquals(queryParamsOttenuti.get("company"),"demo");
    }// end of single test


    /**
     * Recupera la sigla della company come parametro in ingresso.
     * Il nome della company, contenuto nell'URI, è recuperato se presente nella forma:
     * 1) /demo
     * 2) /demo=
     * 3) /company=demo
     * 4) /?company=demo
     * 5) /?company=demo&user=gac
     * 6) http://localhost:8090?demo
     */
    @Test
    public void getCompanyName() {
        String uriSorgente;
        String siglaOttenuta;

        uriSorgente = "https://stackoverflow.com?param1=value1&param2=&param3=value3&param3";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"");

        uriSorgente = "company=pippoz";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"pippoz");

        uriSorgente = "/company=pippoz";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"pippoz");

        uriSorgente = "http://localhost:8090?company=demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/param1=value1";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"");

        uriSorgente = "demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "demo=";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/demo=";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "company=demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/company=demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "?company=demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/?company=demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "/?company=demo&user=gac";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "?company=demo&user=gac";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");

        uriSorgente = "http://localhost:8090?demo";
        siglaOttenuta = AlgosStartService.getCompanyName(uriSorgente);
        assertEquals(siglaOttenuta,"demo");
    }// end of single test

}// end of test class
