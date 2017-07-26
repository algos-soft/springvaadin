package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.AlgosBottone;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 23/06/17
 *
 * Barra di comando con bottoni, specializzata per il form
 */
@SpringComponent
public class FormToolbar extends AlgosToolbar {

    @Autowired
    @Qualifier(Cost.TAG_BOT_BACK)
    private AlgosBottone buttonAnnulla;

    @Autowired
    @Qualifier(Cost.TAG_BOT_REVERT)
    private AlgosBottone buttonRevert;

    @Autowired
    @Qualifier(Cost.TAG_BOT_REGISTRA)
    private AlgosBottone buttonRegistra;


    public FormToolbar() {
    }// end of constructor

    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge il listener
     */
    @PostConstruct
    public void inizia() {
        super.addButton(buttonAnnulla);
        super.addButton(buttonRevert);
        super.addButton(buttonRegistra);
    }// end of method

    public void enableNew(boolean status) {
        if (buttonAnnulla != null) {
            buttonAnnulla.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableRevert(boolean status) {
        if (buttonRevert != null) {
            buttonRevert.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableRegistra(boolean status) {
        if (buttonRegistra != null) {
            buttonRegistra.setEnabled(status);
        }// end of if cycle
    }// end of method


}// end of class
