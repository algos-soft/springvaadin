package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 13/06/17.
 * Presenta i dati di una lista usando una Grid
 * Aggiunge una ToolBar in basso coi bottoni di comando (contenenti già i listener per lanciare gli eventi)
 */
@Lazy
@Qualifier(Cost.TAG_VERS)
@SpringComponent
public class VersioneList extends AlgosList {

    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void caption() {
        super.captionList = "Elenco di tutte le versioni";
    }// end of method

}// end of class
