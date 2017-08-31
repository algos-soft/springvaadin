package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

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


    public TextField field = null;


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

