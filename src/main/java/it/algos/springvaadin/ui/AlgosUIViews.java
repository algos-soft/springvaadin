package it.algos.springvaadin.ui;

import it.algos.springvaadin.entity.log.LogNavView;
import com.vaadin.server.VaadinSession;
import it.algos.springvaadin.entity.stato.StatoNavView;
import it.algos.springvaadin.entity.indirizzo.IndirizzoNavView;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import it.algos.springvaadin.entity.company.CompanyNavView;
import it.algos.springvaadin.entity.versione.VersioneNavView;
import it.algos.springvaadin.entity.versione.VersioneView;
import it.algos.springvaadin.help.HelpNavView;
import it.algos.springvaadin.help.HelpView;
import it.algos.springvaadin.home.HomeNavView;
import it.algos.springvaadin.home.HomeView;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.menu.MenuLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gac on 12/06/17
 * <p>
 * Superclasse astratta della UI di partenza dell'applicazione, selezionata da SpringBoot
 * Questa classe NON prevede l'Annotation @SpringViewDisplay()
 * Mantiene i metodi di creazione ed accesso delle views
 * Lascia che la sottoclasse AlgosUI si occupi SOLO della parte grafica
 */
public abstract class AlgosUIViews extends AlgosUIParams {


    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * A seconda del layout può essere posizionato in alto, oppure a sinistra
     */
    @Autowired
    protected MenuLayout menuLayout;

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
        super.init(request);

        //--Crea i menu per la gestione delle SpringView (standard e specifiche)
        this.addAllViste();
    }// end of method

    /**
     * Aggiunge tutte le viste (SpringView) standard e specifiche
     */
    protected void addAllViste() {
        //--l'eventuale menu Home è sempre il primo
        if (usaItemMenuHome) {
            menuLayout.addView(HomeNavView.class);
        }// end of if cycle

        this.addVisteStandard();
        this.addVisteSpecifiche();

        //--l'eventuale menu Help è sempre l'ultimo
        if (usaItemMenuHelp) {
            menuLayout.addView(HelpNavView.class);
        }// end of if cycle
    }// end of method


    /**
     * Aggiunge le viste (moduli) standard
     * Alcuni moduli sono specifici di un collegamento come programmatore
     * Alcuni moduli sono già definiti per tutte le applicazioni (LogMod, VersMod, PrefMod)
     * Vengono usati come da relativo flag: AlgosApp.USE_LOG, AlgosApp.USE_VERS, AlgosApp.USE_PREF
     */
    protected void addVisteStandard() {
        menuLayout.addView(StatoNavView.class);
        if (LibParams.useVers()) {
            menuLayout.addView(VersioneNavView.class);
        }// end of if cycle
        if (LibParams.useLog()) {
            menuLayout.addView(LogNavView.class);
        }// end of if cycle
        if (LibParams.useMultiCompany()) {
            menuLayout.addView(IndirizzoNavView.class);
            menuLayout.addView(CompanyNavView.class);
        }// end of if cycle
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
    }// end of method


    /**
     * Adds a lazy view to the MenuBar
     *
     * @param viewClass the view class to instantiate
     */
    protected void addView(Class<? extends View> viewClass) {
        menuLayout.addView(viewClass);
    }// end of method


    /**
     * Mostra una view
     *
     * @param viewName the view to show
     */
    public void navigateTo(String viewName) {
        getNavigator().navigateTo(viewName);
    }// end of method

}// end of class
