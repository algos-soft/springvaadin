package it.algos.springvaadin.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Project springwam
 * Created by Algos
 * User: gac
 * Date: dom, 24-set-2017
 * Time: 18:18
 * Company demo
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 */
@SpringComponent
@Slf4j
public class CompanySpringBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    private CompanyService service;

    //--il service (contenente la repository) viene iniettato nel costruttore
    private IndirizzoService serviceIndirizzo;

    //--il service (contenente la repository) viene iniettato nel costruttore
    private StatoService serviceStato;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public CompanySpringBoot(@Qualifier(Cost.TAG_COMP) AlgosService service,
                             @Qualifier(Cost.TAG_IND) AlgosService serviceIndirizzo,
                             @Qualifier(Cost.TAG_STA) AlgosService serviceStato) {
        this.service = (CompanyService) service;
        this.serviceIndirizzo = (IndirizzoService) serviceIndirizzo;
        this.serviceStato = (StatoService) serviceStato;
    }// end of Spring constructor


    /**
     * Creazione di una company demo
     * <p>
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    public void creaAll() {
        if (nessunRecordEsistente()) {
            creaCompanyDemo();
        } else {
            log.info("La collezione di company è presente");
        }// end of if/else cycle
    }// end of method


    /**
     * Controlla se la collezione esiste già
     */
    private boolean nessunRecordEsistente() {
        return service.count() == 0;
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     */
    private void creaCompanyDemo() {
        log.warn("Company: " + service.crea("demo", "Algos s.r.l.", creaIndirizzoDemo()));
    }// end of method


    /**
     * Crea un indirizzo di company
     */
    private Indirizzo creaIndirizzoDemo() {
        return serviceIndirizzo.crea("Via Soderini, 55", "Milano", "20146", creaStato());
    }// end of method


    /**
     * Recupera (se esiste già) oppure crea uno stato per l'indirizzo di company
     */
    private Stato creaStato() {
        return serviceStato.crea("Italia");
    }// end of method



}// end of class
