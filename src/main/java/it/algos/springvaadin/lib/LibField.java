package it.algos.springvaadin.lib;


import com.vaadin.data.Converter;
import com.vaadin.data.HasValue;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.app.StaticContextAccessor;
import it.algos.springvaadin.converter.FirstCapitalConverter;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;

import javax.persistence.metamodel.Attribute;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 18 ott 2016.
 * Creazione dei field dalla annotation
 */
public class LibField {

    /**
     * Create a single field.
     * The field type is chosen according to the annotation @AIField.
     *
     * @param attr the metamodel Attribute
     */
    @SuppressWarnings("all")
    public static AlgosField create(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        AlgosField field = null;
        AFType type = LibAnnotation.getTypeField(clazz, publicFieldName);
        String caption = LibAnnotation.getNameField(clazz, publicFieldName);
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        Object[] items = null;
        int widthEM = LibAnnotation.getWidthEM(clazz, publicFieldName);
        String width = widthEM + "em";
        boolean enabled = LibAnnotation.isEnabled(clazz, publicFieldName);
        boolean required = LibAnnotation.isRequired(clazz, publicFieldName);

        if (type != null) {
            switch (type) {
                case text:
                    field = new AlgosTextField();
                    break;
                case integer:
                    field = new AlgosIntegerField();
                    break;
                case email:
                    field = new AlgosTextField();
                    break;
                case enumeration:
                    if (fieldAnnotation.clazz() != null) {
                        Class<? extends Object> classEnumeration = fieldAnnotation.clazz();
                        if (classEnumeration.isEnum()) {
                            items = classEnumeration.getEnumConstants();
                            field = new AlgosComboArrayField(items, fieldAnnotation.nullSelectionAllowed());
                        }// end of if cycle
                    }// end of if cycle
                    break;
                case combo:
                    if (fieldAnnotation.clazz() != null) {
                        Class<? extends Object> classRelated = fieldAnnotation.clazz();
                        if (AlgosService.class.isAssignableFrom(classRelated)) {
                            try { // prova ad eseguire il codice
                                items = ((AlgosService) StaticContextAccessor.getBean(classRelated)).findAll().toArray();
                                field = new AlgosComboClassField(items, fieldAnnotation.nullSelectionAllowed());
                            } catch (Exception unErrore) { // intercetta l'errore
                            }// fine del blocco try-catch
                        }// end of if cycle
                    }// end of if cycle
                    break;
                case date:
                    field = new AlgosDateField();
                    break;
                case localdate:
                    field = new AlgosDateField();
                    break;
                case localdatetime:
                    field = new AlgosDateTimeField("Localtime");//@todo viene sovrascritto dall'Annotation
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch

            if (field != null) {
                field.setName(publicFieldName);
            }// end of if cycle

            if (field != null && fieldAnnotation != null) {
                ((AbstractField)field).setEnabled(enabled);
                ((AbstractField)field).setRequiredIndicatorVisible(required);
                ((AbstractField)field).setCaption(caption);
                ((AbstractField)field).setWidth(width);

                if (LibParams.displayToolTips()) {
                    field.setDescription(fieldAnnotation.help());
                }// end of if cycle
            }// end of if cycle
        }// end of if cycle

        if (field != null) {
            ((AbstractField)field).addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                    publish();
                }// end of inner method
            });// end of anonymous inner class
        }// end of if cycle

        return field;
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
            vaadinField.setCaption(fieldAnnotation.name());
