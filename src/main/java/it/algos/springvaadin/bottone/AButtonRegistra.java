package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Registra una entity nel modulo corrente
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_REGISTRA)
public class AButtonRegistra extends AButton {


    public AButtonRegistra(ApplicationEventPublisher applicationEventPublisher) {
       super(applicationEventPublisher);
        super.setType(TypeButton.registra);
    }// end of @Autowired constructor


}// end of class
