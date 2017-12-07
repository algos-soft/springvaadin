package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import it.algos.springvaadin.annotation.AIList;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListNew;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.ListToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

import static it.algos.springvaadin.lib.Cost.VIEW_VERS_LIST;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 11:03
 * Annotated with Lombok @Slf4j (facoltativo)
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with 'session' @Scope (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 * Annotated with @SpringView (obbligatorio) usato da SpringNavigator
 * Annotated with @AIList (obbligatorio) per specificare una List (altra opzione è @AIForm) al Presenter
 * Constructor annotated with @Lazy to avoid the Circular Dependency for/to AlgosPresenter
 */
@Slf4j
@SpringComponent
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@SpringView(name = "ruolotest")
@AIList
public class RoleListNew extends AlgosListNew {


    /**
     * Costruttore @Autowired (nella superclasse)
     * Use @Lazy to avoid the Circular Dependency
     * A simple way to break the cycle is saying Spring to initialize one of the beans lazily.
     * That is: instead of fully initializing the bean, it will create a proxy to inject it into the other bean.
     * The injected bean will only be fully created when it’s first needed.
     */
    public RoleListNew(
            @Lazy @Qualifier(Cost.TAG_ROL) IAlgosPresenter presenter,
            @Qualifier(Cost.TAG_ROL) AlgosService service,
            ListToolbar toolbar,
            AlgosGrid grid) {
        super(presenter, service, toolbar, grid);
    }// end of Spring constructor


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore
     * Uso opzionale (si può usare qualsiasi firma)
     */
    @PostConstruct
    public void inizia() {
    }// end of method


}// end of class
