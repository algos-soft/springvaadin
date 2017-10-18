package it.algos.springvaadin.annotation;

import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.login.ARoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gac on 05 ott 2016.
 * AlgosInterfaceField (AIField)
 * Annotation to add some property for a single field.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //can use in field only.
public @interface AIField {


    /**
     * (Optional) Classe della property.
     * Utilizzato nei Combo, nei Link e nelle Enumeration.
     */
    Class<? extends Object> clazz() default Object.class;


    /**
     * (Required) The type of the field.
     * Defaults to the text type.
     */
    AFieldType type() default AFieldType.text;


    /**
     * (Optional) The name of the field.
     * Defaults to the property or field name.
     */
    String name() default "";


    /**
     * (Optional) The width of the field.
     * Expressed in int, to be converted in String ending with "em"
     * Defaults to 0.
     */
    int widthEM() default 0;


    /**
     * (Optional) The number of rows of textArea field.
     * Expressed in int
     * Defaults to 2.
     */
    int numRowsTextArea() default 2;


    /**
     * (Optional) Status (field required for DB) of the the field.
     * Defaults to false.
     */
    boolean required() default false;


    /**
     * (Optional) Status (field enabled in form) of the the field.
     * Defaults to true.
     */
    boolean enabled() default true;


    /**
     * (Optional) Visibilità a secondo del ruolo dell'User collegato
     * Defaults to guest.
     */
    ARoleType roleTypeVisibility() default ARoleType.guest;


    /**
     * (Optional) field that get focus
     * Only one for form
     * Defaults to false.
     */
    boolean focus() default false;


    /**
     * (Optional) help text on rollover
     * Defaults to null.
     */
    String help() default "";


    /**
     * (Optional) Status (first letter capital) of the the field.
     * Defaults to false.
     */
    boolean firstCapital() default false;


    /**
     * (Optional) Status (all letters upper) of the the field.
     * Defaults to false.
     */
    boolean allUpper() default false;


    /**
     * (Optional) Status (all letters lower) of the the field.
     * Defaults to false.
     */
    boolean allLower() default false;


    /**
     * (Optional) Status (only number) of the the field.
     * Defaults to false.
     */
    boolean onlyNumber() default false;


    /**
     * (Optional) Status (only letters) of the the field.
     * Defaults to false.
     */
    boolean onlyLetter() default false;


    /**
     * (Optional) Status (allowed null selection in popup) of the the field.
     * Meaning sense only for AFieldType.combo.
     * Defaults to false.
     */
    boolean nullSelectionAllowed() default false;


}// end of interface annotation
