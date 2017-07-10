package it.algos.springvaadin.form;

import com.vaadin.ui.Component;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 10/07/17
 * .
 */
public interface AlgosForm {

    /**
     */
    public void restart(AlgosEntity entity, List<String> fields);


    /**
     * Restituisce il componente concreto
     */
    public Component getComponent();

}// end of interface
