package it.algos.springvaadin.ui;

import com.sun.media.jfxmedia.logging.Logger;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.VersioneNavView;
import it.algos.springvaadin.footer.AlgosFooter;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.nav.AlgosNavView;
import it.algos.springvaadin.view.ViewPlaceholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * Created by gac on 30/05/17.
 * <p>
 * Superclasse astratta della UI di partenza dell'applicazione, selezionata da SpringBoot
 * Questa classe DEVE prevedere l'Annotation @SpringViewDisplay()
 * <p>
 * La @SpringViewDisplay() crea un SpringNavigator con SpringViewProvider con tutte le view indirizzabili
 * <p>
 * Delega alla superclasse astratta la regolazione dei parametri di gestione della parte grafica
 * Delega ad un service la business logic iniziale (request parameter, cookie, security, developper)
 * Costruisce un layout standard (modificabile nello specifico per la gioia di Alex)
 * Delega ad altre classi l'implementazione effettiva degli specifici layout
 */
@SpringViewDisplay()
public abstract class AlgosUI extends AlgosUIViews implements ViewDisplay {

    //--versione della classe per la serializzazione
    private static final long serialVersionUID = 1L;


    //--crea la UI di base, un VerticalLayou
    protected VerticalLayout root;

    //--A placeholder component which a SpringNavigator can populate with different views
    protected Panel panel = new Panel();

    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * A seconda del layout può essere posizionato in alto, oppure a sinistra
     */
    @Autowired
    @Lazy
    protected MenuLayout menuLayout;

    //--A placeholder for spring views
    @Autowired
    @Lazy
    protected ViewPlaceholder viewPlaceholder;

    //--A placeholder for a footer component
    @Autowired
    @Lazy
    protected AlgosFooter footer;


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

        Object alfa=request.getContextPath();
        Object beta=request.getParameterMap();
        Object delta=request.getPathInfo();
        Object gamma=request.getRemoteAddr();
        Object omega=request.getRemoteHost();
        Object omicron=request.getRemotePort();
        Object sex=request.getWrappedSession();

        //--Crea l'interfaccia utente (User Interface)
        this.creaUI();
    }// end of method

    /**
     * Crea l'interfaccia utente (User Interface) iniziale dell'applicazione
     * Crea i menu specifici
     * Layout standard composto da:
     * Top      - una barra composita di menu e login
     * Body     - un placeholder per il portale della tavola/modulo
     * Footer   - un striscia per eventuali informazioni (Algo, copyright, ecc)
     * <p>
     * Può essere sovrascritto per gestire la UI in maniera completamente diversa
     */
    protected void creaUI() {

        //--crea la UI di base, un VerticalLayout
        root = new VerticalLayout();
        root.setSizeFull();
        this.setContent(root);

        //--Crea l'interfaccia utente (User Interface)
        //--si può usare una UI divisa in 3 sezioni (menu, body, footer)
        //--oppure a schermo pieno (panel),
        if (usaViewTreComponenti) {
            creaViewTreComponenti();
        } else {
            creaSingoloPanel();
        }// end of if/else cycle

        if (AlgosApp.USE_DEBUG) {
            root.addStyleName("blueBg");
            panel.addStyleName("blueBg");
            viewPlaceholder.addStyleName("yellowBg");
        } else {
            root.addStyleName("colorebase");
            panel.addStyleName("colorebase");
            viewPlaceholder.addStyleName("colorebase");
        }// end of if/else cycle
    }// end of method

    /**
     * Crea l'interfaccia utente (User Interface) iniziale dell'applicazione
     * Top  - una barra composita di menu e login
     * Body - un placeholder per il portale della tavola/modulo
     * Footer - un striscia per eventuali informazioni (Algo, copyright, ecc)
     * Tutti i 3 componenti vengono inseriti a livello di root nel layout verticale
     *
     * @return layout - normalmente un Panel
     */
    protected void creaViewTreComponenti() {
        try { // prova ad eseguire il codice
            root.removeAllComponents();
        } catch (Exception unErrore) { // intercetta l'errore
            Logger.logMsg(1, unErrore.toString());
        }// fine del blocco try-catch

        try { // prova ad eseguire il codice
            if (menuLayout!=null) {
                root.addComponent(menuLayout);
            }// end of if cycle

            root.addComponentsAndExpand(viewPlaceholder);
            root.addComponent(footer);

        } catch (Exception unErrore) { // intercetta l'errore
            Logger.logMsg(1, unErrore.toString());
        }// fine del blocco try-catch
    }// end of method


    /**
     * Crea l'interfaccia utente (User Interface) iniziale dell'applicazione
     * Panel - un placeholder per il portale della tavola/modulo
     * Le applicazioni specifiche, possono sovrascrivere questo metodo nella sottoclasse
     * Il panel viene inserito a livello di root
     *
     * @return layout - normalmente un Panel
     */
    protected Panel creaSingoloPanel() {
        panel.setSizeUndefined();
        root.addComponent(panel);
        root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        return panel;
    }// end of method


    /**
     * Punto di ritorno da getNavigator().navigateTo(viewName)
     * Visualizza la view nel placeholder
     */
    public void showView(View view) {
        View navView=view;
        if (view instanceof AlgosNavView) {
            navView=((AlgosNavView)view).getLinkedView();
        }// end of if cycle


        if (usaViewTreComponenti) {
            creaViewTreComponenti();
            viewPlaceholder.setContent((Component) navView);

//            root.removeAllComponents();
//            root.addComponent(menuLayout);
//            root.addComponent((Component)view);
//            root.addComponent(footer);
        } else {
            panel.setContent((Component) view);
        }// end of if/else cycle
    }// end of method


}// end of UI class