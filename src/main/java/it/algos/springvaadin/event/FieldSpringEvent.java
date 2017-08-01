package it.algos.springvaadin.event;

import com.vaadin.ui.AbstractField;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Created by gac on 18/07/17
 * .
 */
public class FieldSpringEvent extends AlgosSpringEvent {

    private AbstractField fieldChanged;

    public FieldSpringEvent(AlgosPresenterImpl source) {
        super(source);
    }// end of constructor

    public FieldSpringEvent(AlgosPresenterImpl source, AbstractField fieldChanged) {
        super(source);
        this.fieldChanged = fieldChanged;
    }// end of constructor

    public AbstractField getFieldChanged() {
        return fieldChanged;
    }

}// end of class
