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
 * Time: 08:46
 * Registra una entity nel modulo linkato
 * Esegue alcune regolazioni nel modulo chiamante
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_REGISTRA)
public class BottoneRegistraLink extends Bottone {

    public BottoneRegistraLink(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.registraLink);
    }// end of @Autowired constructor

}// end of class

