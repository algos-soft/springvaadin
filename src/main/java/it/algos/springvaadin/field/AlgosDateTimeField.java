package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.lib.LibNum;
import it.algos.springvaadin.lib.MeseEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by gac on 24/06/17
 * .
 */
public class AlgosDateTimeField extends CustomField<LocalDateTime> implements AlgosField{

    private String name;
    private TextField textField;

    public AlgosDateTimeField(String caption) {
        super();
        textField = new TextField();
        setCaption(caption);
        setWidth("10em");
    }


    @Override
    protected Component initContent() {
        return textField;
    }

    public Class<? extends LocalDateTime> getType() {
        return LocalDateTime.class;
    }

    @Override
    protected void doSetValue(LocalDateTime localDateTime) {
        String value = "";
        String sepD = "-";
        String sepDT = "  ";
        String sepT = ":";

//        DateTimeFormatter formatter = DateTimeFormatter
//                .ofLocalizedDateTime(FormatStyle.FULL)
//                .withLocale(Locale.ITALY);


        value += localDateTime.getDayOfMonth();
        value += sepD;
        value += MeseEnum.getShort(localDateTime.getMonthValue());//nome breve (3 caratteri) in italiano
        value += sepD;
        value += localDateTime.getYear();//le ultime due cifre
        value += sepDT;
        value += LibNum.addZeri(localDateTime.getHour());//aggiunge eventuale zero iniziale per avere sempre 2 cifre
        value += sepT;
        value += LibNum.addZeri(localDateTime.getMinute());//aggiunge eventuale zero iniziale per avere sempre 2 cifre
        value += sepT;
        value += LibNum.addZeri(localDateTime.getSecond());//aggiunge eventuale zero iniziale per avere sempre 2 cifre

        textField.setValue(value);
    }// end of method

    @Override
    public LocalDateTime getValue() {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }// end of method

    @Override
    public String getName() {
        return name;
    }// end of method

}// end of class
