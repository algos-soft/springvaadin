package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;
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
public class RolePresenter {

    private RoleList list;
    private RoleForm form;

    public RolePresenter(@Lazy RoleList list, @Lazy RoleForm form) {
        this.list = list;
        this.form = form;
    }// end of method

}// end of class
