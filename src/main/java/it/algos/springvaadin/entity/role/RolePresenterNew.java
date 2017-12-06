package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 12:31
 */
@Slf4j
@SpringComponent
//@Scope("vaadin-view")
@Scope("session")
public class RolePresenterNew {

    //    @Autowired
//    @Lazy
    protected RoleListNew list;
    protected RoleFormNew form;

    public int numero;

    public RolePresenterNew(@Lazy RoleListNew list,@Lazy RoleFormNew form) {
        this.list = list;
        this.form = form;
    }

    @PostConstruct
    public void fix() {
        numero = 364;
    }// end of method

//    @Autowired
//    public void setList(RoleListNew list) {
//        this.list = list;
//    }
//
//    public RoleListNew getList() {
//        return list;
//    }
//
//    @Autowired
//    public void setForm(RoleFormNew form) {
//        this.form = form;
//    }
//
//    public RoleFormNew getForm() {
//        return form;
//    }


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
//    @PostConstruct
    public void inizia() {
        RoleListNew lista = list;
        RolePresenterNew p = lista.presenter;
        int alfa = list.num;
        list.num = 88;
        list.inizia();
        form.numweoverde=172737474;
        form.inizia();
        int a = 87;
    }// end of method

}// end of class
