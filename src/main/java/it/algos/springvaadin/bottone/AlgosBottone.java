package it.algos.springvaadin.bottone;

import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.ui.AlgosUIParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringComponent
public abstract class AlgosBottone extends Button {


    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;


    public AlgosBottone() {
    }// end of constructor


    public AlgosBottone(String caption, Resource icona, boolean enabled) {
        super(caption, icona);
        this.setEnabled(enabled);
    }// end of constructor


    /**
     * Metodo invocato DOPO il costruttore
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


    private void fire(Button.ClickEvent clickEvent) {
        AlgosPresenter presenter = LibVaadin.getCurrentPresenter();

        AlgosSpringEvent buttonSpringEvent = new ButtonSpringEvent(presenter, this);
        applicationEventPublisher.publishEvent(buttonSpringEvent);
    }// end of method



}// end of class
