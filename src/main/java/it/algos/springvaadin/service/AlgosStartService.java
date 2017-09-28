package it.algos.springvaadin.service;

import com.vaadin.sass.internal.util.StringUtil;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.lib.LibText;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.Location;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 01/06/17.
 * Classe statica per la Business Logic iniziale dell'applicazione
 */
@SpringComponent
public class AlgosStartService {

    @Autowired
    private CompanyService companyService;

    /**
     * Legge eventuali parametri passati nella request
     * <p>
     */
    //@todo da sviluppare
    public static boolean checkParams(VaadinRequest request) {
        boolean continua = false;

        Object alfa = request.getContextPath();
        Object beta = request.getParameterMap();
        Object delta = request.getPathInfo();
        Object gamma = request.getCookies();

        return continua;
    }// end of static method


    /**
     * Legge i cookies dalla request
     * <p>
     */
    //@todo da sviluppare
    public static boolean checkCookies(VaadinRequest request) {
        boolean continua = false;

        Object alfa = request.getContextPath();
        Object beta = request.getParameterMap();
        Object delta = request.getPathInfo();
        Object gamma = request.getCookies();

        return continua;
    }// end of static method

    /**
     * Controlla il login della security
     * <p>
     * Creazione del wrapper di informazioni mantenuto nella sessione <br>
     */
    //@todo da sviluppare
    public static boolean checkSecurity(VaadinRequest request) {
        boolean continua = false;

        Object alfa = request.getContextPath();
        Object beta = request.getParameterMap();
        Object delta = request.getPathInfo();
        Object gamma = request.getCookies();

        return continua;
    }// end of static method

    /**
     * Controlla la company selezionata (sigla nell'url del browser come company=xxx)
     * È stata intercettata nella @RequestMapping di AlgosController
     * e inserita nel modello dati della request che arriva ad AlgosUIParams
     * Se esiste una company con la sigla indicata, recupara la Company e la memorizzata nella sessione
     * <p>
     * //     * Nella sessione corrente, dovrebbe già esserci l'attributo 'company,
     * //     * iniettato dal modello dati del reindirizzo effettuato in AlgosController
     * //     * Ma forse si potrebbe arrivare alla Ui di partenza in altro modo
     * //     * ed è meglio 'forzare' il valore anche se è già presente
     *
     * @todo da perfezionare il valore di ritorno
     * Restituisce vero se esiste una company valida
     */
    public boolean checkCompany(VaadinRequest request) {
        boolean continua = false;
        String siglaCompany = "";
        Company company = null;
        HttpServletRequest httpRequest;
        String queryString;

        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            queryString = httpRequest.getQueryString();
            siglaCompany = getCompanyName(queryString);
        }// end of if cycle

//        @todo DEMO demo DEMO DA  LLEEVVAARREE
//        if (siglaCompany.equals("")) {
//            siglaCompany = "demo";
//        }// end of if cycle

        if (LibText.isValid(siglaCompany)) {
            company = companyService.findBySigla(siglaCompany);
        }// end of if cycle

        if (company != null) {
            LibSession.setCompany(company);
            continua = true;
        }// end of if cycle

        return continua;
    }// end of static method


    /**
     * Controlla che la company ricevuta come parametro in ingresso, sia valida
     */
    public boolean isCompanyValida(HttpServletRequest request) {
        return getCompany(request) != null;
    }// end of static method

    /**
     * Recupera la company come parametro in ingresso.
     * Il nome della company, contenuto nell'URI, è recuperato se presente nella forma:
     * 1) /demo
     * 2) /demo=
     * 3) /company=demo
     * 4) /?company=demo
     * 5) /?company=demo&user=gac
     * 6) http://localhost:8090?demo
     * Deve poi essere una company valida prevista nella collezione 'company'
     */
    public Company getCompany(HttpServletRequest request) {
        Company company = null;
        String siglaCompany = getCompanyName(request.getRequestURI());

        if (!siglaCompany.equals("")) {
            company = companyService.findBySigla(siglaCompany);
        }// end of if cycle

        return company;
    }// end of method

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
    public static String getCompanyName(String url) {
        String companyName = "";
        Map<String, String> mappaParams = getParams(url);
        String tagCompany = "company";
        String tagVuoto = "";

        if (mappaParams.size() > 0) {
            if (mappaParams.containsKey(tagCompany)) {
                companyName = mappaParams.get(tagCompany);
                return companyName;
            }// end of if cycle

            if (mappaParams.size() == 1) {
                Object[] valori = mappaParams.values().toArray();
                if (valori[0] == null || valori[0].equals(tagVuoto)) {
                    Object[] chiavi = mappaParams.keySet().toArray();
                    return (String) chiavi[0];
                }// end of if cycle
            }// end of if cycle
        }// end of if cycle

        return companyName;
    }// end of method


    /**
     * Recupera i parametri in ingresso.
     * 1) /demo
     * 2) /demo=
     * 3) /company=demo
     * 4) /?company=demo
     * 5) /?company=demo&user=gac
     * 6) http://localhost:8090?demo
     */
    public static Map getParams(String url) {
        MultiValueMap<String, String> queryMultiParams = null;
        String tagMettere = "?";
        String tagLevare = "/";

        if (url.startsWith(tagLevare)) {
            url = LibText.levaTesta(url, tagLevare);
        }// end of if cycle
        queryMultiParams = UriComponentsBuilder.fromUriString(url).build().getQueryParams();

        if (queryMultiParams.size() == 0) {
            url = tagMettere + url;
            queryMultiParams = UriComponentsBuilder.fromUriString(url).build().getQueryParams();
        }// end of if cycle

        return queryMultiParams.toSingleValueMap();
    }// end of method


}// end of static class
