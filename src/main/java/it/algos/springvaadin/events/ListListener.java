package it.algos.springvaadin.events;

import com.vaadin.spring.annotation.SpringComponent;

/**
 * Created by gac on 03/06/17.
 * Azioni possibili lanciate da una Grid
 */
@SpringComponent
public interface ListListener {

    public void create();

    public void edit();

    public void delete();

    public void search();

    public void showAll();

}// end of interface
