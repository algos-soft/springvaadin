package it.algos.springvaadin.controller;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 06/06/17
 * <p>
 * Controller standard dell'applicazione
 * Possono esserci diverse classi con @RestController e funzionano tutte.
 * Meglio però raggruppare insieme tutte le 'mappature'
 * Si possono aggiungere tutte le diverse mappature necessarie, purché non ci sia ambiguità
 * <p>
 *
 * @see http://atmarkplant.com/spring-boot-request-parameters/
 * @see http://www.baeldung.com/spring-requestmapping
 * @see https://spring-corner.com/2017/03/16/lannotazione-requestmapping/
 * @see http://howtodoinjava.com/spring/spring-mvc/spring-mvc-requestmapping-annotation-examples/
 * @see http://stacktips.com/tutorials/java/spring/how-spring-controller-request-mapping-works-in-spring-mvc
 * @see https://github.com/rwe17/spring-boot-vaadin-demo/blob/master/src/main/java/demo/ui/controller/MessageController.java
 * @see https://dzone.com/articles/new-requestparam-annotations-in-spring-boot-14-spr
 * @see http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-request-mapping/
 * @see https://www.mkyong.com/spring-mvc/spring-abstract-controller-example/
 * <p>
 * Nel RedirectView, il contextRelative normalmente è true.
 * contextRelative whether to interpret the given URL as relative to the current ServletContext
 * <p>
 * Mappo prima la classe del Controller in modo che entri qui SOLO se c'è un path di dettaglio (dopo /)
 * Se non entra qui, va direttamente alla UI principale; quella con @SpringUI()
 * Se entra qui, controlla le varie mappature come specificato nel/nei metodo/i
 * La @RequestMapping può filtrare condizioni della Request
 * I nomi dei metodi sono irrilevanti
 * I parametri in ingresso di ogni metodo possono comprendere tutto quello che SpringBoot conosce al momento
 * Il ritorno dei metodi può essere di svariati tipi (compatibili)
 * Da ogni metodo si può (eventualmente) proseguire per la UI principale; quella con @SpringUI()
 */
@Controller
@RequestMapping()
public class AlgosController {

    private final static String NAME_APPLICATION = "wam";

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }


