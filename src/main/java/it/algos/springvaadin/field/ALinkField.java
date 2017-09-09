package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonEditLink;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.label.LabelBold;
import it.algos.springvaadin.lib.Cost;
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


    //--componente grafico del field per visualizzare il toString() dell'istanza rappresentata nel field
    private Label label = null;


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public ALinkField() {
        super(null);
    }// end of @Autowired constructor

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public ALinkField(@Qualifier(Cost.BOT_LINK) AButton button) {
        super(button);
    }// end of @Autowired constructor


    /**
     * Regolazioni varie DOPO aver creato l'istanza
     * L'istanza può essere creata da Spring o con clone(), ma necessita comunque di questi due parametri
     */
    protected void inizia(String publicFieldName, ApplicationListener source) {
        super.inizia(publicFieldName, source);
        if (label == null) {
            label = new LabelBold();
        }// end of if cycle

        if (button != null) {
            button.setSource(source);
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        if (button != null) {
            button.setFieldParent(this);
            return new HorizontalLayout(button, label);
        } else {
            return label;
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


    public void setTypeLink(TypeButton typeLink) {
        if (button != null) {
            ((AButtonEditLink) button).setTypeLink(typeLink);
        }// end of if cycle
    }// end of method

    protected void subClonazione(AField oldField) {
        button = oldField.button.clone("");
    }// end of method

}// end of class

