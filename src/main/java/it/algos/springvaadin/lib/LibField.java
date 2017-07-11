package it.algos.springvaadin.lib;


import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.app.StaticContextAccessor;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.service.AlgosServiceOld;

import javax.persistence.metamodel.Attribute;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by gac on 18 ott 2016.
 * Creazione dei field dalla annotation
 */
public class LibField {

    /**
     * Create a single field.
     * The field type is chosen according to the annotation.
     *
     * @param attr the metamodel Attribute
     */
    @SuppressWarnings("all")
    public static AbstractField create(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AbstractField vaadinField = null;
        Field javaField;
        Annotation annotation = null;
        AIField fieldAnnotation = null;
        Object[] items = null;
        AlgosServiceOld service = null;
        javaField = LibReflection.getField(clazz, publicFieldName);

        if (javaField != null) {
            annotation = javaField.getAnnotation(AIField.class);
        }// end of if cycle

        if (annotation != null && annotation instanceof AIField) {
            fieldAnnotation = (AIField) annotation;
        }// end of if cycle

        if (fieldAnnotation != null) {
            switch (fieldAnnotation.type()) {
                case text:
                    vaadinField = new AlgosTextField();
                    if (LibParams.displayToolTips()) {
                        ((AlgosTextField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case integer:
                    vaadinField = new AlgosIntegerField();
                    if (LibParams.displayToolTips()) {
                        ((AlgosIntegerField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case enumeration:
                    if (fieldAnnotation.clazz() != null) {
                        Class<? extends Object> classEnumeration = fieldAnnotation.clazz();
                        if (classEnumeration.isEnum()) {
                            items = classEnumeration.getEnumConstants();
                            vaadinField = new AlgosComboArrayField(items, fieldAnnotation.nullSelectionAllowed());
                        }// end of if cycle
                    }// end of if cycle
                    if (vaadinField != null && LibParams.displayToolTips()) {
                        ((AlgosComboArrayField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case combo:
                    if (fieldAnnotation.clazz() != null) {
                        Class<? extends Object> classRelated = fieldAnnotation.clazz();
                        if (AlgosServiceOld.class.isAssignableFrom(classRelated)) {
                            try { // prova ad eseguire il codice
                                items = ((AlgosServiceOld) StaticContextAccessor.getBean(classRelated)).findAll().toArray();
                                vaadinField = new AlgosComboClassField(items, fieldAnnotation.nullSelectionAllowed());
                            } catch (Exception unErrore) { // intercetta l'errore
                            }// fine del blocco try-catch
                        }// end of if cycle
                    }// end of if cycle
                    if (vaadinField != null && LibParams.displayToolTips()) {
                        ((AlgosComboClassField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case date:
                    vaadinField = new AlgosDateField();
                    if (LibParams.displayToolTips()) {
                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case localdate:
                    vaadinField = new AlgosDateField();
                    if (LibParams.displayToolTips()) {
                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                case localdatetime:
                    vaadinField = new AlgosDateTimeField("Localtime");//@todo viene sovrascritto dall'Annotation
                    if (LibParams.displayToolTips()) {
                        ((AlgosDateTimeField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch

            if (vaadinField != null && fieldAnnotation != null) {
                vaadinField.setEnabled(fieldAnnotation.enabled());
                vaadinField.setCaption(fieldAnnotation.caption());
                vaadinField.setWidth(fieldAnnotation.width());
                vaadinField.setRequiredIndicatorVisible(fieldAnnotation.enabled());

                if (LibParams.displayToolTips()) {
                    vaadinField.setDescription(fieldAnnotation.help());
                }// end of if cycle
            }// end of if cycle

        }// end of if cycle

        return vaadinField;
    }// end of static method

    /**
     * Create a single field.
     * The field type is chosen according to the annotation.
     *
     * @param attr the metamodel Attribute
     */
    @SuppressWarnings("all")
    public static AbstractField createField(Attribute attr) {
        AbstractField vaadinField = null;
        java.lang.reflect.Field javaField = null;
        Annotation annotation = null;
        String fieldType = "";
        AIField fieldAnnotation = null;

        try { // prova ad eseguire il codice
            javaField = attr.getJavaMember().getDeclaringClass().getDeclaredField(attr.getName());
        } catch (Exception unErrore) { // intercetta l'errore
//            return createFieldJavaType(attr);
        }// fine del blocco try-catch

        if (javaField != null) {
            annotation = javaField.getAnnotation(AIField.class);
        } else {
//            return createFieldJavaType(attr);
        }// end of if/else cycle

        if (annotation != null && annotation instanceof AIField) {
            fieldAnnotation = (AIField) annotation;
        } else {
//            return createFieldJavaType(attr);
        }// end of if/else cycle

        if (fieldAnnotation != null) {
            switch (fieldAnnotation.type()) {
                case text:
                    vaadinField = new TextField();
                    ((TextField) vaadinField).setEnabled(fieldAnnotation.enabled());
                    if (LibParams.displayToolTips()) {
                        ((TextField) vaadinField).setDescription(fieldAnnotation.help());
                    }// end of if cycle
                    break;
//                case integer:
//                    vaadinField = new AlgosIntegerField();
//                    ((AlgosIntegerField) vaadinField).setEnabled(fieldAnnotation.enabled());
//                    if (LibParams.displayToolTips()) {
//                        ((AlgosIntegerField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case email:
//                    vaadinField = new EmailField();
//                    ((EmailField) vaadinField).setInputPrompt(fieldAnnotation.prompt());
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((EmailField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case checkbox:
//                    vaadinField = new CheckBoxField();
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((CheckBoxField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case area:
//                    vaadinField = new TextArea();
//                    ((TextArea) vaadinField).setInputPrompt(fieldAnnotation.prompt());
//                    ((TextArea) vaadinField).setColumns(fieldAnnotation.columns());
//                    ((TextArea) vaadinField).setRows(fieldAnnotation.rows());
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((TextArea) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case date:
//                    vaadinField = new AlgosDateField();
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case time:
//                    vaadinField = new AlgosDateField();
//                    vaadinField.setWidth("220px");
//                    ((AlgosDateField) vaadinField).setResolution(AlgosDateField.RESOLUTION_SEC);
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case password:
//                    vaadinField = new PasswordField();
//                    ((PasswordField) vaadinField).setEnabled(fieldAnnotation.enabled());
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((PasswordField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case combo:
//                    vaadinField = new RelatedComboField(fieldAnnotation.clazz());
//                    ((RelatedComboField) vaadinField).setEnabled(fieldAnnotation.enabled());
//                    ((RelatedComboField) vaadinField).setNullSelectionAllowed(fieldAnnotation.nullSelectionAllowed());
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((RelatedComboField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
//                case enumeration:
//                    Class clazz = fieldAnnotation.clazz();
//                    Object[] values = clazz.getEnumConstants();
//                    vaadinField = new ArrayComboField(values);
//                    ((ArrayComboField) vaadinField).setEnabled(fieldAnnotation.enabled());
//                    ((ArrayComboField) vaadinField).setNullSelectionAllowed(fieldAnnotation.nullSelectionAllowed());
//                    if (AlgosApp.DISPLAY_TOOLTIPS) {
//                        ((ArrayComboField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
                default: // caso non definito
//                    vaadinField = createFieldJavaType(attr);
            } // fine del blocco switch

//            vaadinField.setRequired(fieldAnnotation.required());
            vaadinField.setCaption(fieldAnnotation.caption());
            vaadinField.setWidth(fieldAnnotation.width());
//            vaadinField.setRequiredError(fieldAnnotation.error());

            return vaadinField;
        } else {
//            return createFieldJavaType(attr);
            return null;
        }// end of if/else cycle
    }// end of static method

//    /**
//     * Create a single field.
//     * The field type is chosen according to the Java type.
//     *
//     * @param attr the metamodel Attribute
//     */
//    public static Field createFieldJavaType(Attribute attr) {
//        Field field = null;
//
//        if (attr != null) {
//
//
//            try {
//
//                if (attr.isAssociation()) { // questo può fallire!!
//
//                    // relazione OneToMany - usiamo un campo lista (?)
//                    if (attr instanceof PluralAttribute) {
//                        PluralAttribute pa = (PluralAttribute) attr;
//                        Class clazz = pa.getElementType().getJavaType();
//                        //field = new RelatedComboField(clazz);
//                        //field=null;// todo qui ci vuole una lista
//                    }
//
//                    // relazione ManyToOne - usiamo un campo combo
//                    if (attr instanceof SingularAttribute) {
//                        SingularAttribute sa = (SingularAttribute) attr;
//                        Class clazz = sa.getJavaType();
//                        field = new RelatedComboField(clazz);
//                    }
//
//                } else {
//
//                    Class clazz = attr.getJavaType();
//
//                    if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
//                        field = new CheckBoxField();
//                    }
//
//                    if (clazz.equals(String.class)) {
//                        field = new TextField();
//                    }
//
//                    if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
//                        field = new AlgosIntegerField();
//                    }
//
//                    if (clazz.equals(Long.class) || clazz.equals(long.class)) {
//                        field = new LongField();
//                    }
//
//                    if (clazz.equals(Date.class)) {
//                        field = new AlgosDateField();
//                    }
//
//                    if (clazz.equals(BigDecimal.class)) {
//                        field = new DecimalField();
//                    }
//
//                    if (clazz.equals(Timestamp.class)) {
//                        field = new AlgosDateField();
//                    }
//
//                    if (clazz.isEnum()) {
//                        Object[] values = clazz.getEnumConstants();
//                        ArrayComboField acf = new ArrayComboField(values);
//                        acf.setNullSelectionAllowed(true);
//                        field = acf;
//                    }
//
//                }
//
//                // create and assign the caption
//                if (field != null) {
//                    String caption = DefaultFieldFactory.createCaptionByPropertyId(attr.getName());
//                    field.setCaption(caption);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//        return field;
//
//    }// end of static method


    /**
     * Crea un (eventuale) validator, basato sulle @Annotation della Entity
     */
    public static AbstractValidator creaValidator(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AbstractValidator validator = null;

        return validator;
    }// end of method


    /**
     * Restituisce una singola Annotation.
     *
     * @param attr the metamodel Attribute
     *
     * @return valore dell'Annotation - Valore di default, se non la trova
     */
    public static AIField getAnnotation(Attribute attr) {
        AIField fieldAnnotation = null;
        java.lang.reflect.Field javaField = null;
        Annotation annotation = null;

        try { // prova ad eseguire il codice
            javaField = attr.getJavaMember().getDeclaringClass().getDeclaredField(attr.getName());
            annotation = javaField.getAnnotation(AIField.class);
            fieldAnnotation = (AIField) annotation;
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return fieldAnnotation;
    }// end of static method

}// end of abstract static class
