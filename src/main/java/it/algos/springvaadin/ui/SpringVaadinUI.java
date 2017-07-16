package it.algos.springvaadin.ui;

import it.algos.springvaadin.entity.bolla.BollaNavView;
import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Grid;

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
public class SpringVaadinUI extends AlgosUI {


    /**
     * Metodo invocato DOPO il costruttore e PRIMA del metodo init(VaadinRequest request)
     * Serve per regolare eventuali parametri utilizzati nel metodo init(VaadinRequest request)
     * Stampa a video (productionMode) i valori per controllo
     */
    @PostConstruct
    @Override
    void inizia() {
        super.inizia();
        super.printBefore(InterfacciaUtente.specifica);
        this.specificFixAndPrint();
        super.printAfter(InterfacciaUtente.specifica);
    }// end of method


    /**
     * Regola alcuni flag specifici dell'applicazione che riguardano l'interfaccia video
     * Can be overwritten on local xxxUI.specificFixAndPrint() method of subclass
     * Stampa a video (productionMode) i valori per controllo
     */
    protected void specificFixAndPrint() {

//        System.out.println("Non ci sono regolazioni specifiche per questa applicazione che usa solo quelle standard");

        super.gridSelectionMode = Grid.SelectionMode.SINGLE;
        System.out.println("AlgosUIParams.gridSelectionMode: " + super.gridSelectionMode);

        super.displayToolTips = true;
        System.out.println("AlgosUIParams.displayToolTips: " + super.displayToolTips);

        super.usaSeparateFormDialog = false;
        System.out.println("AlgosUIParams.usaSeparateFormDialog: " + super.usaSeparateFormDialog);
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
		menuLayout.addView(BollaNavView.class);
    }// end of method

}// end of class
