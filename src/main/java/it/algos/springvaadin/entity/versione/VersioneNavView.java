package it.algos.springvaadin.entity.versione;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.nav.AlgosNavView;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by gac on 07/07/17
 */
@SpringView(name = VersioneNavView.VIEW_NAME)
public class VersioneNavView extends AlgosNavView {


    //--nome usato da SpringNavigator e dal Menu per selezionare questa vista
    public static final String VIEW_NAME = "versione";


    //--icona del Menu
    public static final Resource VIEW_ICON = VaadinIcons.DIPLOMA;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param presenter iniettato da Spring, della sottoclasse indicata da @Qualifier
     */
    public VersioneNavView(@Qualifier(Cost.TAG_VERS) AlgosPresenter presenter) {
        super(presenter);
    }// end of Spring constructor


}// end of class
