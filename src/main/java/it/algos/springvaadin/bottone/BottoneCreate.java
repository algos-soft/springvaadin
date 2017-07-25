package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_CREATE)
@SpringComponent
public class BottoneCreate extends AlgosBottone {

    public BottoneCreate() {
        super("Nuovo", VaadinIcons.PLUS, true);
    }// end of constructor

}// end of class
