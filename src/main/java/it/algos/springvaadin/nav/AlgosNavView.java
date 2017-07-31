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
 * Serve SOLO per intercettare le selezioni del menu e 'lanciare' il corrispondente xxxPresenter
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * Passa il controllo alla classe xxxPresenter che gestisce la business logic
 * La classe xxxPresenter costruisce (iniezione) tutte le classi necessarie: xxxView, xxxService
 * La classe xxxPresenter riceve inoltre la entityClass del modello dati (dal costruttore della sottoclasse)
 * Il metodo getLinkedView() fornisce, tramite xxxPresenter,
 * la view effettiva da visualizzare richiesta da AlgosUI.showView()
 */
public abstract class AlgosNavView implements View {


    //--gestore principale del modulo, iniettato dal costruttore
    private AlgosPresenter presenter;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
     * Nella sottoclasse concreta si usa un @Qualifier(), per avere la sottoclasse specifica
     * Nella sottoclasse concreta si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param presenter iniettato da Spring
     */
    public AlgosNavView(AlgosPresenter presenter) {
        this.presenter = presenter;
    }// end of Spring constructor


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Passa il controllo alla classe xxxPresenter che gestisce la business logic
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        presenter.enter();
    }// end of method


    /**
     * Gestore della business logic
     * Richiesto da LibSpring.getPresenter()
     */
    public AlgosPresenter getPresenter() {
        return presenter;
    }// end of method


    /**
     * Vista effettiva da usare/visualizzare
     * Richiesta da AlgosUI.showView()
     */
    public AlgosView getLinkedView() {
        return presenter.getView();
    }// end of method


}// end of abstract class
