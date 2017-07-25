package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.AlgosBottone;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.bottone.BottoneCreate;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17
 * .
 */
@SpringComponent
public class ListToolbar extends AlgosToolbar {


    @Autowired()
    @Qualifier(Cost.TAG_BOT_CREATE)
    private AlgosBottone buttonCreate;

    @Autowired()
    @Qualifier(Cost.TAG_BOT_EDIT)
    private AlgosBottone buttonEdit;

    @Autowired()
    @Qualifier(Cost.TAG_BOT_DELETE)
    private AlgosBottone buttonDelete;

    @Autowired()
    @Qualifier(Cost.TAG_BOT_SEARCH)
    private AlgosBottone buttonSearch;

    public ListToolbar() {
    }// end of constructor

    @PostConstruct
    public void inizia() {
        super.addButton(buttonCreate);
        super.addButton(buttonEdit);
        super.addButton(buttonDelete);
        super.addButton(buttonSearch);
    }// end of method

    public void enableNew(boolean status) {
        this.buttonCreate.setEnabled(status);
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
