package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by gac on 03/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per la lista (Grid)
 * Nel ciclo restart() di List, la toolbar costruisce i bottoni ("prototype") usando la factory AButtonFactory
 * Viene poi iniettato il parametro obbligatorio (source)
 * Ulteriori parametri (target, entityBean), vengono iniettati direttamente solo in alcuni bottoni
 * Tutti i bottoni possono essere abilitati/disabilitati
 * I bottoni standard sono 3 o 4
 * Eventuali bottoni aggiuntivi, possono essere aggiunti nella classe specifica xxxList.toolbarInizializza()
 * Tutti i bottoni possono essere abilitati/disabilitati
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BAR_LIST)
public class ListToolbar extends AToolbarImpl {


//    /**
//     * Flag per visualizzare o meno il bottone New - di default true
//     */
//    private boolean usaBottoneNew = true;
//
//    /**
//     * Flag per visualizzare o meno il bottone Edit - di default true
//     */
//    private boolean usaBottoneEdit = true;
//
//    /**
//     * Flag per visualizzare o meno il bottone Delete - di default true
//     */
//    private boolean usaBottoneDelete = true;
//
//    /**
//     * Flag per visualizzare o meno il bottone Search - di default false
//     */
//    private boolean usaBottoneRicerca = false;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    public ListToolbar(AButtonFactory buttonFactory) {
        super(buttonFactory);
    }// end of @Autowired constructor

//    public void selezionaBottoniOld(List<String> listaBottoni) {
//        if (!listaBottoni.contains("usaBottoneNew")) {
//            usaBottoneNew = false;
//        }// end of if cycle
//        if (!listaBottoni.contains("usaBottoneEdit")) {
//            usaBottoneEdit = false;
//        }// end of if cycle
//        if (!listaBottoni.contains("usaBottoneDelete")) {
//            usaBottoneDelete = false;
//        }// end of if cycle
//        if (listaBottoni.contains("usaBottoneRicerca")) {
//            usaBottoneRicerca = true;
//        }// end of if cycle
//    }// end of method

    /**
     * Metodo invocato da restart() di Form e List
     * Seleziona i bottoni da mostrare nella toolbar
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source       dell'evento generato dai bottoni
     * @param listaBottoni da visualizzare
     */
    @Override
    public void inizializza(ApplicationListener source, List<String> listaBottoni) {
        this.removeAllComponents();

        if (listaBottoni.contains(Cost.TAG_BOT_NEW)) {
            super.creaAddButton(AButtonType.create, source);
        }// end of if cycle
        if (listaBottoni.contains(Cost.TAG_BOT_EDIT)) {
            super.creaAddButton(AButtonType.edit, source);
        }// end of if cycle
        if (listaBottoni.contains(Cost.TAG_BOT_DELETE)) {
            super.creaAddButton(AButtonType.delete, source);
        }// end of if cycle
        if (listaBottoni.contains(Cost.TAG_BOT_SEARCH)) {
            super.creaAddButton(AButtonType.search, source);
        }// end of if cycle

    }// end of method


//    public void setUsaBottoneRicerca(boolean usaBottoneRicerca) {
//        this.usaBottoneRicerca = usaBottoneRicerca;
//    }// end of method

}// end of class
