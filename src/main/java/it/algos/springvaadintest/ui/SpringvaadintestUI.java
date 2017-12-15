package it.algos.springvaadintest.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.home.AHomeView;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.ui.AUI;
import it.algos.springvaadintest.view.VaadintestView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 30/05/17.
 * <p>
 * UI di partenza dell'applicazione, selezionata da SpringBoot con @SpringUI()
 * Questa classe DEVE estendere AUIParams
 * Questa classe DEVE prevedere l'Annotation @SpringUI()
 * All'interno dell'applicazionbe, @SpringUI() deve essere utilizzata in una sola classe che estenda UI
 */
@Theme("algos")
@SpringUI()
@SpringViewDisplay()
@Slf4j
@Scope("session")
public class SpringvaadintestUI extends AUI {

//    /**
//     * Metodo invocato DOPO il costruttore e PRIMA del metodo init(VaadinRequest request)
//     * Serve per regolare eventuali parametri utilizzati nel metodo init(VaadinRequest request)
//     * Stampa a video (productionMode) i valori per controllo
//     */
//    @PostConstruct
//    @Override
//    protected void inizia() {

//    /**
//     * Initializes this UI.
//     * This method is intended to build the view and configure non-component functionality.
//     * Performing the initialization in a constructor is not suggested as the state of the UI
//     * is not properly set up when the constructor is invoked.
//     * <p>
//     * The {@link VaadinRequest} can be used to get information about the request that caused this UI to be created.
//     * </p>
//     * Se viene sovrascritto dalla sottoclasse, deve (DEVE) richiamare anche il metodo della superclasse
//     * di norma DOPO aver effettuato alcune regolazioni <br>
//     * Nella sottoclasse specifica viene eventualmente regolato il nome del modulo di partenza <br>
//     *
//     * @param request the Vaadin request that caused this UI to be created
//     */
//    @Override
//    protected void init(VaadinRequest request) {
//        super.init(request);
//
//        String message = "Algos® ";
//        String companyCode = LibSession.getCompany() != null ? LibSession.getCompany().getCode() : "";
//        if (AlgosApp.USE_MULTI_COMPANY && LibText.isValid(companyCode)) {
//            message += " - " + companyCode;
//        }// end of if cycle
//        footer.setAppMessage("SpringVaadintest 1.0");
//        log.info("Versione dell'applicazione: SpringVaadintest 1.0");
//
//        super.printBefore(InterfacciaUtente.specifica);
//        this.specificFixAndPrint();
//        super.printAfter(InterfacciaUtente.specifica);
//    }// end of method
//
//
//    /**
//     * Regola alcuni flag specifici dell'applicazione che riguardano l'annotation video
//     * Can be overwritten on local xxxUI.specificFixAndPrint() method of subclass
//     * Stampa a video (productionMode) i valori per controllo
//     */
//    protected void specificFixAndPrint() {
//
//        super.gridSelectionMode = Grid.SelectionMode.MULTI;
//        log.info("AUIParams.gridSelectionMode: " + super.gridSelectionMode);
//
//        super.displayToolTips = true;
//        log.info("AUIParams.displayToolTips: " + super.displayToolTips);
//
//        super.usaSeparateFormDialog = false;
//        log.info("AUIParams.usaSeparateFormDialog: " + super.usaSeparateFormDialog);
//
//        super.usaDialoghiVerbosi = true;
//        log.info("AUIParams.usaDialoghiVerbosi: " + super.usaDialoghiVerbosi);
//
//        super.usaBottoniColorati = true;
//        log.info("AUIParams.usaBottoniColorati: " + super.usaBottoniColorati);
//
//    }// end of method

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
        menuLayout.addView(RoleList.class);
    }// end of method

    /**
     * Lancio della vista iniziale
     * Chiamato DOPO aver finito di costruire il MenuLayout e la AlgosUI
     * Deve (DEVE) essere sovrascritto dalla sottoclasse
     */
    @Override
    protected void startVistaIniziale() {
        getNavigator().navigateTo(Cost.VIEW_ROL_LIST);
    }// end of method

}// end of class
