package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;

import java.util.logging.Logger;

@SuppressWarnings({"serial"})
public class AlgosIntegerField extends CustomField<Integer> {

    private TextField textField;
    private static final Logger logger = Logger.getLogger(AlgosIntegerField.class.getName());

    public AlgosIntegerField(String caption) {
        super();
        textField = new TextField();
        setCaption(caption);
        setWidth("5em");
    }

    public AlgosIntegerField() {
        this(null);
    }

    @Override
    protected Component initContent() {
        return textField;
    }


    private void writeValue(Integer integer) {
        setValue(integer);
    }


    public Class<? extends Integer> getType() {
        return Integer.class;
    }

    @Override
    public void setWidth(String width) {
        textField.setWidth(width);
    }

    @Override
    public boolean isReadOnly() {
        return textField.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        textField.setReadOnly(readOnly);
    }

    @Override
    public void setValue(Integer integer) {
        textField.setValue(integer + "");
    }

    @Override
    protected void doSetValue(Integer integer) {
        textField.setValue(integer + "");
    }

    @Override
    public Integer getValue() {
        String textValue = textField.getValue();

        if (textValue != null && textValue.length() > 0) {
            return Integer.decode(textField.getValue());
        } else {
            return null;
        }// end of if/else cycle
    }
}
