package it.algos.springvaadin.entity.role;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AList;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.AView;
import it.algos.springvaadin.view.IAView;
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
public class RoleList extends AList {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
//    public RoleList(@Qualifier(Cost.TAG_ROL) AlgosService service, AlgosGrid grid, ListToolbar toolbar) {
//        super(service, grid, toolbar);
//    }// end of Spring constructor
    public RoleList(@Lazy @Qualifier(Cost.TAG_ROL) IAPresenter presenter, IAGrid grid) {
        super(presenter, grid);
    }// end of Spring constructor


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Aggiunge i riferimenti agli oggetti specifici di questa view (this)
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);
        this.addComponent(new Label(htlm.setRossoBold("Ruolo - Grid (provvisorio)"), ContentMode.HTML));
    }// end of method

}// end of class
