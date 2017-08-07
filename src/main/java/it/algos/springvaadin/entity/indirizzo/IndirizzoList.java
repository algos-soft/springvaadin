package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 07-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_IND)
public class IndirizzoList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public IndirizzoList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor


}// end of class
