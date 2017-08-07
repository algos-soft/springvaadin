package it.algos.springvaadin.field;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

/**
 * Created by gac on 30/06/17
 *
 */
public class AlgosComboClassField extends CustomField implements AlgosField{

    private String name;
    private ComboBox combo = new ComboBox();


    /**
     * The default constructor.
     */
    public AlgosComboClassField(Object[] items) {
        this(items, true);
    }// end of constructor

    /**
     * The general constructor.
     */
    public AlgosComboClassField(Object[] items, boolean nullSelectionAllowed) {
        combo.setItems(items);
        combo.setEmptySelectionAllowed(nullSelectionAllowed);
    }// end of constructor

    @Override
    protected Component initContent() {
        return combo;
    }// end of method


    @Override
    protected void doSetValue(Object items) {
    }// end of method

    @Override
    public Object getValue() {
        return combo.getValue();
    }// end of method

    @Override
    public void setName(String name) {
        this.name = name;
    }// end of method

    @Override
    public String getName() {
        return name;
    }// end of method

}// end of class
