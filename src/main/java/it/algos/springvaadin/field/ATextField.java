package it.algos.springvaadin.field;

import com.vaadin.ui.*;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 17:36
 */
public class ATextField extends AField {

    private TextField field;

    @Override
    protected Component initContent() {
        field = new TextField();
        return field;
    }// end of method

    @Override
    public String getValue() {
        return field.getValue();
    }// end of method

    @Override
    protected void doSetValue(Object value) {
        field.setValue((String)value);
    }// end of method

}// end of class

