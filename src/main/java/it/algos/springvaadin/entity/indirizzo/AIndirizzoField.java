package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 10:37
 */
@SpringComponent
public class AIndirizzoField extends CustomField<Boolean> {

    private final Button button = new Button("Off");
    private boolean value;

    @Override
    protected Component initContent() {
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
    protected void doSetValue(Boolean value) {
        this.value = value;
        button.setCaption(value ? "On" : "Off");
    }
}// end of class

