package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.entity.persona.PersonaService;
import it.algos.springvaadin.entity.stato.Stato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
public class CompanyData {


    @Autowired
    protected CompanyService service;

    @Autowired
    protected PersonaService personaService;

    @Autowired
    protected IndirizzoService indirizzoService;


    /**
     * Creazione di una collezione
     */
    public void creaAll() {
        if (nessunRecordEsistente()) {
            creaCompany();
        } else {
            log.info("La collezione di company è presente");
        }// end of if/else cycle
    }// end of method


    /**
     * Controlla se la collezione esiste già
     */
    private boolean nessunRecordEsistente() {
        return service.count() == 0;
    }// end of method


    /**
     * Crea una collezione di company
     */
    private void creaCompany() {
        creaAndLog(
                "demo",
                "Algos s.r.l.",
                personaService.newEntity("Mario", "Rossi"),
                "mariorossi@win.com",
                indirizzoService.newEntity("via Soderini, 55", "Milano", "20131", (Stato) null));

        creaAndLog(
                "test",
                "Associazione Volontaria di Misericordia",
                personaService.newEntity("Marcello", "Tamburini"),
                "presidente@associazioneroverasco.it",
                indirizzoService.newEntity("piazza Libertà", "Roverasco", "35117", (Stato) null));
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param contact     persona di riferimento (facoltativo)
     * @param email       delal company (facoltativo)
     * @param indirizzo   della company (facoltativo)
     */
    private void creaAndLog(String sigla, String descrizione, Persona contact, String email, Indirizzo indirizzo) {
        service.findOrCrea(sigla, descrizione, contact, email, indirizzo);
        log.warn("Company: " + sigla);
    }// end of method


}// end of class
