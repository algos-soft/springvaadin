package it.algos.springvaadin.entity.versione;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 13/06/17.
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
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

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     */
    protected void inizializza() {
        if (LibSession.isDeveloper()) {
            caption = "";
            caption += "</br>Lista visibile solo all'admin";
            caption += "</br>Usa la company (se AlgosApp.USE_MULTI_COMPANY=true) che è obbligatoria";
            caption += "</br>Solo il developer vede queste note";
        }// end of if cycle
    }// end of method

}// end of class
