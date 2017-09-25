package it.algos.springvaadin.entity.indirizzo;

import lombok.extern.slf4j.Slf4j;
import com.vaadin.ui.*;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 16-set-2017
 * Time: 15:57
 */
@Slf4j


//@SpringComponent
@Qualifier(Cost.TAG_IND)
public class IndirizzoField extends CustomField<AEntity> implements AlgosField {


    private ApplicationEventPublisher applicationEventPublisher;
    private IndirizzoAButtonEdit buttonEdit;
    private IndirizzoPresenter indirizzoPresenter;
    private ApplicationListener<AEvent> formSource;

    private String name;
    private AEntity indirizzo = null;
    private Label label = new Label();


    public IndirizzoField(ApplicationEventPublisher applicationEventPublisher, IndirizzoPresenter indirizzoPresenter) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.indirizzoPresenter = indirizzoPresenter;
        this.buttonEdit = buttonEdit;
        this.buttonEdit.setSource(indirizzoPresenter);
    }// end of Spring constructor


    @Override
    public Component initContent() {
        return new HorizontalLayout(buttonEdit, label);
    }// end of method

    @Override
    public void doSetValue(AEntity indirizzo) {
        this.indirizzo = indirizzo;
        buttonEdit.setEntityBean(indirizzo);
        label.setValue(indirizzo.toString());
    }// end of method

    @Override
    public void doValue(AEntity entityBean) {
        this.indirizzo = (AEntity) entityBean;
        buttonEdit.setEntityBean(indirizzo);
        label.setValue(indirizzo.toString());
    }// end of method

    @Override
    public AEntity getValue() {
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
    public void setSource(ApplicationListener<AEvent> formSource) {
        this.formSource = formSource;
    }// end of method

}// end of class

