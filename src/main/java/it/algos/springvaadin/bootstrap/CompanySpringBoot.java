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
 * <p>
 * ATTENZIONE: in questa fase NON sono disponibili le Librerie e le classi che dipendono dalla UI e dalla Session
 */
@Slf4j
public class CompanySpringBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    private CompanyService serviceCompany;

    //--il service (contenente la repository) viene iniettato nel costruttore
    private IndirizzoService serviceIndirizzo;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public CompanySpringBoot(@Qualifier(Cost.TAG_COMP) AlgosService serviceCompany,
                             @Qualifier(Cost.TAG_IND) AlgosService serviceIndirizzo) {
        this.serviceCompany = (CompanyService) serviceCompany;
        this.serviceIndirizzo = (IndirizzoService) serviceIndirizzo;
    }// end of Spring constructor


    /**
     * Creazione di una company demo
     * <p>
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    public void creaAll() {
        if (serviceCompany.isNonEsiste(Cost.SIGLA_COMPANY_DEMO)) {
            creaAndLog(Cost.SIGLA_COMPANY_DEMO, "Algos s.r.l.", "Via Soderini, 55", "Milano", "20146");
        } else {
            log.info("La company demo è presente");
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param sigla sigla di riferimento interna (interna, obbligatoria ed unica)
     * @param desc  ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param ind   indirizzo stradale
     * @param loc   citta, paese
     * @param cap   postale
     */
    private void creaAndLog(String sigla, String desc, String ind, String loc, String cap) {
//        serviceCompany.findOrCrea(sigla, desc, serviceIndirizzo.newEntity(ind, loc, cap));
        log.warn("Company: " + sigla);
    }// end of method


}// end of class
