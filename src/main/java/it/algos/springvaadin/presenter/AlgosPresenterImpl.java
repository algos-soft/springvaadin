package it.algos.springvaadin.presenter;

import com.vaadin.ui.*;
import it.algos.springvaadin.dialog.ImageDialog;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.TypeField;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.ALinkField;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.search.AlgosSearch;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;
import org.springframework.context.ApplicationListener;
import org.springframework.dao.DuplicateKeyException;
import com.vaadin.data.ValidationResult;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by gac on 14/06/17.
 * Implementazione standard dell'annotation AlgosPresenter
 */
@Slf4j
public abstract class AlgosPresenterImpl extends AlgosPresenterEvents {


    //--la vista specifica viene iniettata dal costruttore della sottoclasse concreta
    protected AlgosView view;


    //--il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
    public AlgosService service;


    //--dialogo di ricerca
    protected AlgosSearch search;


    //--dialogo di gestione delle immagini
    protected ImageDialog imageDialog;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AEntity> entityClass;


    private AField parentField;


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AlgosPresenterImpl(AlgosView view, AlgosService service, AlgosSearch search) {
        this.view = view;
        this.service = service;
        this.search = search;
    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     * Regola il modello-dati specifico nel Service
     */
    @PostConstruct
    private void inizia() {
        if (service != null) {
            service.setEntityClass(entityClass);
        }// end of if cycle

        if (view != null) {
            view.setPresenter(this);
        }// end of if cycle
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
        List items = null;
        List<String> columns = null;

        if (service != null) {
            items = service.findAll();
            columns = service.getListColumnNames();
        }// end of if cycle

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
        modifica((AEntity) null);
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
    public void edit(AEntity entityBean) {

        if (entityBean != null) {
            modifica(entityBean);
        } else {
            List<AEntity> beanList = view.getEntityBeans();

            //patch @todo passa qui due volte (per errore) non trovato perché
            //la seconda volta il presenter è 'farlocco'
            if (beanList != null && beanList.size() == 1) {
                modifica(beanList.get(0));
            }// end of if cycle
        }// end of if/else cycle

    }// end of method


    /**
     * Evento - Edit button pressed in field coming from different collection
     * <p>
     * L'evento è stato generato in un modulo diverso da questo, che era 'linkato' dal field
     * Apre un form in un dialogo separato
     * Passa a view -> form -> toolbar -> buttonRegistraLink il parentField
     * Al termine dell'elaborazione del dialogo, elabora un 'ritorno' al field di origine
     * Se la property gestita dal field è '@DBRef', registra direttamente le modifiche alla conferma (bottone Registra)
     * e passa al parentField le info necessarie a visualizzare correttamente le modifiche nel form di provenienza
     * Se la property gestita dal field NON è '@DBRef', NON registra le modifiche alla conferma (bottone Accetta)
     * ma passa le modifiche stesse al parentField che le visualizza e deciderà se registrare il tutto o meno.
     *
     * @param entityBean
     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     */
    @Override
    public void editLink(ApplicationListener source, AEntity entityBean, AField sourceField, AButtonType type) {
        List<Field> reflectFields = service.getFormFields();

//        //--inversione - cambio Collection (modulo)
//        ApplicationListener target = oldSource;
//        ApplicationListener newSource = this;

        if (entityBean == null) {
            entityBean = service.newEntity();
        }// end of if cycle
        view.setFormLink(source, this, entityBean, sourceField, reflectFields, type);
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
    public void doppioClick(AEntity entityBean) {
        modifica(entityBean);
    }// end of method


    /**
     * Modifica singolo record (entityBean)
     */
    public void modifica(AEntity entityBean) {
        modifica(entityBean, false, false, true);
    }// end of method

    /**
     * Modifica singolo record (entityBean)
     *
     * @param usaToolbarLink barra alternativa di bottoni per gestire il ritorno ad altro modulo
     */
    public void modifica(AEntity entityBean, boolean usaSeparateFormDialog, boolean usaToolbarLink, boolean usaBottoneRegistra) {
        List<Field> reflectFields = service.getFormFields();

        if (entityBean == null) {
            entityBean = service.newEntity();
        }// end of if cycle

        if (entityBean != null) {
            view.setForm(this, entityBean, reflectFields, usaSeparateFormDialog);
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
            List<AEntity> beanList = view.getEntityBeans();

            if (LibParams.chiedeConfermaPrimaDiCancellare()) {
                chiedeConfermaPrimaDiCancellare(beanList);
            } else {
                cancellazione(beanList);
            }// end of if/else cycle

            this.presentaLista();
        }// end of if cycle
    }// end of method

    @Override
    public void deleteLink(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField, AButtonType type) {
        if (LibParams.chiedeConfermaPrimaDiCancellare()) {
            chiedeConfermaPrimaDiCancellare(source, target, entityBean, sourceField, type);
        } else {
            service.delete(entityBean);
            publisher.publishEvent(new AFieldEvent(TypeField.fieldModificato, target, source, (AEntity) null, sourceField));
        }// end of if/else cycle
    }// end of method

    @Override
    public void search() {
        search.show(UI.getCurrent());
    }// end of method


    @Override
    public void importa() {
    }// end of method

    /**
     * Presenta un dialogo di conferma prima della cancellazione effettiva
     */
    public void chiedeConfermaPrimaDiCancellare(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField, AButtonType type) {
        String message = "Sei sicuro di voler eliminare il record: " + LibText.setRossoBold(entityBean + "") + " ?";
        ConfirmDialog dialog = new ConfirmDialog("Delete", message, new ConfirmDialog.Listener() {

            @Override
            public void onClose(ConfirmDialog dialog, boolean confirmed) {
                if (confirmed) {
                    service.delete(entityBean);
                    publisher.publishEvent(new AFieldEvent(TypeField.fieldModificato, target, source, (AEntity) null, sourceField));
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
     * Presenta un dialogo di conferma prima della cancellazione effettiva
     */
    public void chiedeConfermaPrimaDiCancellare(List<AEntity> beanList) {
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
    protected void cancellazione(List<AEntity> beanList) {
        boolean cancellato = false;

        if (beanList != null && beanList.size() > 0) {
            for (AEntity entityBean : beanList) {

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


    @Override
    public void back() {
        view.closeFormWindow();
        if (imageDialog != null) {
            UI.getCurrent().removeWindow(imageDialog);
        }// end of if cycle
    }// end of method

    /**
     * Evento
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    @Override
    public void revert() {
        view.revertEntity();
        view.enableButtonForm(AButtonType.revert, false);
        view.enableButtonForm(AButtonType.registra, false);
    }// end of method


    /**
     * Evento 'save' (registra) button pressed in form
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * Registra le modifiche nel DB, tramite il service
     * Ritorna alla lista
     */
    public void registra() {
        if (registraModifiche()) {
            this.presentaLista();
        }// end of if cycle
    }// end of method

    protected void registraLink(ApplicationListener target, ApplicationListener source, AField sourceField) {
        if (registraModifiche()) {
            ((ALinkField) sourceField).refreshFromDialogLinkato(view.commit());
            AEntity entityBean = view.getFormEntityBean();
            publisher.publishEvent(new AFieldEvent(TypeField.fieldModificato, source, target, entityBean, sourceField));
            LibAvviso.info("Le modifiche sono state registrate");
        }// end of if cycle
    }// end of method

    protected void registraLinkBack(ApplicationListener target, AEntity entityBean, AField sourceField) {
        AEntity entityBeanNew = null;

        if (true) {
//            if (view.entityIsOk()) {
            entityBeanNew = view.commit();
            entityBeanNew.id = "";
            view.closeFormWindow();
        }// end of if cycle

        publisher.publishEvent(new AFieldEvent(TypeField.fieldModificato, target, target, entityBeanNew, sourceField));
    }// end of method

    /**
     * Evento 'accetta' (conferma) button pressed in form
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * NON registra le modifiche nel DB
     * Ritorna alla lista
     */
    @Override
    public void accetta() {
        AEntity entityBean;
        String tag = "</br>";

        if (view.entityIsOk()) {
            entityBean = view.commit();
            view.closeFormWindow();
            if (parentField != null) {
                parentField.doValue(entityBean);
                parentField.getFormPresenter().valoreCambiato();
            }// end of if cycle
            this.presentaLista();
        } else {
            if (LibParams.usaDialoghiVerbosi()) {
                String message = "";
                for (ValidationResult errore : view.getEntityErrors()) {
                    message += tag + "* " + errore.getErrorMessage();
                }// end of for cycle
                message = LibText.levaTesta(message, tag);
                Notification nota = new Notification("Errore", message, Notification.Type.WARNING_MESSAGE, true);
                nota.show(Page.getCurrent());
            }// end of if cycle
        }// end of if/else cycle
    }// end of method

    @Override
    public void valoreCambiato() {
        view.enableButtonForm(AButtonType.revert, true);
        view.enableButtonForm(AButtonType.registra, true);
        view.enableButtonForm(AButtonType.accetta, true);
    }// end of method

    @Override
    public void fieldModificato(ApplicationListener source, AEntity entityBean, AField sourceField) {
        ((ALinkField) sourceField).refreshFromDialogLinkato(entityBean);
        this.view.enableButtonForm(AButtonType.registra, true);
    }// end of method

    /**
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * Registra le modifiche nel DB, tramite il service
     */
    public boolean registraModifiche() {
        boolean entityRegistrata = false;
        AEntity entityBean;
        String tag = "</br>";

        if (view.entityIsOk()) {
            entityBean = view.commit();
            if (saveNotDuplicated(entityBean)) {
                view.saveSons();
                entityRegistrata = true;
                view.closeFormWindow();
            }// end of if cycle
        } else {
            if (LibParams.usaDialoghiVerbosi()) {
                String message = "";
                List<ValidationResult> alfa = view.getEntityErrors();
                for (ValidationResult errore : view.getEntityErrors()) {
                    message += tag + "* " + errore.getErrorMessage();
                }// end of for cycle
                message = LibText.levaTesta(message, tag);
                Notification nota = new Notification("Errore", message, Notification.Type.WARNING_MESSAGE, true);
                nota.show(Page.getCurrent());
            }// end of if cycle
        }// end of if/else cycle

        return entityRegistrata;
    }// end of method

    public boolean saveNotDuplicated(AEntity entityBean) {
        boolean entityRegistrata = false;

        try { // prova ad eseguire il codice
            service.save(entityBean);
            entityRegistrata = true;
        } catch (Exception unErrore) { // intercetta l'errore
            if (unErrore instanceof StringIndexOutOfBoundsException) {
                LibAvviso.error(unErrore.getMessage());
            }// end of if cycle
            if (unErrore instanceof DuplicateKeyException) {
                String tagIni = "duplicate";
                String tagEnd = "nested";
                String message = unErrore.getMessage();
                message = message.substring(message.indexOf(tagIni), message.indexOf(tagEnd));
                LibAvviso.error(message);
            }// end of if cycle
        }// fine del blocco try-catch

        return entityRegistrata;
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
        view.enableButtonList(AButtonType.edit, unaSolaRigaSelezionata);

        //--il bottone Delete viene abilitato in funzione della modalità di selezione adottata
        switch (LibParams.gridSelectionMode()) {
            //--nella selezione singola, il bottone Delete viene abilitato se c'è UNA SOLA riga selezionata
            case SINGLE:
                view.enableButtonList(AButtonType.delete, unaSolaRigaSelezionata);
                break;
            //--nella selezione multipla, il bottone Delete viene abilitato se c'è UNA O PIU righe selezionate
            case MULTI:
                numRigheSelezionate = view.numRigheSelezionate();
                if (numRigheSelezionate >= 1) {
                    view.enableButtonList(AButtonType.delete, true);
                } else {
                    view.enableButtonList(AButtonType.delete, false);
                }// end of if/else cycle
                break;
            case NONE:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

    }// end of method


    public AEntity getModel() {
        return null;
    }// end of method

    public void setView(AlgosView view) {
        this.view = view;
    }

    public AlgosView getView() {
        return view;
    }
}// end of class
