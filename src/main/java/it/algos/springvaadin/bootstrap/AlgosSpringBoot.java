package it.algos.springvaadin.bootstrap;

import it.algos.springvaadin.app.AlgosApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by gac on 10/06/17.
 * <p>
 * In order to create a class that acts like a bootstrap for the application,
 * that class needs to implement the InitializingBean
 * of the package org.springframework.beans.factory.InitializingBean
 * <p>
 * This class will be executed first when the application is coming up and is ready to server requests.
 * <p>
 * Any class that implements InitializingBean will be executed before the application is up
 * and its afterPropertiesSet method will be called
 *
 * @see http://www.ekiras.com/2015/10/spring-boot-how-to-create-bootstrap-class.html
 */
@Component
@Slf4j
public class AlgosSpringBoot implements InitializingBean {

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
        this.printBefore(Boot.generico);
        this.genericFixAndPrint();
        this.printAfter(Boot.generico);
    }// end of method

    /**
     * Regola alcuni flag dell'applicazione
     * Valori di default
     * Can be overwritten on local xxxSpringBoot.specificFixAndPrint() method
     * Stampa a video (productionMode) i valori per controllo
     */
    protected void genericFixAndPrint() throws Exception {
        AlgosApp.USE_DEBUG = false;
        log.info("AlgosApp.USE_DEBUG: " + AlgosApp.USE_DEBUG);

        AlgosApp.USE_MULTI_COMPANY = true;
        log.info("AlgosApp.USE_MULTI_COMPANY: " + AlgosApp.USE_MULTI_COMPANY);

        AlgosApp.USE_SECURITY = false;
        log.info("AlgosApp.USE_SECURITY: " + AlgosApp.USE_SECURITY);

        AlgosApp.USE_LOGIN_OBBLIGATORIO = false;
        log.info("AlgosApp.USE_LOGIN_OBBLIGATORIO: " + AlgosApp.USE_LOGIN_OBBLIGATORIO);

        AlgosApp.USE_CHECK_PARAMS = true;
        log.info("AlgosApp.USE_CHECK_PARAMS: " + AlgosApp.USE_CHECK_PARAMS);

        AlgosApp.USE_CHECK_COOKIES = true;
        log.info("AlgosApp.USE_CHECK_COOKIES: " + AlgosApp.USE_CHECK_COOKIES);

        AlgosApp.USE_VERS = false;
        log.info("AlgosApp.USE_VERS: " + AlgosApp.USE_VERS);

        AlgosApp.USE_LOG = false;
        log.info("AlgosApp.USE_LOG: " + AlgosApp.USE_LOG);

        AlgosApp.USE_PREF = false;
        log.info("AlgosApp.USE_PREF: " + AlgosApp.USE_PREF);

//        AlgosApp.DISPLAY_NEW_RECORD_ONLY = true;
//        System.out.println("AlgosApp.DISPLAY_NEW_RECORD_ONLY: " + AlgosApp.DISPLAY_NEW_RECORD_ONLY);
//
//        AlgosApp.DISPLAY_TOOLTIPS = false;
//        System.out.println("AlgosApp.DISPLAY_TOOLTIPS: " + AlgosApp.DISPLAY_TOOLTIPS);
//
//        AlgosApp.COMBO_BOX_NULL_SELECTION_ALLOWED = false;
//        System.out.println("AlgosApp.COMBO_BOX_NULL_SELECTION_ALLOWED: " + AlgosApp.COMBO_BOX_NULL_SELECTION_ALLOWED);
    }// end of method


    /**
     * Stampa a video (productionMode) una info PRIMA dei valori
     */
    protected void printBefore(Boot boot) {
        switch (boot) {
            case generico:
                log.info("Application is coming up and is ready to server requests - PRIMA della chiamata del browser - start generic bootstrap code nella classe AlgosSpringBoot");
                break;
            case specifico:
                log.info("Start specific bootstrap code nella classe xxxSpringBoot");
                break;
        } // fine del blocco switch
    }// end of method

    /**
     * Stampa a video (productionMode) una info DOPO i valori
     */
    protected void printAfter(Boot boot) {
        switch (boot) {
            case generico:
                log.info("All this params can be found also in LibParams - End generic bootstrap code");
                break;
            case specifico:
                log.info("End specific bootstrap code");
                break;
        } // fine del blocco switch
    }// end of method

    public enum Boot {
        generico, specifico
    }// end of internal enumeration

}// end of class