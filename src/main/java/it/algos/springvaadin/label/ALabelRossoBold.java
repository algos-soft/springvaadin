package it.algos.springvaadin.label;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 28-feb-2018
 * Time: 12:51
 */
public class ALabelRossoBold  extends ALabelBold{


    public ALabelRossoBold() {
        this("");
    }// end of constructor


    public ALabelRossoBold(VaadinIcons icona) {
        this(icona.getHtml());
    }// end of constructor


    public ALabelRossoBold(String text) {
        super(text);
        this.addStyleName("rosso");
    }// end of constructor


}// end of class
