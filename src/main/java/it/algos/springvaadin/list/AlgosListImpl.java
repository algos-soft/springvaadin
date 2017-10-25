package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibColumn;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.renderer.ByteStringRenderer;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * Created by gac on 20/06/17
 * Implementazione standard dell'annotation AlgosList
 */
public abstract class AlgosListImpl extends VerticalLayout implements AlgosList {


    //--il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
    public AlgosService service;

    //--valore che può essere regolato nella classe specifica
    //--usando un metodo @PostConstruct
    protected String caption;


    //--AlgosGrid, iniettata dal costruttore
    //--un eventuale Grid specifico verrebbe iniettato dal costruttore della sottoclasse concreta
    protected AlgosGrid grid;


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    protected AToolbar toolbar;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param service iniettata da Spring
     * @param grid    iniettata da Spring
     * @param toolbar iniettata da Spring
     */
    public AlgosListImpl(AlgosService service, AlgosGrid grid, @Qualifier(Cost.BAR_LIST) AToolbar toolbar) {
        this.service = service;
        this.grid = grid;
        this.toolbar = toolbar;
    }// end of Spring constructor


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la finestra diventa attiva
     *
     * @param source      di riferimento per gli eventi
     * @param entityClass del modello dati
     * @param items       da visualizzare nella grid
     * @param columns     da visualizzare nella grid
     */
    @Override
    public void restart(AlgosPresenterImpl source, Class<? extends AEntity> entityClass, List items, List<String> columns) {
        Label label;
        this.setMargin(false);
        String textLabel = "";
        List<String> listaBottoni;
        this.removeAllComponents();

        caption = "";
        this.inizializza();
        if (items != null) {
            if (items.size() == 1) {
                textLabel = entityClass.getSimpleName() + " - Elenco di " + items.size() + " scheda. ";
            } else {
                textLabel = entityClass.getSimpleName() + " - Elenco di " + items.size() + " schede. ";
            }// end of if/else cycle
        } else {
            textLabel = entityClass.getSimpleName() + " - Al momento non c'è nessuna scheda. ";
        }// end of if/else cycle


        if (caption != null) {
            textLabel += caption;
        }// end of if cycle
        if (LibParams.usaAvvisiColorati()) {
            label = new LabelRosso(textLabel);
        } else {
            label = new Label(textLabel);
        }// end of if/else cycle
        this.addComponent(label);

        grid.inizia(entityClass, items, columns);
        this.addComponent(grid);

        //--Prepara la toolbar e la aggiunge al contenitore grafico
        listaBottoni = service.getListBottonNames();
        inizializzaToolbar(source);
        fixToolbar();
        this.addComponent((ListToolbar) toolbar);

        if (AlgosApp.USE_DEBUG) {
            this.addStyleName("rosso");
            grid.addStyleName("verde");
        }// fine del blocco if
    }// end of method


    /**
     * Chiamato ogni volta che la finestra diventa attiva
     * Può essere sovrascritto per un'intestazione (caption) della grid
     */
    protected void inizializza() {
    }// end of method


    protected void fixToolbar() {
    }// end of method

    /**
     * Prepara la toolbar
     * <p>
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    protected void inizializzaToolbar(ApplicationListener source) {
        toolbar.inizializza(source);
    }// end of method


    /**
     * Righe selezionate nella Grid
     *
     * @return numero di righe selezionate
     */
    @Override
    public int numRigheSelezionate() {
        return grid.numRigheSelezionate();
    }// end of method


    /**
     * Una riga selezionata nella grid
     *
     * @return true se è selezionata una ed una sola riga nella Grid
     */
    @Override
    public boolean isUnaRigaSelezionata() {
        return grid.unaRigaSelezionata();
    }// end of method


    /**
     * Abilita o disabilita lo specifico bottone della Toolbar
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param status abilitare o disabilitare
     */
    @Override
    public void enableButton(AButtonType type, boolean status) {
        toolbar.enableButton(type, status);
    }// end of method


    /**
     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
     * Lista nulla, se nessuna riga è selezionata
     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
     * Lista di uno o più elementi, se è Grid.SelectionMode.MULTI
     *
     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
     */
    @Override
    public List<AEntity> getEntityBeans() {
        return grid.getEntityBeans();
    }// end of method


    /**
     * Elemento selezionato nella Grid
     *
     * @return entityBean
     */
    public AEntity getEntityBean() {
        return grid.getEntityBean();
    }// end of method

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    @Override
    public Component getComponent() {
        return this;
    }// end of method


    public void setPresenter(AlgosPresenterImpl presenter) {
//        toolbar.regolaBottoni(presenter);
    }// end of method

}// end of class
