package it.algos.springtemplates.scripts;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springtemplates.enumeration.Chiave;
import it.algos.springtemplates.enumeration.Componente;
import it.algos.springtemplates.enumeration.Progetto;
import it.algos.springtemplates.enumeration.Token;
import it.algos.springvaadin.service.AFileService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.File;
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

    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    public ATextService text;

    private static String SEP = "/";
    private static String PATH_MAIN = "/src/main";
    private static String PATH_JAVA = PATH_MAIN + "/java/it/algos";
    private static String MODULO_BASE = "springvaadin";
    private static String MODULO_TEMPLATES = "springtemplates";
    private static String NOME_SOURCES = "sources";
    private static String NOME_ENTITY = "entity";

    private String nameProject;             //--dal dialogo di input
    private String nameProjectLower;        //--dal dialogo di input
    private String nameModulo;              //--dalla enumeration Progetto
    private String namePackageLower;        //--dal dialogo di input
    private String nameEntityFirstUpper;    //--dal dialogo di input

    private String userDir;         //--di sistema
    private String pathRoot;        //--userDir meno MODULO_BASE
    private String pathProject;     //--pathRoot più nameProject (usato come radice per pom.xml e README.text)
    private String pathMain;        //--pathProject più PATH_MAIN (usato come radice per resources e webapp)
    private String pathModulo;      //--pathMain più PATH_JAVA più nameProject (usato come radice per i files java)
    private String pathEntity;      //--pathModulo più NOME_ENTITY
    private String pathPackage;     //--pathEntity più namePackage

    private String pathSource;      //--pathRoot più PATH_JAVA più MODULO_TEMPLATES più NOME_SOURCES

    public TElabora() {
    }// end of constructor


    /**
     * Creazione completa del package
     * Crea una directory
     * Crea i files previsti nella enumeration
     */
    public void newPackage(Map<Chiave, Object> mappaInput) {
        this.regola();
        this.regolaProgetto(mappaInput);
        this.creaDirectory(mappaInput);
        this.creaClassi(mappaInput);
    }// end of method


    /**
     * Regolazioni iniziali indipendenti dal dialogo di input
     */
    private void regola() {
        this.userDir = System.getProperty("user.dir");
        this.pathRoot = text.levaCodaDa(userDir, SEP);

        this.pathSource = userDir + SEP + PATH_JAVA + SEP + MODULO_TEMPLATES + SEP + NOME_SOURCES;
    }// end of method


    /**
     * Regolazioni iniziali con i valori del dialogo di input
     */
    private void regolaProgetto(Map<Chiave, Object> mappaInput) {
        Progetto progetto;

        if (mappaInput.containsKey(Chiave.nameProject)) {
            progetto = (Progetto) mappaInput.get(Chiave.nameProject);
            if (progetto != null) {
                this.nameProject = progetto.getNameProject();
                this.nameProjectLower = nameProject.toLowerCase();
                this.nameModulo = progetto.getNameModulo();
                this.pathProject = pathRoot + SEP + nameProject;
                this.pathModulo = pathProject + PATH_JAVA + SEP + nameModulo;
                this.pathEntity = pathModulo + SEP + NOME_ENTITY;
            }// end of if cycle
        }// end of if cycle

    }// end of method


    private boolean creaDirectory(Map<Chiave, Object> mappaInput) {

        if (mappaInput.containsKey(Chiave.namePackageLower)) {
            this.namePackageLower = (String) mappaInput.get(Chiave.namePackageLower);
        }// end of if cycle

        if (text.isValid(namePackageLower)) {
            this.pathPackage = pathEntity + SEP + namePackageLower;
            return file.creaDirectory(pathPackage);
        } else {
            return false;
        }// end of if/else cycle
    }// end of method


    private void creaClassi(Map<Chiave, Object> mappaInput) {
//        for (NomeClasse nome : NomeClasse.values()) {
//        }// end of for cycle
        creaClasse(Componente.entity, mappaInput);
    }// end of method


    private void creaClasse(Componente componente, Map<Chiave, Object> mappaInput) {
        String nomeFileTextSorgente = "";
        String pathFileTextSorgente = "";
        String pathClasseJava = "";
        String sourceText = "";

        if (mappaInput.containsKey(Chiave.nameEntityFirstUpper)) {
            this.nameEntityFirstUpper = (String) mappaInput.get(Chiave.nameEntityFirstUpper);
            if (mappaInput.containsKey(Chiave.usaCompany) && (boolean) mappaInput.get(Chiave.usaCompany)) {
                nomeFileTextSorgente = componente.getSourceNameCompany();
            } else {
                nomeFileTextSorgente = componente.getSourceName();
            }// end of if/else cycle
        } else {
            return;
        }// end of if/else cycle

        pathFileTextSorgente = pathSource + SEP + nomeFileTextSorgente;
        sourceText = file.leggeFile(pathFileTextSorgente);

        Map<Token, String> mappa = new HashMap<>();
        mappa.put(Token.lowerProject, nameProject);
        mappa.put(Token.lowerModulo, nameModulo);
        mappa.put(Token.packageName, namePackageLower);
        mappa.put(Token.importCost, "");
        mappa.put(Token.user, "Gac");
        mappa.put(Token.today, LocalDate.now().toString());
        mappa.put(Token.tag, "ACost.TAG_ROL");
        mappa.put(Token.entity, nameEntityFirstUpper);
        String replacedText = Token.replaceAll(sourceText, mappa);

        pathClasseJava = pathPackage + SEP + nameEntityFirstUpper + componente.getJavaClassName();
        file.scriveFile(pathClasseJava, replacedText, true);
    }// end of method


}// end of class
