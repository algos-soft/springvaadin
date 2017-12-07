package it.algos.springvaadin.entity.role;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AList;
import it.algos.springvaadin.view.AView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by gac on 11-nov-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@SpringView(name = "ruololist")
public class RoleList extends AView {

    private RolePresenter presenter;

    /**
     * Costruttore @Autowired (nella superclasse)
     */
//    public RoleList(@Qualifier(Cost.TAG_ROL) AlgosService service, AlgosGrid grid, ListToolbar toolbar) {
//        super(service, grid, toolbar);
//    }// end of Spring constructor
    public RoleList(@Lazy RolePresenter presenter) {
        this.presenter = presenter;
    }// end of Spring constructor


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        this.addComponent(new Label(htlm.setRossoBold("Ruolo - Grid (provvisorio)"), ContentMode.HTML));
    }// end of method

}// end of class
