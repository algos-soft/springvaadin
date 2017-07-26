package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.Bottone;
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


    private final Bottone buttonAnnulla;
    private final Bottone buttonRevert;
    private final Bottone buttonRegistra;


    @Autowired
    public FormToolbar(
            @Qualifier(Cost.TAG_BOT_BACK) Bottone buttonAnnulla,
            @Qualifier(Cost.TAG_BOT_REVERT) Bottone buttonRevert,
            @Qualifier(Cost.TAG_BOT_REGISTRA) Bottone buttonRegistra) {
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
