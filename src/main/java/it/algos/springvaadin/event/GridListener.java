package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.model.AlgosModel;

/**
 * Created by gac on 03/06/17.
 * Azioni possibili lanciate da una Grid
 */
@SpringComponent
public interface GridListener {

    public void attach();

    public void click();

    public void doppioClick(AlgosModel entityBean);

    public void selectionChanged();

    public void listener();

}// end of interface
