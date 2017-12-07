package it.algos.springvaadin.label;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import it.algos.springvaadin.service.AHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 08:31
 */
@SpringComponent
@Scope("singleton")
public class LabelBold extends Label {

    @Autowired
    protected AHtmlService htlmx;

    public LabelBold() {
        this("");
    }// end of constructor

    public LabelBold(String text) {
        super();
        this.setContentMode(ContentMode.HTML);

        this.setValue(text);
    }// end of constructor

    @Override
    public void setValue(String value) {
        if (htlmx != null) {
            super.setValue(htlmx.setBold(value));
        }// end of if cycle
    }// end of method

}// end of class

