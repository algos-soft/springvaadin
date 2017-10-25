package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_STA)
public class StatoList extends AlgosListImpl {


    private AButton buttonImport;


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public StatoList(@Qualifier(Cost.TAG_STA) AlgosService service, AlgosGrid grid, ListToolbar toolbar) {
        super(service, grid, toolbar);
        toolbar.setUsaBottoneRicerca(false);
    }// end of Spring constructor


    /**
     * Chiamato ogni volta che la finestra diventa attiva
     * Può essere sovrascritto per un'intestazione (caption) della grid
     */
    @Override
    protected void inizializza() {
        if (LibSession.isDeveloper()) {
            caption = "";
            caption += "</br>Lista visibile solo al developer";
            caption += "</br>NON usa la company";
            caption += "</br>La key property ID utilizza la property alfaTre";
            caption += "</br>Le property nome, alfaDue e alfaTre sono uniche e non possono essere nulle";
            caption += "</br>La property numerico può essere nulla";
        }// end of if cycle
    }// end of method


    /**
     * Prepara la toolbar
     * <p>
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    @Override
    protected void inizializzaToolbar(ApplicationListener source) {
        super.inizializzaToolbar(source);
        buttonImport = toolbar.creaAddButton(AButtonType.importa, source);
    }// end of method


    public void enableImport(boolean status) {
        if (buttonImport != null) {
            buttonImport.setEnabled(status);
        }// end of if cycle
    }// end of method


}// end of class
