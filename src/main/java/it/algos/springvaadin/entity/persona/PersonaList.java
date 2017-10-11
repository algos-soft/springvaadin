package it.algos.springvaadin.entity.persona;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 11-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PER)
public class PersonaList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public PersonaList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     */
    protected void inizializza() {
        if (LibSession.isDeveloper()) {
            caption = "";
            caption += "</br>Lista visibile solo al developer";
            caption += "</br>NON usa la company";
            caption += "</br>L'entity è 'embedded' nelle collezioni che la usano (no @Annotation property DbRef)";
            caption += "</br>In pratica questa lista non dovrebbe mai essere usata";
        }// end of if cycle
    }// end of method

}// end of class
