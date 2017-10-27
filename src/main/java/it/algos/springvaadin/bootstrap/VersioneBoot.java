package it.algos.springvaadin.bootstrap;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.log.Log;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.entity.log.LogType;
import it.algos.springvaadin.entity.preferenza.PrefType;
import it.algos.springvaadin.entity.preferenza.Preferenza;
import it.algos.springvaadin.entity.preferenza.PreferenzaService;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.login.ARoleType;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 21-ott-2017
 * Time: 22:06
 * <p>
 * Log delle versioni, modifiche e patch installate
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
@Slf4j
public class VersioneBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    protected VersioneService service;


    //--il service (contenente la repository) viene iniettato nel costruttore
    protected CompanyService companyService;


    //--il service (contenente la repository) viene iniettato nel costruttore
    protected PreferenzaService preferenzaService;


    //--il service (contenente la repository) viene iniettato nel costruttore
    protected LogService logger;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneBoot(
            @Qualifier(Cost.TAG_VERS) AlgosService service,
            @Qualifier(Cost.TAG_COMP) AlgosService companyService,
            @Qualifier(Cost.TAG_PRE) AlgosService preferenzaService,
            @Qualifier(Cost.TAG_LOG) AlgosService logger) {
        this.service = (VersioneService) service;
        this.companyService = (CompanyService) companyService;
        this.preferenzaService = (PreferenzaService) preferenzaService;
        this.logger = (LogService) logger;
    }// end of Spring constructor


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine      di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     */
    protected void creaVersioneAndLog(int ordine, String gruppo, String descrizione) {
        creaVersioneAndLog(ordine, "", gruppo, descrizione, "");
    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine       di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param siglaCompany (facoltativa)
     * @param gruppo       codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione  (obbligatoria, non unica)
     */
    protected void creaAndLogCompany(int ordine, String siglaCompany, String gruppo, String descrizione) {
        creaVersioneAndLog(ordine, siglaCompany, gruppo, descrizione, "");
    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine       di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param siglaCompany (facoltativa)
     * @param gruppo       codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione  (obbligatoria, non unica)
     */
    protected void creaAndLogCompany(int ordine, String siglaCompany, String gruppo, String descrizione, String note) {
        creaVersioneAndLog(ordine, siglaCompany, gruppo, descrizione, note);
    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine      di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     * @param note        descrittive (facoltative)
     */
    protected void creaVersioneAndLog(int ordine, String gruppo, String descrizione, String note) {
        creaVersioneAndLog(ordine, "", gruppo, descrizione, note);
    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine       di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param siglaCompany (facoltativa)
     * @param gruppo       codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione  (obbligatoria, non unica)
     * @param note         descrittive (facoltative)
     */
    private void creaVersioneAndLog(int ordine, String siglaCompany, String gruppo, String descrizione, String note) {
        Company company;
        Versione vers = service.findOrCrea(ordine, gruppo, descrizione);

        if (LibText.isValid(note)) {
            vers.note = note;
            try { // prova ad eseguire il codice
                service.save(vers);
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
        }// end of if cycle

        if (LibText.isValid(siglaCompany)) {
            company = companyService.findBySigla(siglaCompany);
            if (company != null) {
                vers.setCompany(company);
                try { // prova ad eseguire il codice
                    service.save(vers);
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error(unErrore.toString());
                }// fine del blocco try-catch
            }// end of if cycle

            //--siamo in fase di boot e NON esiste ancora la session,
            //  perciò non può prenderla in automatico durante il save standard
            //  comunque non sono sicuro che serva questo log
            if (false) {
                log.warn(gruppo, descrizione);
                Log logg = logger.newEntity("debug", LogType.versione.toString(), descrizione, null);
                logg.setCompany(company);
                try { // prova ad eseguire il codice
                    logger.save(logg);
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error(unErrore.toString());
                }// fine del blocco try-catch
            }// end of if/else cycle
        }// end of if cycle

    }// end of method


    /**
     * Creazione di una versione (se non trovata)
     * Creazione di una preferenza (se non trovata)
     * Log a video
     *
     * @param ordineVersione        di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param codePreferenza        sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     * @param typePreferenza        di dato memorizzato (obbligatorio)
     * @param descrizionePreferenza visibile (obbligatoria)
     * @param valuePreferenza       valore della preferenza (obbligatorio)
     */
    protected void creaPreferenzaAndVersioneAndLog(
            int ordineVersione,
            String codePreferenza,
            PrefType typePreferenza,
            String descrizionePreferenza,
            Object valuePreferenza) {
        creaPreferenzaAndVersioneAndLog(
                ordineVersione,
                "",
                codePreferenza,
                typePreferenza,
                ARoleType.developer,
                descrizionePreferenza,
                valuePreferenza,
                false);
    }// end of method


    /**
     * Creazione di una versione (se non trovata)
     * Creazione di una preferenza (se non trovata)
     * Log a video
     *
     * @param ordineVersione        di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param siglaCompany          (facoltativa)
     * @param codePreferenza        sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     * @param typePreferenza        di dato memorizzato (obbligatorio)
     * @param descrizionePreferenza visibile (obbligatoria)
     * @param valuePreferenza       valore della preferenza (obbligatorio)
     */
    protected void creaPreferenzaAndVersioneAndLog(
            int ordineVersione,
            String siglaCompany,
            String codePreferenza,
            PrefType typePreferenza,
            String descrizionePreferenza,
            Object valuePreferenza) {
        creaPreferenzaAndVersioneAndLog(
                ordineVersione,
                siglaCompany,
                codePreferenza,
                typePreferenza,
                ARoleType.developer,
                descrizionePreferenza,
                valuePreferenza,
                false);
    }// end of method


    /**
     * Creazione di una versione (se non trovata)
     * Creazione di una preferenza (se non trovata)
     * Log a video
     *
     * @param ordineVersione        di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param siglaCompany          (facoltativa)
     * @param codePreferenza        sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     * @param typePreferenza        di dato memorizzato (obbligatorio)
     * @param levelPreferenza       di accesso alla preferenza (obbligatorio)
     * @param descrizionePreferenza visibile (obbligatoria)
     * @param valuePreferenza       valore della preferenza (obbligatorio)
     * @param riavvioPreferenza     riavvio del programma per avere effetto (obbligatorio, di default false)
     */
    protected void creaPreferenzaAndVersioneAndLog(
            int ordineVersione,
            String siglaCompany,
            String codePreferenza,
            PrefType typePreferenza,
            ARoleType levelPreferenza,
            String descrizionePreferenza,
            Object valuePreferenza,
            boolean riavvioPreferenza) {
        Company company = null;
        Preferenza preferenza = null;
        Versione versione = null;
        String gruppo = "Pref";
        String descrizioneVersione = "Creazione della preferenza " + codePreferenza + " di tipo " + typePreferenza;
        String note = "Senza company specifica, perché è un valore di default \nPuò essere modificato nella singola company";

//        if (LibText.isValid(siglaCompany)) {
        company = companyService.findBySigla(siglaCompany);
//        }// end of if cycle

        preferenza = preferenzaService.findOrCrea(
                0,
                codePreferenza,
                typePreferenza,
                levelPreferenza,
                descrizionePreferenza,
                typePreferenza.objectToBytes(valuePreferenza),
                riavvioPreferenza);
        preferenza.setCompany(company);
        try { // prova ad eseguire il codice
            preferenzaService.save(preferenza);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch

        versione = service.findOrCrea(ordineVersione, gruppo, descrizioneVersione);
        versione.setCompany(company);
        versione.note = note;
        try { // prova ad eseguire il codice
            service.save(versione);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch

        logger.warn(LogType.versione.toString(), descrizioneVersione);
    }// end of method


}// end of class
