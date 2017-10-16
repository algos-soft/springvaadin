package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PRE)
public class PreferenzaList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public PreferenzaList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     * Può essere sovrascritto per un'intestazione (caption) della grid
     */
    @Override
    protected void inizializza() {
        super.caption = "";
    }// end of method

}// end of class
