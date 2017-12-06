package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListNew;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
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
 * Time: 11:03
 */
@Slf4j
@SpringComponent
//@Scope("vaadin-view")
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@SpringView(name = "ruolotest")
public class RoleListNew extends AlgosListNew {

//    @Autowired
    protected RolePresenterNew presenter;

    public int num;

    public RoleListNew(@Lazy RolePresenterNew presenter) {
        this.presenter=presenter;
    }

    @PostConstruct
    public void fix() {
        num = 33333;
    }// end of method


//    @Autowired
//    public void setPresenter(RolePresenterNew presenter) {
//        this.presenter = presenter;
//    }
//
//    public RolePresenterNew getPresenter() {
//        return presenter;
//    }

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    @Override
    public Component getComponent() {
        RolePresenterNew p = presenter;
        RoleListNew l = p.list;
        int betya = presenter.numero;
        presenter.numero = 17;
        presenter.inizia();
        return new Label("alfa");
    }// end of method


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
//    @PostConstruct
    public void inizia() {
        int a = 87;
    }// end of method

}// end of class
