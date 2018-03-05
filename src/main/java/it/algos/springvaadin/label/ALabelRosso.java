package it.algos.springvaadin.label;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class ALabelRosso extends ALabel {


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
