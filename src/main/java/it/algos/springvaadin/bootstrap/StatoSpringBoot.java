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
    @PostConstruct
    public void inizializza() {
        if (service.count() < 2) {
            creaStati();
        } else {
            log.info("La collezione di stati è presente");
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione di una collezione di stati
     */
    public void creaStati() {
        String fileName = "Stati";
        List<String> righe = LibFile.readResources(fileName);
        service.deleteAll();

        for (String riga : righe) {
            creaStato(riga);
        }// end of for cycle
    }// end of method

    /**
     * Creazione di un singolo stato
     */
    private boolean creaStato(String riga) {
        String[] parti = riga.split(",");
        Stato stato;
        int ordine = 0;
        String nome = "";
        String alfaDue = "";
        String alfaTre = "";
        String numerico = "";
        byte[] bandiera = null;

        if (parti.length > 0) {
            nome = parti[0];
        }// end of if cycle
        if (parti.length > 1) {
            alfaDue = parti[1];
        }// end of if cycle
        if (parti.length > 2) {
            alfaTre = parti[2];
            bandiera = getImageBytes(alfaTre.toUpperCase());
        }// end of if cycle
        if (parti.length > 3) {
            numerico = parti[3];
        }// end of if cycle

        stato = service.newEntity(ordine, nome, alfaDue, alfaTre, numerico, bandiera);

        try { // prova ad eseguire il codice
            stato = (Stato) service.save(stato);
            if (bandiera == null || bandiera.length == 0) {
                log.warn("Stato: " + riga + " - Manca la bandiera");
            } else {
                log.info("Stato: " + riga + " - Tutto OK");
            }// end of if/else cycle
        } catch (Exception unErrore) { // intercetta l'errore
            try { // prova ad eseguire il codice
                stato = service.newEntity(ordine, nome, alfaDue, alfaTre, numerico, new byte[0]);
                log.warn("Stato: " + riga + " - Dimensioni bandiera eccessive");
            } catch (Exception unErrore2) { // intercetta l'errore
                log.error("Stato: " + riga + " - Non sono riuscito a crearlo");
            }// fine del blocco try-catch
        }// fine del blocco try-catch

        return stato != null;
    }// end of method

    /**
     * Recupera una bandiera dalle risorse statiche
     */
    private byte[] getImageBytes(String alfaTre) {
        byte[] imgBytes = new byte[0];
        File filePath;
//        String realPath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        String realPath = "/Users/gac/Documents/IdeaProjects/springvaadin/src/main/webapp/img/";
        String name = alfaTre.toUpperCase();
        String fullPath = realPath + name + ".png";

        try { // prova ad eseguire il codice
            filePath = new File(fullPath);
            if (filePath.exists() && !filePath.isDirectory()) {
                imgBytes = Files.readAllBytes(Paths.get(fullPath));
            }// end of if cycle
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return imgBytes;
    }// end of method

}// end of class
