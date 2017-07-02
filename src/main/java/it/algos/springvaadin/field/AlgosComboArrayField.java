package it.algos.springvaadin.field;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import it.algos.springvaadin.entity.log.Livello;

/**
 * Created by gac on 30/06/17
 * .
 */
public class AlgosComboArrayField extends CustomField {


    private ComboBox combo = new ComboBox();


    /**
     * The default constructor.
     */
    public AlgosComboArrayField(Object[] items) {
        this(items, true);
    }// end of constructor

    /**
     * The general constructor.
     */
    public AlgosComboArrayField(Object[] items, boolean nullSelectionAllowed) {
        combo.setItems(items);
        combo.setEmptySelectionAllowed(nullSelectionAllowed);
    }// end of constructor

    @Override
    protected Component initContent() {
        combo.setValue(Livello.values()[0]);
        return combo;
    }// end of method


    @Override
    protected void doSetValue(Object items) {
    }// end of method

    @Override
    public Object getValue() {
        return combo.getValue();
    }// end of method

}// end of class
