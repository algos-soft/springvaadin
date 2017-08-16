package it.algos.springvaadin.label;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import it.algos.springvaadin.lib.LibText;

public class LabelRosso extends Label {

    public LabelRosso(String text) {
        super(LibText.setRossoBold(text), ContentMode.HTML);
    }// end of constructor

}// end of class
