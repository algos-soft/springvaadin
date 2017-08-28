package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 30/06/17
 */
public class AlgosComboClassField extends CustomField implements AlgosField {

    private String name;
    private ComboBox combo = new ComboBox();
    private ApplicationListener<AlgosSpringEvent> formSource;


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
    public Component initContent() {
        return combo;
    }// end of method


    @Override
    protected void doSetValue(Object item) {
        combo.setValue(item);
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

    @Override
    public void doValue(AlgosEntity entityBean) {
        int a = 87;
    }// end of method

    @Override
    public void saveSon() {
    }// end of method

    @Override
    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method


    @Override
    public void setSource(ApplicationListener<AlgosSpringEvent> formSource) {
        this.formSource = formSource;
    }// end of method

    public ComboBox getCombo() {
        return combo;
    }// end of method

}// end of class

