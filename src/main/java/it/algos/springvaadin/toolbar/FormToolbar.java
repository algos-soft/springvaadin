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
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
public class FormToolbar extends AlgosToolbar {


    private final AlgosBottone buttonAnnulla;
    private final AlgosBottone buttonRevert;
    private final AlgosBottone buttonRegistra;


    @Autowired
    public FormToolbar(
            @Qualifier(Cost.TAG_BOT_BACK) AlgosBottone buttonAnnulla,
            @Qualifier(Cost.TAG_BOT_REVERT) AlgosBottone buttonRevert,
            @Qualifier(Cost.TAG_BOT_REGISTRA) AlgosBottone buttonRegistra) {
        this.buttonAnnulla = buttonAnnulla;
        this.buttonRevert = buttonRevert;
        this.buttonRegistra = buttonRegistra;
    }// end of constructor

    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge i bottoni al contenitore grafico
     */
    @PostConstruct
    public void inizia() {
        super.addButton(buttonAnnulla);
        super.addButton(buttonRevert);
        super.addButton(buttonRegistra);
    }// end of method

    public void enableAnnulla(boolean status) {
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
