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
//@SpringComponent
//@Scope("prototype")
public class ALabelVerdeBold  extends ALabelBold{


    public ALabelVerdeBold() {
        this("");
    }// end of constructor


    public ALabelVerdeBold(VaadinIcons icona) {
        this(icona.getHtml());
    }// end of constructor


    public ALabelVerdeBold(String text) {
        super(text);
        this.addStyleName("verde");
    }// end of constructor


}// end of class
