package it.algos.springvaadintest.bootstrap;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoService;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoService;
import it.algos.springvaadin.lib.LibFile;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.lib.LibText;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
        String bandiera = "";

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

//        bandiera = getEncodedImmge(alfaTre + ".png");
        stato = service.newEntity(ordine, nome, alfaDue, alfaTre, numerico,"");

        try { // prova ad eseguire il codice
            stato = (Stato) service.save(stato);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

////        service.alfa("AUS.png");
        return stato != null;
    }// end of method

//    /**
//     * Recupera una bandiera dalle risorse statiche
//     */
//    public String getEncodedImmge(String alfaTre) {
//        String asB64 = "niente immagine";
//        byte[] data = null;
//        data = LibResource.getImgBytes(alfaTre);
//
//        try { // prova ad eseguire il codice
//            String newFileName = "pippo";
//            Mongo mongo = new Mongo("localhost", 27017);
//            DB db = mongo.getDB("test");
//            GridFS gfsPhoto = new GridFS(db, "stato");
//            File imageFile = new File("/Users/gac/Documents/IdeaProjects/springvaadin/src/main/webapp/img/AUS.png");
//            GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
//            gfsFile.setFilename(newFileName);
//            gfsFile.save();
//
//        } catch (Exception unErrore) { // intercetta l'errore
//            int a=86;
//        }// fine del blocco try-catch
//
//
//        String newFileName = "pippo";
//        Mongo mongo = new Mongo("localhost", 27017);
//        DB db = mongo.getDB("test");
//        GridFS gfsPhoto = new GridFS(db, "stato");
//        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
//        System.out.println(imageForOutput);
//
////        if (data != null) {
////            asB64 = Base64.getEncoder().encodeToString(data);
////            asB64 = asB64.substring(0, 1000);
////        }// end of if cycle
//
//        return asB64;
//    }// end of method

}// end of class
