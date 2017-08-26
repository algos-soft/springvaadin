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
@Qualifier(Cost.BOT_CREATE)
public class BottoneCreate extends Bottone {


    public BottoneCreate(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.create);
    }// end of @Autowired constructor

    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AlgosSpringEvent event)
     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            applicationEventPublisher.publishEvent(new ButtonSpringEvent(source, type));
        } else {
            int a=87;
        }// end of if/else cycle
    }// end of method

}// end of class
