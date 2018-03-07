package it.algos.springvaadin.label;

import com.vaadin.icons.VaadinIcons;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 07-mar-2018
 * Time: 08:39
 */
@Slf4j
public class ALabelRosso extends ALabel{

    public ALabelRosso() {
        this("");
    }// end of constructor


    public ALabelRosso(VaadinIcons icona) {
        this(icona.getHtml());
    }// end of constructor


    public ALabelRosso(String text) {
        super(text);
        this.addStyleName("rosso");
    }// end of constructor

}// end of class
