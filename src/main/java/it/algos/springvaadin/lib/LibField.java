package it.algos.springvaadin.lib;


import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.converter.AlgosConverter;
import it.algos.springvaadin.converter.FirstCapitalConverter;
import it.algos.springvaadin.converter.LowerConverter;
import it.algos.springvaadin.converter.UpperConverter;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.validator.AlgosLetterOnlyValidator;
import it.algos.springvaadin.validator.AlgosNumberOnlyValidator;
import it.algos.springvaadin.validator.AlgosStringLengthValidator;
import it.algos.springvaadin.validator.AlgosUniqueValidator;

import javax.annotation.PostConstruct;
import javax.persistence.metamodel.Attribute;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 18 ott 2016.
 * Creazione dei field dalla annotation
 * In Spring le librerie NON possono essere astratte, altrimenti si perde @PostConstruct e @Autowired
 */
@SpringComponent
public class LibField {



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


    /**
     * Crea una (eventuale) lista di validator, basato sulle @Annotation della Entity
     * Lista dei validators da utilizzare PRIMA dei converters
     */
    public static List<AbstractValidator> creaValidatorsPre(AEntity entityBean, final String publicFieldName) {
        List<AbstractValidator> lista = new ArrayList();
        List<Validator> listaTmp = creaValidators(entityBean, publicFieldName);

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
    public static List<AbstractValidator> creaValidatorsPost(AEntity entityBean, final String publicFieldName) {
        List<AbstractValidator> lista = new ArrayList();
        List<Validator> listaTmp = creaValidators(entityBean, publicFieldName);

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
    private static List<Validator> creaValidators(AEntity entityBean, final String publicFieldName) {
        List<Validator> lista = new ArrayList<>();
        Class<? extends AEntity> clazz = entityBean.getClass();
        AbstractValidator validator = null;
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        String fieldName = LibText.primaMaiuscola(publicFieldName);
        fieldName = LibText.setRossoBold(fieldName);
        String message = "";
        int min = 0;
        int max = 0;
        boolean notNull = LibAnnotation.isNotNull(clazz, publicFieldName);
        boolean notEmpty = LibAnnotation.isNotEmpty(clazz, publicFieldName);
        boolean checkSize = LibAnnotation.isSize(clazz, publicFieldName);
        boolean checkUnico = LibAnnotation.isUnico(clazz, publicFieldName);
        boolean checkOnlyNumber = LibAnnotation.isOnlyNumber(clazz, publicFieldName);
        boolean checkOnlyLetter = LibAnnotation.isOnlyLetter(clazz, publicFieldName);
        Object oldValue;

        if (fieldAnnotation != null) {
            min = LibAnnotation.getMin(clazz, publicFieldName);
            max = LibAnnotation.getMax(clazz, publicFieldName);

            switch (fieldAnnotation.type()) {
                case text:
                    if (checkUnico) {
                        oldValue = LibReflection.getValue(entityBean, publicFieldName);
                        validator = new AlgosUniqueValidator(clazz, publicFieldName, oldValue);
                        lista.add(new Validator(validator, Posizione.prima));
                    }// end of if cycle
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(clazz, publicFieldName);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new Validator(validator, Posizione.prima));
                    }// end of if cycle
                    if (checkSize) {
                        String messageSize = LibAnnotation.getSizeMessage(clazz, publicFieldName, notEmpty);
                        validator = new AlgosStringLengthValidator(messageSize, min, max);
                        lista.add(new Validator(validator, Posizione.dopo));
                    }// end of if cycle
                    if (checkOnlyNumber) {
                        validator = new AlgosNumberOnlyValidator(publicFieldName);
                        lista.add(new Validator(validator, Posizione.dopo));
                    }// end of if cycle
                    if (checkOnlyLetter) {
                        validator = new AlgosLetterOnlyValidator(publicFieldName);
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
    public static List<AlgosConverter> creaConverters(AEntity entityBean, final String publicFieldName) {
        List<AlgosConverter> lista = new ArrayList<>();
        Class<? extends AEntity> clazz = entityBean.getClass();
        AlgosConverter converter = null;
        AIField fieldAnnotation = LibAnnotation.getField(clazz, publicFieldName);
        boolean checkFirstCapital = LibAnnotation.isFirstCapital(clazz, publicFieldName);
        boolean checkUpper = LibAnnotation.isAllUpper(clazz, publicFieldName);
        boolean checkLower = LibAnnotation.isAllLower(clazz, publicFieldName);

        if (fieldAnnotation != null) {
            switch (fieldAnnotation.type()) {
                case text:
                    if (checkFirstCapital) {
                        converter = new FirstCapitalConverter();
                        lista.add(converter);
                    }// end of if cycle
                    if (checkUpper) {
                        converter = new UpperConverter();
                        lista.add(converter);
                    }// end of if cycle
                    if (checkLower) {
                        converter = new LowerConverter();
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


//    /**
//     *
//     */
//    private static void publish(AlgosPresenterImpl presenter) {
//        AEvent fieldSpringEvent = null;
//
//        if (presenter != null) {
//            fieldSpringEvent = new AFieldEvent(presenter);
//            presenter.getApplicationEventPublisher().publishEvent(fieldSpringEvent);
//        }// end of if cycle
//
//    }// end of method

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
