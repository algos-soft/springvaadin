package it.algos.springtemplates.scripts;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springtemplates.enumeration.Chiave;
import it.algos.springtemplates.enumeration.Task;
import it.algos.springtemplates.enumeration.Progetto;
import it.algos.springtemplates.enumeration.Token;
import it.algos.springvaadin.annotation.AIScript;
import it.algos.springvaadin.lib.ACost;
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
    private static String JAVA_SUFFIX = ".java";
    private static String LIST_VIEW_SUFFIX = "List";
    private static String FORM_VIEW_SUFFIX = "Form";
    private static String TAG = "TAG_";
    private static String VIEW = "VIEW_";
    private static String NAME_COST = "Acost";
    private static String NAME_APP_COST = "AppCost";
    private static String IMPORT = "import it.algos.";
    private static String PATH_MAIN = "/src/main";
    private static String PATH_JAVA = PATH_MAIN + "/java/it/algos";
    private static String MODULO_BASE = "springvaadin";
    private static String MODULO_TEMPLATES = "springtemplates";
    private static String DIR_SOURCES = "sources";
    private static String DIR_ENTITY = "entity";
    private static String DIR_APP = "application";
    private static String DIR_LIB = "lib";
    private static String DIR_UI = "ui";


    private String nameProject;             //--dal dialogo di input
    private String nameProjectLower;        //--dal dialogo di input
    private String nameModulo;              //--dalla enumeration Progetto
    private String namePackageLower;        //--dal dialogo di input
    private String nameEntityFirstUpper;    //--dal dialogo di input
    private String tagBreveTreChar;         //--dal dialogo di input

    private String userDir;         //--di sistema
    private String pathRoot;        //--userDir meno MODULO_BASE
    private String pathProject;     //--pathRoot più nameProject (usato come radice per pom.xml e README.text)
    private String pathMain;        //--pathProject più PATH_MAIN (usato come radice per resources e webapp)
    private String pathModulo;      //--pathMain più PATH_JAVA più nameProject (usato come radice per i files java)
    private String pathApplication; //--pathModulo più DIR_APP
    private String pathUI;          //--pathModulo più DIR_UI
    private String pathEntity;      //--pathModulo più DIR_ENTITY
    private String pathPackage;     //--pathEntity più namePackage

    private String nameCost;        //--NAME_COST (springvaadin) o NAME_APP_COST (altri progetti)
    private String dirCost;         //--DIR_LIB (springvaadin) o DIR_APP (altri progetti)
    private String pathFileCost;    //--pathModulo più lib (springvaadin) o application (altri progetti)
    private String qualifier;       //--NAME_COST (springvaadin) o NAME_APP_COST (altri progetti) più TAG più tagBreveTreChar
    private String qualifierView;   //--NAME_COST (springvaadin) o NAME_APP_COST (altri progetti) più VIEW più tagBreveTreChar
    private String importCost;

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
        this.regolaTag(mappaInput);
        this.creaDirectory();
        this.creaTasks(mappaInput);
        this.addPackageMenu();
        this.addTagCostanti();
    }// end of method


    /**
     * Regolazioni iniziali indipendenti dal dialogo di input
     */
    private void regola() {
        this.userDir = System.getProperty("user.dir");
        this.pathRoot = text.levaCodaDa(userDir, SEP);

        this.pathSource = userDir + SEP + PATH_JAVA + SEP + MODULO_TEMPLATES + SEP + DIR_SOURCES;
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
                this.pathApplication = pathModulo + SEP + DIR_APP;
                this.pathEntity = pathModulo + SEP + DIR_ENTITY;
                this.pathUI = pathModulo + SEP + DIR_UI;
            }// end of if cycle
        }// end of if cycle

    }// end of method


    private void regolaTag(Map<Chiave, Object> mappaInput) {
        Progetto progetto;

        if (mappaInput.containsKey(Chiave.namePackageLower)) {
            this.namePackageLower = (String) mappaInput.get(Chiave.namePackageLower);
        }// end of if cycle

        if (mappaInput.containsKey(Chiave.tagBreveTreChar)) {
            this.tagBreveTreChar = (String) mappaInput.get(Chiave.tagBreveTreChar);
        }// end of if cycle

        if (mappaInput.containsKey(Chiave.nameProject)) {
            progetto = (Progetto) mappaInput.get(Chiave.nameProject);
            if (progetto != null) {
                if (progetto == Progetto.vaadin) {
                    nameCost = NAME_COST;
                    dirCost = DIR_LIB;
                } else {
                    nameCost = NAME_APP_COST;
                    dirCost = DIR_APP;
                }// end of if/else cycle
                qualifier = nameCost + "." + TAG + tagBreveTreChar;
                qualifierView = nameCost + "." + VIEW + tagBreveTreChar;
                importCost = IMPORT + nameModulo + "." + dirCost + "." + nameCost + ";";
                pathFileCost = pathModulo + "/" + dirCost + "/" + nameCost + JAVA_SUFFIX;
            }// end of if cycle
        }// end of if cycle
    }// end of method

    private boolean creaDirectory() {
        if (text.isValid(namePackageLower)) {
            this.pathPackage = pathEntity + SEP + namePackageLower;
            return file.creaDirectory(pathPackage);
        } else {
            return false;
        }// end of if/else cycle
    }// end of method


    private void creaTasks(Map<Chiave, Object> mappaInput) {
        for (Task task : Task.values()) {
            creaTask(task, mappaInput);
        }// end of for cycle
    }// end of method


    private void creaTask(Task task, Map<Chiave, Object> mappaInput) {
        String nomeFileTextSorgente = "";
        String pathFileTextSorgente = "";
        String sourceTemplatesText = "";

        if (mappaInput.containsKey(Chiave.nameEntityFirstUpper)) {
            this.nameEntityFirstUpper = (String) mappaInput.get(Chiave.nameEntityFirstUpper);
            if (mappaInput.containsKey(Chiave.usaCompany) && (boolean) mappaInput.get(Chiave.usaCompany)) {
                nomeFileTextSorgente = task.getSourceNameCompany();
            } else {
                nomeFileTextSorgente = task.getSourceName();
            }// end of if/else cycle
        } else {
            return;
        }// end of if/else cycle

        pathFileTextSorgente = pathSource + SEP + nomeFileTextSorgente;
        sourceTemplatesText = file.leggeFile(pathFileTextSorgente);

        this.checkAndWriteFile(task, mappaInput, sourceTemplatesText);
    }// end of method


    private void checkAndWriteFile(Task task, Map<Chiave, Object> mappaInput, String sourceTemplatesText) {
        String fileNameJava = "";
        String pathFileJava;
        String oldFileText = "";

        fileNameJava = nameEntityFirstUpper + task.getJavaClassName();
        pathFileJava = pathPackage + SEP + fileNameJava;

        if (mappaInput.containsKey(Chiave.usaSovrascrive) && (boolean) mappaInput.get(Chiave.usaSovrascrive)) {
            this.writeFile(pathFileJava, sourceTemplatesText);
            System.out.println(fileNameJava + " esisteva già ed è stato modificato");
        } else {
            oldFileText = file.leggeFile(pathFileJava);
            if (text.isValid(oldFileText)) {
                if (checkFile(oldFileText)) {
                    this.writeFile(pathFileJava, sourceTemplatesText);
                    System.out.println(fileNameJava + " esisteva già ed è stato modificato");
                } else {
                    System.out.println(fileNameJava + " esisteva già e NON è stato modificato");
                }// end of if/else cycle
            } else {
                this.writeFile(pathFileJava, sourceTemplatesText);
                System.out.println(fileNameJava + " non esisteva ed è stato creato");
            }// end of if/else cycle
        }// end of if/else cycle
    }// end of method


    private boolean checkFile(String oldFileText) {
        boolean sovrascrivibile = true;
        String fileNameJava = "";
        String pathFileJava;
        String tagUno = "@AIScript(sovrascrivibile";
        String tagDue = "=";
        String tagTre = ")";
        int posIni;
        int posEnd;
        String estratto;

        if (oldFileText.contains(tagUno)) {
            sovrascrivibile = false;
            posIni = oldFileText.indexOf(tagUno);
            posIni = oldFileText.indexOf(tagDue, posIni);
            posEnd = oldFileText.indexOf(tagTre, posIni);
            estratto = oldFileText.substring(posIni, posEnd);
            estratto = text.levaTesta(estratto, tagDue);

            if (estratto.equals("true")) {
                sovrascrivibile = true;
            }// end of if cycle
        }// end of if cycle

        return sovrascrivibile;
    }// end of method


    private void writeFile(String pathFileJava, String sourceTemplatesText) {
        String replacedText = "";
        replacedText = replaceText(sourceTemplatesText);
        file.scriveFile(pathFileJava, replacedText, true);
    }// end of method


    private String replaceText(String sourceText) {
        Map<Token, String> mappa = new HashMap<>();

        mappa.put(Token.lowerProject, nameProject);
        mappa.put(Token.lowerModulo, nameModulo);
        mappa.put(Token.packageName, namePackageLower);
        mappa.put(Token.user, "Gac");
        mappa.put(Token.today, LocalDate.now().toString());
        mappa.put(Token.tag, qualifier);
        mappa.put(Token.tagView, qualifierView);
        mappa.put(Token.entity, nameEntityFirstUpper);
        mappa.put(Token.importCost, importCost);

        return Token.replaceAll(sourceText, mappa);
    }// end of method


    private void addPackageMenu() {
        String fileNameUIClass = "";
        String pathFileUIClass = "";

        fileNameUIClass = text.primaMaiuscola(nameModulo) + "UI";
        pathFileUIClass = pathUI + SEP + fileNameUIClass + JAVA_SUFFIX;

        addVisteSpecifichePackage(fileNameUIClass, pathFileUIClass);
        addImportPackage(pathFileUIClass, nameEntityFirstUpper);
        addImportPackage(pathFileUIClass, nameEntityFirstUpper + LIST_VIEW_SUFFIX);
    }// end of method


    private void addVisteSpecifichePackage(String fileNameUIClass, String pathFileUIClass) {
        String aCapo = "\n\t\t";
        String tagPackage = "";
        String tagMethod = "protected void addVisteSpecifiche() {";
        String textUIClass = file.leggeFile(pathFileUIClass);

        if (isEsisteMetodo(fileNameUIClass, textUIClass, tagMethod)) {
            tagPackage = "menuLayout.addView(" + nameEntityFirstUpper + ".class, " + nameEntityFirstUpper + "List.class);";

            if (textUIClass.contains(tagPackage)) {
            } else {
                textUIClass = text.sostituisce(textUIClass, tagMethod, tagMethod + aCapo + tagPackage);
                file.scriveFile(pathFileUIClass, textUIClass, true);

                System.out.println("Il package " + text.primaMaiuscola(namePackageLower) + " è stato aggiunto al menu");
            }// end of if/else cycle
        }// end of if cycle
    }// end of method


    private boolean isEsisteMetodo(String fileNameUIClass, String textUIClass, String tagMethod) {
        boolean esiste = false;

        if (textUIClass.contains(tagMethod)) {
            esiste = true;
        } else {
            System.out.println("Nella classe iniziale " + fileNameUIClass + " manca il metodo " + tagMethod);
        }// end of if/else cycle

        return esiste;
    }// end of method


    private void addImportPackage(String pathFileUIClass, String fileNameClass) {
        String aCapo = "\n";
        String tagImport = "";
        String tagInizioInserimento = "\n/**";
        int posIni = 0;
        String textUIClass = file.leggeFile(pathFileUIClass);

        tagImport = "import it.algos." + nameModulo + ".entity." + namePackageLower + "." + fileNameClass + ";";

        if (textUIClass.contains(tagImport)) {
        } else {
            posIni = textUIClass.indexOf(tagInizioInserimento);
            textUIClass = text.inserisce(textUIClass, tagImport, posIni);
            file.scriveFile(pathFileUIClass, textUIClass, true);

            System.out.println("L'import del file " + fileNameClass + " è stato inserito negli import iniziali");
        }// end of if/else cycle
    }// end of method


    private void addTagCostanti() {
        this.addTagCost(TAG + tagBreveTreChar, namePackageLower);
        this.addTagCost(VIEW + tagBreveTreChar + "_" + LIST_VIEW_SUFFIX.toUpperCase(), namePackageLower + LIST_VIEW_SUFFIX);
        this.addTagCost(VIEW + tagBreveTreChar + "_" + FORM_VIEW_SUFFIX.toUpperCase(), namePackageLower + FORM_VIEW_SUFFIX);
    }// end of method

    private void addTagCost(String tag, String value) {
        String aCapo = "\n\t";
        String tagFind = "public abstract class " + nameCost + " {";
        String tagStatic = "";
        String textCostClass = file.leggeFile(pathFileCost);
        int posIni = 0;

        tagStatic = "public final static String " + tag + " = \"" + value + "\";";

        if (textCostClass.contains(tagStatic)) {
        } else {
            posIni = textCostClass.indexOf(tagFind);
            posIni = posIni + tagFind.length();
            tagStatic = aCapo + tagStatic;

            textCostClass = text.inserisce(textCostClass, tagStatic, posIni);
            file.scriveFile(pathFileCost, textCostClass, true);

            System.out.println("La costante statica TAG_" + tag + " è stata inserita nel file " + nameCost);
        }// end of if/else cycle

    }// end of method


}// end of class
