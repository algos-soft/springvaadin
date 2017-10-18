package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import it.algos.springvaadin.entity.preferenza.PrefType;
import it.algos.springvaadin.lib.Cost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
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
public class AJSonField extends AField {

    private AField jSonField = null;

    @Autowired
    private AFieldFactory fieldFactory;

    /**
     * Regolazioni varie DOPO aver creato l'istanza
     * L'istanza può essere creata da Spring o con clone(), ma necessita comunque di questi due parametri
     */
    @Override
    protected void inizializza(String publicFieldName, ApplicationListener source) {
        super.inizializza(publicFieldName, source);
        chooseComponent();
    }// end of method


    @Override
    public void setWidth(String width) {
        if (jSonField != null) {
            jSonField.setWidth(width);
            jSonField.setHeight("3em");
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        return jSonField;
    }// end of method


    private Component chooseComponent() {
        PrefType type = PrefType.string;
        AFieldType fieldType = AFieldType.text;
        jSonField = fieldFactory.crea(null, fieldType, source, "value", null);
        return jSonField;
    }// end of method


    /**
     * Recupera dalla UI il valore (eventualmente) selezionato
     * Alcuni fields (ad esempio quelli non enabled, ed altri) non modificano il valore
     * Elabora le (eventuali) modifiche effettuate dalla UI e restituisce un valore del typo previsto per il DB mongo
     */
    @Override
    public byte[] getValue() {
        String textValue;
        if (jSonField != null) {
            textValue = (String) jSonField.getValue();
            return PrefType.string.objectToBytes(textValue);
        } else {
            return null;
        }// end of if/else cycle
    }// end of method


    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    public void doSetValue(Object value) {
        String stringValue=(String)PrefType.string.bytesToObject((byte[])value);
        jSonField.setValue(stringValue);
    }// end of method

}// end of class
