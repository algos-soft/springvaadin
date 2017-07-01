package it.algos.springvaadin.entities.log;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.entities.company.CompanyForm;
import it.algos.springvaadin.entities.company.CompanyList;
import it.algos.springvaadin.entities.company.CompanyPresenter;
import it.algos.springvaadin.entities.company.CompanyView;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 30/06/17.
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

    public static final String VIEW_NAME = "log";
    public static final Resource VIEW_ICON = VaadinIcons.TAG;


    //--il presenter viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private LogPresenter logPresenter;


    //--la lista viene iniettata in questa classe
    //--viene iniettata qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private LogList logList;


    //--il form viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private LogForm logForm;


    /**
     * Metodo invocato subito DOPO il costruttore
     * <p>
     * Performing the initialization in a constructor is not suggested
     * as the state of the UI is not properly set up when the constructor is invoked.
     * <p>
     * Ci possono essere diversi metodi con @PostConstruct e firme diverse e funzionano tutti,
     * ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    @Override
    protected void inizia() {
        super.inizia();

        //--casting per gestire la property generica
        presenter = logPresenter;

        //--casting per gestire la property generica
        list = logList;

        //--casting per gestire la property generica
        form = logForm;

        //--eventuali intestazioni informative per List e Form
        //--valori standard che possono essere sovrascritti nella classi specifiche
        super.captionList = "Elenco di tutti i logs";
        super.captionFormCreate = "Nuovo log";
        super.captionFormEdit = "Modifica log";
    }// end of method


}// end of class
