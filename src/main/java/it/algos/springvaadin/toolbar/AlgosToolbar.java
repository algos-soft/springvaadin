package it.algos.springvaadin.toolbar;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.event.Bottone;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 03/06/17.
 * .
 */
public abstract class AlgosToolbar extends HorizontalLayout {

    public AlgosToolbar() {
    }// end of constructor

    @PostConstruct
    protected void inizia() {
    }// end of method

    protected Button addButton(Bottone bottoneEnum) {
        Button bottone = bottoneEnum.get();
        addComponent(bottone);
        return bottone;
    }// end of method

}// end of class
