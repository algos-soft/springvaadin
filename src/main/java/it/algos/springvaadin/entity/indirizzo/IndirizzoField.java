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
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringComponent
public class IndirizzoField extends CustomField<Indirizzo> implements AlgosField {


    private ApplicationEventPublisher applicationEventPublisher;
    private IndirizzoBottoneEdit buttonEdit;

    private String name;
    private Indirizzo indirizzo = new Indirizzo();
    private Label label = new Label();


    public IndirizzoField(ApplicationEventPublisher applicationEventPublisher, IndirizzoBottoneEdit buttonEdit, IndirizzoPresenter presenter) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.buttonEdit = buttonEdit;
        this.buttonEdit.setPresenter(presenter);
    }// end of Spring constructor


    @Override
    protected Component initContent() {
        return new HorizontalLayout(buttonEdit, label);
    }// end of method

    @Override
    protected void doSetValue(Indirizzo indirizzo) {
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


}// end of class
