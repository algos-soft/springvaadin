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


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    private ApplicationListener target;


    /**
     * @param source Obbligatorio presenter che gestisce l'evento
     */
    public AEvent(ApplicationListener source) {
        this(source, null);
    }// end of constructor


    /**
     * @param source Obbligatorio presenter che gestisce l'evento
     * @param target Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     */
    public AEvent(ApplicationListener source, ApplicationListener target) {
        super(source);
        this.target = target;
    }// end of constructor


    public ApplicationListener getTarget() {
        return target;
    }// end of method

}// end of abstract class