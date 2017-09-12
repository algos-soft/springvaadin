package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.dialog.ImageDialog;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.field.AField;
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
 * Crea ogni bottone del tpo richiesto e previsto nella Enumeration AButtonType
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

    @Autowired
    private ImageDialog imageDialog;

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
     * @param type        del bottone, secondo la Enumeration AButtonType
     * @param source      dell'evento generato dal bottone
     * @param target      a cui indirizzare l'evento generato dal bottone
     * @param sourceField che contiene il bottone bottone
     *
     * @return il bottone creato
     */
    public AButton crea(AButtonType type, ApplicationListener source, ApplicationListener target, AField sourceField) {
        AButton button = buttonFactory.apply(AButton.class);

        if (button != null) {
            button.inizializza(publisher, type, source, target, sourceField);
        }// end of if cycle

        return button;
    }// end of method


}// end of class


