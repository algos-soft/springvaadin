package it.algos.springvaadin.service;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:58
 * Classe di Libreria
 * Gestisce le interfacce @Annotation standard di Springs
 * Gestisce le interfacce specifiche di Springvaadin: AIColumn, AIField, AIEntity, AIForm, AIList
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class AAnnotationService {


    /**
     * Get the specific annotation of the class.
     *
     * @param clazz the entity class
     *
     * @return the Annotation for the specific class
     */
    public SpringView getSpringView(final Class<? extends IAView> clazz) {
        return clazz.getAnnotation(SpringView.class);
    }// end of method


    /**
     * Get the name of the spring-view.
     *
     * @param clazz the entity class
     *
     * @return the name of the spring-view
     */
    @SuppressWarnings("all")
    public String getViewName(final Class<? extends IAView> clazz) {
        String name = "";
        SpringView annotation = getSpringView(clazz);

        if (annotation != null) {
            name = annotation.name();
        }// end of if cycle

        return name;
    }// end of method

}// end of class
