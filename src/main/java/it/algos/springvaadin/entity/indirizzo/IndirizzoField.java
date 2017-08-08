package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.bottone.BottoneEdit;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringComponent
public class IndirizzoField extends CustomField<Indirizzo> implements AlgosField {


    private ApplicationEventPublisher applicationEventPublisher;
    private Bottone buttonEdit;
    private IndirizzoPresenter presenter;

    private String name;
    private Indirizzo indirizzo = new Indirizzo();
    private Label label = new Label();

    public IndirizzoField(ApplicationEventPublisher applicationEventPublisher, BottoneEdit buttonEdit, IndirizzoPresenter presenter) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.buttonEdit = buttonEdit;
        this.presenter = presenter;
    }// end of Spring constructor

    @Override
    protected Component initContent() {
        // Handle the event with an anonymous class
        buttonEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fire(clickEvent);
            }// end of inner method
        });// end of anonymous inner class
        return new HorizontalLayout(buttonEdit, label);
    }// end of method

    @Override
    protected void doSetValue(Indirizzo indirizzo) {
        int a = 87;
        label.setValue(indirizzo.toString());
    }// end of method

    @Override
    public Indirizzo getValue() {
        int a = 33;
        return null;
    }// end of method

    @Override
    public void setName(String name) {
        this.name = name;
    }// end of method

    @Override
    public String getName() {
        return name;
    }// end of method

    /**
     */
    private void fire(Button.ClickEvent clickEvent) {
        presenter.modifica(indirizzo);
        presenter.registraModifiche();
    }// end of method

}// end of class
