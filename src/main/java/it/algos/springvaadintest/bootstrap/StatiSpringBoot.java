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
public class StatiSpringBoot {


    //--il service (contenente la repository) viene iniettato qui
    @Autowired
    protected StatoService service;


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void creaStati() {
        String fileName = "Stati";
        List<String> righe= LibFile.readResources(fileName);

        for (String stato : righe) {
            creaAndLog(stato);
        }// end of for cycle

    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     */
    private Stato creaAndLog(String nome) {
        Stato stato = service.crea(nome);
        log.warn("Stato: " + stato);
        return stato;
    }// end of method


}// end of class
