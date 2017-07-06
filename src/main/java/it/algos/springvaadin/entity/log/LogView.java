package it.algos.springvaadin.entity.log;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
@SpringView(name = LogView.VIEW_NAME)
public class LogView extends AlgosView {

    //--nome usato da SpringNavigator e dal Menu per selezionare questa vista
    public static final String VIEW_NAME = "log";

    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.TAG;

    /**
     * Presenter specifico, iniettato in questa classe
     * Lista specifica, iniettata in questa classe
     * Form specifico, iniettato in questa classe
     * Vengono iniettati qui per avere le classi specifiche.
     * Nella superclasse vengono gestite le properties generiche.
     */
    @Autowired
    public LogView(LogPresenter presenter, LogList list, LogForm form) {
        super.presenter = presenter;
        super.list = list;
        super.form = form;

        //--eventuali intestazioni informative per List e Form
        super.captionList = "Elenco di tutti i logs";
        super.captionFormCreate = "Nuovo log";
        super.captionFormEdit = "Modifica log";
    }// fine del metodo costruttore Autowired

}// end of class
