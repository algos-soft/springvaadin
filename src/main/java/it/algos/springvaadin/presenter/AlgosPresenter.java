package it.algos.springvaadin.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by gac on 14/06/17.
 * <p>
 * Classe che gestisce la business logic di un 'modulo'
 * Viene creata la prima volta dalla View (lazy injected) che a sua volta viene creata dallo SpringNavigator
 * Crea (lazy injection) Modello, View (la view è scope-singleton, quindi la stessa da cui siamo arrivati) e Service
 * <p>
 * – We have some type of scope:
 * + singleton: only one instance is created (default scope)
 * + prototype: new instance is created everytime prototype bean is referenced.
 * + request: one instance for a single HTTP request.
 * + session: one instance for an HTTP Session
 * + globalSession: one instance for a global HTTP Session. Typically only valid when used in a Portlet context.
 * + application: Scopes a single bean definition to the lifecycle of a ServletContext
 * + (Only valid in the context of a web-aware Spring ApplicationContext).
 *
 * @see http://javasampleapproach.com/spring-framework/spring-bean-scope-using-annotation-singleton-prototype-request-session-global-session-application
 */
@SpringComponent
@Scope
public class AlgosPresenter extends AlgosPresenterEvents {


    //--il modello dati specifico viene iniettato nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosModel model;


    //--la vista specifica viene iniettata nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosView view;


    //--il service (dao, repository) viene iniettato nella sottoclasse concreta
    //--viene poi effettuato un casting (nella sottoclasse) per gestire la property generica
    protected AlgosService service;


    boolean newRecord = false;


