package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Created by gac on 23/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per il form
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
@Scope("prototype")
public class FormToolbar extends AlgosToolbar {


    private final Bottone buttonAnnulla;
    private final Bottone buttonRevert;
    private final Bottone buttonRegistra;


    public FormToolbar(
            @Qualifier(Cost.BOT_ANNULLA) Bottone buttonAnnulla,
            @Qualifier(Cost.BOT_REVERT) Bottone buttonRevert,
            @Qualifier(Cost.BOT_REGISTRA) Bottone buttonRegistra) {
        this.buttonAnnulla = buttonAnnulla;
        this.buttonRevert = buttonRevert;
        this.buttonRegistra = buttonRegistra;
    }// end of @Autowired constructor

    /**
     * Metodo invocato da Form
     * Aggiunge i bottoni al contenitore grafico
     */
    @Override
    public void inizia() {
        super.addButton(buttonAnnulla);
        super.addButton(buttonRevert);
        super.addButton(buttonRegistra);
    }// end of method

    @Override
    public void enableAnnulla(boolean status) {
        if (buttonAnnulla != null) {
            buttonAnnulla.setEnabled(status);
        }// end of if cycle
    }// end of method

    @Override
    public void enableRevert(boolean status) {
        if (buttonRevert != null) {
            buttonRevert.setEnabled(status);
        }// end of if cycle
    }// end of method

    @Override
    public void enableRegistra(boolean status) {
        if (buttonRegistra != null) {
            buttonRegistra.setEnabled(status);
        }// end of if cycle
    }// end of method

    /**
     * Inserisce nei bottoni Registra o Accetta il Field che va notificato
     *
     * @param parentField che ha richiesto questo form
     */
    public void setParentField(AField parentField) {
        if (buttonRegistra != null) {
            buttonRegistra.setFieldParent(parentField);
        }// end of if cycle
    }// end of method

}// end of class
