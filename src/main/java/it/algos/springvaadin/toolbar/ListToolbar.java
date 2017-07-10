package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.Bottone;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17
 * .
 */
@SpringComponent
public class ListToolbar extends AlgosToolbar {

    private Button buttonNew;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonSearch;
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

    public void enableNew(boolean status) {
        this.buttonNew.setEnabled(status);
    }// end of method

    public void enableEdit(boolean status) {
        this.buttonEdit.setEnabled(status);
    }// end of method

    public void enableDelete(boolean status) {
        this.buttonDelete.setEnabled(status);
    }// end of method

    public void enableSearch(boolean status) {
        this.buttonSearch.setEnabled(status);
    }// end of method

}// end of class
