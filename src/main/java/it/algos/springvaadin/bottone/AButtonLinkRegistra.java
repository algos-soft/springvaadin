package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.event.TypeField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 03-set-2017
 * Time: 08:46
 * Registra una entity nel modulo linkato
 * Esegue alcune regolazioni nel modulo chiamante
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_LINK_REGISTRA)
public class AButtonLinkRegistra extends AButton {


    /**
     * Costruttore base senza parametri
     * Viene utilizzato dalla Funzione -> BottoneFactory in AlgosConfiguration
     * Il publisher viene iniettato successivamente
     * Regola alcuni parametri statici
     */
    public AButtonLinkRegistra() {
        super();
        super.setType(TypeButton.linkRegistra);
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
    public AButtonLinkRegistra(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.linkRegistra);
    }// end of @Autowired constructor


}// end of class

