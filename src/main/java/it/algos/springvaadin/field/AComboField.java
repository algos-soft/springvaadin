package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
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


    protected void fixCombo(Object[] items, boolean nullSelectionAllowed) {
        if (combo != null) {
            combo.setItems(items);
            combo.setEmptySelectionAllowed(nullSelectionAllowed);
            combo.setValue(items[0]);
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


    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    protected void doSetValue(Object value) {
        if (combo != null) {
            combo.setValue(value);
        }// end of if cycle
    }// end of method

    /**
     * Recupera dalla UI il valore (eventualmente) selezionato
     * Alcuni fields (ad esempio quelli non enabled, ed altri) non modificano il valore
     * Elabora le (eventuali) modifiche effettuate dalla UI e restituisce un valore del typo previsto per il DB mongo
     */
    @Override
    public Object getValue() {
        if (combo != null) {
            return combo.getValue();
        } else {
            return null;
        }// end of if/else cycle
    }// end of method

    /**
     * Aggiunge il listener al field
     */
    protected void addListener() {
        if (combo != null) {
            combo.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                    publish();
                }// end of inner method
            });// end of anonymous inner class
        }// end of if cycle
    }// end of method

}// end of class

