package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Qualifier;

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
    public StatoList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
        toolbar.setUsaBottoneRicerca(false);
    }// end of Spring constructor

    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizia() {
        caption = "</br>Lista visibile solo al developer.";
        caption += "</br>La collezione viene usata dalle altre collezioni come 'references' (tramite @DBRef) e non 'embedded'";
        caption += "</br>La key property ID utilizza la property alfaTre";
        caption += "</br>Le property nome, alfaDue e alfaTre sono uniche e non possono essere nulle";
        caption += "</br>La property numerico può essere nulla";
    }// end of method


    /**
     * Prepara la toolbar
     */
    @Override
    protected void toolbarInizializza(AlgosPresenterImpl source) {
        super.toolbarInizializza(source);
        buttonImport = toolbar.creaAddButton(AButtonType.importa, source);
    }// end of method


    public void enableImport(boolean status) {
        if (buttonImport != null) {
            buttonImport.setEnabled(status);
        }// end of if cycle
    }// end of method


}// end of class
