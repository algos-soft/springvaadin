package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.bottone.BottoneEdit;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringComponent
@Qualifier(Cost.TAG_IND)
public class IndirizzoField extends CustomField<Indirizzo> implements AlgosField {


    private ApplicationEventPublisher applicationEventPublisher;
    private IndirizzoBottoneEdit buttonEdit;
    private IndirizzoPresenter indirizzoPresenter;
    private ApplicationListener<AlgosSpringEvent> formSource;

    private String name;
    private Indirizzo indirizzo = null;
    private Label label = new Label();


    public IndirizzoField(ApplicationEventPublisher applicationEventPublisher, IndirizzoBottoneEdit buttonEdit, IndirizzoPresenter indirizzoPresenter) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.indirizzoPresenter = indirizzoPresenter;
        this.buttonEdit = buttonEdit;
        this.buttonEdit.setSource(indirizzoPresenter);
    }// end of Spring constructor


    @Override
    protected Component initContent() {
        return new HorizontalLayout(buttonEdit, label);
    }// end of method

    @Override
    public void doSetValue(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
        buttonEdit.setEntityBean(indirizzo);
        label.setValue(indirizzo.toString());
    }// end of method

    @Override
    public void doValue(AlgosEntity entityBean) {
        this.indirizzo = (Indirizzo) entityBean;
        buttonEdit.setEntityBean(indirizzo);
        label.setValue(indirizzo.toString());
    }// end of method

    @Override
    public Indirizzo getValue() {
        return indirizzo;
    }// end of method

    @Override
    public void setName(String name) {
        this.name = name;
    }// end of method

    @Override
    public String getName() {
        return name;
    }// end of method

    @Override
    public void saveSon() {
        indirizzoPresenter.registra();
    }// end of method

    @Override
    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method

    @Override
    public void setSource(ApplicationListener<AlgosSpringEvent> formSource) {
        this.formSource = formSource;
    }// end of method

}// end of class
