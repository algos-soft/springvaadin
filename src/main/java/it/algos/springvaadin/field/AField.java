package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
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
public class AField<T> extends CustomField<Object> implements AlgosField {

    private String name;
    private AlgosPresenterImpl presenter;


    public AField(AlgosPresenterImpl presenter) {
        super();
        this.presenter = presenter;
        addListener();
    }

    @Override
    public Component initContent() {
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

    public void setPresenter(AlgosPresenterImpl presenter) {
        this.presenter = presenter;
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


    /**
     * Aggiunge il listener al field
     */
    protected void addListener() {
//        ((CustomField) this).addValueChangeListener(new HasValue.ValueChangeListener<String>() {
//            @Override
//            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
//                publish();
//            }// end of inner method
//        });// end of anonymous inner class
    }// end of method

    /**
     *
     */
    protected void publish() {
        if (presenter != null) {
            presenter.getApplicationEventPublisher().publishEvent(new FieldSpringEvent(presenter));
        }// end of if cycle
    }// end of method


}// end of class

