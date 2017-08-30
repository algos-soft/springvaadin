package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 10:37
 */
//@SpringComponent//@todo rimettere
public class AIndirizzoField extends AField<Boolean> implements AlgosField {

    private final Button button = new Button("Off");
    private boolean value;
    private String name;

//    public AIndirizzoField() {
//        super(null);
//    }

    @Override
    public Component initContent() {
        button.addClickListener(event -> {
            setValue(!getValue());
            button.setCaption(getValue() ? "On" : "Off");
        });

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("Click the button"));
        layout.addComponent(button);
        return layout;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    protected void doSetValue(Object value) {
        this.value = (boolean)value;
        button.setCaption((boolean)value ? "On" : "Off");
    }

    @Override
    public void doValue(AlgosEntity entityBean) {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void saveSon() {

    }

    @Override
    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }

    @Override
    public void setSource(ApplicationListener<AlgosSpringEvent> formSource) {

    }
}// end of class

