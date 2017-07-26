package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_REVERT)
@SpringComponent
public class BottoneRevert extends AlgosBottone {

    public BottoneRevert() {
        super("Ripristina", VaadinIcons.REFRESH, false, TipoBottone.revert);
    }// end of constructor

}// end of class
