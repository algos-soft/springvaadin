package it.algos.springvaadin.label;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
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
@Scope("prototype")
public class ALabelBold extends ALabel {


    public ALabelBold(AHtmlService htlm) {
        this(htlm, "");
    }// end of constructor

    @Autowired
    public ALabelBold(AHtmlService htlm, String text) {
        super();
        this.htlm = htlm;
        this.setContentMode(ContentMode.HTML);

        this.setValue(text);
    }// end of constructor

    @Override
    public void setValue(String value) {
        if (htlm != null) {
            super.setValue(htlm.setBold(value));
        } else {
            super.setValue(value);
        }// end of if/else cycle
    }// end of method

}// end of class

