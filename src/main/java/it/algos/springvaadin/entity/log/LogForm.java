package it.algos.springvaadin.entity.log;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Layout;
import it.algos.springvaadin.entity.versione.Versione;
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
 * Created by gac on 30/06/17.
 * Presenta i dati di una entity o bean, sotto forma di un Form
 */
@SpringComponent
@Qualifier(Cost.TAG_LOG)
public class LogForm extends AlgosFormImpl {



    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public LogForm(FormToolbar toolbar) {
        super(toolbar);
    }// end of Spring constructor


    /**
     */
    protected String fixCaption(AlgosEntity entity) {
        super.captionCreate = "Nuovo log";
        super.captionEdit = "Modifica log";

        return super.fixCaption(entity);
    }// end of method



}// end of class
