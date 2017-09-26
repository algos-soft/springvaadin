package it.algos.springvaadin.service;

import com.vaadin.sass.internal.util.StringUtil;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.lib.LibText;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.Location;
import java.net.URI;

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
        String siglaCompany = LibSession.checkCompany(request);
        Company company = null;

        //@todo DEMO demo DEMO DA  LLEEVVAARREE
        if (siglaCompany.equals("")) {
            siglaCompany = "demo";
        }// end of if cycle

        if (LibText.isValid(siglaCompany)) {
            company = companyService.findBySigla(siglaCompany);
        }// end of if cycle

        if (company != null) {
            LibSession.setCompany(company);
        }// end of if cycle

        return continua;
    }// end of static method


}// end of static class
