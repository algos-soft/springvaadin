package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import it.algos.springvaadin.field.AComboField;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AImageField;
import it.algos.springvaadin.field.AJSonField;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PRE)
public class PreferenzaForm extends AlgosFormImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public PreferenzaForm(@Qualifier(Cost.BAR_FORM) AToolbar toolbar,
                          @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        super(toolbar, toolbarLink);
    }// end of Spring constructor


    /**
     * Eventuali regolazioni specifiche per i fields
     */
    @Override
    protected void fixFields() {
        this.addChangeTypeButton();
    }// end of method


    /**
     * Fissa il target del field Type in modo che 'lanci' i cambiamenti verso il field AJSonField
     */
    private void addChangeTypeButton() {
        AField fType = getField("type");
        AField fValue = getField("value");
        AComboField fieldType;
        AJSonField fieldValue;

        if (fType != null && fType instanceof AComboField && fValue != null && fValue instanceof AJSonField) {
            fieldType = (AComboField) fType;
            fieldValue = (AJSonField) fValue;
        } else {
            return;
        }// end of if/else cycle

        fieldType.setTarget(fieldValue);
    }// end of method

}// end of class

