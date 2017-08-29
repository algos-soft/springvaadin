package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 17:36
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_TEXT)
public class ATextField extends AField {

    private TextField field;

    public ATextField() {
        super();
    }

    @Override
    public Component initContent() {
        if (field == null) {
            field = new TextField();
            addListener();
        }// end of if cycle

        return field;
    }// end of method

    @Override
    public String getValue() {
        if (field != null) {
            return field.getValue();
        } else {
            return null;
        }// end of if/else cycle
    }// end of method


    @Override
    protected void doSetValue(Object value) {
        if (field != null) {
            field.setValue((String) value);
        }// end of if cycle
    }// end of method


    /**
     * Aggiunge il listener al field
     */
    protected void addListener() {
        if (field != null) {
            field.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                    publish();
                }// end of inner method
            });// end of anonymous inner class
        }// end of if cycle

    }// end of method


}// end of class

