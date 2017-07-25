package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.Bottone;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 23/06/17
 * .
 */
@SpringComponent
public class FormToolbar extends AlgosToolbar {

    private Button buttonAnnulla;
    private Button buttonRevert;
    private Button buttonRegistra;

    public FormToolbar() {
    }// end of constructor

    @PostConstruct
    public void inizia() {
//        buttonAnnulla = super.addButton(Bottone.back);//@todo rimettere
//        buttonRevert = super.addButton(Bottone.revert);//@todo rimettere
//        buttonRegistra = super.addButton(Bottone.registra);//@todo rimettere
    }// end of method

    public Button getButtonAnnulla() {
        return buttonAnnulla;
    }

    public Button getButtonRevert() {
        return buttonRevert;
    }

    public Button getButtonRegistra() {
        return buttonRegistra;
    }
}// end of class
