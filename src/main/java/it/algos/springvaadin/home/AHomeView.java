package it.algos.springvaadin.home;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AList;
import it.algos.springvaadin.panel.APanel;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.AView;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:52
 */
@Slf4j
@Scope("session")
@SpringView(name = Cost.TAG_HOME)
public class AHomeView extends AView {

    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.HOME;

    /**
     * Classe da aggiungere a menuLayout solo per questo modulo
     * Viene eliminata dal menuLayout quando la view ''esce'
     */
    private Class<? extends IAView> viewForThisModuleOnly = RoleForm.class;

    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public AHomeView() {
        super(null);
    }// end of Spring constructor


    /**
     * Metodo invocato (dalla SpringNavigator di SpringBoot) ogni volta che la view diventa attiva
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Passa il controllo al Presenter
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //--Regolazioni comuni a tutte le views
        super.enter(event);

        //--menu specifico di questa view. Verrà rimosso in uscita della view
        menuLayout.addViewBefore(viewForThisModuleOnly, RoleList.class);

        //--Non essendoci il Presenter, passa il controllo direttamente alla superclasse di questa view
//        super.start(null, null, null, null,null,null);
    }// end of method

    /**
     * Crea il corpo centrale della view
     * Componente grafico obbligatorio
     * Sovrascritto nella sottoclasse della view specifica (AList, AForm, ...)
     */
    protected void creaBody(Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
        bodyLayout.setContent(new Label(htlm.setRossoBold("Home"), ContentMode.HTML));
    }// end of method


    /**
     * Elimina il menuLayout dalla vista 'uscente'
     */
    @Override
    public void removeComponents() {
        super.removeComponents();
        menuLayout.removeView(viewForThisModuleOnly);
    }// end of method

}// end of class
