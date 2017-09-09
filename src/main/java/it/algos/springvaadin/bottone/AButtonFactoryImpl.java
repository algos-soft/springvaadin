package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.lib.Cost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 15:34
 * <p>
 * Factory class per la creazione dei bottoni
 * Crea ogni bottone del tpo richiesto e previsto nella Enumeration TypeButton
 * Nella creazione viene iniettato il parametro obbligatorio della 'sorgente' dell'evento generato dal bottone
 * Eventuali altri parametri facoltativi (target, entityBean), possono essere aggiunti. Da altre classi.
 */
@SpringComponent
@Slf4j
public class AButtonFactoryImpl implements AButtonFactory {


    /**
     * Funzione specificata in AlgosConfiguration
     */
    private Function<Class<? extends AButton>, AButton> buttonFactory;


    /**
     * Publisher degli eventi a livello Application
     */
    private ApplicationEventPublisher publisher;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param buttonFactory specificata in AlgosConfiguration
     * @param publisher     degli eventi a livello Application
     */
    public AButtonFactoryImpl(
            Function<Class<? extends AButton>, AButton> buttonFactory,
            ApplicationEventPublisher publisher) {
        this.buttonFactory = buttonFactory;
        this.publisher = publisher;
    }// end of @Autowired constructor


    /**
     * Creazione di un bottone
     *
     * @param type   del bottone, secondo la Enumeration TypeButton
     * @param source dell'evento generato dal bottone
     *
     * @return il bottone creato
     */
    @Override
    public AButton crea(TypeButton type, ApplicationListener source) {
        AButton button = null;

        switch (type) {
            case accetta:
                button = buttonFactory.apply(AButtonAccetta.class);
                break;
            case annulla:
                button = buttonFactory.apply(AButtonAnnulla.class);
                break;
            case back:
                button = buttonFactory.apply(AButtonBack.class);
                break;
            case chooser:
                button = buttonFactory.apply(AButtonChooser.class);
                break;
            case create:
                button = buttonFactory.apply(AButtonCreate.class);
                break;
            case delete:
                button = buttonFactory.apply(AButtonDelete.class);
                break;
            case edit:
//                button = buttonFactory.apply(AButtonEdit.class);
                button = buttonFactory.apply(AButton.class);
                button.setType(type);
                break;
            case editLink:
                button = buttonFactory.apply(AButtonEditLink.class);
                break;
            case editLinkDBRef:
                button = buttonFactory.apply(AButtonEditLink.class);
                button.setType(TypeButton.editLinkDBRef);
                break;
            case editLinkNoDBRef:
                button = buttonFactory.apply(AButtonEditLink.class);
                button.setType(TypeButton.editLinkNoDBRef);
                break;
            case image:
                button = buttonFactory.apply(AButtonImage.class);
                break;
            case importa:
                button = buttonFactory.apply(AButtonImport.class);
                break;
            case linkAccetta:
                button = buttonFactory.apply(AButtonLinkAccetta.class);
                break;
            case linkRegistra:
                button = buttonFactory.apply(AButtonLinkRegistra.class);
                break;
            case registra:
                button = buttonFactory.apply(AButtonRegistra.class);
                break;
            case revert:
                button = buttonFactory.apply(AButtonRevert.class);
                break;
            case search:
                button = buttonFactory.apply(AButtonSearch.class);
                break;
            case show:
                button = buttonFactory.apply(AButtonShowAll.class);
                break;
            default:
                log.warn("AButtonFactoryImpl - Caso non definito di switch");
                break;
        } // end of switch statement

        if (button != null) {
            button.inizializza(publisher, source);
        }// end of if cycle

        return button;
    }// end of method


}// end of class


