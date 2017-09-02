package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.ALinkField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.toolbar.FormToolbar;

/**
 * Created by gac on 13/06/17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_COMP)
public class CompanyForm extends AlgosFormImpl {

    @Autowired
    private IndirizzoPresenter target;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public CompanyForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     * Eventuali regolazioni specifiche per i fields
     */
    @Override
    protected void fixFields() {
        AField field = getField("indirizzo");

        if (field != null && field instanceof ALinkField) {
            field.setEntityBean(entityBean);
            field.setTarget(target);
        }// end of if cycle

    }// end of method

}// end of class
