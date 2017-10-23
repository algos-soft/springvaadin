package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.abotstrrappe.VersioneBoot;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.entity.log.LogType;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@SpringComponent
@Slf4j
public class VersioneSpringVaadinBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    private VersioneService service;


    //--il service (contenente la repository) viene iniettato nel costruttore
    private CompanyService companyService;


    //--il service (contenente la repository) viene iniettato nel costruttore
    private LogService logger;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneSpringVaadinBoot(
            @Qualifier(Cost.TAG_VERS) AlgosService service,
            @Qualifier(Cost.TAG_COMP) AlgosService companyService,
            @Qualifier(Cost.TAG_LOG) AlgosService logger) {
        this.service = (VersioneService) service;
        this.companyService = (CompanyService) companyService;
        this.logger = (LogService) logger;
    }// end of Spring constructor


    /**
     * Running logic after the Spring context has been initialized
     * Any class that use this @EventListener annotation,
     * will be executed before the application is up and its onApplicationEvent method will be called
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.cronistoriaVersioni();
    }// end of method


    /**
     * Cronistoria delle versioni istallate nell'applicazione
     */
    private void cronistoriaVersioni() {
        int k = 1;

        //--prima installazione del programma
        //--non fa nulla, solo informativo
        if (service.ordineNonAncoraUsato(k)) {
            creaAndLog(k,
                    "Setup",
                    "Creazione ed installazione iniziale dell'applicazione",
                    "Senza company specifica, perché è un'operazione valida per tutte le companies");
        }// fine del blocco if
        k++;

        if (service.ordineNonAncoraUsato(k)) {
            creaAndLog(k,
                    "Flag",
                    "Regolazione di alcuni flags di controllo generali per l'applicazione",
                    "Senza company specifica, perché è un'operazione valida per tutte le companies");
        }// fine del blocco if
        k++;

        if (service.ordineNonAncoraUsato(k)) {
            creaAndLog(k,
                    "Company",
                    "Creazione di una nuova company \"crf\"");
        }// fine del blocco if
        k++;

        if (service.ordineNonAncoraUsato(k)) {
            creaAndLogCompany(k,
                    "demo",
                    "Company",
                    "Creazione iniziale della company con i dati demo");
        }// fine del blocco if
        k++;

    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine      di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     */
    private void creaAndLog(int ordine, String gruppo, String descrizione) {
        creaAndLog(ordine, "", gruppo, descrizione, "");
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
    private void creaAndLogCompany(int ordine, String siglaCompany, String gruppo, String descrizione) {
        creaAndLog(ordine, siglaCompany, gruppo, descrizione, "");
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
    private void creaAndLog(int ordine, String gruppo, String descrizione, String note) {
        creaAndLog(ordine, "", gruppo, descrizione, note);
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
    private void creaAndLog(int ordine, String siglaCompany, String gruppo, String descrizione, String note) {
        Company company;
        Versione vers = service.findOrCrea(ordine, gruppo, descrizione);

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
        }// end of if cycle

        if (LibText.isValid(note)) {
            vers.note = note;
            try { // prova ad eseguire il codice
                service.save(vers);
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
        }// end of if cycle

        logger.warn(LogType.versione, descrizione);
    }// end of method

}// end of class
