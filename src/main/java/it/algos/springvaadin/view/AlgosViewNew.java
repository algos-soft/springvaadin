package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.entity.role.IAlgosPresenter;
import it.algos.springvaadin.lib.LibAnnotation;
import lombok.extern.slf4j.Slf4j;

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
     * Gestore principale del modulo, iniettato dal costruttore
     */
    protected IAlgosPresenter presenter;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AlgosViewNew(IAlgosPresenter presenter) {
        this.presenter = presenter;
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
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent() {
        return null;
    }// end of method

}// end of class
