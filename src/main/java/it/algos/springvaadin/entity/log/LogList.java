package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 30-set-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_LOG)
public class LogList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public LogList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizializza() {
        caption = "</br>Lista visibile solo all'admin.";
        caption += "</br>Usa la company che è obbligatoria se l'applicazione è MultiCompany";
        caption += "</br>Solo il developer vede queste note";
    }// end of method

}// end of class
