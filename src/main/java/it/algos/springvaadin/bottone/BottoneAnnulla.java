package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_ANNULLA)
@SpringComponent
public class BottoneAnnulla extends AlgosBottone {

    public BottoneAnnulla() {
        super("Annulla", VaadinIcons.CLOSE, false, TipoBottone.annulla);
    }// end of constructor

}// end of class
