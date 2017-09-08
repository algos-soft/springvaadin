package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_ACCETTA)
public class AButtonAccetta extends AButton {


    /**
     * Costruttore base senza parametri.
     * Viene utilizzato dalla Funzione -> BottoneFactory
     * Il publisher viene iniettato successivamente
     * Rimanda al costruttore completo, in modo da usufruire di eventuali regolazioni di parametri statici
     */
    public AButtonAccetta() {
        this(null);
    }// fine del metodo costruttore base


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    @Autowired
    public AButtonAccetta(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.accetta);
    }// end of @Autowired constructor



    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        if (source != null) {
            publisher.publishEvent(new AButtonEvent(type, source));
        }// end of if cycle
    }// end of method

}// end of class
