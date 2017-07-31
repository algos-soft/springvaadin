package it.algos.springvaadin.form;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.field.AlgosIntegerField;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gac on 10/07/17
 * Implementazione concreta dell'interfaccia
 */
public class AlgosFormImpl extends VerticalLayout implements AlgosForm {

    //--eventuale finestra (in alternativa alla presentazione a tutto schermo)
    private Window window;

    //--L'entityBean viene inserita come parametro nel metodo restart, chiamato dal presenter
    protected AlgosEntity entityBean;

    //--collegamento tra i fields e la entityBean
    private Binder binder;

    //--eventuali intestazioni informative per List e Form
    //--valori standard che possono essere sovrascritti nella classi specifiche
    protected String captionCreate;
    protected String captionEdit;
    private final static String CAPTION_CREATE_DEFAULT = "Nuova scheda";
    private final static String CAPTION_EDIT_DEFAULT = "Modifica scheda";


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    private FormToolbar toolbar;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Questa classe (View) è la prima del gruppo (modulo) invocata da SpringNavigator
     * Deve quindi iniettarsi il riferimento al gestore principale (xxxPresenter)
     * Nella sottoclasse concreta si usa un @Qualifier(), per avere la sottoclasse specifica
     * Nella sottoclasse concreta si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param toolbar iniettata da Spring
     */
    public AlgosFormImpl(FormToolbar toolbar) {
        this.toolbar = toolbar;
    }// end of Spring constructor


    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param entityBean istanza da presentare
     * @param fields     del form da visualizzare
     */
    @Override
    public void restart(AlgosEntity entityBean, List<String> fields) {
        this.entityBean = entityBean;

        if (LibParams.usaSeparateFormDialog()) {
            usaSeparateFormDialog(fields);
        } else {
            usaAllScreen(fields);
        }// end of if/else cycle
    }// end of method


    /**
     * Crea una finestra a se, che verrà chiusa alla dismissione del Form
     *
     * @param fields del form da visualizzare
     */
    private void usaSeparateFormDialog(List<String> fields) {
        String caption = "";
        Label label;
        this.removeAllComponents();

        if (window != null) {
            window.close();
            window = null;
        }// end of if cycle

        // create the window
        window = new Window();
        window.setResizable(true);
        window.setModal(true);
        window.setHeightUndefined();

        VerticalLayout layout = new VerticalLayout();

        caption = fixCaption(entityBean);
        label = new Label(LibText.setRossoBold(caption), ContentMode.HTML);
        layout.addComponent(label);
        if (AlgosApp.USE_DEBUG) {
            label.addStyleName("greenBg");
        }// fine del blocco if

        creaFields(layout, fields);

        layout.addComponent(new Label());
        toolbar.inizia();
        layout.addComponent(toolbar);

        window.setContent(layout);
        window.center();
        LibVaadin.getUI().addWindow(window);
        window.bringToFront();
    }// end of method


    /**
     * Crea i campi, li aggiunge al binder, li aggiunge al layout
     *
     * @param layout in cui inserire i campi (window o panel)
     * @param fields del form da visualizzare
     */
    protected void creaFields(Layout layout, List<String> fields) {
        binder = new Binder(entityBean.getClass());
        AbstractField field;
        AbstractValidator validator = null;
        Object value = null;

        for (String publicFieldName : fields) {
            field = LibField.create(entityBean.getClass(), publicFieldName);
//            validator = LibField.creaValidator(entity.getClass(), publicFieldName);

            if (field != null) {
                layout.addComponent(field);

                if (field.isEnabled()) {
                    if (validator != null) {
                        binder.forField(field).withValidator(validator).bind(publicFieldName);
                    } else {
                        binder.forField(field).bind(publicFieldName);
                    }// end of if/else cycle
                } else {
                    try { // prova ad eseguire il codice
                        value = LibReflection.getValue(entityBean, publicFieldName);
                        field.setValue(value);
                    } catch (Exception unErrore) { // intercetta l'errore

                    }// fine del blocco try-catch
                }// end of if/else cycle

            }// end of if cycle
        }// end of for cycle

        binder.readBean(entityBean);
    }// end of method


    /**
     * Esegue il 'rollback' del Form
     * Revert (ripristina) button pressed in form
     * Usa la entityBean già presente nel form, ripristinando i valori iniziali
     * Rimane nel form SENZA registrare
     */
    @Override
    public void revert() {
        binder.readBean(entityBean);
    }// end of method


    /**
     * Trasferisce i valori dai campi dell'interfaccia alla entityBean
     * Esegue la (eventuale) validazione dei dati
     * Esegue la (eventuale) trasformazione dei dati
     *
     * @return la entityBean del Form
     */
    @Override
    public AlgosEntity commit() {

        try { // prova ad eseguire il codice
            binder.writeBean(entityBean);
        } catch (Exception unErrore) { // intercetta l'errore
            int errore = 87;
        }// fine del blocco try-catch

        closeWindow();

        return entityBean;
    }// end of method


    /**
     * Usa tutto lo schermo
     *
     * @param fields del form da visualizzare
     */
    private void usaAllScreen(List<String> fields) {
        String caption = "";
        Label label;
        this.removeAllComponents();

        caption = fixCaption(entityBean);
        label = new Label(LibText.setRossoBold(caption), ContentMode.HTML);
        this.addComponent(label);

        creaFields(this, fields);

        this.addComponent(new Label());
        toolbar.inizia();
        this.addComponent(toolbar);
    }// end of method


    /**
     * Label di informazione
     *
     * @param entityBean istanza da presentare
     *
     * @return la label a video
     */
    protected String fixCaption(AlgosEntity entityBean) {
        String caption = "";

        if (entityBean != null && entityBean.getId() != null) {
            if (captionEdit == null || captionEdit.equals("")) {
                captionEdit = CAPTION_EDIT_DEFAULT;
            }// end of if cycle
            caption = captionEdit;
        } else {
            if (captionCreate == null || captionCreate.equals("")) {
                captionCreate = CAPTION_CREATE_DEFAULT;
            }// end of if cycle
            caption = captionCreate;
        }// end of if/else cycle

        return caption;
    }// end of method


    /**
     * Chiude la (eventuale) finestra utilizzata
     */
    @Override
    public void closeWindow() {
        if (window != null) {
            window.close();
            window = null;
        }// end of if cycle
    }// end of method


    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    @Override
    public Component getComponent() {
        return this;
    }// end of method


    /**
     * Restituisce la entity utilizzata
     *
     * @return la entityBean del Form
     */
    @Override
    public AlgosEntity getEntity() {
        return entityBean;
    }// end of method


    /**
     * Abilita il bottone Annulla del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableAnnulla(boolean status) {
        toolbar.enableAnnulla(status);
    }// end of method


    /**
     * Abilita il bottone Revert del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRevert(boolean status) {
        toolbar.enableRevert(status);
    }// end of method


    /**
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRegistra(boolean status) {
        toolbar.enableRegistra(status);
    }// end of method

}// end of class
