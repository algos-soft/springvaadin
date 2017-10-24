package it.algos.springvaadin.abotstrrappe;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.entity.log.LogType;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
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
    protected LogService logger;


    /**
     * Flag per assicurarsi che questa classe di inizializzazione venga eseguita PRIMA di un'eventuale sottoclasse
     * Questo dovuto al fatto che l'ordine di 'chiamata' delle classi che usano l'Annotation @EventListener,
     * non è controllabile
     */
    protected boolean classeVersioneBootAncoraDaEseguire = true;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneBoot(
            @Qualifier(Cost.TAG_VERS) AlgosService service,
            @Qualifier(Cost.TAG_LOG) AlgosService logger) {
        this.service = (VersioneService) service;
        this.logger = (LogService) logger;
    }// end of Spring constructor


    /**
     * Running logic after the Spring context has been initialized
     * Any class that use this @EventListener annotation,
     * will be executed before the application is up and its onApplicationEvent method will be called
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.classeVersioneBootAncoraDaEseguire) {
            this.aggiornaVersioniBase();
        }// end of if cycle
    }// end of method


    /**
     * Prima vengono create le versioni base del framework
     * Una eventuale sottoclasse, dopo aver controllato che il metodo inizializzaCronistoriaVersioni()
     * sia stato eseguito una ed una sola volta, può aggiungere ulteriori versioni, mantenendo la corretta
     * numerazione progressiva che è indispensabile per valutare se la versione è già stata eseguita o meno
     */
    protected void aggiornaVersioniBase() {
        this.versioniBase();
        this.classeVersioneBootAncoraDaEseguire = false;
    }// end of method


    /**
     * Cronistoria delle versioni istallate nel framework
     */
    private void versioniBase() {
        int k = 1;

        //--prima installazione del programma
        //--non fa nulla, solo informativo
        if (!service.esiste(k)) {
            creaAndLog(k++,
                    "Setup",
                    "Creazione ed installazione iniziale dell'applicazione",
                    "Senza company specifica, perché è un'operazione valida per tutte le companies");
        }// fine del blocco if

        if (!service.esiste(k)) {
            creaAndLog(k++,
                    "Flag",
                    "Regolazione di alcuni flags di controllo generali per l'applicazione",
                    "Senza company specifica, perché è un'operazione valida per tutte le companies");
        }// fine del blocco if
    }// end of method


    /**
     * Creazione di una entity (se non trovata)
     * Log a video
     *
     * @param ordine      di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     */
    protected void creaAndLog(int ordine, String gruppo, String descrizione) {
        creaAndLog(ordine, gruppo, descrizione, "");
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
    protected void creaAndLog(int ordine, String gruppo, String descrizione, String note) {
        Versione vers = service.findOrCrea(ordine, gruppo, descrizione);

        if (LibText.isValid(note)) {
            vers.note = note;
            try { // prova ad eseguire il codice
                service.save(vers);
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
        }// end of if cycle

        logger.warn(LogType.versione.toString(), descrizione);
    }// end of method

}// end of class
