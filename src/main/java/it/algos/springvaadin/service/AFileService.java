package it.algos.springvaadin.service;

import com.vaadin.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.nio.file.Files;

import static javax.script.ScriptEngine.FILENAME;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 06-mar-2018
 * Time: 09:54
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class AFileService {


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public ATextService text;

    /**
     * Default constructor
     */
    public AFileService() {
    }// end of constructor


    /**
     * Controlla l'esistenza di un file
     *
     * @param nameFileToBeChecked nome completo del file
     *
     * @return true se il file esiste
     * false se non è un file o se non esiste
     */
    public boolean isEsisteFile(String nameFileToBeChecked) {
        return isEsisteFile(new File(nameFileToBeChecked));
    }// end of method


    /**
     * Controlla l'esistenza di un file
     *
     * @param fileToBeChecked file col path completo
     *
     * @return true se il file esiste
     * false se non è un file o se non esiste
     */
    public boolean isEsisteFile(File fileToBeChecked) {
        boolean status = false;

        status = fileToBeChecked.exists();
        if (status) {
            if (fileToBeChecked.isFile()) {
                System.out.println("Il file " + fileToBeChecked + " esiste");
            } else {
                System.out.println(fileToBeChecked + " non è un file");
                status = false;
            }// end of if/else cycle
        } else {
            System.out.println("Il file " + fileToBeChecked + " non esiste");
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Controlla l'esistenza di una directory
     *
     * @param nameDirectoryToBeChecked nome completo della directory
     *
     * @return true se la directory esiste
     * false se non è una directory o se non esiste
     */
    public boolean isEsisteDirectory(String nameDirectoryToBeChecked) {
        return isEsisteDirectory(new File(nameDirectoryToBeChecked));
    }// end of method


    /**
     * Controlla l'esistenza di una directory
     *
     * @param directoryToBeChecked file col path completo
     *
     * @return true se la directory esiste
     * false se non è una directory o se non esiste
     */
    public boolean isEsisteDirectory(File directoryToBeChecked) {
        boolean status = false;

        status = directoryToBeChecked.exists();
        if (status) {
            if (directoryToBeChecked.isDirectory()) {
                System.out.println("La directory " + directoryToBeChecked + " esiste");
            } else {
                System.out.println(directoryToBeChecked + " non è una directory");
                status = false;
            }// end of if/else cycle
        } else {
            System.out.println("La directory " + directoryToBeChecked + " non esiste");
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Cancella un file
     *
     * @param nameFileToBeDeleted nome completo del file
     */
    public boolean deleteFile(String nameFileToBeDeleted) {
        return deleteFile(new File(nameFileToBeDeleted));
    }// end of method


    /**
     * Cancella un file
     *
     * @param fileToBeDeleted file col path completo
     *
     * @return true se il file è stata cancellata
     * false se non è stato cancellato o se non esiste
     */
    public boolean deleteFile(File fileToBeDeleted) {
        boolean status = false;

        status = fileToBeDeleted.exists();
        if (status) {
            if (fileToBeDeleted.isFile()) {
                status = fileToBeDeleted.delete();
                if (status) {
                    System.out.println("Il file " + fileToBeDeleted + " è stata cancellato");
                } else {
                    System.out.println("Il file " + fileToBeDeleted + " non è stata cancellato, perché non ci sono riuscito");
                }// end of if/else cycle
            } else {
                System.out.println(fileToBeDeleted + " non è stato cancellato, perché non è un file");
                status = false;
            }// end of if/else cycle
        } else {
            System.out.println("Il file " + fileToBeDeleted + " non è stata cancellato, perché non esiste");
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Cancella una directory
     *
     * @param nameDirectoryToBeDeleted nome completo della directory
     */
    public boolean deleteDirectory(String nameDirectoryToBeDeleted) {
        return deleteDirectory(new File(nameDirectoryToBeDeleted.toLowerCase()));
    }// end of method


    /**
     * Cancella una directory
     *
     * @param directoryToBeDeleted file col path completo
     *
     * @return true se la directory è stata cancellata
     * false se non è stata cancellata o se non esiste
     */
    public boolean deleteDirectory(File directoryToBeDeleted) {
        boolean status = false;

        status = directoryToBeDeleted.exists();
        if (status) {
            if (directoryToBeDeleted.isDirectory()) {
                status = FileSystemUtils.deleteRecursively(directoryToBeDeleted);
                if (status) {
                    System.out.println("La directory " + directoryToBeDeleted + " è stata cancellata");
                } else {
                    System.out.println("La directory " + directoryToBeDeleted + " non è stata cancellata, perché non ci sono riuscito");
                }// end of if/else cycle
            } else {
                System.out.println(directoryToBeDeleted + " non è stato cancellato, perché non è una directory");
                status = false;
            }// end of if/else cycle
        } else {
            System.out.println("La directory " + directoryToBeDeleted + " non è stata cancellata, perché non esiste");
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Crea un file
     *
     * @param nameFileToBeCreated nome completo del file
     */
    public boolean creaFile(String nameFileToBeCreated) {
        return creaFile(new File(nameFileToBeCreated));
    }// end of method


    /**
     * Crea un file
     *
     * @param fileToBeCreated file col path completo
     */
    public boolean creaFile(File fileToBeCreated) {
        boolean status = false;

        status = fileToBeCreated.exists();
        if (status) {
            System.out.println("Il file " + fileToBeCreated + " non è stato creato, perché esiste già");
        } else {
            try { // prova ad eseguire il codice
                status = fileToBeCreated.createNewFile();
                System.out.println("Il file " + fileToBeCreated + " è stato creato");
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
                System.out.println("Non è stato possibile creare il file " + fileToBeCreated);
            }// fine del blocco try-catch
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Crea una directory
     *
     * @param nomeCompletoDirectory nome completo della directory
     */
    public boolean creaDirectory(String nomeCompletoDirectory) {
        boolean creata = false;

        creata = new File(nomeCompletoDirectory).mkdir();
        if (creata) {
            System.out.println("La directory " + nomeCompletoDirectory + " è stata creata");
        } else {
            System.out.println("La directory " + nomeCompletoDirectory + " esisteva già, oppure non è stato possibile crearla");
        }// end of if/else cycle

        return creata;
    }// end of method


    /**
     * Scrive un file
     * Se non esiste, lo crea
     *
     * @param nameFileToBeWritten nome completo del file
     * @param text                contenuto del file
     */
    public boolean scriveFile(String nameFileToBeWritten, String text) {
        return scriveFile(nameFileToBeWritten, text, false);
    }// end of method


    /**
     * Scrive un file
     * Se non esiste, lo crea
     *
     * @param nameFileToBeWritten nome completo del file
     * @param text                contenuto del file
     * @param sovrascrive         anche se esiste già
     */
    public boolean scriveFile(String nameFileToBeWritten, String text, boolean sovrascrive) {
        boolean status = false;
        File fileToBeWritten;
        FileWriter fileWriter;

        if (isEsisteFile(nameFileToBeWritten)) {
            if (sovrascrive) {
                status = sovraScriveFile(nameFileToBeWritten, text);
                System.out.println("Il file " + nameFileToBeWritten + " esisteva già ed è stato aggiornato");
            } else {
                System.out.println("Il file " + nameFileToBeWritten + " esisteva già e non è stato modificato");
                return false;
            }// end of if/else cycle
        } else {
            status = creaFile(nameFileToBeWritten);
            if (status) {
                status = sovraScriveFile(nameFileToBeWritten, text);
                System.out.println("Il file " + nameFileToBeWritten + " non esisteva ed è stato creato");
            } else {
                System.out.println("Il file " + nameFileToBeWritten + " non esisteva e non è stato creato");
                return false;
            }// end of if/else cycle
        }// end of if/else cycle

        return status;
    }// end of method


    /**
     * Sovrascrive un file
     *
     * @param nameFileToBeWritten nome completo del file
     * @param text                contenuto del file
     */
    public boolean sovraScriveFile(String nameFileToBeWritten, String text) {
        boolean status = false;
        File fileToBeWritten;
        FileWriter fileWriter = null;

        if (isEsisteFile(nameFileToBeWritten)) {
            fileToBeWritten = new File(nameFileToBeWritten);
            try { // prova ad eseguire il codice
                fileWriter = new FileWriter(fileToBeWritten);
                fileWriter.write(text);
                fileWriter.flush();
                status = true;
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            } finally {
                try { // prova ad eseguire il codice
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error(unErrore.toString());
                }// fine del blocco try-catch
            }// fine del blocco try-catch-finally
        } else {
            System.out.println("Il file " + nameFileToBeWritten + " non esiste e non è stato creato");
        }// end of if/else cycle

        return status;
    }// end of method

    /**
     * Legge un file
     *
     * @param nameFileToBeRead nome completo del file
     */
    public String leggeFile(String nameFileToBeRead) {
        String testo = "";
        String aCapo = "\n";
        String currentLine;

        //-- non va, perché se arriva it/algos/Alfa.java becca anche il .java
//        nameFileToBeRead=  nameFileToBeRead.replaceAll("\\.","/");

        try (BufferedReader br = new BufferedReader(new FileReader(nameFileToBeRead))) {
            while ((currentLine = br.readLine()) != null) {
                testo += currentLine;
                testo += "\n";
            }// fine del blocco while

            testo = text.levaCoda(testo, aCapo);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch

        return testo;
    }// end of method

}// end of class
