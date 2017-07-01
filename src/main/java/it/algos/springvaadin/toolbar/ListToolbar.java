package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.events.Bottone;
import it.algos.springvaadin.lib.LibParams;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17
 * .
 */
@SpringComponent
public class ListToolbar extends AlgosToolbar {

    public Button buttonNew;
    public Button buttonEdit;
    public Button buttonDelete;
    public Button buttonSearch;
    private Button buttonAll;

    public ListToolbar() {
    }// end of constructor

    @PostConstruct
    public void inizia() {
        buttonNew = super.addButton(Bottone.create);
        buttonEdit = super.addButton(Bottone.edit);
        buttonDelete = super.addButton(Bottone.delete);
        buttonSearch = super.addButton(Bottone.search);
//        buttonAll = super.addButton(Bottone.showAll);
    }// end of method


}// end of class
