package it.algos.springvaadin.event;

import com.vaadin.ui.AbstractField;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 18/07/17
 * .
 */
public class FieldSpringEvent extends AlgosSpringEvent {

    private AField fieldChanged;

    public FieldSpringEvent(AlgosPresenterImpl source) {
        super(source);
    }// end of constructor

    public FieldSpringEvent(AlgosPresenterImpl source, AField fieldChanged) {
        super(source);
        this.fieldChanged = fieldChanged;
    }// end of constructor

    public AField getFieldChanged() {
        return fieldChanged;
    }// end of method

}// end of class
