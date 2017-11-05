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
import it.algos.springvaadin.login.ARoleType;
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
    public boolean checkParams(VaadinRequest request) {
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
    public boolean checkCookies(VaadinRequest request) {
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
    public boolean checkSecurity(VaadinRequest request) {
        boolean continua = false;
        LibSession.setLogin(null);
        LibSession.setAdmin(false);
        LibSession.setDeveloper(false);
        String siglaUser = getSiglaUtente(request);

//        if (LibText.isValid(siglaUser)) {
//            if (siglaUser.equals("gac")) {
//                LibSession.setDeveloper(true);
//            }// end of if cycle
//        }// end of if cycle

        if (getRoleUtente(request) == ARoleType.user) {
//            LibSession.setAdmin(true);
        }// end of if cycle

        if (getRoleUtente(request) == ARoleType.admin) {
            LibSession.setAdmin(true);
        }// end of if cycle

        if (getRoleUtente(request) == ARoleType.developer) {
            LibSession.setDeveloper(true);
        }// end of if cycle

        return continua;
    }// end of static method


    public String getSiglaUtente(VaadinRequest request) {
        String siglaUser = "";
        HttpServletRequest httpRequest;
        String queryString;

        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            queryString = httpRequest.getQueryString();
            siglaUser = getSiglaUtente(queryString);
        }// end of if cycle

        return siglaUser;
    }// end of  method

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
        LibSession.setCompany((Company) null);
        String siglaCompany = getSiglaCompany(request);
        Company company = null;

        if (LibText.isValid(siglaCompany)) {
            company = companyService.findByCode(siglaCompany);
        }// end of if cycle

        if (company != null) {
            LibSession.setCompany(company);
            continua = true;
        }// end of if cycle

        return continua;
    }// end of  method

    public String getSiglaCompany(VaadinRequest request) {
        String siglaCompany = "";
        HttpServletRequest httpRequest;
        String queryString;

        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            queryString = httpRequest.getQueryString();
            siglaCompany = getSiglaCompany(queryString);
        }// end of if cycle

        return siglaCompany;
    }// end of  method

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
        String siglaCompany = getSiglaCompany(request.getRequestURI());

        if (!siglaCompany.equals("")) {
            company = companyService.findByCode(siglaCompany);
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
    public String getSiglaCompany(String url) {
        String companyName = "";
        Map<String, String> mappaParams = getParams(url);
        String tagCompany = "company";
        String tagVuoto = "";
        String tagIniPatch = "v-";

        if (mappaParams.size() > 0) {
            if (mappaParams.containsKey(tagCompany)) {
                companyName = mappaParams.get(tagCompany);
                return companyName;
            } else {
                Object[] chiavi = mappaParams.keySet().toArray();
                Object[] valori = mappaParams.values().toArray();

                if (mappaParams.size() == 1 && ((String) chiavi[0]).startsWith(tagIniPatch)) {
                    return "";
                }// end of if cycle

                if (valori[0] == null || valori[0].equals(tagVuoto)) {
                    return (String) chiavi[0];
                }// end of if cycle
            }// end of if/else cycle
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
    public Map getParams(String url) {
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

    /**
     * Recupera la sigla dell'Utente come parametro in ingresso.
     * Il nickname del User, contenuto nell'URI, è recuperato se presente nella forma:
     * 1) /user=gac
     * 2) /utente=gac
     * 3) /admin=gac
     * 4) /developer=gac
     * 5) /?user=gac
     * 6) /?utente=gac
     * 7) /?admin=gac
     * 8) /?developer=gac
     * 9) /company=demo&user=gac
     * 10) /?company=demo&user=gac
     * 11) http://localhost:8090?demo&user=gac
     * 12) /demo&user=gac
     * 13) /?demo&user=gac
     * 14) /user=gac&demo
     * 15) /?user=gac&demo
     */
    public String getSiglaUtente(String url) {
        String siglaUtente = "";
        Map<String, String> mappaParams = getParams(url);
        String[] tagUtenti = {"user", "ute", "utente", "admin", "dev", "developer"};
        String tagVuoto = "";
        String tagIniPatch = "v-";

        if (mappaParams.size() > 0) {
            for (String tag : tagUtenti) {
                if (mappaParams.containsKey(tag)) {
                    return mappaParams.get(tag);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return siglaUtente;
    }// end of method

    /**
     * Recupera il ruolo dell'utente come parametro in ingresso.
     * Il ruolo dell'utente, contenuto nell'URI, è recuperato se presente nella forma:
     * 1) /user=gac
     * 2) /utente=gac
     * 3) /admin=gac
     * 4) /developer=gac
     * 5) /?user=gac
     * 6) /?utente=gac
     * 7) /?admin=gac
     * 8) /?developer=gac
     * 9) /company=demo&user=gac
     * 10) /?company=demo&user=gac
     * 11) http://localhost:8090?demo&user=gac
     * 12) /demo&user=gac
     * 13) /?demo&user=gac
     * 14) /user=gac&demo
     * 15) /?user=gac&demo
     */
    public ARoleType getRoleUtente(VaadinRequest request) {
        ARoleType ruoloUtente = null;
        HttpServletRequest httpRequest;
        String queryString;

        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            queryString = httpRequest.getQueryString();
            ruoloUtente = getRoleUtente(queryString);
        }// end of if cycle

        return ruoloUtente;
    }// end of  method

    /**
     * Recupera il ruolo dell'utente come parametro in ingresso.
     * Il ruolo dell'utente, contenuto nell'URI, è recuperato se presente nella forma:
     * 1) /user=gac
     * 2) /utente=gac
     * 3) /admin=gac
     * 4) /developer=gac
     * 5) /?user=gac
     * 6) /?utente=gac
     * 7) /?admin=gac
     * 8) /?developer=gac
     * 9) /company=demo&user=gac
     * 10) /?company=demo&user=gac
     * 11) http://localhost:8090?demo&user=gac
     * 12) /demo&user=gac
     * 13) /?demo&user=gac
     * 14) /user=gac&demo
     * 15) /?user=gac&demo
     */
    public ARoleType getRoleUtente(String url) {
        ARoleType ruoloUtente = null;
        String[] tagUtente = {"user", "ute", "utente"};
        String[] tagAdmin = {"admin"};
        String[] tagDeveloper = {"dev", "developer"};
        Map<String, String> mappaParams = getParams(url);

        if (mappaParams.size() > 0) {
            for (String tag : tagUtente) {
                if (mappaParams.containsKey(tag)) {
                    return ARoleType.user;
                }// end of if cycle
            }// end of for cycle
            for (String tag : tagAdmin) {
                if (mappaParams.containsKey(tag)) {
                    return ARoleType.admin;
                }// end of if cycle
            }// end of for cycle
            for (String tag : tagDeveloper) {
                if (mappaParams.containsKey(tag)) {
                    return ARoleType.developer;
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return ruoloUtente;
    }// end of method

}// end of static class
