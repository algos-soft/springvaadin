package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_ACCETTA)
public class BottoneAccetta extends Bottone {


    public BottoneAccetta(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.accetta);
    }// end of @Autowired constructor

    /**
     * Recupera il presenter dalla 'catena' grafica attiva
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            applicationEventPublisher.publishEvent(new ButtonSpringEvent(source, type));
        }// end of if cycle
    }// end of method

}// end of class
