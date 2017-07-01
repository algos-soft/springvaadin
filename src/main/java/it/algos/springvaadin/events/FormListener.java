package it.algos.springvaadin.events;

import com.vaadin.spring.annotation.SpringComponent;

/**
 * Created by gac on 04/06/17.
 * Azioni possibili lanciate da un Form
 */
@SpringComponent
public interface FormListener {

    public void annulla();

    public void registra();

}// end of interface
