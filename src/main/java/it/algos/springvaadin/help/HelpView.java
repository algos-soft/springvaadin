package it.algos.springvaadin.help;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;
import it.algos.springvaadin.view.AlgosViewImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 01/06/17.
 * Help view
 * Può delle righe di aiuto
 * Se presente, è l'ultima del menu
 */
@SpringComponent
//@Scope()
@SpringView(name = HelpView.VIEW_NAME)
public abstract class HelpView extends AlgosViewImpl {

    public static final String VIEW_NAME = "help";
    public static final Resource VIEW_ICON = VaadinIcons.QUESTION;


    @PostConstruct
    protected void inizia() {
//        addComponent(new Label("Help view"));
//        addComponent(new Label("Icona nel menu/bottone: " + VaadinIcons.HEART));
//        addComponent(new Label(""));
//        addComponent(new Label("Da sviluppare"));
    }// end of method

//    public HelpView(AlgosPresenter presenter, AlgosList list, AlgosForm form) {
//        super(presenter, list, form);
//    }// fine del metodo costruttore Autowired

//    @Override
//    public ApplicationEventPublisher getApplicationEventPublisher() {
//        return null;
//    }
}// end of class
