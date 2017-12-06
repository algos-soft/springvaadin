package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosService;
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
 * Time: 12:31
 */
@Slf4j
@SpringComponent
//@Scope("vaadin-view")
@Scope("session")
@Qualifier(Cost.TAG_ROL)
public class RolePresenterNew extends AlgosPresenter{

    //    @Autowired
//    @Lazy
    protected RoleListNew list;
    protected RoleFormNew form;

    public int numero;

    public RolePresenterNew(@Qualifier(Cost.TAG_ROL) AlgosService service, @Lazy RoleListNew list, @Lazy RoleFormNew form) {
        super(service,list);
        this.list = list;
        this.form = form;
        super.entityClass = Role.class;
    }





}// end of class
