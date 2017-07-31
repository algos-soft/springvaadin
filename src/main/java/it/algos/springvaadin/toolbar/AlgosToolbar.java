package it.algos.springvaadin.toolbar;

import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.lib.LibParams;

/**
 * Created by gac on 03/06/17.
 * .
 * Superclasse per le barre di comando con bottoni
 */
public abstract class AlgosToolbar extends HorizontalLayout {

    void addButton(Bottone bottone) {
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

}// end of class
