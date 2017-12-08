package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:23
 */
@Slf4j
public abstract class AView extends VerticalLayout implements IAView {

    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     */
    @Autowired
    protected MenuLayout menuLayout;

    @Autowired
    protected ATextService text;

    @Autowired
    protected AHtmlService htlm;

    /**
     * Gestore principale del modulo, iniettato dal costruttore
     */
    protected IAPresenter presenter;

    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AView(IAPresenter presenter) {
        super();
        this.presenter = presenter;
    }// end of Spring constructor

    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizia() {
        //@todo RIMETTERE
//        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
        this.addStyleName("blueBg");
//        }// end of if cycle

        this.setMargin(false);
        this.setWidth("100%");
        this.setHeight("100%");

        menuLayout.start();
        this.addComponent(menuLayout);
    }// end of method


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Aggiunge i riferimenti agli oggetti specifici di questa view (this)
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        View oldView = event.getOldView();
        View view = event.getNewView();
        IAView algosView;

        if (view instanceof IAView) {
            algosView = (IAView) view;
        }// end of if cycle

        if (oldView instanceof IAView) {
            ((IAView) oldView).removeLayout();
        }// end of if cycle

        this.removeAllComponents();
        this.addComponent(menuLayout);

        //@todo RIMETTERE
//        if (LibAnnotation.isList(view.getClass())) {
//            presenter.setList();
//        }// end of if cycle

        if (presenter!=null) {
            presenter.setList();
        }// end of if cycle

    }// end of method

    /**
     * Elimina il menuLayout dalla vista 'uscente'
     */
    @Override
    public void removeLayout() {
        this.removeComponent(menuLayout);
    }// end of method

}// end of class
