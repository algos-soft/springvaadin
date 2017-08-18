package it.algos.springvaadin.interfaccia;

import it.algos.springvaadin.field.AFType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by gac on 05 ott 2016.
 * AlgosInterfaceList (AIField)
 * Annotation to add some property for a single collection or list.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in class and interface.
public @interface AIList {



    /**
     * (Optional) Status (shows the ID property as column) .
     * Defaults to false.
     */
    boolean showsID() default false;


    /**
     * (Optional) List of visible columns on Grid
     * Defaults to all.
     */
    String[] columns() default "";

}// end of interface annotation
