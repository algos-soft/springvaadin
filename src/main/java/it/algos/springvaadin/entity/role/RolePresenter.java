package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.form.IAForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.IAList;
import it.algos.springvaadin.presenter.APresenter;
import it.algos.springvaadin.service.IAService;
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


    public RolePresenter(
            @Lazy @Qualifier(Cost.TAG_ROL) IAService service,
            @Lazy @Qualifier(Cost.TAG_ROL) IAList list,
            @Lazy @Qualifier(Cost.TAG_ROL) IAForm form) {
        super(service, list, form);
    }// end of method


}// end of class
