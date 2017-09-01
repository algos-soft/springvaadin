package it.algos.springvaadin.field;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.BottoneLink;
import it.algos.springvaadin.label.LabelBold;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 07:38
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_LINK)
public class ALinkField extends AField {


    @Autowired
    private BottoneLink buttonLink;
    private Label label = new LabelBold();

    @Override
    public Component initContent() {
        if (buttonLink != null && label != null) {
            return new HorizontalLayout(buttonLink, label);
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

    @Override
    public void setSource(AlgosPresenterImpl source) {
        if (buttonLink != null) {
            if (source != null) {
                buttonLink.setSource(source);
            }// end of if cycle

            if (entityBean != null) {
                buttonLink.setEntityBean(entityBean);
            }// end of if cycle
        }// end of if cycle
    }// end of method

}// end of class

