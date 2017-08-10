package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Company demo
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 */
@SpringComponent
@Slf4j
public class CompSpringBoot {


    //--il service (contenente la repository) viene iniettato qui
    @Autowired
    protected CompanyService service;

    @Autowired
    protected IndirizzoService indirizzoService;


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void demoCompany() {
        creaAndLog("crf", "Croce Rossa Fidenza", creaAndLogIndirizzo("Viale dei Tigli, 37", "Bologna", "34100", "Italia"));
        creaAndLog("pap", "Pubblica Assistenza Pianoro", creaAndLogIndirizzo("via San Sisto, 1", "Milano", "20100", "Italia"));
        creaAndLog("crpt", "Croce Rossa Ponte Taro", creaAndLogIndirizzo("Largo Donegani, 8", "Parma", "81763", "Italia"));
        creaAndLog("gaps", "Gruppo Accoglienza Pronto Soccorso", creaAndLogIndirizzo("Via Dante, 24", "Roma", "60000", "Italia"));
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param sigla       sigla di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param indirizzo   (facoltativo)
     */
    private Company creaAndLog(String sigla, String descrizione, Indirizzo indirizzo) {
        Company comp = service.crea(sigla, descrizione, indirizzo);
        log.warn("Company: " + comp);
        return comp;
    }// end of method


    /**
     * Creazione di una entity di indirizzo
     * Log a video
     *
     * @param indirizzo: via, nome e numero (obbligatoria, non unica)
     * @param localita:  località (obbligatoria, non unica)
     * @param cap:       codice di avviamento postale (obbligatoria, non unica)
     * @param stato:     stato (obbligatoria, non unica)
     */
    private Indirizzo creaAndLogIndirizzo(String indirizzo, String localita, String cap, String stato) {
        Indirizzo ind = indirizzoService.crea(indirizzo, localita, cap, stato);
        log.warn("Indirizzo: " + ind);
        return ind;
    }// end of method

}// end of class
