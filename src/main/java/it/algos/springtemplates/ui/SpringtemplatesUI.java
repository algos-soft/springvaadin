package it.algos.springtemplates.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import it.algos.springtemplates.scripts.TDialogo;
import it.algos.springtemplates.scripts.TElabora;
import it.algos.springtemplates.scripts.TRecipient;
import it.algos.springvaadin.button.AButtonFactory;
import it.algos.springvaadin.dialog.AEditDialog;
import it.algos.springvaadin.lib.ACost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 05-mar-2018
 * Time: 15:56
 */
@Theme("algos")
@SpringUI()
@SpringViewDisplay()
@Slf4j
@VaadinSessionScope
public class SpringtemplatesUI extends UI implements ViewDisplay {



    //--crea la UI di base, un VerticalLayout
    protected VerticalLayout root;

    private String nomeProgetto;
    private String nomePackage = "";
    private boolean usaCompany = false;


    /**
     * Factory per la creazione dei bottoni
     */
    @Autowired
    private AButtonFactory buttonFactory;


    /**
     * Factory per la creazione dei files
     */
    @Autowired
    private TElabora elabora;

    /**
     * Initializes this UI.
     * This method is intended to build the view and configure non-component functionality.
     * Performing the initialization in a constructor is not suggested as the state of the UI
     * is not properly set up when the constructor is invoked.
     * <p>
     * The {@link VaadinRequest} can be used to get information about the request that caused this UI to be created.
     * </p>
     * Se viene sovrascritto dalla sottoclasse, deve (DEVE) richiamare anche il metodo della superclasse
     * di norma DOPO aver effettuato alcune regolazioni <br>
     * Nella sottoclasse specifica viene eventualmente regolato il nome del modulo di partenza <br>
     *
     * @param request the Vaadin request that caused this UI to be created
     */
    @Override
    protected void init(VaadinRequest request) {
        this.creazioneInizialeUI();
    }// end of method


    /**
     * Crea la UI di base (User Interface) iniziale dell'applicazione, un VerticalLayout
     * Layout standard composto da:
     * Top      - una barra composita di menu e login
     * Body     - un placeholder per il portale della tavola/modulo
     * Footer   - un striscia per eventuali informazioni (Algo, copyright, ecc)
     * Tutti i 3 componenti vengono inseriti a livello di root nel layout verticale
     * <p>
     * Può essere sovrascritto per gestire la UI in maniera completamente diversa
     */
    protected void creazioneInizialeUI() {
        root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(new MarginInfo(true, false, false, true));

        if (ACost.DEBUG) {// @TODO costante provvisoria da sostituire con preferenzeService
            root.addStyleName("pinkBg");
        }// end of if cycle

        this.setContent(root);

        getNavigator().navigateTo(ACost.VIEW_TEMPLATES);
    }// end of method


    @Override
    public void showView(View view) {
        this.nomeProgetto();
    }// end of method


    private void nomeProgetto() {
        new TDialogo(buttonFactory,  new TRecipient() {
            @Override
            public void gotInput(String input, Window win) {
                nomeProgetto = input;
                usaCompany();
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    private void nomePackage() {
        new AEditDialog(buttonFactory, "Package", "Ruolo", new AEditDialog.Recipient() {
            @Override
            public void gotInput(String input, Window win) {
                nomePackage = input;
                elabora();
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    private void usaCompany() {
        new AEditDialog(buttonFactory, "Company", new AEditDialog.Recipient() {
            @Override
            public void gotInput(String input, Window win) {
                if (input.equals("si")) {
                    usaCompany = true;
                } else {
                    usaCompany = false;
                }// end of if/else cycle
                elabora();
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    private void elabora() {
//        Notification.show("Progetto: ", nomeProgetto + " - " + (usaCompany ? "true" : "false"), Notification.Type.HUMANIZED_MESSAGE);
        elabora.newPackage(nomePackage, usaCompany);
    }// end of method


}// end of class
