package it.algos.springvaadin.entity.versione;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 13/06/17.
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersioneList extends AlgosListImpl {

    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public VersioneList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor

}// end of class
