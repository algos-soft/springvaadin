package it.algos.springvaadin.label;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import it.algos.springvaadin.service.AHtmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 11-feb-2018
 * Time: 11:29
 */
@Slf4j
@SpringComponent
@Scope("prototype")
public class ALabel extends Label {

    /**
     * Service (@Scope = 'singleton'). Unica per tutta l'applicazione. Usata come libreria.
     */
    @Autowired
    protected AHtmlService htlm;


    public ALabel() {
        this("");
    }// end of constructor


    public ALabel(String text) {
        super();
        this.setContentMode(ContentMode.HTML);

        this.setValue(text);
    }// end of constructor

}// end of class
