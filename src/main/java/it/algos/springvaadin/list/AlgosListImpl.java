package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.ListToolbar;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gac on 20/06/17
 * Implementazione standard dell'annotation AlgosList
 */
public class AlgosListImpl extends VerticalLayout implements AlgosList {


    //--valore che può essere regolato nella classe specifica
    //--usando un metodo @PostConstruct
    protected String caption;


    //--AlgosGrid, iniettata dal costruttore
    //--un eventuale Grid specifico verrebbe iniettato dal costruttore della sottoclasse concreta
    private AlgosGrid grid;


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    protected AToolbar toolbar;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param grid    iniettata da Spring
     * @param toolbar iniettata da Spring
     */
    public AlgosListImpl(AlgosGrid grid, ListToolbar toolbar) {
        this.grid = grid;
        this.toolbar = toolbar;
    }// end of Spring constructor


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la finestra diventa attiva
     *
     * @param presenter   di riferimento per gli eventi
     * @param entityClass del modello dati
     * @param items       da visualizzare nella grid
     * @param columns     da visualizzare nella grid
     */
    @Override
    public void restart(AlgosPresenterImpl presenter, Class<? extends AEntity> entityClass, List items, List<String> columns) {
        Label label;
        this.setMargin(false);
        String textLabel = "";

        this.removeAllComponents();

        textLabel = entityClass.getSimpleName() + " - Elenco di " + items.size() + " schede. ";
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

        toolbarInizializza(presenter);
        this.addComponent((ListToolbar)toolbar);

        if (AlgosApp.USE_DEBUG) {
            this.addStyleName("rosso");
            grid.addStyleName("verde");
        }// fine del blocco if
    }// end of method


    /**
     * Prepara la toolbar
     */
    protected void toolbarInizializza(AlgosPresenterImpl source) {
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
     * Abilita il bottone Create dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableNew(boolean status) {
        toolbar.enableButton(AButtonType.create, status);
    }// end of method


    /**
     * Abilita il bottone Edit dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableEdit(boolean status) {
        toolbar.enableButton(AButtonType.edit, status);
    }// end of method


    /**
     * Abilita il bottone Delet della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableDelete(boolean status) {
        toolbar.enableButton(AButtonType.delete, status);
    }// end of method


    /**
     * Abilita il bottone Search della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableSearch(boolean status) {
        toolbar.enableButton(AButtonType.search, status);
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
