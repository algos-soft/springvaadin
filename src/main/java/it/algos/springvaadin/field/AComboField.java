package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 31-ago-2017
 * Time: 22:42
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.FIELD_COMBO)
public class AComboField extends AField {

    private ComboBox combo = null;


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        combo = new ComboBox();
    }// end of method


    public void fixCombo(Object[] items, boolean nullSelectionAllowed) {
        if (combo != null) {
            combo.setItems(items);
            combo.setEmptySelectionAllowed(nullSelectionAllowed);
        }// end of if cycle
    }// end of method


    public void setWidth(String width) {
        if (combo != null) {
            combo.setWidth(width);
        }// end of if cycle
    }// end of method


    @Override
    public Component initContent() {
        return combo;
    }// end of method


}// end of class

