package it.algos.springvaadin.home;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Layout;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.toolbar.AToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 01-ott-2017
 * Time: 20:26
 */
@SpringComponent
@Qualifier(Cost.TAG_HOME)
public class HomeForm extends AlgosFormImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public HomeForm() {
        super(null,null );
    }// end of Spring constructor


    /**
     * Crea i campi, li aggiunge al layout, li aggiunge al binder
     *
     * @param source     presenter di riferimento da cui vengono generati gli eventi
     * @param target
     * @param layout     in cui inserire i campi (window o panel)
     * @param reflectFields del form da visualizzare
     */
    @Override
    protected void creaAddBindFields(ApplicationListener source, ApplicationListener target, Layout layout, List<Field> reflectFields) {
        super.creaAddBindFields(source, target, layout, reflectFields);
    }// end of method

}// end of class
