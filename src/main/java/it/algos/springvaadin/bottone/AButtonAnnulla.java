package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_ANNULLA)
public class AButtonAnnulla extends AButton {

    public AButtonAnnulla(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(TypeButton.annulla);
    }// end of @Autowired constructor

}// end of class
