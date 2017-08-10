package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.model.AlgosEntity;

/**
 * Created by gac on 03/06/17.
 * Eventi possibili lanciati dai bottoni della ToolBar di una List contenente una Grid
 */
@SpringComponent
public interface ListListener {

    public void create();

    public void edit(AlgosEntity entityBean);

    public void edit(AlgosEntity entityBean, AlgosField parentField);

    public void delete();

    public void search();

    public void showAll();

}// end of interface
