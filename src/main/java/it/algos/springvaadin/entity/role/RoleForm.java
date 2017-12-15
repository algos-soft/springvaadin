package it.algos.springvaadin.entity.role;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.form.AForm;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.IAPresenter;
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
 * Time: 23:18
 */
@Slf4j
@SpringComponent
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@SpringView(name = Cost.VIEW_ROL_FORM)
public class RoleForm extends AForm {


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
     * @param presenter iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public RoleForm(@Lazy @Qualifier(Cost.TAG_ROL) IAPresenter presenter, @Qualifier(Cost.BAR_FORM) IAToolbar toolbar) {
        super(presenter, toolbar);
    }// end of Spring constructor


}// end of class
