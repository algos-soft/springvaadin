package it.algos.springvaadin.entity.log;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;
import it.algos.springvaadin.view.AlgosViewImpl;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 01/06/17
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * SpringBoot inietta le sottoclassi specifiche (xxxPresenter, xxxList e xxxForm)
 * Nel metodo @PostConstruct, viene effettuato il casting alle property più generiche
 * Passa il controllo alla classe AlgosPresenter che gestisce la business logic
 * <p>
 * Riceve i comandi ed i dati da xxxPresenter (sottoclasse di AlgosPresenter)
 * Gestisce due modalità di presentazione dei dati: List e Form
 * Presenta i componenti grafici passivi
 * Presenta i componenti grafici attivi: azioni associate alla Grid e bottoni coi listener
 */
@SpringComponent
@Qualifier(Cost.TAG_LOG)
@SpringView(name = LogView.VIEW_NAME)
public abstract class LogView  extends AlgosViewImpl{


    //--nome usato da SpringNavigator e dal Menu per selezionare questa vista
    public static final String VIEW_NAME = "log";


    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.TAG;


//    /**
//     * In questa sottoclasse di AlgosView vwengono iniettati i riferimenti alle altre classi
//     * Si usano i @Qualifier(), per avere le sottoclassi specifiche
//     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
//     * Nella superclasse AlgosView vengono gestite le classi generiche
//     */
//    public LogView(
//            @Qualifier(Cost.TAG_LOG) AlgosPresenter presenter,
//            @Qualifier(Cost.TAG_LOG) AlgosList list,
//            @Qualifier(Cost.TAG_LOG) AlgosForm form) {
//        super(presenter, list, form);
//    }// fine del metodo costruttore (Autowired nella superclasse)
//
}// end of class
