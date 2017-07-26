package it.algos.springvaadin.toolbar;

import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.bottone.Bottone;

/**
 * Created by gac on 03/06/17.
 * .
 * Superclasse per le barre di comando con bottoni
 */
public abstract class AlgosToolbar extends HorizontalLayout {

    void addButton(Bottone bottone) {
        addComponent(bottone);
    }// end of method

}// end of class
