package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 21-ago-2017
 * Time: 06:24
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_CHOOSER)
public class BottoneChooser extends Bottone{

    public BottoneChooser(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.chooser);
    }// end of @Autowired constructor

}// end of class

