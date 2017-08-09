package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.bottone.TipoBottone;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
public class IndirizzoBottoneEdit extends Bottone {

    private AlgosEntity entityBean;

    public IndirizzoBottoneEdit(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.setCaption("");
        super.setIcon(VaadinIcons.EDIT);
        super.setEnabled(true);
        super.tipo = TipoBottone.edit;

        super.inizia();
    }// end of method


    public void setPresenter(AlgosPresenterImpl presenter) {
        this.presenter = presenter;
    }// end of method


    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method


    public void setEntityBean(AlgosEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method


    /**
     * Recupera il presenter dalla 'catena' grafica attiva
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        applicationEventPublisher.publishEvent(new ButtonSpringEvent(presenter, this, entityBean));
    }// end of method

}// end of class
