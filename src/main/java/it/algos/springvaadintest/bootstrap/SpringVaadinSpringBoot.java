package it.algos.springvaadintest.bootstrap;

import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bootstrap.AlgosSpringBoot;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by gac on 12/06/17.
 * .
 */
@Component
public class SpringVaadinSpringBoot extends AlgosSpringBoot {

    /**
     * InitializingBean chiama questo metodo per TUTTE le classi e sottoclassi che implementano l'interfaccia.
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
    protected void specificFixAndPrint() throws Exception {
        AlgosApp.USE_DEBUG = false;
        System.out.println("AlgosApp.USE_DEBUG: " + AlgosApp.USE_DEBUG);

        AlgosApp.USE_VERS = true;
        System.out.println("AlgosApp.USE_VERS: " + AlgosApp.USE_VERS);

        AlgosApp.USE_MULTI_COMPANY = true;
        System.out.println("AlgosApp.USE_MULTI_COMPANY: " + AlgosApp.USE_MULTI_COMPANY);

        AlgosApp.USE_LOG = true;
        System.out.println("AlgosApp.USE_LOG: " + AlgosApp.USE_LOG);
    }// end of method

}// end of class
