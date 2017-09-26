package it.algos.springvaadin.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Log delle versioni, modifiche e patch installate
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 * <p>
 * ATTENZIONE: in questa fase NON sono disponibili le Librerie e le classi che dipendono dalla UI e dalla Session
 */
@SpringComponent
@Slf4j
public class VersioneSpringBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    protected VersioneService service;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneSpringBoot(@Qualifier(Cost.TAG_VERS) AlgosService service) {
        this.service = (VersioneService) service;
    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizializza() {
        cronistoriaVersioni();
    }// end of method


    /**
     * Cronistoria delle versioni istallate
     * Viene sovrascritto dalla sottoclasse
     * Rimanda alla superclasse PRIMA di essere eseguito nella sottoclasse
     */
    protected int cronistoriaVersioni() {
        int k = 1;

        //--prima installazione del programma
        //--non fa nulla, solo informativo
        if (service.isVersioneInesistente(k++)) {
            creaAndLog("Setup", "Creazione ed installazione iniziale dell'applicazione");
        }// fine del blocco if

        if (service.isVersioneInesistente(k++)) {
            creaAndLog("Flag", "Regolazione dei flags di controllo");
        }// fine del blocco if

        return k;
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     */
    protected void creaAndLog(String titolo, String descrizione) {
        log.warn("Versione: " + service.crea(titolo, descrizione));
    }// end of method

}// end of class
