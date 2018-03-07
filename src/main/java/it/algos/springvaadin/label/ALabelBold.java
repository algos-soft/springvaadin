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
public class ALabelBold extends ALabel {


    public ALabelBold() {
        this( "");
    }// end of constructor

    public ALabelBold( String text) {
        super();
        this.setContentMode(ContentMode.HTML);

        this.setValue(text);
    }// end of constructor

    @Override
    public void setValue(String value) {
        super.setValue("<strong>" + value + "</strong>");
    }// end of method

}// end of class

