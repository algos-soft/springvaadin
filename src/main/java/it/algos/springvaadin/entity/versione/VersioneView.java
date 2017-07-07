package it.algos.springvaadin.entity.versione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;
import it.algos.springvaadin.view.AlgosViewImpl;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.List;

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
//@Qualifier(Cost.TAG_VERS)
//@SpringView(name = VersioneView.VIEW_NAME)
public  class VersioneView extends AlgosViewImpl {


    //--nome usato da SpringNavigator e dal Menu per selezionare questa vista
    public static final String VIEW_NAME = "pippo";


    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.DIPLOMA;

//    public VersioneView() {
//    }

//    /**
//     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
//     * Deve quindi iniettarsi il riferimento al gestore principale (Presenter)
//     * Si usa un @Qualifier(), per avere la sottoclasse specifica
//     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
//     */
//    public VersioneView(@Qualifier(Cost.TAG_VERS) AlgosPresenter presenter) {
//        super(presenter);
//    }// fine del metodo costruttore (Autowired nella superclasse)


    /**
     * Metodo invocato da AlgosPresenter
     * <p>
     * Regola la lista (che usa una Grid)
     * Visualizza la lista
     *
     * @param clazz
     * @param items           da visualizzare nella Grid
     * @param colonneVisibili e ordinate della lista
     */
    public void setList(AlgosEntity clazz, List items, List<String> colonneVisibili) {

    }

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}// end of class
