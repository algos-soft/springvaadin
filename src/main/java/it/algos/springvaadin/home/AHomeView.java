package it.algos.springvaadin.home;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.AView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
     * Costruttore @Autowired (nella superclasse)
     */
    public AHomeView() {
        super(null);
    }// end of Spring constructor

    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);
        this.addComponent(new Label(htlm.setRossoBold("Home"), ContentMode.HTML));
    }// end of method

}// end of class
