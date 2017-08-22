package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_REGISTRA)
public class BottoneRegistra extends Bottone {


    public BottoneRegistra(ApplicationEventPublisher applicationEventPublisher) {
       super(applicationEventPublisher);
        super.setType(BottonType.registra);
    }// end of @Autowired constructor


}// end of class
