package it.algos.springvaadin.label;

import com.vaadin.icons.VaadinIcons;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 18-nov-2017
 * Time: 17:56
 */
@Slf4j
public class ALabelVerde extends ALabel {


    public ALabelVerde() {
        this("");
    }// end of constructor


    public ALabelVerde(VaadinIcons icona) {
        this(icona.getHtml());
    }// end of constructor


    public ALabelVerde(String text) {
        super(text);
        this.addStyleName("verde");
    }// end of constructor

}// end of class
