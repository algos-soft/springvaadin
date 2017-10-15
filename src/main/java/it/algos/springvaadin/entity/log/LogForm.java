package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AImageField;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 30-set-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_LOG)
public class LogForm extends AlgosFormImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public LogForm(@Qualifier(Cost.BAR_FORM) AToolbar toolbar,
                   @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        super(toolbar, toolbarLink);
    }// end of Spring constructor


}// end of class

