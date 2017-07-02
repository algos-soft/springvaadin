package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.Bottone;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 23/06/17
 * .
 */
@SpringComponent
public class FormToolbar extends AlgosToolbar {

    private Button buttonAnnulla;
    private Button buttonRegistra;

    public FormToolbar() {
    }// end of constructor

    @PostConstruct
    public void inizia() {
        buttonAnnulla = super.addButton(Bottone.back);
        buttonRegistra = super.addButton(Bottone.registra);
    }// end of method

    public Button getButtonAnnulla() {
        return buttonAnnulla;
    }

    public Button getButtonRegistra() {
        return buttonRegistra;
    }
}// end of class
