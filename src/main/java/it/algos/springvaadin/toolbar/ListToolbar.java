package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.lib.Cost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

import static lombok.AccessLevel.PACKAGE;

/**
 * Created by gac on 03/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per la lista (Grid)
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
public class ListToolbar extends AlgosToolbar {


    private final AlgosBottone buttonCreate;
    private final AlgosBottone buttonEdit;
    private final AlgosBottone buttonDelete;
    private final AlgosBottone buttonSearch;

    @Autowired
    public ListToolbar(
            @Qualifier(Cost.TAG_BOT_CREATE) AlgosBottone buttonCreate,
            @Qualifier(Cost.TAG_BOT_EDIT) AlgosBottone buttonEdit,
            @Qualifier(Cost.TAG_BOT_DELETE) AlgosBottone buttonDelete,
            @Qualifier(Cost.TAG_BOT_SEARCH) AlgosBottone buttonSearch) {
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
