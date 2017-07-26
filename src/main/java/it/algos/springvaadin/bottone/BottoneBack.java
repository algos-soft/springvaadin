package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_BACK)
@SpringComponent
public class BottoneBack extends AlgosBottone {

    public BottoneBack() {
        super("Annulla", VaadinIcons.BACKWARDS, true, TipoBottone.back);
    }// end of constructor

}// end of class
