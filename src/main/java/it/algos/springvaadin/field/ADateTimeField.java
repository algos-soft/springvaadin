package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibNum;
import it.algos.springvaadin.lib.MeseEnum;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by gac on 24/06/17
 * .
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_DATE_TIME)
public class ADateTimeField extends AField {


    /**
     * Componente principale
     */
    protected DateField dateField;


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        dateField = new DateField();
        // Display only year, month, and day in ISO format
        dateField.setDateFormat("dd-MM-yyyy");
    }// end of method


    public void setWidth(String width) {
        if (dateField != null) {
            dateField.setWidth(width);
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        return dateField;
    }// end of method


    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    protected void doSetValue(Object value) {
        LocalDateTime localDateTime = null;

        if (value instanceof LocalDateTime) {
            localDateTime = (LocalDateTime) value;
        } else {
            return;
        }// end of if/else cycle

        dateField.setValue(localDateTime.toLocalDate());
    }// end of method

    /**
     * Recupera dalla UI il valore (eventualmente) selezionato
     * Alcuni fields (ad esempio quelli non enabled, ed altri) non modificano il valore
     * Elabora le (eventuali) modifiche effettuate dalla UI e restituisce un valore del typo previsto per il DB mongo
     */
    @Override
    public Object getValue() {
        LocalDate localDate=dateField.getValue();
//        LocalDateTime localTime = LocalDateTime.of(localDate,null);
        return LocalDateTime.now();
    }// end of method

}// end of class
