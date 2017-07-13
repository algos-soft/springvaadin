package it.algos.springvaadin.field;

import javax.persistence.Column;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gac on 05 ott 2016.
 * Annotation to add some property for a single column of the Grid.
 * Alcune property sono in comune con AIField
 * Se nell'annotation AIColumn manca una property ,
 * si prende il valore della corrispondente property di AIField
 * (se esiste, altrimenti il valore di default)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //can use in field only.
public @interface AIColumn {

    Class<? extends Object> clazz() default Object.class;

    //--solo se è diverso da quello indicato in AIField
    //--se manca in entrambe le interfaccie, prende il valore di default di AIField
    AFType type() default AFType.nullo;

    /**
     * (Optional) The name of the field.
     * Defaults to the property or field name.
     */
    String name() default "";

    double width() default 80;

    String prompt() default "";

    String help() default "";

}// end of interface annotation
