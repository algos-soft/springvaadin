package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(Cost.TAG_BOT_SEARCH)
@SpringComponent
public class BottoneSearch extends AlgosBottone {

    public BottoneSearch() {
        super("Ricerca", VaadinIcons.SCISSORS, false,TipoBottone.search);
    }// end of constructor

}// end of class
