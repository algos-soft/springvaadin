package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.APresenter;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.service.IAService;
import it.algos.springvaadin.list.IAList;
import it.algos.springvaadin.form.IAForm;
import it.algos.springvaadin.toolbar.IAToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 23:15
 */
@Slf4j
@SpringComponent
@Scope("session")
@Qualifier(Cost.TAG_ROL)
public class RolePresenter extends APresenter {


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     * Use @Lazy to avoid the Circular Dependency
     * A simple way to break the cycle is saying Spring to initialize one of the beans lazily.
     * That is: instead of fully initializing the bean, it will create a proxy to inject it into the other bean.
     * The injected bean will only be fully created when it’s first needed.
     *
     * @param service iniettato da Spring
     * @param list iniettato da Spring
     * @param form iniettato da Spring
     */
    public RolePresenter(
            @Lazy @Qualifier(Cost.TAG_ROL) IAService service,
            @Lazy @Qualifier(Cost.TAG_ROL) IAList list,
            @Lazy @Qualifier(Cost.TAG_ROL) IAForm form) {
        super(service, list, form);
        super.entityClass = Role.class;
    }// end of Spring constructor


}// end of class
