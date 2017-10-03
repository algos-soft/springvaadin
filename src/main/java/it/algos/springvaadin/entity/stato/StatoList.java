package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Qualifier(Cost.TAG_STA)
public class StatoList extends AlgosListImpl {

    private AButton buttonImport;

    @Autowired
    private StatoService service;

    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public StatoList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
        toolbar.setUsaBottoneRicerca(false);
    }// end of Spring constructor

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     */
    protected void inizializza() {
        chekStatiEsistenti();
        if (LibSession.isDeveloper()) {
            caption = "</br>Lista visibile solo al developer.";
            caption += "</br>La collezione viene usata dalle altre collezioni come 'references' (tramite @DBRef) e non 'embedded'";
            caption += "</br>La key property ID utilizza la property alfaTre";
            caption += "</br>Le property nome, alfaDue e alfaTre sono uniche e non possono essere nulle";
            caption += "</br>La property numerico può essere nulla";
        }// end of if cycle
    }// end of method


    /**
     * Crea una collezione di stati
     * Controlla se la collezione esiste già
     */
    public void chekStatiEsistenti() {
        if (service.count() < 2) {
            service.creaStati();
        } else {
            log.info("La collezione di stati è presente");
        }// end of if/else cycle
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
