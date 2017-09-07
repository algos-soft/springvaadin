package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_ACCETTA)
public class AButtonAccetta extends AButton {


    public AButtonAccetta(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(TypeButton.accetta);
    }// end of @Autowired constructor

    /**
     * Recupera il presenter dalla 'catena' grafica attiva
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            publisher.publishEvent(new AButtonEvent(type, source));
        }// end of if cycle
    }// end of method

}// end of class
