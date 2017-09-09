package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 21-ago-2017
 * Time: 06:24
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_CHOOSER)
public class AButtonChooser extends AButton {


    /**
     * Costruttore base senza parametri
     * Viene utilizzato dalla Funzione -> BottoneFactory in AlgosConfiguration
     * Il publisher viene iniettato successivamente
     * Regola alcuni parametri statici
     */
    public AButtonChooser() {
        super();
        super.setType(TypeButton.chooser);
    }// fine del metodo costruttore base


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    @Autowired
    @Deprecated //@todo utilizzo la Funzione -> BottoneFactory in AlgosConfiguration
    public AButtonChooser(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.chooser);
    }// end of @Autowired constructor


}// end of class

