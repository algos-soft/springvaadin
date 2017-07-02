package it.algos.springvaadin.event;

import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by gac on 03/06/17
 * Link: http://www.baeldung.com/spring-events
 */
public abstract class AlgosSpringEvent extends ApplicationEvent {

    public AlgosSpringEvent(AlgosPresenter source) {
        super(source);
    }// end of constructor

}// end of class