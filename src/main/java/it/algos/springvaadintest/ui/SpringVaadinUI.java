package it.algos.springvaadintest.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.ui.AlgosUI;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 30/05/17.
 * <p>
 * UI di partenza dell'applicazione, selezionata da SpringBoot con @SpringUI()
 * Questa classe DEVE estendere AlgosUIParams
 * Questa classe DEVE prevedere l'Annotation @SpringUI()
 * All'interno dell'applicazionbe, @SpringUI() deve essere utilizzata in una sola classe che estenda UI
 */
@Theme("algos")
@SpringUI()
@SpringViewDisplay()
@Slf4j
public class SpringVaadinUI extends AlgosUI {

    /**
     * Metodo invocato DOPO il costruttore e PRIMA del metodo init(VaadinRequest request)
     * Serve per regolare eventuali parametri utilizzati nel metodo init(VaadinRequest request)
     * Stampa a video (productionMode) i valori per controllo
     */
    @PostConstruct
    @Override
    protected void inizia() {
        super.inizia();
        footer.setAppMessage("SpringVaadin 1.0");
        log.info("Versione dell'applicazione: SpringVaadin 1.0");

        super.printBefore(InterfacciaUtente.specifica);
        this.specificFixAndPrint();
        super.printAfter(InterfacciaUtente.specifica);

    }// end of method


    /**
     * Regola alcuni flag specifici dell'applicazione che riguardano l'annotation video
     * Can be overwritten on local xxxUI.specificFixAndPrint() method of subclass
     * Stampa a video (productionMode) i valori per controllo
     */
    protected void specificFixAndPrint() {

        super.gridSelectionMode = Grid.SelectionMode.MULTI;
        log.info("AlgosUIParams.gridSelectionMode: " + super.gridSelectionMode);

        super.displayToolTips = true;
        log.info("AlgosUIParams.displayToolTips: " + super.displayToolTips);

        super.usaSeparateFormDialog = false;
        log.info("AlgosUIParams.usaSeparateFormDialog: " + super.usaSeparateFormDialog);

        super.usaDialoghiVerbosi = true;
        log.info("AlgosUIParams.usaDialoghiVerbosi: " + super.usaDialoghiVerbosi);

        super.usaBottoniColorati = true;
        log.info("AlgosUIParams.usaBottoniColorati: " + super.usaBottoniColorati);

    }// end of method


    /**
     * Creazione delle viste (moduli) specifiche dell'applicazione.
     * La superclasse AlgosUIParams crea (flag true/false) le viste (moduli) usate da tutte le applicazioni
     * I flag si regolano in @PostConstruct:init()
     * <p>
     * Aggiunge al menu generale, le viste (moduli) disponibili alla partenza dell'applicazione
     * Ogni modulo può eventualmente modificare il proprio menu
     * <p>
     * Deve (DEVE) essere sovrascritto dalla sottoclasse
     * Chiama il metodo  addView(...) della superclasse per ogni vista (modulo)
     * La vista viene aggiunta alla barra di menu principale (di partenza)
     * La vista viene aggiunta allo SpringViewProvider usato da SpringNavigator
     */
    protected void addVisteSpecifiche() {
        getNavigator().navigateTo(Cost.TAG_COMP);
    }// end of method

}// end of class
