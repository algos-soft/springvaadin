package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AlgosBottone;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17
 *
 * Barra di comando con bottoni, specializzata per la lista (Grid)
 */
@SpringComponent
public class ListToolbar extends AlgosToolbar {


    @Autowired
    @Qualifier(Cost.TAG_BOT_CREATE)
    private AlgosBottone buttonCreate;

    @Autowired
    @Qualifier(Cost.TAG_BOT_EDIT)
    private AlgosBottone buttonEdit;

    @Autowired
    @Qualifier(Cost.TAG_BOT_DELETE)
    private AlgosBottone buttonDelete;

    @Autowired
    @Qualifier(Cost.TAG_BOT_SEARCH)
    private AlgosBottone buttonSearch;

    public ListToolbar() {
    }// end of constructor

    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge il listener
     */
    @PostConstruct
    public void inizia() {
        super.addButton(buttonCreate);
        super.addButton(buttonEdit);
        super.addButton(buttonDelete);
        super.addButton(buttonSearch);
    }// end of method

    public void enableNew(boolean status) {
        if (buttonCreate != null) {
            buttonCreate.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableEdit(boolean status) {
        if (buttonEdit != null) {
            buttonEdit.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableDelete(boolean status) {
        if (buttonDelete != null) {
            buttonDelete.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableSearch(boolean status) {
        if (buttonSearch != null) {
            buttonSearch.setEnabled(status);
        }// end of if cycle
    }// end of method

}// end of class
