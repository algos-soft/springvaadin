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
public @interface AIColumn {

    Class<? extends Object> clazz() default Object.class;

    AFType type() default AFType.text;

    String header() default "x";

    double width() default 80;

    String prompt() default "";

    String help() default "";

}// end of interface annotation
