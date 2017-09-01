package it.algos.springvaadin.bottone;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BOT_EDIT)
public class BottoneEdit extends Bottone {


    public BottoneEdit(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.edit);
    }// end of @Autowired constructor


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        AEntity entityBean = null;

        if (((AlgosPresenterImpl) source).getView().isUnaRigaSelezionata()) {
            entityBean = ((AlgosPresenterImpl) source).getView().getEntityBean();
        }// end of if cycle

        applicationEventPublisher.publishEvent(new ButtonSpringEvent(source, type, entityBean));
    }// end of method

}// end of class
