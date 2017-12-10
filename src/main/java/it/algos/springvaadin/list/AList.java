package it.algos.springvaadin.list;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleService;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.panel.APanel;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.service.IAService;
import it.algos.springvaadin.toolbar.ListToolbar;
import it.algos.springvaadin.view.AView;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 23:07
 * Implementazione standard dell'interfaccia IAList
 * Questa vista 'normalmente' si compone di:
 * Top - MenuBar di navigazione tra le views gestita da SpringNavigator
 * Caption - Eventuali scritte esplicative come collezione usata, records trovati, ecc
 * Body - Grid. Scorrevole
 * Footer - Barra dei bottoni di comando per lanciare eventi
 * Annotated with Lombok @Slf4j (facoltativo)
 */
@Slf4j
public abstract class AList extends AView implements IAList {


    /**
     * Contenitore grafico (Grid) per visualizzare i dati
     * Un eventuale Grid specifico può essere iniettato dalla sottoclasse concreta
     */
    @Autowired
    protected IAGrid grid;

    @Autowired
    protected ListToolbar toolbar;

    @Autowired
    public RoleService service;

//    @Autowired
//    private APanel panel;

    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AList(IAPresenter presenter) {
        super(presenter);
    }// end of Spring constructor


    /**
     * Metodo invocato (dalla SpringNavigator di SpringBoot) ogni volta che la view diventa attiva
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Passa il controllo al Presenter
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //--Regolazioni comuni a tutte le views
        super.enter(event);

        //--Passa il controllo al Presenter
        //--Il punto di ingresso invocato da SpringNavigator è unico e gestito dalla supeclasse AView
        //--Questa classe diversifica la chiamata al Presenter a seconda del tipo di view (List, Form, ... altro)
        //--Il Presenter prepara/elabora i dati e poi ripassa il controllo al metodo AList.start() di questa view
        if (presenter != null) {
            presenter.setList();
        }// end of if cycle
    }// end of method


    /**
     * Crea il corpo centrale della view
     * Componente grafico obbligatorio
     * Sovrascritto nella sottoclasse della view specifica (AList, AForm, ...)
     */
    protected APanel creaBody(Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
        grid.inizia(null, entityClazz, columns, items, 50);
        bodyLayout.setContent((Component) grid);
//        VerticalLayout layout = new VerticalLayout();
//        layout.addComponent((Component)grid);
        return bodyLayout;
    }// end of method


    /**
     * Crea la barra inferiore dei bottoni di comando
     * Chiamato ogni volta che la finestra diventa attiva
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     */
    protected VerticalLayout creaBottom(IAPresenter source, List<EAButtonType> typeButtons) {
        VerticalLayout bottomLayout = new VerticalLayout();
        bottomLayout.setMargin(false);
        bottomLayout.setHeightUndefined();
        toolbar.inizializza(source, typeButtons);

//        fixToolbar();

        bottomLayout.addComponent((ListToolbar) toolbar);
        return bottomLayout;
    }// end of method

}// end of class
