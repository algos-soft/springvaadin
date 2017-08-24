package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.BottonType;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
public class IndirizzoBottoneEdit extends Bottone {

    private AlgosEntity entityBean;

    @Autowired
    @Qualifier(Cost.TAG_IND)
    private AlgosField field;

    public IndirizzoBottoneEdit(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
        super.setType(BottonType.importa);
    }// end of @Autowired constructor

//    /**
//     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
//     */
//    @PostConstruct
//    protected void inizia() {
//        super.setCaption("");
//        super.setIcon(VaadinIcons.EDIT);
//        super.setEnabled(true);
////        super.tipo = TipoBottone.editLink;
//
//        super.inizia();
//    }// end of method



    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method


    public void setEntityBean(AlgosEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        applicationEventPublisher.publishEvent(new ButtonSpringEvent(source, this, entityBean, field));
    }// end of method

}// end of class
