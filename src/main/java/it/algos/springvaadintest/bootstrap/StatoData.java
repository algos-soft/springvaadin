package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.lib.LibFile;
import it.algos.springvaadin.lib.LibText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 */
@SpringComponent
@Slf4j
public class StatoData {


    //--il service (contenente la repository) viene iniettato qui
    @Autowired
    protected StatoService service;


    /**
     * Crea una collezione di stati
     */
    public void creaAll() {
        if (nessunRecordEsistente()) {
            creaStati();
        } else {
            log.info("La collezione di stati è presente");
        }// end of if/else cycle
    }// end of method


    /**
     * Controlla se la collezione esiste già
     */
    private boolean nessunRecordEsistente() {
        return service.count() == 0;
    }// end of method


    /**
     * Creazione di una collezione di stati
     */
    public void creaStati() {
        String fileName = "Stati";
        List<String> righe = LibFile.readResources(fileName);
        service.deleteAll();

        for (String riga : righe) {
            if (creaStato(riga)) {
                log.warn("Stato: " + riga);
            }// end of if cycle
        }// end of for cycle
    }// end of method

    /**
     * Creazione di un singolo stato
     */
    public boolean creaStato(String riga) {
        String[] parti = riga.split(",");
        Stato stato;
        int ordine = 0;
        String nome = "";
        String alfaDue = "";
        String alfaTre = "";
        String numerico = "";

        if (parti.length > 0) {
            nome = parti[0];
        }// end of if cycle
        if (parti.length > 1) {
            alfaDue = parti[1];
        }// end of if cycle
        if (parti.length > 2) {
            alfaTre = parti[2];
        }// end of if cycle
        if (parti.length > 3) {
            numerico = parti[3];
        }// end of if cycle

        stato = service.newEntity(ordine, nome, alfaDue, alfaTre, numerico);

        try { // prova ad eseguire il codice
            stato = (Stato) service.save(stato);

        } catch (Exception unErrore) { // intercetta l'errore
            int a=87;
        }// fine del blocco try-catch

        return stato != null;
    }// end of method

}// end of class
