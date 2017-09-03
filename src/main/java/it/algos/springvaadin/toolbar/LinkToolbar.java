package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 23/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per il form
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
@Scope("prototype")
public class LinkToolbar extends AlgosToolbar {


    private final Bottone buttonAnnulla;
    private final Bottone buttonRevert;
    private final Bottone buttonAccettaLink;
    private final Bottone buttonRegistraLink;


    public LinkToolbar(
            @Qualifier(Cost.BOT_BACK) Bottone buttonAnnulla,
            @Qualifier(Cost.BOT_REVERT) Bottone buttonRevert,
            @Qualifier(Cost.BOT_REVERT) Bottone buttonAccettaLink,
            @Qualifier(Cost.BOT_ACCETTA) Bottone buttonRegistraLink) {
        this.buttonAnnulla = buttonAnnulla;
        this.buttonRevert = buttonRevert;
        this.buttonAccettaLink = buttonAccettaLink;
        this.buttonRegistraLink = buttonRegistraLink;
    }// end of @Autowired constructor

    /**
     * Metodo invocato (dalla annotation) DOPO il costruttore
     * Aggiunge i bottoni al contenitore grafico
     */
    @PostConstruct
    public void inizia() {
        super.addButton(buttonAnnulla);
        super.addButton(buttonRevert);
        super.addButton(buttonAccettaLink);
        super.addButton(buttonRegistraLink);
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

    public void enableAccetta(boolean status) {
        if (buttonAccettaLink != null) {
            buttonAccettaLink.setEnabled(status);
        }// end of if cycle
    }// end of method

    public void enableRegistra(boolean status) {
        if (buttonRegistraLink != null) {
            buttonRegistraLink.setEnabled(status);
        }// end of if cycle
    }// end of method


}// end of class
