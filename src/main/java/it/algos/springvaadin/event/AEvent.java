package it.algos.springvaadin.event;

import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 18:00
 * Eventi specifici del Framework
 * Link: http://www.baeldung.com/spring-events
 */
public abstract class AEvent extends ApplicationEvent {

    /**
     * @param source (obbligatorio) presenter che gestisce l'evento
     */
    public AEvent(ApplicationListener source) {
        super(source);
    }// end of constructor

}// end of abstract class