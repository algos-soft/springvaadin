package it.algos.springvaadin.field;

import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

public interface AlgosField {


    public void doValue(AlgosEntity entityBean);

    public void setName(String name);

    public String getName();

    public void setDescription(String description) ;

    public void saveSon();

    public AlgosPresenterImpl getFormPresenter() ;

    public void setFormPresenter(AlgosPresenterImpl formPresenter) ;

    }// end of interface
