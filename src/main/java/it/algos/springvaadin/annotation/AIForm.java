package it.algos.springvaadin.annotation;

import it.algos.springvaadin.form.FormButton;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by gac on 05 ott 2016.
 * AlgosInterfaceForm (AIForm)
 * Annotation to add some property for a single form.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in class and interface.
public @interface AIForm{


    /**
     * (Optional) Status (shows the ID property field) .
     * Defaults to false.
     */
    boolean showsID() default false;


    /**
     * (Optional) The width of the ID field.
     * Expressed in int, to be converted in String ending with "em"
     * Defaults to "16em".
     */
    int widthIDEM() default 16;


    /**
     * (Optional) List of visible fields on Form
     * Defaults to all.
     */
    String[] fields() default "";

    /**
     * (Optional) List of buttom on bottom
     * Specific for developer role
     * Defaults to standard.
     */
    FormButton dev() default FormButton.standard;

    /**
     * (Optional) List of buttom on bottom
     * Specific for admin role
     * Defaults to standard.
     */
    FormButton admin() default FormButton.standard;

    /**
     * (Optional) List of buttom on bottom
     * Specific for user role
     * Defaults to standard.
     */
    FormButton user() default FormButton.show;

}// end of interface annotation
