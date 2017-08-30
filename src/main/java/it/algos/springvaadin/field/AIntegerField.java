package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 29-ago-2017
 * Time: 07:46
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_INTEGER)
public class AIntegerField extends AField {


    public TextField field = null;

    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        field = new TextField();
    }// end of method


    /**
     * Regola i parametri base per la visualizzazione del field nel form
     * Possono essere sovrascritti nella sottoclasse specifica
     * Possono essere successivamente modificati da una @Annotation
     */
    protected void regolaParametri() {
        super.regolaParametri();
        this.setWidth(STANDARD_INT_WITH);
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
    public Integer getValue() {
        String textValue = "";

        if (field != null) {
            textValue = field.getValue();
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
        if (field != null && value instanceof Integer) {
            field.setValue(value + "");
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

