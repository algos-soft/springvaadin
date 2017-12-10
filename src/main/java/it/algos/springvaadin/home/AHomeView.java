package it.algos.springvaadin.home;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.RoleForm;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AList;
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
public class AHomeView extends AList {

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

        menuLayout.addViewBefore(viewForThisModuleOnly, RoleList.class);
        this.addComponent(new Label(htlm.setRossoBold("Home"), ContentMode.HTML));
    }// end of method

//    /**
//     * Metodo invocato dal Presenter (dopo che ha elaborato i dati da visualizzare)
//     * Ricrea tutto ogni volta che la view diventa attiva
//     * La view comprende:
//     * 1) Top: il menuLayout - aggiunto e regolato nel metodo AView.enter() della superclasse
//     * 2) Caption: eventuali scritte esplicative come collezione usata, records trovati, ecc
//     * 3) Body: scorrevole contenente la Grid
//     * 4) Footer - Barra dei bottoni di comando per lanciare eventi
//     *
//     * @param source      di riferimento per gli eventi
//     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
//     * @param columns     visibili ed ordinate della Grid
//     * @param items       da visualizzare nella Grid
//     */
//    public void start(IAPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
//        menuLayout.addView(RoleForm.class);
//        this.addComponent(new Label(htlm.setRossoBold("Home"), ContentMode.HTML));
//    }// end of method


    /**
     * Elimina il menuLayout dalla vista 'uscente'
     */
    @Override
    public void removeComponents() {
        super.removeComponents();
        menuLayout.removeView(viewForThisModuleOnly);
    }// end of method

}// end of class
