package it.algos.springvaadin.presenter;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by gac on 14/06/17.
 * <p>
 * Classe che gestisce la business logic di un 'modulo'
 * Viene creata la prima volta dalla xxxNavView (injected) che a sua volta viene creata dallo SpringNavigator
 * <p>
 * Utilizza una xxxView (da non confondersi con xxxNavView) specifica, iniettata
 * -(che a sua volta usa una xxxList per la Grid ed una xxxForm per la scheda)
 * Utilizza un xxxService specifico, iniettato
 * -(che a sua volta usa una AlgosEntity ed eventualmente una Repository)
 */
@SpringComponent
public abstract class AlgosPresenter extends AlgosPresenterEvents {


    //--la vista specifica viene iniettata dal costruttore della sottoclasse concreta
    protected AlgosView view;


    //--il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
    protected AlgosService service;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AlgosEntity> entityClass;


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AlgosPresenter(AlgosView view, AlgosService service) {
        this.view = view;
        this.service = service;
    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     * Regola il modello-dati specifico nel Service
     */
    @PostConstruct
    private void inizia() {
        this.service.setEntityClass(entityClass);
    }// end of method


    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     * La partenza standard è quella di mostrare una lista
     * Recupera i dati dal DB
     * Passa i dati alla view
     */
    public void enter() {
        this.presentaLista();
    }// end of method


    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     * oppure
     * metodo invocato da un Evento (azione) che necessita di aggiornare e ripresentare la Lista
     * tipo dopo un delete, dopo un nuovo record, dopo la modifica di un record
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Passa il controllo alla view con i dati necessari
     */
    protected void presentaLista() {
        List items = service.findAll();
        List<String> columns = service.getListColumns();

        view.setList(entityClass, items, columns);
    }// end of method


    /**
     * Evento
     * Create button pressed in grid
     * Create a new item and edit it in a form
     * <p>
     * Recupera i dati dal service
     * Passa i dati alla view
     */
    @Override
    public void create() {
        modifica((AlgosEntity) null);
    }// end of method


    /**
     * Evento
     * Edit button pressed in grid
     * Recupera il record selezionato
     * Display the item in a form
     * <p>
     * Recupera i dati dal service
     * Passa i dati alla view
     */
    @Override
    public void edit() {
        List<AlgosEntity> beanList = view.getEntityBeans();

        //patch @todo passa qui due volte (per errore) non trovato perché
        //la seconda volta il presenter è 'farlocco'
        if (beanList != null && beanList.size() == 1) {
            modifica(beanList.get(0));
        }// end of if cycle

    }// end of method


    /**
     * Evento
     * Row pressed double in grid
     * Recupera il record selezionato
     * Display the item in a form
     * <p>
     * Recupera i dati dal service
     * Passa i dati alla view
     */
    @Override
    public void doppioClick(AlgosEntity entityBean) {
        modifica(entityBean);
    }// end of method


    /**
     * Modifica singolo record (entityBean)
     */
    protected void modifica(AlgosEntity entityBean) {
        List<String> fields = service.getFormFields();

        if (entityBean == null) {
            entityBean = service.newEntity();
        }// end of if cycle
        view.setForm(entityBean, fields);
    }// end of method

    /**
     * Evento
     * Delete (cancella) button pressed in list
     * Recupera il/i record/s selezionato/i
     * Cancella il/i record/s selezionato/i
     * Torna alla lista
     */
    @Override
    public void delete() {
        if (service != null && view != null) {
            List<AlgosEntity> beanList = view.getEntityBeans();

            if (LibParams.chiedeConfermaPrimaDiCancellare()) {
                chiedeConfermaPrimaDiCancellare(beanList);
            } else {
                cancellazione(beanList);
            }// end of if/else cycle

            this.presentaLista();
        }// end of if cycle
    }// end of method


    /**
     * Presenta un dialogo di conferma prima della cancellazione effettiva
     */
    public void chiedeConfermaPrimaDiCancellare(List<AlgosEntity> beanList) {
        String message;

        if (beanList == null) {
            Notification.show("Errore", "Non riesco a cancellare", Notification.Type.ERROR_MESSAGE);
            return;
        }// end of if cycle

        if (beanList.size() == 1) {
            message = "Sei sicuro di voler eliminare il record: " + LibText.setRossoBold(beanList.get(0) + "") + " ?";
        } else {
            message = "Sei sicuro di voler eliminare i " + LibText.setRossoBold(beanList.size() + "") + " records selezionati ?";
            if (LibParams.usaDialoghiVerbosi()) {
                for (int k = 0; k < beanList.size(); k++) {
                    message += "</br>&nbsp;&nbsp;&nbsp;&nbsp;" + (k + 1) + ") " + beanList.get(k);
                }// end of for cycle
            }// end of if cycle
        }// end of if/else cycle

        ConfirmDialog dialog = new ConfirmDialog("Delete", message, new ConfirmDialog.Listener() {

            @Override
            public void onClose(ConfirmDialog dialog, boolean confirmed) {
                if (confirmed) {
                    cancellazione(beanList);
                    presentaLista();
                }// end of if cycle
            }// end of inner method
        });// end of anonymous inner class
        dialog.getCancelButton().setIcon(VaadinIcons.ARROW_BACKWARD);
        dialog.getConfirmButton().setIcon(VaadinIcons.CLOSE);
        dialog.setConfirmButtonText("Elimina");

        if (LibParams.usaBottoniColorati()) {
            dialog.getCancelButton().addStyleName("buttonGreen");
            dialog.getConfirmButton().addStyleName("buttonRed");
        }// end of if cycle

        dialog.show(LibVaadin.getUI());
    }// end of method


    /**
     * Cancella il/i record/s selezionato/i
     */
    protected void cancellazione(List<AlgosEntity> beanList) {
        boolean cancellato = false;

        if (beanList != null && beanList.size() > 0) {
            for (AlgosEntity entityBean : beanList) {

                if (service.delete(entityBean)) {
                    cancellato = true;
                }// end of if cycle

            }// end of for cycle
        }// end of if cycle

        if (cancellato) {
            this.presentaLista();
        }// end of if cycle

    }// end of method


    /**
     * Evento
     * Back (annulla) button pressed in form
     * Ritorna alla lista SENZA registrare
     */
    @Override
    public void annulla() {
        view.closeFormWindow();
        this.presentaLista();
    }// end of method


    /**
     * Evento
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    @Override
    public void revert() {
        view.revertEntity();
        view.enableRevert(false);
        view.enableRegistra(false);
    }// end of method


    /**
     * Evento 'save' (registra) button pressed in form
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * Registra le modifiche nel DB, tramite il service
     * Ritorna alla lista
     */
    public void registra() {
        registraModifiche();
        this.presentaLista();
    }// end of method

    @Override
    public void fieldModificato() {
        view.enableRevert(true);
        view.enableRegistra(true);
    }// end of method

    /**
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * Registra le modifiche nel DB, tramite il service
     */
    private AlgosEntity registraModifiche() {
        return service.save(view.commit());
    }// end of method


    /**
     * Modificata la selezione della Grid
     * Controlla nella Grid quanti sono i records selezionati
     * Abilita e disabilita i bottoni Modifica e Elimina della List
     */
    @Override
    public void selectionChanged() {
        boolean unaSolaRigaSelezionata = false;
        int numRigheSelezionate = 0;

        if (view == null) {
            return;
        }// end of if cycle

        //--il bottone Edit viene abilitato se c'è UNA SOLA riga selezionata
        unaSolaRigaSelezionata = view.isUnaRigaSelezionata();
        view.enableEdit(unaSolaRigaSelezionata);

        //--il bottone Delete viene abilitato in funzione della modalità di selezione adottata
        switch (LibParams.gridSelectionMode()) {
            //--nella selezione singola, il bottone Delete viene abilitato se c'è UNA SOLA riga selezionata
            case SINGLE:
                view.enableDelete(unaSolaRigaSelezionata);
                break;
            //--nella selezione multipla, il bottone Delete viene abilitato se c'è UNA O PIU righe selezionate
            case MULTI:
                numRigheSelezionate = view.numRigheSelezionate();
                if (numRigheSelezionate >= 1) {
                    view.enableDelete(true);
                } else {
                    view.enableDelete(false);
                }// end of if/else cycle
                break;
            case NONE:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

    }// end of method


    public AlgosEntity getModel() {
        return null;
    }// end of method

    public void setView(AlgosView view) {
        this.view = view;
    }

    public AlgosView getView() {
        return view;
    }
}// end of class
