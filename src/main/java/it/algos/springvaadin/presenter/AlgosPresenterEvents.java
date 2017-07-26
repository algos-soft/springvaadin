package it.algos.springvaadin.presenter;

import com.vaadin.ui.Notification;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bottone.AlgosBottone;
import it.algos.springvaadin.bottone.BottoneCreate;
import it.algos.springvaadin.bottone.TipoBottone;
import it.algos.springvaadin.event.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by gac on 18/06/17
 * .
 */
public abstract class AlgosPresenterEvents implements AlgosPresenterInterface {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * Evento
     * Create button pressed in grid
     * Create a new item and edit it in a form
     */
    @Override
    public void create() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Nuovo", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void edit() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Modifica", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void delete() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Cancella", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void search() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Cerca", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void showAll() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Tutto (annulla la selezione)", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method


    @Override
    public void attach() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Azione", "Aggiunta  una riga nella Grid", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void click() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Azione", "Click nella Grid", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method


    @Override
    public void doppioClick(AlgosEntity entityBean) {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Azione", "Doppio click nella Grid", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void selectionChanged() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Azione", "Modificata la selezione della Grid", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void listener() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Azione", "Azione generica della Grid", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method


    @Override
    public void annulla() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Annulla", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void revert() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Revert", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void registra() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("TipoBottone", "Premuto Registra", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method

    @Override
    public void fieldModificato() {
        if (AlgosApp.USE_DEBUG) {
            Notification.show("Field", "Modificato valore del campo", Notification.Type.HUMANIZED_MESSAGE);
        }// end of if cycle
    }// end of method


    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     */
    @Override
    public void enter() {
    }// end of method


    /**
     * Usato da Azione
     * Usato da TipoBottone
     */
    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }// end of method


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AlgosSpringEvent event) {
        if (event.getSource().getClass() == this.getClass()) {

            if (event instanceof ButtonSpringEvent) {
                onListEvent((ButtonSpringEvent) event);
            }// end of if/else cycle

            if (event instanceof ActionSpringEvent) {
                onGridAction((ActionSpringEvent) event);
            }// end of if cycle

            if (event instanceof FieldSpringEvent) {
                fieldModificato();
            }// end of if cycle

        }// end of if cycle
    }// end of method


    /**
     * Handle a button event
     * Vedi enum TipoBottone
     *
     * @param event the event to respond to
     */
    private void onListEvent(ButtonSpringEvent event) {
        TipoBottone tipo = event.getBottone().tipo;

        switch (tipo) {
            case create:
                create();
                break;
            case edit:
                edit();
                break;
            case delete:
                delete();
                break;
            case search:
                search();
                break;
            case showAll:
                showAll();
                break;
            case back:
                annulla();
                break;
            case revert:
                revert();
                break;
            case registra:
                registra();
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch
    }// end of method


    /**
     * Handle an action event
     * Vedi enum Azione
     *
     * @param event the event to respond to
     */
    private void onGridAction(ActionSpringEvent event) {
        Azione azioneRichiesta = event.getAzione();
        AlgosEntity entityBean = event.getEntityBean();

        switch (azioneRichiesta) {
            case attach:
                click();
                break;
            case click:
                click();
                break;
            case doppioClick:
                doppioClick(entityBean);
                break;
            case selectionChanged:
                selectionChanged();
                break;
            case listener:
                listener();
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch
    }// end of method

}// end of class
