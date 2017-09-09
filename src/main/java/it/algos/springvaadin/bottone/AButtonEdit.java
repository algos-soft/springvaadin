package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_EDIT)
public class AButtonEdit extends AButton {


    /**
     * Costruttore base senza parametri
     * Viene utilizzato dalla Funzione -> BottoneFactory in AlgosConfiguration
     * Il publisher viene iniettato successivamente
     * Regola alcuni parametri statici
     */
    public AButtonEdit() {
        super();
        super.setType(TypeButton.edit);
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
    public AButtonEdit(ApplicationEventPublisher publisher) {
        super(publisher);
        super.setType(TypeButton.edit);
    }// end of @Autowired constructor


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        AEntity entityBean = null;

        if (((AlgosPresenterImpl) source).getView().isUnaRigaSelezionata()) {
            entityBean = ((AlgosPresenterImpl) source).getView().getEntityBean();
        }// end of if cycle

        publisher.publishEvent(new AButtonEvent(type, source, null, entityBean, null));
    }// end of method

}// end of class
