package it.algos.springvaadin.event;

import com.vaadin.ui.AbstractField;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 18/07/17
 * .
 */
public class FieldSpringEvent extends AlgosSpringEvent {

    private AbstractField fieldChanged;

    public FieldSpringEvent(AlgosPresenter source) {
        super(source);
    }// end of constructor

    public FieldSpringEvent(AlgosPresenter source, AbstractField fieldChanged) {
        super(source);
        this.fieldChanged = fieldChanged;
    }// end of constructor

    public AbstractField getFieldChanged() {
        return fieldChanged;
    }

}// end of class
