package it.algos.springvaadin.view;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.entity.persona.PersonaPresenter;
import it.algos.springvaadin.entity.persona.PersonaService;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

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
    private AFieldFactory fieldFactory;


    @Autowired
    private ApplicationContext context;

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
     * @param source presenter di riferimento da cui vengono generati gli eventi
     * @param attr   the metamodel Attribute
     */
    @SuppressWarnings("all")
    public AField create(ApplicationListener source, final Class<? extends AEntity> clazz, final String publicFieldName, AEntity entityBean) {
        AField field = null;
        Object[] items = null;
        AFieldType type = LibAnnotation.getTypeField(clazz, publicFieldName);
        String caption = LibAnnotation.getNameField(clazz, publicFieldName);
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        String width = LibAnnotation.getWidthEM(clazz, publicFieldName);
        boolean enabled = LibAnnotation.isEnabled(clazz, publicFieldName);
        boolean required = LibAnnotation.isRequiredWild(clazz, publicFieldName);
        boolean focus = LibAnnotation.isFocus(clazz, publicFieldName);
        boolean visible = LibAnnotation.isVisibile(clazz, publicFieldName);
        boolean nullSelection = LibAnnotation.isNullSelectionAllowed(clazz, publicFieldName);
        Class targetClazz = LibAnnotation.getClass(clazz, publicFieldName);

        //--non riesco (per ora) a leggere le Annotation da una classe diversa (AEntity)
        if (fieldAnnotation == null && publicFieldName.equals(Cost.PROPERTY_ID)) {
            type = AFieldType.id;
        }// end of if cycle

        if (type != null) {
            field = fieldFactory.crea(clazz, type, source, publicFieldName, entityBean);
        }// end of if cycle

        if (type == AFieldType.combo && targetClazz != null && field != null) {
            items = LibMongo.findAll(targetClazz).toArray();
            ((AComboField) field).fixCombo(items, nullSelection);
        }// end of if cycle

        if (type == AFieldType.link && targetClazz != null && field != null) {
            String lowerName = LibText.primaMinuscola(targetClazz.getSimpleName());
            Object bean = context.getBean(lowerName);
            field.setTarget((ApplicationListener) bean);
        }// end of if cycle

        if (field != null && fieldAnnotation != null) {
            field.setVisible(visible);
            field.setEnabled(enabled);
            field.setRequiredIndicatorVisible(required);
            field.setCaption(caption);
            if (!width.equals("")) {
                field.setWidth(width);
            }// end of if cycle
            field.setFocus(focus);

            if (LibParams.displayToolTips()) {
                field.setDescription(fieldAnnotation.help());
            }// end of if cycle

            if (type == AFieldType.dateNotEnabled) {
                field.setEnabled(false);
            }// end of if cycle
        }// end of if cycle

        return field;
    }// end of method

}// end of class

