package it.algos.springvaadin.event;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Window;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;

/**
 * Created by gac on 03/06/17.
 * Eventi possibili lanciati dai bottoni della ToolBar di una List contenente una Grid
 */
@SpringComponent
public interface ListListener {

    public void create();

    public void chooser(AEntity entityBean, Window parentDialog);

    public void edit(AEntity entityBean);

    public void editLink(AEntity entityBean, AField sourceField, AButtonType type);

    public void editImage(AEntity entityBean, AField sourceField);

    public void delete();

    public void search();

    public void showAll();

    public void importa();

}// end of interface
