package it.algos.springvaadin.field;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.bottone.BottoneLink;
import it.algos.springvaadin.label.LabelBold;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.search.AlgosSearch;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 07:38
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with 'prototype', in modo da poterne utilizzare più di uno
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare in AFieldFactory
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_LINK)
public class ALinkField extends AField {


    private Label label = new LabelBold();


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public ALinkField(@Qualifier(Cost.BOT_LINK) Bottone button) {
        super(button);
    }// end of @Autowired constructor


    @Override
    public Component initContent() {
        if (button != null && label != null) {
            return new HorizontalLayout(button, label);
        } else {
            return null;
        }// end of if/else cycle
    }// end of method

    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    protected void doSetValue(Object value) {
        if (value != null) {
            label.setValue(value.toString());
        } else {
            label.setValue("");
        }// end of if/else cycle
    }// end of method


}// end of class

