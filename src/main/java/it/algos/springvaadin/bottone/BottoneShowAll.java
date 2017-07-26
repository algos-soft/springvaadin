package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_SHOW_ALL)
@SpringComponent
public class BottoneShowAll extends Bottone {

    public BottoneShowAll() {
        super("Tutto", VaadinIcons.ALIGN_JUSTIFY, true, TipoBottone.showAll);
    }// end of constructor

}// end of class
