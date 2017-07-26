package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;

/**
 * Created by gac on 04/06/17.
 * Eventi possibili lanciati dai bottoni della ToolBar un Form
 * Eventi possibili lanciati dai Fields di un Form
 */
@SpringComponent
public interface FormListener {

    public void annulla();

    public void revert();

    public void registra();

    public void fieldModificato();

}// end of interface
