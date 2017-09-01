package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.BottonType;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;

//@SpringComponent
public class IndirizzoBottoneEdit extends Bottone {

    private AEntity entityBean;

    @Autowired
    @Qualifier(Cost.TAG_IND)
    private AlgosField field;

    public IndirizzoBottoneEdit(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.editLink);
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
        applicationEventPublisher.publishEvent(new ButtonSpringEvent(source, type, entityBean, field));
    }// end of method

}// end of class
