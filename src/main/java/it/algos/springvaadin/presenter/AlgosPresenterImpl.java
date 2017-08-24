package it.algos.springvaadin.presenter;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import it.algos.springvaadin.dialog.Edit2Dialog;
import it.algos.springvaadin.dialog.EditDialog;
import it.algos.springvaadin.dialog.ImageDialog;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.search.AlgosSearch;
import org.springframework.dao.DuplicateKeyException;
import com.vaadin.data.ValidationResult;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.dialog.ConfirmDialog;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 14/06/17.
 * Implementazione standard dell'interfaccia AlgosPresenter
 */
public abstract class AlgosPresenterImpl extends AlgosPresenterEvents {


    //--la vista specifica viene iniettata dal costruttore della sottoclasse concreta
    protected AlgosView view;


    //--il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
    protected AlgosService service;


    //--dialogo di ricerca
    protected AlgosSearch search;


    //--dialogo di gestione delle immagini
    @Autowired
    protected ImageDialog imageDialog;


    //--il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
    protected Class<? extends AlgosEntity> entityClass;


    private AlgosField parentField;


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
        this.service.setEntityClass(entityClass);
        this.view.setPresenter(this);
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


//    /**
//     * Evento
//     * Apre un dialodo standard di selezioni di files
//     * Create a file chooser
//     */
//    @Override
//    public void chooser(AlgosEntity entityBean, Window parentDialog) {
//
////        Window win=new Window();
////        LibVaadin.getUI().addWindow(win);
//        Edit2Dialog dialog= new Edit2Dialog(new Pippo());
//
////java.awt.Component comp = new java.awt.Panel();
////        win.setContent(new java.awt.Panel());
////        JTextField firstName = new JTextField();
////        final JComponent[] inputs = new JComponent[] {
////                new JLabel("First"),
////                firstName,
////        };
////        int result = JOptionPane.showConfirmDialog(comp, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
////        if (result == JOptionPane.OK_OPTION) {
////            System.out.println("You entered " + firstName.getText() );
////        } else {
////            System.out.println("User canceled / closed the dialog, result = " + result);
////        }
//
//
////        final JFileChooser fileChooser = new JFileChooser();
////        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
////        File folder = VaadinService.getCurrent().getBaseDirectory();
////        EditDialog dialog = new EditDialog("Nome del file da caricare",null);
////        dialog.show();
////        Object obj=dialog.getField();
////        java.awt.Window win=new java.awt.Window();
////        LibVaadin.getUI().addWindow(win);
////java.awt.Panel comp = new java.awt.Panel();
////        win.setContent(comp);
//int a=87;
////        int result = fileChooser.showOpenDialog(null);
////        int returnVal = fc.showOpenDialog(aComponent);
//    }// end of method


//    public  class Pippo implements Edit2Dialog.Recipient{
//
//        @Override
//        public void gotInput(String input,Window win) {
//            int a=987;
//            win.close();
//        }
//    }// end of inner class
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
    public void edit(AlgosEntity entityBean) {

        if (entityBean != null) {
            modifica(entityBean);
        } else {
            List<AlgosEntity> beanList = view.getEntityBeans();

            //patch @todo passa qui due volte (per errore) non trovato perché
            //la seconda volta il presenter è 'farlocco'
            if (beanList != null && beanList.size() == 1) {
                modifica(beanList.get(0));
            }// end of if cycle
        }// end of if/else cycle

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
    public void edit(AlgosEntity entityBean, AlgosField parentField) {
        this.parentField = parentField;

        if (entityBean != null) {
            modifica(entityBean);
        } else {
            List<AlgosEntity> beanList = view.getEntityBeans();

            //patch @todo passa qui due volte (per errore) non trovato perché
            //la seconda volta il presenter è 'farlocco'
            if (beanList != null && beanList.size() == 1) {
                modifica(beanList.get(0));
            }// end of if cycle
        }// end of if/else cycle

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
    public void editLink(AlgosEntity entityBean, AlgosField parentField) {

        if (entityBean != null) {
            modifica(entityBean);
        }// end of if cycle
    }// end of method


    /**
     * Evento
     * Edit button pressed in field Image
     */
    @Override
    public void editImage(AlgosEntity entityBean, AlgosField parentField) {

        if (imageDialog != null) {
            imageDialog.show(entityBean,this);
        }// end of if cycle

//        if (entityBean != null) {
//            modifica(entityBean);
//        }// end of if cycle
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
    public void modifica(AlgosEntity entityBean) {
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


    @Override
    public void back() {
        view.closeFormWindow();
        UI.getCurrent().removeWindow(imageDialog);
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
        if (registraModifiche()) {
            this.presentaLista();
        }// end of if cycle
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
        AlgosEntity entityBean;
        String tag = "</br>";

        if (view.entityIsOk()) {
            entityBean = view.commit();
            view.closeFormWindow();
            if (parentField != null) {
                parentField.doValue(entityBean);
                parentField.getFormPresenter().fieldModificato();
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
    public void fieldModificato() {
        view.enableRevert(true);
        view.enableRegistra(true);
        view.enableAccetta(true);
    }// end of method

    /**
     * Esegue il 'commit' nel Form, trasferendo i valori dai campi alla entityBean
     * Esegue, nel Form, eventuale validazione e trasformazione dei dati
     * Registra le modifiche nel DB, tramite il service
     */
    public boolean registraModifiche() {
        boolean entityRegistrata = false;
        AlgosEntity entityBean;
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

    public boolean saveNotDuplicated(AlgosEntity entityBean) {
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
