package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonFactory;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.dialog.ImageDialog;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.LibAnnotation;
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
//    private Function<Class<? extends AButton>, AButton> buttonFactory;
    private AButtonFactory buttonFactory;

    /**
     * Funzione specificata in AlgosConfiguration
     */
    private Function<Class<? extends AField>, AField> fieldFactory;


    /**
     * Publisher degli eventi a livello Application
     */
    private ApplicationEventPublisher publisher;


    @Autowired
    private ImageDialog targetImageAutowired;

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
            AButtonFactory buttonFactory,
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
    public AField crea(final Class<? extends AEntity> clazz,AFieldType type, ApplicationListener source, ApplicationListener target, String publicFieldName) {
        AField field = null;

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
                case checkbox:
                    field = fieldFactory.apply(ACheckBoxField.class);
                    break;
                case localdatetime:
                    field = fieldFactory.apply(ADateTimeField.class);
                    break;
                case dateNotEnabled:
                    field = fieldFactory.apply(ADateNotEnabledField.class);
                    break;
                case image:
                    field = fieldFactory.apply(AImageField.class);
                    target = targetImageAutowired;
                    field.setButton(buttonFactory.crea(AButtonType.image, source, target, field));
                    break;
                case resource:
                    field = fieldFactory.apply(AImageField.class);
                    break;
                case combo:
                    field = fieldFactory.apply(AComboField.class);
                    break;
                case link:
                    field = fieldFactory.apply(ALinkField.class);
                    if (LibAnnotation.isDBRef(clazz,publicFieldName)) {
                        ((ALinkField) field).setButtonEdit(buttonFactory.crea(AButtonType.editLinkDBRef, source, target, field));
                    } else {
                        ((ALinkField) field).setButtonEdit(buttonFactory.crea(AButtonType.editLinkNoDBRef, source, target, field));
                    }// end of if/else cycle
                    ((ALinkField) field).setButtonDelete(buttonFactory.crea(AButtonType.deleteLink, source, target, field));
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch

            if (field != null) {
                field.inizializza(publicFieldName, source, target);
            }// end of if cycle

        } catch (Exception unErrore) { // intercetta l'errore
            log.warn(unErrore.toString());
        }// fine del blocco try-catch

        return field;
    }// end of method

}// end of class

