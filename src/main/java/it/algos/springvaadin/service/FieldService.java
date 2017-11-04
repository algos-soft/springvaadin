package it.algos.springvaadin.service;


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
import it.algos.springvaadin.entity.preferenza.PrefEffect;
import it.algos.springvaadin.entity.preferenza.Preferenza;
import it.algos.springvaadin.field.*;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;
import javax.persistence.metamodel.Attribute;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 18 ott 2016.
 * Creazione dei field dalla annotation
 * In Spring le librerie NON possono essere astratte, altrimenti si perde @PostConstruct e @Autowired
 */
@SpringComponent
public class FieldService {

    @Autowired
    private AFieldFactory fieldFactory;

    @Autowired
    private ApplicationContext context;


    /**
     * Create a single field.
     * The field type is chosen according to the annotation @AIField.
     *
     * @param source          presenter di riferimento da cui vengono generati gli eventi
     * @param reflectionField di riferimento per estrarre le Annotation
     */
    @SuppressWarnings("all")
    public AField create(ApplicationListener source, Field reflectionField, AEntity entityBean) {
        AField algosField = null;
        Object[] items = null;
        boolean nuovaEntity = LibText.isEmpty(entityBean.id);
        AFieldType type = LibAnnotation.getColumnType(reflectionField);
        String caption = LibAnnotation.getColumnName(reflectionField);
        AIField fieldAnnotation = LibAnnotation.getFormAnnotation(reflectionField);
        String width = LibAnnotation.getWidthEM(reflectionField);
        int rows = LibAnnotation.getNumRows(reflectionField);
        boolean required = LibAnnotation.isRequired(reflectionField);
        boolean focus = LibAnnotation.isFocus(reflectionField);
        boolean visible = LibAnnotation.isFieldVisibile(reflectionField, nuovaEntity);
        boolean enabled = LibAnnotation.isFieldEnabled(reflectionField, nuovaEntity);
        boolean nullSelection = LibAnnotation.isNullSelectionAllowed(reflectionField);
        Class targetClazz = LibAnnotation.getClass(reflectionField);

        //--non riesco (per ora) a leggere le Annotation da una classe diversa (AEntity)
        if (fieldAnnotation == null && reflectionField.getName().equals(Cost.PROPERTY_ID)) {
            type = AFieldType.id;
        }// end of if cycle

        if (type != null) {
            algosField = fieldFactory.crea(source, type, reflectionField, entityBean);
        }// end of if cycle

        if (type == AFieldType.combo && targetClazz != null && algosField != null) {
            items = LibMongo.findAll(targetClazz).toArray();
            ((AComboField) algosField).fixCombo(items, nullSelection);
        }// end of if cycle

        if (type == AFieldType.enumeration && targetClazz != null && algosField != null) {
            if (targetClazz.isEnum()) {
                items = targetClazz.getEnumConstants();
            }// end of if cycle

            if (algosField != null && algosField instanceof AComboField && items != null) {
                ((AComboField) algosField).fixCombo(items, false);
            }// end of if cycle

        }// end of if cycle

        if (type == AFieldType.radio && targetClazz != null && algosField != null) {
            //@todo PATCH - PATCH - PATCH
            if (reflectionField.getName().equals("attivazione") && entityBean.getClass().getName().equals(Preferenza.class.getName())) {
                items = PrefEffect.values();
            }// end of if cycle
            //@todo PATCH - PATCH - PATCH

            if (items != null) {
                ((ARadioField) algosField).fixRadio(items);
            }// end of if cycle
        }// end of if cycle

        if (type == AFieldType.link && targetClazz != null && algosField != null) {
            String lowerName = LibText.primaMinuscola(targetClazz.getSimpleName());
            Object bean = context.getBean(lowerName);
            algosField.setTarget((ApplicationListener) bean);
        }// end of if cycle

        if (algosField != null && fieldAnnotation != null) {
            algosField.setVisible(visible);
            algosField.setEnabled(enabled);
            algosField.setRequiredIndicatorVisible(required);
            algosField.setCaption(caption);
            if (LibText.isValid(width)) {
                algosField.setWidth(width);
            }// end of if cycle
            if (rows > 0) {
                algosField.setRows(rows);
            }// end of if cycle
            algosField.setFocus(focus);

            if (LibParams.displayToolTips()) {
                algosField.setDescription(fieldAnnotation.help());
            }// end of if cycle

            if (type == AFieldType.dateNotEnabled) {
                algosField.setEnabled(false);
            }// end of if cycle
        }// end of if cycle

        return algosField;
    }// end of method

//    /**
//     * Create a single field.
//     * The field type is chosen according to the annotation.
//     *
//     * @param attr the metamodel Attribute
//     */
//    @SuppressWarnings("all")
//    public static AbstractField createField(Attribute attr) {
//        AbstractField vaadinField = null;
//        java.lang.reflect.Field javaField = null;
//        Annotation annotation = null;
//        String fieldType = "";
//        AIField fieldAnnotation = null;
//
//        try { // prova ad eseguire il codice
//            javaField = attr.getJavaMember().getDeclaringClass().getDeclaredField(attr.getName());
//        } catch (Exception unErrore) { // intercetta l'errore
////            return createFieldJavaType(attr);
//        }// fine del blocco try-catch
//
//        if (javaField != null) {
//            annotation = javaField.getAnnotation(AIField.class);
//        } else {
////            return createFieldJavaType(attr);
//        }// end of if/else cycle
//
//        if (annotation != null && annotation instanceof AIField) {
//            fieldAnnotation = (AIField) annotation;
//        } else {
////            return createFieldJavaType(attr);
//        }// end of if/else cycle
//
//        if (fieldAnnotation != null) {
//            switch (fieldAnnotation.type()) {
//                case text:
//                    vaadinField = new TextField();
////                    ((TextField) vaadinField).setEnabled(fieldAnnotation.());
//                    if (LibParams.displayToolTips()) {
//                        ((TextField) vaadinField).setDescription(fieldAnnotation.help());
//                    }// end of if cycle
//                    break;
////                case integer:
////                    vaadinField = new AlgosIntegerField();
////                    ((AlgosIntegerField) vaadinField).setEnabled(fieldAnnotation.enabled());
////                    if (LibParams.displayToolTips()) {
////                        ((AlgosIntegerField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case email:
////                    vaadinField = new EmailField();
////                    ((EmailField) vaadinField).setInputPrompt(fieldAnnotation.prompt());
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((EmailField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case checkbox:
////                    vaadinField = new CheckBoxField();
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((CheckBoxField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case area:
////                    vaadinField = new TextArea();
////                    ((TextArea) vaadinField).setInputPrompt(fieldAnnotation.prompt());
////                    ((TextArea) vaadinField).addColumns(fieldAnnotation.columns());
////                    ((TextArea) vaadinField).setRows(fieldAnnotation.rows());
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((TextArea) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case date:
////                    vaadinField = new AlgosDateField();
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case time:
////                    vaadinField = new AlgosDateField();
////                    vaadinField.setWidth("220px");
////                    ((AlgosDateField) vaadinField).setResolution(AlgosDateField.RESOLUTION_SEC);
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((AlgosDateField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case password:
////                    vaadinField = new PasswordField();
////                    ((PasswordField) vaadinField).setEnabled(fieldAnnotation.enabled());
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((PasswordField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case combo:
////                    vaadinField = new RelatedComboField(fieldAnnotation.clazz());
////                    ((RelatedComboField) vaadinField).setEnabled(fieldAnnotation.enabled());
////                    ((RelatedComboField) vaadinField).setNullSelectionAllowed(fieldAnnotation.nullSelectionAllowed());
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((RelatedComboField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
////                case enumeration:
////                    Class clazz = fieldAnnotation.clazz();
////                    Object[] values = clazz.getEnumConstants();
////                    vaadinField = new ArrayComboField(values);
////                    ((ArrayComboField) vaadinField).setEnabled(fieldAnnotation.enabled());
////                    ((ArrayComboField) vaadinField).setNullSelectionAllowed(fieldAnnotation.nullSelectionAllowed());
////                    if (AlgosApp.DISPLAY_TOOLTIPS) {
////                        ((ArrayComboField) vaadinField).setDescription(fieldAnnotation.help());
////                    }// end of if cycle
////                    break;
//                default: // caso non definito
////                    vaadinField = createFieldJavaType(attr);
//            } // fine del blocco switch
//
////            vaadinField.setRequired(fieldAnnotation.required());
//            vaadinField.setCaption(fieldAnnotation.name());
////            vaadinField.setWidth(fieldAnnotation.width());
////            vaadinField.setRequiredError(fieldAnnotation.error());
//
//            return vaadinField;
//        } else {
////            return createFieldJavaType(attr);
//            return null;
//        }// end of if/else cycle
//    }// end of static method


    /**
     * Crea una (eventuale) lista di validator, basato sulle @Annotation della Entity
     * Lista dei validators da utilizzare PRIMA dei converters
     */
    public List<AbstractValidator> creaValidatorsPre(AEntity entityBean, Field reflectionField) {
        List<AbstractValidator> lista = new ArrayList();
        List<AValidator> listaTmp = creaValidators(entityBean, reflectionField);

        for (AValidator validator : listaTmp) {
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
    public List<AbstractValidator> creaValidatorsPost(AEntity entityBean, Field reflectionField) {
        List<AbstractValidator> lista = new ArrayList();
        List<AValidator> listaTmp = creaValidators(entityBean, reflectionField);

        for (AValidator validator : listaTmp) {
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
    private List<AValidator> creaValidators(AEntity entityBean, Field reflectionField) {
        List<AValidator> lista = new ArrayList<>();
        Class<? extends AEntity> clazz = entityBean.getClass();
        AbstractValidator validator = null;
        AIField fieldAnnotation = LibAnnotation.getFormAnnotation(reflectionField);
        AFieldType type = null;
        String fieldName = LibText.primaMaiuscola(reflectionField.getName());
        fieldName = LibText.setRossoBold(fieldName);
        String message = "";
        int min = 0;
        int max = 0;
        boolean notNull = LibAnnotation.isNotNull(reflectionField);
        boolean notEmpty = LibAnnotation.isNotEmpty(reflectionField);
        boolean checkSize = LibAnnotation.isSize(reflectionField);
        boolean checkUnico = LibAnnotation.isUnico(reflectionField);
        boolean checkOnlyNumber = LibAnnotation.isOnlyNumber(reflectionField);
        boolean checkOnlyLetter = LibAnnotation.isOnlyLetter(reflectionField);
        Object oldValue;

        if (fieldAnnotation != null) {
            type = fieldAnnotation.type();
            Object a = type;
            min = LibAnnotation.getMin(reflectionField);
            max = LibAnnotation.getMax(reflectionField);

            switch (type) {
                case text:
                    if (checkUnico) {
                        oldValue = LibReflection.getValue(entityBean, reflectionField.getName());
                        validator = new AlgosUniqueValidator(clazz, reflectionField.getName(), oldValue);
                        lista.add(new AValidator(validator, Posizione.prima));
                    }// end of if cycle
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(reflectionField);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new AValidator(validator, Posizione.prima));
                    }// end of if cycle
                    if (checkSize) {
                        String messageSize = LibAnnotation.getSizeMessage(reflectionField, notEmpty);
                        validator = new AlgosStringLengthValidator(messageSize, min, max);
                        lista.add(new AValidator(validator, Posizione.dopo));
                    }// end of if cycle
                    if (checkOnlyNumber) {
                        validator = new AlgosNumberOnlyValidator(reflectionField.getName());
                        lista.add(new AValidator(validator, Posizione.dopo));
                    }// end of if cycle
                    if (checkOnlyLetter) {
                        validator = new AlgosLetterOnlyValidator(reflectionField.getName());
                        lista.add(new AValidator(validator, Posizione.dopo));
                    }// end of if cycle
                    break;
                case integer:
                    addAnte(lista, new AlgosNumberNotNullValidator(reflectionField.getName()));
                    break;
                case integernotzero:
                    addAnte(lista, new AlgosNumberNotNullValidator(reflectionField.getName()));
                    addAnte(lista, new AlgosNumberNotZeroValidator(reflectionField.getName()));
                    break;
                case email:
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(reflectionField);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new AValidator(validator, Posizione.prima));
                    }// end of if cycle
                    addAnte(lista, new AlgosEmailValidator(reflectionField.getName()));
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
                case textarea:
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(reflectionField);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new AValidator(validator, Posizione.prima));
                    }// end of if cycle
                    break;
                case enumeration:
                    if (notEmpty) {
                        String messageEmpty = LibAnnotation.getNotEmptyMessage(reflectionField);
                        validator = new StringLengthValidator(messageEmpty, 1, 10000);
                        lista.add(new AValidator(validator, Posizione.prima));
                    }// end of if cycle

                    break;
                default: // caso non definito
            } // fine del blocco switch
        }// end of if cycle

        return lista;
    }// end of method

    private static void addAnte(List<AValidator> lista, AbstractValidator validator) {
        lista.add(new AValidator(validator, Posizione.prima));
    }// end of method

    private static void addPost(List<AValidator> lista, AbstractValidator validator) {
        lista.add(new AValidator(validator, Posizione.dopo));
    }// end of method

    /**
     * Crea una (eventuale) lista di converter, basato sulle @Annotation della Entity
     */
    public List<AlgosConverter> creaConverters(AEntity entityBean, Field reflectionField) {
        List<AlgosConverter> lista = new ArrayList<>();
        Class<? extends AEntity> clazz = entityBean.getClass();
        AlgosConverter converter = null;
        AIField fieldAnnotation = LibAnnotation.getFormAnnotation(reflectionField);
        boolean checkFirstCapital = LibAnnotation.isFirstCapital(reflectionField);
        boolean checkUpper = LibAnnotation.isAllUpper(reflectionField);
        boolean checkLower = LibAnnotation.isAllLower(reflectionField);

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

    private static class AValidator {
        AbstractValidator validator;
        Posizione posizione;

        public AValidator(AbstractValidator validator, Posizione posizione) {
            this.validator = validator;
            this.posizione = posizione;
        }// end of constructor
    }// end of internal class

    private enum Posizione {
        prima, dopo
    }// end of internal enumerationù

}// end of abstract static class
