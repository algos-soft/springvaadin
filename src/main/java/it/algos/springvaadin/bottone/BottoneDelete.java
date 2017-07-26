package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_DELETE)
@SpringComponent
public class BottoneDelete extends Bottone {

    public BottoneDelete() {
        super("Elimina", VaadinIcons.SCISSORS, true,TipoBottone.delete);
    }// end of constructor

}// end of class
