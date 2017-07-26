package it.algos.springvaadin.bottone;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;

@SpringComponent
public abstract class Bottone extends Button {


    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Enumeration utilizza per 'marcare' un evento, in fase di generazione
     * e 'riconoscerlo' nel metodo onListEvent()
     */
    public TipoBottone tipo;

//    public Bottone() {
//    }// end of constructor


    public Bottone(String caption, Resource icona, boolean enabled, TipoBottone tipo) {
        super(caption, icona);
        this.setEnabled(enabled);
        this.tipo = tipo;
    }// end of constructor


    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge il listener
     */
    @PostConstruct
    private void inizia() {
        // Handle the event with an anonymous class
        this.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fire(clickEvent);
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    /**
     * Recupera il Presenter dalla 'catena' grafica attiva
     * Costruisce e lancia l'evento che viene pubblicato dal Singleton ApplicationEventPublisher
     */
    private void fire(Button.ClickEvent clickEvent) {
        AlgosPresenter presenter = LibVaadin.getCurrentPresenter();

        AlgosSpringEvent buttonSpringEvent = new ButtonSpringEvent(presenter, this);
        applicationEventPublisher.publishEvent(buttonSpringEvent);
    }// end of method


}// end of class
