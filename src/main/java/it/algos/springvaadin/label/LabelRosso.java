package it.algos.springvaadin.label;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class LabelRosso extends Label {


    public LabelRosso() {
        this("");
    }// end of constructor


    public LabelRosso(String text) {
        super(text);
        this.addStyleName("rosso");
    }// end of constructor


}// end of class