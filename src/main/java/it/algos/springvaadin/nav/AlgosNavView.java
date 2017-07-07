package it.algos.springvaadin.nav;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import it.algos.springvaadin.entity.versione.VersionePresenter;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gac on 07/07/17
 * <p>
 * Layer di collegamento tra Spring e MVP
 * Serve SOLO per intercettare le selezioni del menu e 'lanciare' il corrispondente AlgosPresenter
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * Passa il controllo alla classe xxxPresenter che gestisce la business logic
 * La classe xxxPresenter costruisce (iniezione) tutte le classi necessarie, tra cui xxxView
 * La vista xxxView viene mantenuta qui perché il metodo showView (Spring),
 * che richiama questa classe, possa visualizzare la view effettiva
 * <p>
 */
public abstract class AlgosNavView implements View {


    //--gestore principale del modulo
    private AlgosPresenter presenter;


    //--vista effettiva da usare/visualizzare
    private AlgosView linkedView;


    /**
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (Presenter)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    @Autowired
    public AlgosNavView(AlgosPresenter presenter) {
        this.presenter = presenter;
        linkedView = presenter.view;
    }// fine del metodo costruttore (Autowired nella superclasse)


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Passa il controllo alla classe xxxPresenter che gestisce la business logic
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.enter();
    }// end of method


    public AlgosView getLinkedView() {
        return linkedView;
    }// end of getter method


}// end of class
