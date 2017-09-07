package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Created by gac on 03/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per la lista (Grid)
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BAR_LIST)
public class ListToolbar extends AlgosToolbar {


    private final AButton buttonCreate;
    private final AButton buttonEdit;
    private final AButton buttonDelete;
    private final AButton buttonSearch;

    private boolean usaBottoneRicerca = true;


    public ListToolbar(
            @Qualifier(Cost.BOT_CREATE) AButton buttonCreate,
            @Qualifier(Cost.BOT_EDIT) AButton buttonEdit,
            @Qualifier(Cost.BOT_DELETE) AButton buttonDelete,
            @Qualifier(Cost.BOT_SEARCH) AButton buttonSearch) {
        this.buttonCreate = buttonCreate;
        this.buttonEdit = buttonEdit;
        this.buttonDelete = buttonDelete;
        this.buttonSearch = buttonSearch;
    }// end of constructor


    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge i bottoni al contenitore grafico
     */
    public void inizia() {
        this.removeAllComponents();
        super.addButton(buttonCreate);
        super.addButton(buttonEdit);
        super.addButton(buttonDelete);

        if (usaBottoneRicerca) {
            super.addButton(buttonSearch);
        }// end of if cycle

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

    public void setUsaBottoneRicerca(boolean usaBottoneRicerca) {
        this.usaBottoneRicerca = usaBottoneRicerca;
    }// end of method

}// end of class
