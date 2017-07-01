package it.algos.springvaadin.presenter;

import it.algos.springvaadin.entities.versione.Versione;
import it.algos.springvaadin.events.AlgosSpringEvent;
import it.algos.springvaadin.events.FormListener;
import it.algos.springvaadin.events.GridListener;
import it.algos.springvaadin.events.ListListener;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.context.ApplicationListener;

import java.util.List;

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
