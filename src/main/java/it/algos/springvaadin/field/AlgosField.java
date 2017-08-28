package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

public interface AlgosField {


    public void doValue(AlgosEntity entityBean);

    public void setName(String name);

    public String getName();

    public void setDescription(String description);

    public void saveSon();

    public AlgosPresenterImpl getFormPresenter();

    public void setSource(ApplicationListener<AlgosSpringEvent> formSource);

    public Component initContent() ;
}// end of interface
