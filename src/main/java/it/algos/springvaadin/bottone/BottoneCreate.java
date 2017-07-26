package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_CREATE)
@SpringComponent
public class BottoneCreate extends Bottone {

    public BottoneCreate() {
        super( "Nuovo", VaadinIcons.PLUS, true,TipoBottone.create);
    }// end of constructor

}// end of class
