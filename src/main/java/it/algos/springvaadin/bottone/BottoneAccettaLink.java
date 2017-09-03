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
 * Date: dom, 03-set-2017
 * Time: 10:15
 */
    @SpringComponent
    @Scope("prototype")
    @Qualifier(Cost.BOT_ACCETTA_LINK)
    public class BottoneAccettaLink extends Bottone {

        public BottoneAccettaLink(ApplicationEventPublisher publisher) {
            super(publisher);
            super.setType(TypeButton.registraLink);
        }// end of @Autowired constructor

    }// end of class

