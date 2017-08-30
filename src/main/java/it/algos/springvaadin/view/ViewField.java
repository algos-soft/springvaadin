package it.algos.springvaadin.view;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import it.algos.springvaadin.app.StaticContextAccessor;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.interfaccia.AIField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
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

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AIFieldFactory fieldFactory;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public ViewField(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
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
        String width = LibAnnotation.getWidthEM(clazz, publicFieldName);
        boolean enabled = LibAnnotation.isEnabled(clazz, publicFieldName);
        boolean required = LibAnnotation.isRequiredWild(clazz, publicFieldName);
        boolean focus = LibAnnotation.isFocus(clazz, publicFieldName);

        //--non riesco (per ora) a leggere le Annotation da una classe diversa (AlgosEntity)
        if (fieldAnnotation == null && publicFieldName.equals(Cost.PROPERTY_ID)) {
            type = AFType.id;
        }// end of if cycle

        if (type != null) {
            switch (type) {
                case id:
                    field = fieldFactory.crea(type, publicFieldName, presenter);
                    break;
                case text:
                    field = fieldFactory.crea(type, publicFieldName, presenter);
                    break;
                case integer:
                    field = fieldFactory.crea(type, publicFieldName, presenter);
//                    field = new AIntegerField();
                    break;
                case image:
//                    field = new AImageField();
//                    ((AImageField) field).setApplicationEventPublisher(applicationEventPublisher);
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
        }// end of if cycle


        if (field != null && fieldAnnotation != null) {
            field.setEnabled(enabled);
            field.setRequiredIndicatorVisible(required);
            field.setCaption(caption);
            field.setWidth(width);
            field.setFocus(focus);

            if (LibParams.displayToolTips()) {
                field.setDescription(fieldAnnotation.help());
            }// end of if cycle
        }// end of if cycle

//        if (field == null && publicFieldName.equals(Cost.PROPERTY_ID)) {
//            field = fieldFactory.crea(AFType.text, publicFieldName, presenter);
//            ((AbstractField) field).setCaption("Key ID");
//            ((AbstractField) field).setEnabled(false);
//            ((AbstractField) field).setWidth(LibAnnotation.getFormWithID(clazz));
//        }// end of if cycle

        return field;
    }// end of method

}// end of class

