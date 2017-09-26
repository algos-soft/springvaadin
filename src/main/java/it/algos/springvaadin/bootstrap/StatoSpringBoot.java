package it.algos.springvaadin.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibFile;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Project springwam
 * Created by Algos
 * User: gac
 * Date: dom, 24-set-2017
 * Time: 18:18
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 *
 * ATTENZIONE: in questa fase NON sono disponibili le Librerie e le classi che dipendono dalla UI e dalla Session
 */
@SpringComponent
@Slf4j
public class StatoSpringBoot {


    //--il service (contenente la repository) viene iniettato nel costruttore
    private StatoService service;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public StatoSpringBoot(@Qualifier(Cost.TAG_STA) AlgosService service) {
        this.service = (StatoService) service;
    }// end of Spring constructor


    /**
     * Crea una collezione di stati
     * Controlla se la collezione esiste già
     * <p>
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
//    @PostConstruct
    public void inizializza() {
        if (service.count() < 2) {
            service.creaStati();
        } else {
            log.info("La collezione di stati è presente");
        }// end of if/else cycle
    }// end of method


}// end of class
