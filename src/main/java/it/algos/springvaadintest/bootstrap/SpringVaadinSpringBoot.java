package it.algos.springvaadintest.bootstrap;

import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bootstrap.AlgosSpringBoot;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.entity.versione.VersioneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by gac on 12/06/17.
 * .
 */
@Component
@Slf4j
public class SpringVaadinSpringBoot extends AlgosSpringBoot {



    @Autowired
    protected IndirizzoData indirizzoData;

    @Autowired
    protected CompanyData companyData;


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     * Controlla
     */
    @PostConstruct
    private void check() {
        indirizzoData.creaAll();
        companyData.creaAll();
    }// end of method


    /**
     * InitializingBean chiama questo metodo per TUTTE le classi e sottoclassi che implementano l'annotation.
     * Viene normalmente scritto ANCHE nella sottoclasse:
     * - per regolare eventualmente alcuni flag dell'applicazione <br>
     * - per lanciare eventualmente gli schedulers in background <br>
     * - per regolare eventualmente una versione demo <br>
     * - per controllare eventualmente l'esistenza di utenti abilitati all'accesso <br>
     * <p>
     * Stampa a video (productionMode) i valori per controllo
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.printBefore(Boot.specifico);
        this.specificFixAndPrint();
        this.printAfter(Boot.specifico);
    }// end of method

    /**
     * Regola alcuni flag dell'applicazione
     * Valori specifici che modificano quelli di default della supeclasse
     * Stampa a video (productionMode) i valori per controllo
     */
    private void specificFixAndPrint() throws Exception {
        AlgosApp.USE_DEBUG = false;
        log.debug("AlgosApp.USE_DEBUG: " + AlgosApp.USE_DEBUG);

        AlgosApp.USE_VERS = true;
        log.debug("AlgosApp.USE_VERS: " + AlgosApp.USE_VERS);

        AlgosApp.USE_MULTI_COMPANY = true;
        log.debug("AlgosApp.USE_MULTI_COMPANY: " + AlgosApp.USE_MULTI_COMPANY);

        AlgosApp.USE_LOG = true;
        log.debug("AlgosApp.USE_LOG: " + AlgosApp.USE_LOG);

        AlgosApp.USE_SECURITY = true;
        log.debug("AlgosApp.USE_SECURITY: " + AlgosApp.USE_SECURITY);
    }// end of method

}// end of class
