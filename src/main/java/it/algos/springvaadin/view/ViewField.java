package it.algos.springvaadin.view;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import it.algos.springvaadin.app.StaticContextAccessor;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.interfaccia.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.SerializationUtils;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 28-ago-2017
 * Time: 14:25
 */
@SpringComponent
public class ViewField {

    private AField fieldText;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public ViewField(
            @Qualifier(Cost.FIELD_TEXT) AField fieldText) {
        this.fieldText = fieldText;
    }// end of @Autowired constructor


    /**
     * Create a single field.
     * The field type is chosen according to the annotation @AIField.
     *
     * @param presenter di riferimento per gli eventi
     * @param attr      the metamodel Attribute
     */
    @SuppressWarnings("all")
    public AField create(AlgosPresenterImpl presenter, final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AField field = null;
        AFType type = LibAnnotation.getTypeField(clazz, publicFieldName);
        String caption = LibAnnotation.getNameField(clazz, publicFieldName);
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        Object[] items = null;
        String width = LibAnnotation.getWidthEM(clazz, publicFieldName);
        boolean enabled = LibAnnotation.isEnabled(clazz, publicFieldName);
        boolean required = LibAnnotation.isRequiredWild(clazz, publicFieldName);
        boolean focus = LibAnnotation.isFocus(clazz, publicFieldName);

        if (type != null) {
            switch (type) {
                case text:
                    field = fieldText;

//                    ((AField)field).initContent();
                    if (focus) {
                        ((ATextField) field).focus();
                    }// end of if cycle
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
        }// end of if cycle

        if (field != null) {
            field.setName(publicFieldName);
        }// end of if cycle

        return field;
    }// end of method

}// end of class

