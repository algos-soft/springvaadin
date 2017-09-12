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
    protected DateField textField;

//    public ADateTimeField(String caption) {
//        super();
//        textField = new TextField();
//        setCaption(caption);
//        setWidth("10em");
//    }

    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        textField = new DateField();
        // Display only year, month, and day in ISO format
        textField.setDateFormat("dd-MM-yyyy");
//        textField.setDateFormat("&w, &d &N &C");

    }// end of method


    public void setWidth(String width) {
        if (textField != null) {
            textField.setWidth(width);
        }// end of if cycle
    }// end of method

    @Override
    public Component initContent() {
        return textField;
    }// end of method


//    public Class<? extends LocalDateTime> getType() {
//        return LocalDateTime.class;
//    }


    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    protected void doSetValue(Object value) {
        String valueTxt = "";
        LocalDateTime localDateTime = null;
        String sepD = "-";
        String sepDT = "  ";
        String sepT = ":";

        if (value instanceof LocalDateTime) {
            localDateTime = (LocalDateTime) value;
        } else {
            return;
        }// end of if/else cycle

        //        DateTimeFormatter formatter = DateTimeFormatter
//                .ofLocalizedDateTime(FormatStyle.FULL)
//                .withLocale(Locale.ITALY);


        valueTxt += localDateTime.getDayOfMonth();
        valueTxt += sepD;
        valueTxt += MeseEnum.getShort(localDateTime.getMonthValue());//nome breve (3 caratteri) in italiano
        valueTxt += sepD;
        valueTxt += localDateTime.getYear();//le ultime due cifre
        valueTxt += sepDT;
        valueTxt += LibNum.addZeri(localDateTime.getHour());//aggiunge eventuale zero iniziale per avere sempre 2 cifre
        valueTxt += sepT;
        valueTxt += LibNum.addZeri(localDateTime.getMinute());//aggiunge eventuale zero iniziale per avere sempre 2 cifre
        valueTxt += sepT;
        valueTxt += LibNum.addZeri(localDateTime.getSecond());//aggiunge eventuale zero iniziale per avere sempre 2 cifre


        textField.setValue(localDateTime.toLocalDate());
    }// end of method

//    @Override
//    public LocalDateTime getValue() {
//        return null;
//    }


//    @Override
//    public void doValue(AEntity entityBean) {
//    }// end of method
//
//    @Override
//    public void saveSon() {
//    }// end of method
//
//    @Override
//    public AlgosPresenterImpl getFormPresenter() {
//        return null;
//    }// end of method
//
//
//    @Override
//    public void setSource(ApplicationListener<AEvent> formSource) {
//    }// end of method

}// end of class
