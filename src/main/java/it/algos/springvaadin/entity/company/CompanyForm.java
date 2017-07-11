package it.algos.springvaadin.entity.company;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Layout;
import it.algos.springvaadin.entity.log.Log;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibField;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 25/06/17
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@SpringComponent
@Qualifier(Cost.TAG_COMP)
public class CompanyForm extends AlgosFormImpl {


    /**
     * Costruttore @Autowired (nella superclasse
     */
    public CompanyForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     */
    protected String fixCaption(AlgosEntity entity) {
        super.captionCreate = "Nuova company";
        super.captionEdit = "Modifica company";

        return super.fixCaption(entity);
    }// end of method

}// end of class
