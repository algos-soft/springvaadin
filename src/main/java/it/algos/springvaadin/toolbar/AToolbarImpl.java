package it.algos.springvaadin.toolbar;

import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonFactory;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 03/06/17.
 * .
 * Superclasse astratta per le barre di comando con bottoni
 * Le toolbar costruiscono i bottoni ("prototype") usando la factory AButtonFactory
 * Nel ciclo restart() di Form e List, viene poi iniettato il parametro obbligatorio (source)
 * Ulteriori parametri (target, entityBean), vengono iniettati direttamente solo in alcuni bottoni
 * Eventuali bottoni aggiuntivi, oltre quelli standard, possono essere aggiunti in AListImpl.toolbarInizializza()
 * Tutti i bottoni possono essere abilitati/disabilitati
 */
public abstract class AToolbarImpl extends HorizontalLayout implements AToolbar {


    /**
     * Factory per la creazione dei bottoni
     * Autowired nel costruttore
     */
    private AButtonFactory buttonFactory;

    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    public AToolbarImpl(AButtonFactory buttonFactory) {
        this.buttonFactory = buttonFactory;
    }// end of @Autowired constructor


    /**
     * Metodo invocato da restart() di Form e List
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    @Override
    public void inizializza(ApplicationListener source) {
    }// end of method


    /**
     * Inietta nel bottone il parametro
     * Il bottone è già stato inizializzato coi parametri standard
     *
     * @param entityBean in elaborazione
     */
    @Override
    public void inizializzaEdit(AEntity entityBean) {
    }// end of method

    /**
     * Inietta nel bottone i parametri
     * Il bottone è già stato inizializzato coi parametri standard
     *
     * @param entityBean in elaborazione
     * @param target     (window, dialog, presenter) a cui indirizzare l'evento
     */
    @Override
    public void inizializzaEditLink(AEntity entityBean, ApplicationListener target) {
    }// end of method


    /**
     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
     * Inietta nei bottoni il parametro obbligatorio (source)
     * Aggiunge il bottone al contenitore grafico
     */
    @Override
    public AButton creaAddButton(TypeButton type, ApplicationListener source) {
        AButton button = buttonFactory.crea(type, source);

        if (button != null) {
            addComponent(button);
        }// end of if cycle

        return button;
    }// end of method


//    /**
//     * Metodo invocato DOPO la chiamata del browser, da AlgosList e da AlgosForm
//     * I bottoni vengono creati da Spring in una fase iniziale 'statica' e non sanno chi li 'userà'
//     * Quando parte la UI ed il corrispondente xxxPresenter, questo viene iniettato nel bottone
//     * Il bottone usa il presenter per identificare 'dove' gestire l'evento generato
//     */
//    public void regolaBottoni(AlgosPresenterImpl presenter) {
//        for (int k = 0; k < getComponentCount(); k++) {
//            ((AButton) getComponent(k)).regolaBottone(presenter);
//        }// end of for cycle
//    }// end of method
//
//
//    /**
//     * Inserisce nei bottoni Registra o Accetta il Field che va notificato
//     *
//     * @param parentField che ha richiesto questo form
//     */
//    public void setParentField(AField parentField) {
//    }// end of method


    @Override
    public void enableAnnulla(boolean status) {
    }// end of method

    @Override
    public void enableRevert(boolean status) {
    }// end of method

    @Override
    public void enableRegistra(boolean status) {
    }// end of method

    @Override
    public void enableAccetta(boolean status) {
    }// end of method

    @Override
    public void enableNew(boolean status) {
    }// end of method

    @Override
    public void enableEdit(boolean status) {
    }// end of method

    @Override
    public void enableDelete(boolean status) {
    }// end of method

    @Override
    public void enableSearch(boolean status) {
    }// end of method

}// end of class
