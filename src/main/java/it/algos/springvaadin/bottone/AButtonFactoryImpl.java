package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.*;
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
 * Date: ven, 08-set-2017
 * Time: 15:34
 */
@SpringComponent
@Slf4j
public class AButtonFactoryImpl implements AButtonFactory {


    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    public Function<Class<? extends AButton>, AButton> buttonFactory;


    public AButton crea(TypeButton type, ApplicationListener source) {
        AButton button = null;

        switch (type) {
            case accetta:
                button = buttonFactory.apply(AButtonAccetta.class);
                break;
            case annulla:
                break;
            case back:
                break;
            case chooser:
                break;
            case create:
                button = buttonFactory.apply(AButtonCreate.class);
                break;
            case edit:
                button = buttonFactory.apply(AButtonEdit.class);
                break;
            case delete:
                button = buttonFactory.apply(AButtonDelete.class);
                break;
            case search:
                button = buttonFactory.apply(AButtonSearch.class);
                break;
            case importa:
                button = buttonFactory.apply(AButtonImport.class);
                break;
            default:
                log.warn("AButtonFactoryImpl - Caso non definito di switch");
                break;
        } // end of switch statement

        if (button != null) {
            button.setPublisher(publisher);
            button.setSource(source);
        }// end of if cycle

        return button;
    }// end of method

}// end of class


