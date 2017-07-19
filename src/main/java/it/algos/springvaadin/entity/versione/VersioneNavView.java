package it.algos.springvaadin.entity.versione;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.nav.AlgosNavView;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 07/07/17
 * <p>
 * Layer di collegamento tra Spring e MVP
 * Serve SOLO per intercettare le selezioni del menu e 'lanciare' il corrispondente AlgosPresenter
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * Passa il controllo alla classe xxxPresenter che gestisce la business logic
 * La classe xxxPresenter costruisce (iniezione) tutte le classi necessarie, tra cui xxxView
 * Il metodo getLinkedView() fornisce, tramite xxxPresenter,
 * la view effettiva da visualizzare richiesta da AlgosUI.showView()
 */
@SpringView(name = VersioneNavView.VIEW_NAME)
public class VersioneNavView extends AlgosNavView {


    //--nome usato da SpringNavigator e dal Menu per selezionare questa vista
    public static final String VIEW_NAME = "versione";


    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.DIPLOMA;


    /**
     * Costruttore @Autowired (nella superclasse)
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneNavView(@Qualifier(Cost.TAG_VERS) AlgosPresenter presenter) {
        super(presenter);
    }// end of Spring constructor


}// end of class