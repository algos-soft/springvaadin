package it.algos.springvaadin.event;


import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Window;
import it.algos.springvaadin.bottone.BottonType;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 03/06/17.
 * Eventi generati dai bottoni
 * Link: http://www.baeldung.com/spring-events
 */
public class ButtonSpringEvent extends AlgosSpringEvent {


    private final BottonType type;

    //--opzionale
    private AlgosField parentField;

    //--opzionale
    private Window parentDialog;

    //--L'entityBean viene inserita come parametro opzionale
    private AlgosEntity entityBean;


    public ButtonSpringEvent(ApplicationListener source, BottonType type) {
        this(source, type, (AlgosEntity) null);
    }// end of constructor

    public ButtonSpringEvent(ApplicationListener source, BottonType type, Window parentDialog) {
        super(source);
        this.type = type;
        this.parentDialog = parentDialog;
    }// end of constructor


    public ButtonSpringEvent(ApplicationListener source, BottonType type, AlgosEntity entityBean) {
        super(source);
        this.type = type;
        this.entityBean = entityBean;
    }// end of constructor

    public ButtonSpringEvent(ApplicationListener source, BottonType type, AlgosEntity entityBean, AlgosField parentField) {
        super(source);
        this.type = type;
        this.entityBean = entityBean;
        this.parentField = parentField;
    }// end of constructor


    public BottonType getType() {
        return type;
    }// end of method

    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method

    public AlgosField getParentField() {
        return parentField;
    }// end of method

    public Window getParentDialog() {
        return parentDialog;
    }// end of method

    public void setParentDialog(Window parentDialog) {
        this.parentDialog = parentDialog;
    }// end of method

}// end of class