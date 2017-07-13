package it.algos.springvaadin.field;

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
     * (Required) The type of the field.
     * Defaults to the text type.
     */
    AFType type() default AFType.text;


    /**
     * (Optional) The name of the field.
     * Defaults to the property or field name.
     */
    String name() default "";


    /**
     * (Optional) The width of the field.
     * Expressed in int, to be converted in String ending with "em"
     * Defaults to "12em".
     */
    int widthEM() default 12;


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


    Class<? extends Object> clazz() default Object.class;

    int columns() default 20;

    int rows() default 4;


    String prompt() default "";

    String help() default "";



    int min() default 2;

    int max() default 80;

    String error() default "";

    /**
     * (Optional) Status (allowed null selection in popup) of the the field.
     * Meaning sense only for AFType.combo.
     * Defaults to false.
     */
    boolean nullSelectionAllowed() default false;

}// end of interface annotation
