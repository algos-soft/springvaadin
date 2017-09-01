package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

import java.util.Date;

@SuppressWarnings("serial")
public class AlgosDateField extends DateField implements AlgosField{

    private String name;
    public AlgosDateField() {
        this(null);
    }// end of constructor

    public AlgosDateField(String caption) {
        this(caption, null);
    }// end of constructor

    public AlgosDateField(String caption, Date value) {
        super(caption);
    }// end of constructor

    // restituisce la data corrente del giorno
    public static AlgosDateField oggi(String caption) {
        AlgosDateField dataCorrente = null;
        Date data = new Date();

        dataCorrente = new AlgosDateField(caption, data);

        return dataCorrente;
    }// end of method

    // restituisce la data del 1 gennaio dell'anno corrente
    public static AlgosDateField primoGennaio(String caption) {
        AlgosDateField dataCorrente = null;
        Date data = new Date();
        data.setDate(1);
        data.setMonth(0);

        // provvisorio
        dataCorrente = new AlgosDateField(caption, data);

        return dataCorrente;
    }// end of method

    @Override
    public void setName(String name) {
        this.name = name;
    }// end of method

    @Override
    public String getName() {
        return name;
    }// end of method

    @Override
    public void doValue(AEntity entityBean) {
    }// end of method

    @Override
    public void saveSon() {
    }// end of method

    @Override
    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method

    @Override
    public void setSource(ApplicationListener<AEvent> formSource) {
    }// end of method

    public Component initContent() {
        return null;
    }

}// end of class
