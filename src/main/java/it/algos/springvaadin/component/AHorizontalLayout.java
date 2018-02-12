package it.algos.springvaadin.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by gac on 04 ott 2016.
 */
public class AHorizontalLayout extends HorizontalLayout {

    public AHorizontalLayout() {
    }// end of constructor

    public AHorizontalLayout(Component... children) {
        this();
        this.setSpacing(true);
        this.setMargin(false);
        this.addComponents(children);
    }// end of constructor

}// end of class
