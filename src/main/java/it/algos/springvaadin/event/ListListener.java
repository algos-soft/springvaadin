package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;

/**
 * Created by gac on 03/06/17.
 * Eventi possibili lanciati dai bottoni della ToolBar di una List contenente una Grid
 */
@SpringComponent
public interface ListListener {

    public void create();

    public void edit();

    public void delete();

    public void search();

    public void showAll();

}// end of interface
