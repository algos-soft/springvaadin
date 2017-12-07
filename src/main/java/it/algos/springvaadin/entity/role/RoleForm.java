package it.algos.springvaadin.entity.role;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.form.AForm;
import it.algos.springvaadin.lib.Cost;
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
@SpringView(name = "ruoloform")
public class RoleForm extends AForm {

    private RolePresenter presenter;


    public RoleForm(@Lazy RolePresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(new Label(htlm.setRossoBold("Ruolo - Form (provvisorio)"), ContentMode.HTML));
    }// end of method

}// end of class
