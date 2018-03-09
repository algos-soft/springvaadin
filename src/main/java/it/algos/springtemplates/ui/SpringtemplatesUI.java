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
import it.algos.springtemplates.enumeration.Chiave;
import it.algos.springtemplates.scripts.TDialogo;
import it.algos.springtemplates.scripts.TElabora;
import it.algos.springtemplates.scripts.TRecipient;
import it.algos.springvaadin.button.AButtonFactory;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.menu.MenuLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import it.algos.springtemplates.entity.prova.Prova;
import it.algos.springtemplates.entity.prova.ProvaList;
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


    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * A seconda del layout può essere posizionato in alto, oppure a sinistra
     */
    @Autowired
    protected MenuLayout menuLayout;

    //--crea la UI di base, un VerticalLayout
    protected VerticalLayout root;

    private String nomeProgetto;
    private String nomePackage = "";
    private boolean usaCompany = false;


    /**
     * Dialogo di creazione
     */
    @Autowired
    private TDialogo dialogo;


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

        this.setContent(root);
        getNavigator().navigateTo(ACost.VIEW_TEMPLATES);
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
		menuLayout.addView(Prova.class, ProvaList.class);
    }// end of method

    @Override
    public void showView(View view) {
        dialogo.start(new TRecipient() {
            @Override
            public void gotInput(Map<Chiave, Object> mappaInput) {
                elabora(mappaInput);
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    private void elabora(Map<Chiave, Object> mappaInput) {
        elabora.newPackage(mappaInput);
    }// end of method


}// end of class