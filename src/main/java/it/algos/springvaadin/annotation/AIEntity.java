package it.algos.springvaadin.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 25-set-2017
 *
 * Time: 18:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in class and interface.
public @interface AIEntity {

    /**
     * (Optional) Status
     * Defaults to true.
     */
    boolean companyNotNull() default true;


}// end of interface annotation
