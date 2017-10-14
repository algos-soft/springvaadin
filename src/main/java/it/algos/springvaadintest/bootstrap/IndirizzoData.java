package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.entity.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 */
@SpringComponent
@Slf4j
public class IndirizzoData {


//    //--il service (contenente la repository) viene iniettato qui
//    @Autowired
//    protected IndirizzoService service;
//
//    //--il service (contenente la repository) viene iniettato qui
//    @Autowired
//    protected StatoService statoService;
//
//
//    /**
//     * Creazione di una collezione di indirizzi
//     */
//    public void creaAll() {
//        if (nessunRecordEsistente()) {
//            creaIndirizzi();
//        } else {
//            log.info("La collezione di indirizzi è presente");
//        }// end of if/else cycle
//    }// end of method
//
//
//    /**
//     * Controlla se la collezione esiste già
//     */
//    private boolean nessunRecordEsistente() {
//        return service.count() == 0;
//    }// end of method
//
//
//    /**
//     * Crea una collezione di indirizzi
//     */
//    private void creaIndirizzi() {
//        Stato statoDefault = statoService.findByNome("Italia");
//        assert statoDefault != null;
//        Stato statoTest = statoService.findByNome("Francia");
//        assert statoTest != null;
//
//        creaAndLog("Viale dei Tigli, 37", "Bologna", "34100", statoDefault);
//        creaAndLog("Via San Sisto, 1", "Milano", "20100", statoTest);
//        creaAndLog("Largo Donegani, 8", "Parma", "98234", statoDefault);
//        creaAndLog("Piazzale Belvedere, 2", "Firenze", "60200", statoDefault);
//    }// end of method
//
//
//    /**
//     * Creazione di una entity di indirizzo
//     * Log a video
//     *
//     * @param indirizzo: via, nome e numero (obbligatoria, non unica)
//     * @param localita:  località (obbligatoria, non unica)
//     * @param cap:       codice di avviamento postale (obbligatoria, non unica)
//     * @param stato:     stato (obbligatoria, non unica)
//     */
//    private AEntity creaAndLog(String indirizzo, String localita, String cap, Stato stato) {
//        AEntity ind = service.crea(indirizzo, localita, cap, stato);
//        log.warn("Indirizzo: " + ind);
//        return ind;
//    }// end of method

}// end of class
