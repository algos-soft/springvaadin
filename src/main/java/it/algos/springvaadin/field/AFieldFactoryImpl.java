package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 30-ago-2017
 * Time: 11:14
 * <p>
 * Factory class per la creazione dei fields di un Form
 * Crea ogni field del tpo richiesto e previsto nella Enumeration AFieldType
 * Nella creazione viene iniettato il parametro obbligatorio publicFieldName che serve sia per UI,
 * sia per la gestione interna dei fields
 * Nella creazione viene iniettato il parametro obbligatorio del presenter che gestisce il field,
 * secondo lo schema: Presenter -> View -> Form -> Field
 * Eventuali altri parametri facoltativi, possono essere aggiunti.
 */
@SpringComponent
@Slf4j
public class AFieldFactoryImpl implements AFieldFactory {


    /**
     * Funzione specificata in AlgosConfiguration
     * Alcuni fields hanno anche un bottone
     */
    private Function<Class<? extends AButton>, AButton> buttonFactory;


    /**
     * Funzione specificata in AlgosConfiguration
     */
    private Function<Class<? extends AField>, AField> fieldFactory;


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
     * @param fieldFactory  specificata in AlgosConfiguration
     * @param publisher     degli eventi a livello Application
     */
    public AFieldFactoryImpl(
            Function<Class<? extends AButton>, AButton> buttonFactory,
            Function<Class<? extends AField>, AField> fieldFactory,
            ApplicationEventPublisher publisher) {
        this.buttonFactory = buttonFactory;
        this.fieldFactory = fieldFactory;
        this.publisher = publisher;
    }// end of @Autowired constructor


    /**
     * Creazione di un field
     *
     * @param type            del field, secondo la Enumeration AFieldType
     * @param publicFieldName nome visibile del field
     * @param source          del presenter che gestisce questo field
     *
     * @return il field creato
     */
    public AField crea(AFieldType type, String publicFieldName, ApplicationListener source) {
        AField field = null;
        AButton button = null;

        try { // prova ad eseguire il codice
            switch (type) {
                case id:
                    field = fieldFactory.apply(AIdField.class);
                    break;
                case text:
                    field = fieldFactory.apply(ATextField.class);
                    break;
                case integer:
                    field = fieldFactory.apply(AIntegerField.class);
                    break;
                case image:
                    field = fieldFactory.apply(AImageField.class);
                    break;
                case combo:
                    field = fieldFactory.apply(AComboField.class);
                    break;
                case link:
                    field = fieldFactory.apply(ALinkField.class);
//                    button = buttonFactory.apply(AButtonEditLink.class);
//                    if (button != null) {
//                        button.inizia();
//                        button.setPublisher(publisher);
//                        field.button = button;
//                    }// end of if cycle
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch

            if (field != null) {
                field.inizializza(publicFieldName, source);
            }// end of if cycle

        } catch (Exception unErrore) { // intercetta l'errore
            log.warn(unErrore.toString());
        }// fine del blocco try-catch

        return field;
    }// end of method

}// end of class

