package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 31-ago-2017
 * Time: 22:31
 */
public abstract class AText extends AField {


    protected TextField field = null;


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        field = new TextField();
    }// end of method


    public void setWidth(String width) {
        if (field != null) {
            field.setWidth(width);
        }// end of if cycle
    }// end of method


    public void setFocus(boolean focus) {
        if (field != null && focus) {
            field.focus();
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        return field;
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

