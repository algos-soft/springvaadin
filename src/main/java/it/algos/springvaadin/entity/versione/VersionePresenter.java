package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.service.AlgosServiceOld;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by gac on 01/06/17
 * <p>
 * Classe che gestisce la business logic di un 'modulo'
 * Viene creata la prima volta dalla xxxNavView (injected) che a sua volta viene creata dallo SpringNavigator
 * Classe specifica per costruire i riferimenti usati dalla superclasse
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersionePresenter extends AlgosPresenter {

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersionePresenter(@Qualifier(Cost.TAG_VERS) AlgosView view, @Qualifier(Cost.TAG_VERS) AlgosService service) {
        super(view, service);
    }// end of Spring constructor


}// end of class
