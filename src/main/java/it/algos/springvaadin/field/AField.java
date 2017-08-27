package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 11:14
 */
public class AField<T> extends CustomField<Object> {

    private String name;

    @Override
    protected Component initContent() {
        return null;
    }

    @Override
    protected void doSetValue(Object o) {

    }

    @Override
    public Object getValue() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void doValue(AlgosEntity entityBean) {
    }// end of method

    public void saveSon() {
    }// end of method

    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method

    public void setSource(ApplicationListener<AlgosSpringEvent> formSource) {
    }// end of method

}// end of class

