package it.algos.springvaadin.entity.role;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListNew;
import it.algos.springvaadin.view.AlgosViewNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 15:54
 */
@Slf4j
@SpringComponent
//@Scope("vaadin-view")
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@SpringView(name = "ruoloform")
public class RoleFormNew implements View {

    protected RolePresenterNew presenter;

    public int numweoverde;

//    public RoleFormNew( @Lazy  RolePresenterNew presenter) {
//        this.presenter=presenter;
//    }

    @PostConstruct
    public void fix() {
        numweoverde = 33333;
    }// end of method

//    @Autowired
//    public void setPresenter(RolePresenterNew presenter) {
//        this.presenter = presenter;
//    }
//
//    public RolePresenterNew getPresenter() {
//        return presenter;
//    }

    public void inizia() {
        int a = 87;
    }// end of method

}// end of class
