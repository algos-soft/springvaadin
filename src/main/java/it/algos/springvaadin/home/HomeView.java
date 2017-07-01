package it.algos.springvaadin.home;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 01/06/17.
 * Home page
 * Può presentare uno splashScreen oppure vuota (col solo menu) oppure righe di aiuto
 * * Se presente, è la prima del menu
 */
@SpringComponent
//@Scope()
@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends AlgosView {

    public static final String VIEW_NAME = "home";
    public static final Resource VIEW_ICON = VaadinIcons.HOME;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @PostConstruct
    protected void inizia() {
        addComponent(new Label("SplashScreen"));
        addComponent(new Label("Vuota"));
        addComponent(new Label("Righe di aiuto"));
        addComponent(new Label("Icona nel menu/bottone: " + VaadinIcons.HOME));
        addComponent(new Label(""));
        addComponent(new Label("Da sviluppare"));
    }// end of method

//    @Override
//    public ApplicationEventPublisher getApplicationEventPublisher() {
//        return null;
//    }
}// end of class
