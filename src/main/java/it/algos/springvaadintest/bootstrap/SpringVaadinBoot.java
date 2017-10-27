package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bootstrap.AlgosBoot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 12/06/17.
 * Regolazione di flag specifici dell'applicazione
 * <p>
 * Setup non-UI logic here
 * This class will be executed on container startup when the application is ready to server requests.
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat)
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione
 * <p>
 * In order to create a class that acts like a bootstrap for the application,
 * that class needs to implements the @EventListener annotation
 * Any class that implements the @EventListener annotation will be executed before the application is up
 * and its onApplicationEvent method will be called
 * <p>
 * ATTENZIONE: in questa fase NON sono disponibili le Librerie e le classi che dipendono dalla UI e dalla Session
 */
@SpringComponent
@Slf4j
public class SpringVaadinBoot extends AlgosBoot {


//    @Autowired
//    protected CompanyData companyData;
//
//    @Autowired
//    private StatoData statoData;

    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     * Controlla
     */
//    @PostConstruct
    private void check() {
//        statoData.creaAll();
//        companyData.creaAll();
    }// end of method

     /**
     * Running logic after the Spring context has been initialized
     * Any class that use this @EventListener annotation,
     * will be executed before the application is up and its onApplicationEvent method will be called
     * <p>
     * Viene normalmente creata una sottoclasse per l'applicazione specifica:
     * - per regolare eventualmente alcuni flag in maniera non standard
     * - per lanciare eventualmente gli schedulers in background
     * - per costruire e regolare eventualmente una versione demo
     * - per controllare eventualmente l'esistenza di utenti abilitati all'accesso
     * <p>
     * Stampa a video (productionMode) i valori per controllo
     */
    @EventListener
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.inizializzaValoriDefault();
    }// end of method


    /**
     * Prima vengono regolati i valori standard di default
     * Una eventuale sottoclasse, dopo aver controllato che il metodo inizializzaValoriDefault()
     * sia stato eseguito una ed una sola volta, può modificare le impostazioni/regolazioni di base
     */
    @Override
    protected void inizializzaValoriDefault() {

        if (super.classeAlgosBootAncoraDaEseguire) {
            super.inizializzaValoriDefault();
        }// end of if cycle

        this.printBefore(Boot.specifico);
        this.specificFixAndPrint();
        this.printAfter(Boot.specifico);
    }// end of method


    /**
     * Regola alcuni flag dell'applicazione
     * Valori specifici
     * Stampa a video (productionMode) i valori per controllo
     */
    private void specificFixAndPrint() {
        AlgosApp.USE_DEBUG = false;
        log.debug("AlgosApp.USE_DEBUG: " + AlgosApp.USE_DEBUG);

        AlgosApp.USE_SECURITY = true;
        log.debug("AlgosApp.USE_SECURITY: " + AlgosApp.USE_SECURITY);

        AlgosApp.USE_MULTI_COMPANY = true;
        log.debug("AlgosApp.USE_MULTI_COMPANY: " + AlgosApp.USE_MULTI_COMPANY);

        AlgosApp.USE_VERS = true;
        log.debug("AlgosApp.USE_VERS: " + AlgosApp.USE_VERS);

        AlgosApp.USE_LOG = true;
        log.debug("AlgosApp.USE_LOG: " + AlgosApp.USE_LOG);
    }// end of method

}// end of class
