package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 29-ago-2017
 * Time: 07:46
 */
//@SpringComponent
@Qualifier(Cost.FIELD_INTEGER)
public class AIntegerField extends AField {

    private TextField textField;

    public AIntegerField(AlgosPresenterImpl presenter) {
        super(presenter);
    }// end of constructor

    @Override
    public Component initContent() {
        if (textField == null) {
            textField = new TextField();
            addListener();
        }// end of if cycle

        return textField;
    }// end of method

    @Override
    public Integer getValue() {
        String textValue = "";

        if (textField != null) {
            textValue = textField.getValue();
            if (textValue != null && textValue.length() > 0) {
                return Integer.decode(textValue);
            } else {
                return null;
            }// end of if/else cycle
        } else {
            return null;
        }// end of if/else cycle
    }// end of method


    @Override
    protected void doSetValue(Object value) {
        if (textField != null && value instanceof Integer) {
            textField.setValue(value + "");
        }// end of if cycle
    }// end of method


    /**
     * Aggiunge il listener al field
     */
    protected void addListener() {
        if (textField != null) {
            textField.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                    publish();
                }// end of inner method
            });// end of anonymous inner class
        }// end of if cycle

    }// end of method


}// end of class


