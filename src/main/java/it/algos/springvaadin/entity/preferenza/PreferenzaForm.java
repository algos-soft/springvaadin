package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.AImageField;
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
        AField field = getField("type");
        Object objValue = field.getValue();

        if (entityBean != null && LibText.isEmpty(entityBean.id)) {
            field.setValue(PrefType.string);
        }// end of if cycle

    }// end of method

}// end of class
