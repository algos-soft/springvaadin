package it.algos.springvaadin.event;

import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationEvent;

/**
 * Created by gac on 03/06/17
 * Eventi specifici del Framework
 * Link: http://www.baeldung.com/spring-events
 */
public abstract class AlgosSpringEvent extends ApplicationEvent {

    public AlgosSpringEvent(AlgosPresenterImpl source) {
        super(source);
    }// end of constructor

}// end of class