    /**
     * Metodo invocato subito DOPO il costruttore
     * <p>
     * Performing the initialization in a constructor is not suggested
     * as the state of the UI is not properly set up when the constructor is invoked.
     * <p>
     * Ci possono essere diversi metodi con @PostConstruct e firme diverse e funzionano tutti,
     * ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    protected void inizia() {
    }// end of method


    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     * La partenza standard è quella di mostrare una lista
     * Recupera i dati dal DB
     * Passa i dati alla view
     */
    public void enter(AlgosView view) {
        this.view = view;
        this.presentaLista();
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
        newRecord = true;
        modifica((AlgosModel) null);
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
        newRecord = false;
        AlgosModel entityBean = this.getBean();

        //patch @todo passa qui due volte (per errore) non trovato perché
        //la seconda volta il presenter è 'farlocco'
        if (entityBean != null) {
            modifica(entityBean);
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
    public void doppioClick(AlgosModel entityBean) {
        modifica(entityBean);
    }// end of method


    /**
     * Modifica singolo record (entityBean)
     */
    protected void modifica(AlgosModel entityBean) {

        if (model != null && service != null && view != null) {
            Class<? extends AlgosModel> clazz = model.getClass();
            List<String> fields = service.getFormFields();
            if (entityBean == null) {
                view.setForm(clazz, service, fields);
            } else {
                if (entityBean.getId() > 0) {
                    entityBean = service.findById(entityBean.getId());
                }// end of if cycle
                view.setForm(entityBean, service, fields);
            }// end of if/else cycle
            view.getForm().getToolbar().getButtonRegistra().setEnabled(true);//@todo mettere false ed abilitare dopo modifiche
        }// end of if cycle

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
            List<AlgosModel> beanList = view.getList().getGrid().getBeans();

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
    public void chiedeConfermaPrimaDiCancellare(List<AlgosModel> beanList) {
        boolean cancella = false;
        String message;

        if (beanList.size() == 1) {
            message = "Sei sicuro di voler eliminare il record selezionato ?";
        } else {
            message = "Sei sicuro di voler eliminare i " + beanList.size() + " records selezionati ?";
        }// end of if/else cycle

        ConfirmDialog dialog = new ConfirmDialog("Eliminazione", message, new ConfirmDialog.Listener() {

            @Override
            public void onClose(ConfirmDialog dialog, boolean confirmed) {
                if (confirmed) {
                    cancellazione(beanList);
                    presentaLista();
                }// end of if cycle
            }// end of inner method
        });// end of anonymous inner class
        dialog.setConfirmButtonText("Elimina");
        dialog.show(LibVaadin.getUI());
    }// end of method


    /**
     * Cancella il/i record/s selezionato/i
     */
    private void cancellazione(List<AlgosModel> beanList) {
        if (beanList != null && beanList.size() > 0) {
            for (AlgosModel entityBean : beanList) {
                service.delete(entityBean);
            }// end of for cycle
        }// end of if cycle
    }// end of method


    /**
     * Evento
     * Back (annulla) button pressed in form
     * Ritorna alla lista SENZA registrare
     */
    @Override
    public void annulla() {
        if (LibParams.usaSeparateFormDialog()) {
            if (view != null) {
                view.getForm().closeWindow();
            }// end of if cycle
        }// end of if cycle

        this.presentaLista();
    }// end of method


    /**
     * Recupera i dati dal service
     * Passa i dati alla view
     */
    protected void presentaLista() {
        //patch @todo passa qui due volte (per errore) non trovato perché
        //la seconda volta il presenter è 'farlocco'
        if (model != null && service != null && view != null) {
            Class<? extends AlgosModel> clazz = model.getClass();
            List items = service.findAll();
            List<String> columns = service.getListColumns();
            view.setList(clazz, items, columns);
        }// end of if cycle
    }// end of method


    /**
     * Evento
     * Save (registra) button pressed in form
     * Registra le modifiche nel DB
     * Ritorna alla lista
     */
    public void registra() {
        registraModifiche();
        this.presentaLista();
    }// end of method


    /**
     * Recupera l'entityBean dal Form
     * Controlla se è un nuovo record oppure no
     * Registra le modifiche nel DB
     * Chiude l'eventuale finestra separata
     */
    public void registraModifiche() {
        AlgosModel bean;
        AlgosModel entityBean;

        if (view != null) {
            entityBean = view.getForm().getBean();

            if (entityBean != null && entityBean.getId() != null && entityBean.getId() > 0) {
                service.update(entityBean);
            } else {
                service.insert(entityBean);
            }// end of if/else cycle

            if (LibParams.usaSeparateFormDialog()) {
                view.getForm().closeWindow();
            }// end of if cycle
        }// end of if cycle
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

        if (view != null) {
            //--il bottone Edit viene abilitato se c'è UNA SOLA riga selezionata
            unaSolaRigaSelezionata = view.unaRigaSelezionata();
            view.getList().getToolbar().buttonEdit.setEnabled(unaSolaRigaSelezionata);

            //--il bottone Delete viene abilitato in funzione della modalità di selezione adottata
            switch (LibParams.gridSelectionMode()) {
                //--nella selezione singola, il bottone Delete viene abilitato se c'è UNA SOLA riga selezionata
                case SINGLE:
                    unaSolaRigaSelezionata = view.unaRigaSelezionata();
                    view.getList().getToolbar().buttonDelete.setEnabled(unaSolaRigaSelezionata);
                    break;
                //--nella selezione multipla, il bottone Delete viene abilitato se c'è UNA O PIU righe selezionate
                case MULTI:
                    numRigheSelezionate = view.numRigheSelezionate();
                    if (numRigheSelezionate >= 1) {
                        view.getList().getToolbar().buttonDelete.setEnabled(true);
                    } else {
                        view.getList().getToolbar().buttonDelete.setEnabled(false);
                    }// end of if/else cycle
                    break;
                case NONE:
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch

        }// end of if cycle

    }// end of method


    /**
     * Recupera il record selezionato
     */
    @SuppressWarnings("rawtypes")
    private AlgosModel getBean() {
        if (view != null) {
            return view.getList().getGrid().getBean();
        }// end of if cycle

        return null;
    }// end of method

    public AlgosModel getModel() {
        return model;
    }// end of method

}// end of class
