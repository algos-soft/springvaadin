package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_ACCETTA)
@SpringComponent
public class BottoneAccetta extends Bottone {

    public BottoneAccetta() {
        super("Accetta", VaadinIcons.CHECK, false, TipoBottone.accetta);
    }// end of constructor

}// end of class
