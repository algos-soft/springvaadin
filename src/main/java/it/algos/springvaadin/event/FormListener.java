package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;

/**
 * Created by gac on 04/06/17.
 * Azioni possibili lanciate da un Form
 */
@SpringComponent
public interface FormListener {

    public void annulla();

    public void revert();

    public void registra();

    public void fieldModificato();

}// end of interface
