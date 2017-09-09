package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonEditLink;
import it.algos.springvaadin.dialog.ImageDialog;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 30-ago-2017
 * Time: 11:14
 */
@SpringComponent
@Slf4j
public class AFieldFactoryImpl implements AFieldFactory {


    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    public Function<Class<? extends AButton>, AButton> buttonFactory;

    @Autowired
    private Function<Class<? extends AField>, AField> fieldFactory;


    public AField crea(AFType type, String publicFieldName, AlgosPresenterImpl source, Object[] items) {
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
                    if (field != null) {
                        field.fixCombo(items, false);
                    }// end of if cycle
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
                field.inizia(publicFieldName, source);
            }// end of if cycle

        } catch (Exception unErrore) { // intercetta l'errore
            log.warn(unErrore.toString());
        }// fine del blocco try-catch

        return field;
    }// end of method

}// end of class

