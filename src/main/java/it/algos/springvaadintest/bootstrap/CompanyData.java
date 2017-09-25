package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
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


    //--il service (contenente la repository) viene iniettato qui
    @Autowired
    protected CompanyService service;

    @Autowired
    protected IndirizzoService indirizzoService;

//    @Autowired
//    protected StatoData statoData;
//
//    @Autowired
//    protected IndirizzoData indirizzoData;


    /**
     * Creazione di una collezione di indirizzi
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
        List<Indirizzo> lista = indirizzoService.findAll();
        assert lista != null;

        if (lista.size() > 0) {
            creaAndLog("crf", "Croce Rossa Fidenza", lista.get(0));
        }// end of if cycle

        if (lista.size() > 1) {
            creaAndLog("crtp", "Croce Rossa Ponte Taro", lista.get(1));
        }// end of if cycle

        if (lista.size() > 2) {
            creaAndLog("pap", "Pubblica Assistenza Pianoro", lista.get(2));
        }// end of if cycle

        if (lista.size() > 3) {
            creaAndLog("gaps", "Gruppo Accoglienza Pronto Soccorso", lista.get(3));
        }// end of if cycle
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


}// end of class
