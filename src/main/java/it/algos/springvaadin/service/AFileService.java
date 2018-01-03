package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.lib.LibVaadin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@SpringComponent
@Scope("singleton")
public class AFileService {


    /**
     * Legge il contenuto di un file di testo
     *
     * @param nomeFile di testo
     *
     * @return testo diviso per righe
     */
    public List<String> readText(String nomeFile) {
        List<String> righe = null;
        String suffix = ".txt";
        String pathTxt = AlgosApp.RESOURCES_FOLDER_NAME + nomeFile + suffix;
        Path filePath = Paths.get(pathTxt);

        try { // prova ad eseguire il codice
            righe = Files.readAllLines(filePath);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return righe;
    }// end of method


//    /**
//     * Legge una risorsa
//     *
//     * @param nomeRisorsa
//     *
//     * @return testo diviso per righe
//     */
//    public  byte[] getImgBytes(String nomeRisorsa) {
//        List<String> righe = null;
//        String suffix = ".txt";
//        String pathTxt = AlgosApp.RESOURCES_FOLDER_NAME + nomeFile + suffix;
//        Path path = Paths.get(pathTxt);
//
//        try { // prova ad eseguire il codice
//            righe = Files.readAllLines(path);
//        } catch (Exception unErrore) { // intercetta l'errore
//        }// fine del blocco try-catch
//
//        return righe;
//    }// end of method


    /**
     * Legge una risorsa come byte array.
     *
     * @param nomeRisorsaConSuffisso
     *
     * @return il corrispondente byte array.
     */
    public  byte[] getImgBytes(String nomeRisorsaConSuffisso) {
        byte[] bytes = new byte[0];
        String pathTxt = AlgosApp.IMG_FOLDER_NAME + nomeRisorsaConSuffisso;
//        File filePath = new File(pathTxt);

        try { // prova ad eseguire il codice
            bytes = Files.readAllBytes(Paths.get(pathTxt));
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch

        return bytes;
    }// end of method


//    byte[] bytes = new byte[0];
//    String tag = "./";
//    String prefix = "src/main/webapp/VAADIN/img/";
//    String suffix = ".png";
//
//    String percorso = tag + prefix + alfaTre.toUpperCase() + suffix;
//
//    File filePath = new File(percorso);
//            if(filePath.exists()&&!filePath.isDirectory())
//
//    {
//        try { // prova ad eseguire il codice
//            bytes = Files.readAllBytes(Paths.get(percorso));
//        } catch (Exception unErrore) { // intercetta l'errore
//            log.error(unErrore.toString());
//        }// fine del blocco try-catch
//    }// end of if cycle


}// end of abstract static class
