package it.algos.springvaadin.toolbar;

import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Created by gac on 03/06/17.
 * .
 * Superclasse per le barre di comando con bottoni
 */
public abstract class AlgosToolbar extends HorizontalLayout {


    /**
     * Metodo invocato da Form
     * Aggiunge i bottoni al contenitore grafico
     */
    public void inizia() {
    }// end of method


    public void addButton(Bottone bottone) {
        addButton(bottone, "");
    }// end of method

    void addButton(Bottone bottone, String styleName) {

        if (LibParams.usaBottoniColorati()) {
            if (!styleName.equals("")) {
                bottone.addStyleName(styleName);
            }// end of if cycle
        }// end of if cycle

        addComponent(bottone);
    }// end of method


    public void addButtonWithPresenter(Bottone bottone,AlgosPresenterImpl presenter) {
        addButton(bottone, "");
        bottone.setPresenter(presenter);
    }// end of method

    public void setPresenter(AlgosPresenterImpl presenter) {
        for (int k = 0; k < getComponentCount(); k++) {
            ((Bottone) getComponent(k)).setPresenter(presenter);
        }// end of for cycle
    }// end of method

    public void enableAnnulla(boolean status) {
    }// end of method

    public void enableRevert(boolean status) {
    }// end of method

    public void enableRegistra(boolean status) {
    }// end of method

    public void enableAccetta(boolean status) {
    }// end of method

}// end of class