//            vaadinField.setWidth(fieldAnnotation.width());
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
     * Crea una (eventuale) lista di validator, basato sulle @Annotation della Entity
     * Lista dei validators da utilizzare PRIMA dei converters
     */
    public static List<AbstractValidator> creaValidatorsPre(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        List<AbstractValidator> lista = new ArrayList();
        List<Validator> listaTmp = creaValidators(clazz, publicFieldName);

        for (Validator validator : listaTmp) {
            if (validator.posizione == Posizione.prima) {
                lista.add(validator.validator);
            }// end of if cycle
        }// end of for cycle

        return lista;
    }// end of method


    /**
     * Crea una (eventuale) lista di validator, basato sulle @Annotation della Entity
     * Lista dei validators da utilizzare DOPO i converters
     */
    public static List<AbstractValidator> creaValidatorsPost(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        List<AbstractValidator> lista = new ArrayList();
        List<Validator> listaTmp = creaValidators(clazz, publicFieldName);

        for (Validator validator : listaTmp) {
            if (validator.posizione == Posizione.dopo) {
                lista.add(validator.validator);
            }// end of if cycle
        }// end of for cycle

        return lista;
    }// end of method


    /**
     * Crea una (eventuale) lista di validator, basato sulle @Annotation della Entity
     * Lista base, indifferenziata
     */
    private static List<Validator> creaValidators(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        List<Validator> lista = new ArrayList();
        AbstractValidator validator = null;
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        String fieldName = LibText.primaMaiuscola(publicFieldName);
        String message = "";
        int min = 0;
        int max = 0;
        boolean notNull = LibAnnotation.isNotNull(clazz, publicFieldName);
        boolean notEmpty = LibAnnotation.isNotEmpty(clazz, publicFieldName);
        boolean checkSize = LibAnnotation.isSize(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            min = LibAnnotation.getMin(clazz, publicFieldName);
            max = LibAnnotation.getMax(clazz, publicFieldName);

            switch (fieldAnnotation.type()) {
                case text:
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(clazz, publicFieldName);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new Validator(validator, Posizione.prima));
                    }// end of if cycle
                    if (checkSize) {
                        String messageSize = LibAnnotation.getSizeMessage(clazz, publicFieldName);
                        if (messageSize.equals("")) {
                            messageSize = fieldName + " deve essere compreso tra " + min + " e " + max + " caratteri";
                        }// end of if cycle
                        validator = new StringLengthValidator(messageSize, min, max);
                        lista.add(new Validator(validator, Posizione.dopo));
                    }// end of if cycle
                    break;
                case integer:
                    if (notNull) {
                        message = fieldName + " non può essere nullo";
                        validator = new IntegerRangeValidator(message, 1, 99999999);
                        lista.add(new Validator(validator, Posizione.prima));
                    }// end of if cycle
                    break;
                case email:
                    message = "Indirizzo eMail non valido";
                    validator = new EmailValidator(message);
                    break;
                case checkbox:
                    break;
                case date:
                    break;
                case time:
                    break;
                case password:
                    break;
                case combo:
                    break;
                case enumeration:
                    break;
                default: // caso non definito
            } // fine del blocco switch
        }// end of if cycle

        return lista;
    }// end of method


    /**
     * Crea una (eventuale) lista di converter, basato sulle @Annotation della Entity
     */
    public static List<Converter> creaConverters(final Class<? extends AlgosEntity> clazz, final String publicFieldName) {
        List<Converter> lista = new ArrayList();
        Converter converter = null;
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        boolean checkFirstCapital = LibAnnotation.isFirstCapital(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            switch (fieldAnnotation.type()) {
                case text:
                    if (checkFirstCapital) {
                        converter = new FirstCapitalConverter();
                        lista.add(converter);
                    }// end of if cycle
                    break;
                case integer:
                    break;
                case email:
                    break;
                case checkbox:
                    break;
                case date:
                    break;
                case time:
                    break;
                case password:
                    break;
                case combo:
                    break;
                case enumeration:
                    break;
                default: // caso non definito
            } // fine del blocco switch
        }// end of if cycle

        return lista;
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


    /**
     *
     */
    private static void publish() {
        AlgosPresenterImpl presenter = LibVaadin.getCurrentPresenter();
        AlgosSpringEvent fieldSpringEvent = null;

        if (presenter != null) {
            fieldSpringEvent = new FieldSpringEvent(presenter);
            presenter.getApplicationEventPublisher().publishEvent(fieldSpringEvent);
        }// end of if cycle

    }// end of method

    private static class Validator {
        AbstractValidator validator;
        Posizione posizione;

        public Validator(AbstractValidator validator, Posizione posizione) {
            this.validator = validator;
            this.posizione = posizione;
        }// end of constructor
    }// end of internal class

    private enum Posizione {
        prima, dopo
    }// end of internal enumerationù

}// end of abstract static class
