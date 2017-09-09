package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.ui.Button;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;

//@SpringComponent
public class IndirizzoAButtonEdit extends AButton {

    private AEntity entityBean;

    @Autowired
    @Qualifier(Cost.TAG_IND)
    private AField field;

    public IndirizzoAButtonEdit(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(TypeButton.editLink);
    }// end of @Autowired constructor


    public AEntity getEntityBean() {
        return entityBean;
    }// end of method


    public void setEntityBean(AEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        publisher.publishEvent(new AButtonEvent(type, source, null, entityBean, field));
    }// end of method

}// end of class
