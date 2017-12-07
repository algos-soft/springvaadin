package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.entity.role.IAlgosPresenter;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.ui.AlgosUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:58
 * Annotated with Lombok @Slf4j (facoltativo)
 */
@Slf4j
public abstract class AlgosViewNew extends VerticalLayout implements IAlgosView {


    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * A seconda del layout può essere posizionato in alto, oppure a sinistra
     */
    @Autowired
    protected MenuLayout menuLayout;


    /**
     * Gestore principale del modulo, iniettato dal costruttore
     */
    protected IAlgosPresenter presenter;


    //--il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta


    public AlgosService service;

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
     * @param presenter iniettato da Spring
     */
    public AlgosViewNew(IAlgosPresenter presenter,AlgosService service,AToolbar toolbar) {
        this.presenter = presenter;
        this.service = service;
        this.toolbar = toolbar;
    }// end of Spring constructor


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Passa il controllo alla classe xxxPresenter che gestisce la business logic
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        View view = event.getNewView();
        IAlgosView algosView;

        if (view instanceof IAlgosView) {
            algosView = (IAlgosView) view;
        }// end of if cycle

        if (LibAnnotation.isList(view.getClass())) {
            presenter.setList();
        }// end of if cycle

        if (LibAnnotation.isForm(view.getClass())) {
        }// end of if cycle

    }// end of method


    /**
     * Regola l'aspetto grafico di questo contenitore
     */
    protected void fixGUI() {
//        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
//            this.addStyleName("greenBg");
//        }// end of if cycle
        removeAllComponents();
        this.setMargin(false);
        this.setWidth("100%");
        this.setHeight("100%");
    }// end of method

    /**
     * Opzione per personalizzare il menu
     * Sovrascritto
     */
    protected void fixMenu() {
        menuLayout.start();
        this.addComponent(menuLayout);
    }// end of method

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent() {
        return null;
    }// end of method

}// end of class
