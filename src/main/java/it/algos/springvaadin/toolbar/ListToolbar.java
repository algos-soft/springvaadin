package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per la lista (Grid)
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
@Scope("prototype")
public class ListToolbar extends AlgosToolbar {


    private final Bottone buttonCreate;
    private final Bottone buttonEdit;
    private final Bottone buttonDelete;
    private final Bottone buttonSearch;


    public ListToolbar(
            @Qualifier(Cost.TAG_BOT_CREATE) Bottone buttonCreate,
            @Qualifier(Cost.TAG_BOT_EDIT) Bottone buttonEdit,
            @Qualifier(Cost.TAG_BOT_DELETE) Bottone buttonDelete,
            @Qualifier(Cost.TAG_BOT_SEARCH) Bottone buttonSearch) {
        this.buttonCreate = buttonCreate;
        this.buttonEdit = buttonEdit;
        this.buttonDelete = buttonDelete;
        this.buttonSearch = buttonSearch;
    }// end of constructor

    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge i bottoni al contenitore grafico
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
