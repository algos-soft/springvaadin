package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_EDIT)
@SpringComponent
public class BottoneEdit extends AlgosBottone {

    public BottoneEdit() {
        super("Modifica", VaadinIcons.EDIT, true,TipoBottone.edit);
    }// end of constructor

}// end of class
