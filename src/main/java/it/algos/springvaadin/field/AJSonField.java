package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.lib.Cost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 18-ott-2017
 * Time: 07:36
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with 'prototype', in modo da poterne utilizzare più di uno
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare in AFieldFactory
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_IMAGE)
public class AJSonField extends AField{

    private HorizontalLayout placeholder = new HorizontalLayout();

    public void setWidth(String width) {
        if (placeholder != null) {
            placeholder.setWidth(width);
            placeholder.setHeight("4em");
        }// end of if cycle
    }// end of method


}// end of class
