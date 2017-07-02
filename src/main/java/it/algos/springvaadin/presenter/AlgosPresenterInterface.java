package it.algos.springvaadin.presenter;

import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.FormListener;
import it.algos.springvaadin.event.GridListener;
import it.algos.springvaadin.event.ListListener;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 14/06/17
 */
public interface AlgosPresenterInterface extends
        GridListener, ListListener, FormListener, ApplicationListener<AlgosSpringEvent> {

    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     */
    public void enter(AlgosView view);

}// end of interface
