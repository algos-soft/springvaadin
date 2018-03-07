package it.algos.springtemplates.scripts;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springtemplates.enumeration.NomeClasse;
import it.algos.springtemplates.enumeration.Token;
import it.algos.springvaadin.annotation.AIView;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.service.AFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 06-mar-2018
 * Time: 08:35
 */
@Slf4j
@Scope("session")
@SpringComponent()
public class TElabora {


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public AFileService file;

    private static String PATH = "/Users/gac/Documents/IdeaProjects/springvaadin/src/main/java/it/algos/springtemplates/";
    private static String SOURCES = PATH + "sources/";
    private static String ENTITY = PATH + "entity/";


    public TElabora() {
    }// end of constructor


    /**
     * Creazione completa del package
     * Crea una directory
     * Crea i files previsti nella enumeration
     */
    public void newPackage(String nomePackage, boolean usaCompany) {
        this.creaDirectory(nomePackage);
        this.creaClassi(nomePackage, usaCompany);
    }// end of method


    private void creaDirectory(String nomePackage) {
        nomePackage = nomePackage.toLowerCase();

        if (new File(ENTITY + nomePackage).mkdir()) {
            System.out.println("La directory " + nomePackage + " è stata creata");
        } else {
            System.out.println("La directory " + nomePackage + " esisteva già");
        }// end of if/else cycle
    }// end of method


    private void creaClassi(String nomePackage, boolean usaCompany) {
        for (NomeClasse nome : NomeClasse.values()) {
        }// end of for cycle
        creaClasse(NomeClasse.entity,nomePackage);
    }// end of method


    private void creaClasse(NomeClasse nomeClasse,String nomePackage) {
        String nomeFile=nomeClasse.toString();
        nomeFile="Entity.txt";
        String suffisso="";
        String suffixJava=".java";
        String nomeFileSource=SOURCES+nomeFile;
        String sourceText=file.leggeFile(SOURCES+"Entity.txt");
        Map<Token,String> mappa=new HashMap<>();
        mappa.put(Token.lowerProject,"springtemplates");
        mappa.put(Token.packageName,nomePackage.toLowerCase());
        mappa.put(Token.importCost,"");
        mappa.put(Token.user,"Gac");
        mappa.put(Token.today, LocalDate.now().toString());
        mappa.put(Token.tag,"ACost.TAG_ROL");
        mappa.put(Token.entity,"Ruolo");
        mappa.put(Token.entitySuperclass,"AEntity");
        String replacedText= Token.replaceAll(sourceText,mappa);
        file.scriveFile(ENTITY+"ruolo"+"/"+"Ruolo"+suffixJava,replacedText,true);
        int a=87;

//        String text=readFile(nomeFileSource);
    }// end of method


}// end of class