//    @RequestMapping(value = "*", method = RequestMethod.GET)
//    public ModelAndView getdata() {
//
//        List<String> list = new ArrayList<>();
//        list.add("Alfa");
//        list.add("Beta");
//        list.add("Gamma");
//
//        //return back to index.jsp
//        ModelAndView model = new ModelAndView("index");
//        model.addObject("lists", list);
//
//        return model;
//
//    }

    /**
     * Nome del metodo arbitrario.
     * Ritorno arbitrario.
     * Conta solo la presenza di @RequestMapping
     * <p>
     * RequestMethod può essere GET oppure POST. Se non specificato, prende entrambi.
     * Senza parametri in ingresso
     * Scrive il ritorno nel browser
     */
    @RequestMapping(value = "/help/*")
    public String mappaTutto() {
        //@todo posso costruire come voglio la stringa di ritorno, anche recuperando info dal DB
        return "help composito";
    }// end of method


    /**
     * Nome del metodo arbitrario.
     * Ritorno arbitrario.
     * Conta solo la presenza di @RequestMapping
     * <p>
     * RequestMethod può essere GET oppure POST. Se non specificato, prende entrambi.
     * Senza parametri in ingresso
     * Scrive il ritorno nel browser
     */
    @RequestMapping(value = "/hello")
    public String nomeDelMetodoIrrilevante() {
        //@todo posso costruire come voglio la stringa di ritorno, anche recuperando info dal DB
        return "hello";
    }// end of method


    /**
     * Nome del metodo arbitrario.
     * Ritorno arbitrario.
     * Conta solo la presenza di @RequestMapping
     * <p>
     * RequestMethod può essere GET oppure POST. Se non specificato, prende entrambi.
     * Decido i parametri in ingresso e SpringBoot me li fornisce (quelli che gestisce)
     * Costruisce un ritorno con (eventuale) modello dati e vista (url) di destinazione
     * (@todo mi ricorda molto Grails)
     */
    @RequestMapping(value = "/test")
    public ModelAndView altroMetodoConParametri(HttpServletRequest request) {
        //@todo posso estrarre le info dalla Request
        return new ModelAndView(new RedirectView("/", true));
    }// end of method


    /**
     * Nome del metodo arbitrario.
     * Ritorno arbitrario.
     * Conta solo la presenza di @RequestMapping
     * <p>
     * RequestMethod può essere GET oppure POST. Se non specificato, prende entrambi.
     * Decido i parametri in ingresso e SpringBoot me li fornisce (quelli che gestisce)
     * Costruisce un ritorno con (eventuale) modello dati e vista (url) di destinazione
     * (@todo mi ricorda molto Grails)
     */
    @RequestMapping(value = "/errore")
    public String erroreTipo(HttpServletRequest request) {
        //@todo posso estrarre le info dalla Request
        return "errore";
    }// end of method

    /**
     * Il controllo della company deve (DEVE) essere fatto qui,
     * perché adesso la request è quella originale e contiene il nome della company nell'uri
     * Quando si arriva nella AlgosUIParams, la request ha come path solo "/"
     * Costruisce un ritorno con modello dati della company selezionata e vista (url) di destinazione
     */
    @RequestMapping()
    public ModelAndView seleziono(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String companyName = "";
        List<String> companies;
        String[] parti = uri.split("/");

        if (parti != null && parti.length > 0) {
            companyName = parti[1];
        }// end of if cycle

        //--l'applicazione usa usaMultiCompany?
        if (AlgosApp.USE_MULTI_COMPANY) {

            //--se l'applicazione usaMultiCompany, deve esistere una tavola coi nomi delle company (valide)
            String[] array = {"crf", "pap", "crpt", "gaps"};//@todo provvisorio
            companies = LibArray.fromString(array);//@todo provvisorio

            //--esiste nel DB la company indicata?
            if (companies.contains(companyName)) {
                HashMap<String, String> mappa = new HashMap();
                mappa.put(Cost.KEY_MAP_COMPANY, companyName);
                return new ModelAndView(new RedirectView("/"), mappa);
            } else {
                //@todo non gestisce bene l'errore. Va alla partenza normale
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/error");
            }// end of if/else cycle
        } else {
            return new ModelAndView(new RedirectView("/", true));
        }// end of if/else cycle

        return new ModelAndView(new RedirectView("/", true));
    }// end of method


    /**
     * Il controllo della company deve (DEVE) essere fatto qui,
     * perché adesso la request è quella originale e contiene il nome della company nell'uri
     * Quando si arriva nella AlgosUIParams, la request ha come path solo "/"
     * Costruisce un ritorno con modello dati della company selezionata e vista (url) di destinazione
     */
    @RequestMapping(value = {"*"}, method = RequestMethod.GET)
    public ModelAndView selezionoCompany(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String companyName = "";
        List<String> companies;
        String[] parti = uri.split("/");

        if (parti != null && parti.length > 0) {
            companyName = parti[1];
        }// end of if cycle

        //--l'applicazione usa usaMultiCompany?
        if (AlgosApp.USE_MULTI_COMPANY) {

            //--se l'applicazione usaMultiCompany, deve esistere una tavola coi nomi delle company (valide)
            String[] array = {"crf", "pap", "crpt", "gaps"};//@todo provvisorio
            companies = LibArray.fromString(array);//@todo provvisorio

            //--esiste nel DB la company indicata?
            if (companies.contains(companyName)) {
                HashMap<String, String> mappa = new HashMap();
                mappa.put(Cost.KEY_MAP_COMPANY, companyName);
                return new ModelAndView(new RedirectView("/"), mappa);
            } else {
                //@todo non gestisce bene l'errore. Va alla partenza normale
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/error");
            }// end of if/else cycle
        } else {
            return new ModelAndView(new RedirectView("/", true));
        }// end of if/else cycle

        return new ModelAndView(new RedirectView("/", true));
    }// end of method


}// end of class