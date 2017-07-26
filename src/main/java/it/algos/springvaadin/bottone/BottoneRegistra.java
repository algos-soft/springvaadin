package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_REGISTRA)
@SpringComponent
public class BottoneRegistra extends Bottone {

    public BottoneRegistra() {
        super("Registra", VaadinIcons.DATABASE, false, TipoBottone.registra);
    }// end of constructor

}// end of class
