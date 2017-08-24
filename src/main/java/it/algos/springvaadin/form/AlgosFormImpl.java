package it.algos.springvaadin.form;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.Converter;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.converter.AlgosConverter;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.indirizzo.IndirizzoField;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.field.AlgosComboClassField;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.field.AlgosImageField;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.AlgosToolbar;
import it.algos.springvaadin.toolbar.FormToolbar;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 10/07/17
 * Implementazione standard dell'interfaccia AlgosField
 */
public class AlgosFormImpl extends VerticalLayout implements AlgosForm {

    //--eventuale finestra (in alternativa alla presentazione a tutto schermo)
    protected Window window;

    //--flag
    private boolean usaSeparateFormDialog;

    //--L'entityBean viene inserita come parametro nel metodo restart, chiamato dal presenter
    protected AlgosEntity entityBean;

    //--collegamento tra i fields e la entityBean
    private Binder binder;

    private List<AlgosField> fieldList = new ArrayList<>();

    //--intestazioni informative per Form
    //--valori standard
    private final static String CAPTION_CREATE = "Nuova scheda";
    private final static String CAPTION_EDIT = "Modifica scheda";


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    protected FormToolbar toolbar;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public AlgosFormImpl(FormToolbar toolbar) {
        this.toolbar = toolbar;
    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     * Regola il modello-dati specifico nel Service
     */
    @PostConstruct
    protected void inizia() {
        usaSeparateFormDialog = LibParams.usaSeparateFormDialog();
    }// end of method


    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param presenter  di riferimento per gli eventi
     * @param entityBean istanza da presentare
     * @param fields     del form da visualizzare
     */
    @Override
    public void restart(AlgosPresenterImpl presenter, AlgosEntity entityBean, List<String> fields) {
        this.entityBean = entityBean;

        if (usaSeparateFormDialog) {
            usaSeparateFormDialog(presenter, fields);
        } else {
            usaAllScreen(presenter, fields);
        }// end of if/else cycle
    }// end of method


    /**
     * Crea una finestra a se, che verrà chiusa alla dismissione del Form
     *
     * @param presenter di riferimento per gli eventi
     * @param fields    del form da visualizzare
     */
    protected void usaSeparateFormDialog(AlgosPresenterImpl presenter, List<String> fields) {
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
        label = new LabelRosso(caption);
        layout.addComponent(label);
        if (AlgosApp.USE_DEBUG) {
            label.addStyleName("greenBg");
        }// fine del blocco if

        creaAddBindFields(presenter, layout, fields);

        layout.addComponent(new Label());
        toolbar.inizia();
        toolbar.regolaBottoni(presenter);
        layout.addComponent(toolbar);

        window.setContent(layout);
        window.center();
        LibVaadin.getUI().addWindow(window);
        window.bringToFront();
    }// end of method


    /**
     * Crea i campi, li aggiunge al layout, li aggiunge al binder
     *
     * @param presenter  di riferimento per gli eventi
     * @param layout     in cui inserire i campi (window o panel)
     * @param fieldsName del form da visualizzare
     */
    protected void creaAddBindFields(AlgosPresenterImpl presenter, Layout layout, List<String> fieldsName) {
        List<AlgosField> fields = creaFields(presenter, fieldsName);
        addFields(layout, fields);
        bindFields(fields);
    }// end of method


    /**
     * Crea i campi
     *
     * @param presenter  di riferimento per gli eventi
     * @param fieldsName del form da visualizzare
     *
     * @return lista di fields
     */
    protected List<AlgosField> creaFields(AlgosPresenterImpl presenter, List<String> fieldsName) {
        List<AlgosField> lista = new ArrayList<>();
        AlgosField field;

        for (String publicFieldName : fieldsName) {
            field = LibField.create(presenter, entityBean.getClass(), publicFieldName);

            if (field != null) {
                lista.add(field);
            }// end of if cycle
        }// end of for cycle

        this.fieldList = lista;
        return lista;
    }// end of method


    /**
     * Aggiunge i campi al layout
     *
     * @param layout in cui inserire i campi (window o panel)
     * @param fields del form da visualizzare
     */
    protected void addFields(Layout layout, List<AlgosField> fields) {

        for (AlgosField field : fields) {
            layout.addComponent(((AbstractField) field));
        }// end of for cycle

    }// end of method


    /**
     * Aggiunge i campi al binder
     *
     * @param fields del form da visualizzare
     */
    protected void bindFields(List<AlgosField> fields) {
        binder = new Binder(entityBean.getClass());
        List<AbstractValidator> listaValidatorPre;
        List<AlgosConverter> listaConverter;
        List<AbstractValidator> listaValidatorPost;
        Object value = null;
        String publicFieldName;

        for (AlgosField field : fields) {
            publicFieldName = field.getName();
            listaValidatorPre = LibField.creaValidatorsPre(entityBean, publicFieldName);
            listaConverter = LibField.creaConverters(entityBean, publicFieldName);
            listaValidatorPost = LibField.creaValidatorsPost(entityBean, publicFieldName);

            if (((AbstractField) field).isEnabled()) {
                Binder.BindingBuilder builder = binder.forField(((AbstractField) field));
                for (AbstractValidator validator : listaValidatorPre) {
                    builder = builder.withValidator(validator);
                }// end of for cycle
                for (Converter converter : listaConverter) {
                    builder = builder.withConverter(converter);
                }// end of for cycle
                for (AbstractValidator validator : listaValidatorPost) {
                    builder = builder.withValidator(validator);
                }// end of for cycle
                builder.bind(publicFieldName);
            } else {
                try { // prova ad eseguire il codice
                    value = LibReflection.getValue(entityBean, publicFieldName);
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
            }// end of if/else cycle
        }// end of for cycle

        binder.readBean(entityBean);

        //@todo provvisorio (come sempre)
        for (AlgosField field : fields) {
            if (field instanceof AlgosImageField) {
                ((AbstractField) field).setValue(entityBean.getId());
            }// end of if cycle
        }// end of for cycle
    }// end of method

    /**
     * Esegue il 'rollback' del Form
     * Revert (ripristina) button pressed in form
     * Usa la entityBean già presente nel form, ripristinando i valori iniziali
     * Rimane nel form SENZA registrare
     */
    @Override
    public void revert() {
//        AlgosField field = fieldList.get(3);
//        if (field instanceof AlgosComboClassField) {
//            ((AlgosComboClassField) field).setValue(((Indirizzo) entityBean).getStato());
//        }// end of if cycle

        binder.readBean(entityBean);
    }// end of method

    /**
     * Checks all current validation errors
     * Se ce ne sono, rimane nel form SENZA registrare
     *
     * @return ci sono errori in almeno una delle property della entity
     */
    @Override
    public boolean entityHasError() {
        return binder.validate().hasErrors();
    }// end of method

    /**
     * Checks if the entity has no current validation errors at all
     * Se la entity è OK, può essere registrata
     *
     * @return tutte le property della entity sono valide
     */
    @Override
    public boolean entityIsOk() {
        boolean entityValida = false;

        try { // prova ad eseguire il codice
            entityValida = binder != null && binder.validate().isOk();
        } catch (Exception unErrore) { // intercetta l'errore
            Notification.show("Accetta", "Scheda non valida", Notification.Type.WARNING_MESSAGE);
        }// fine del blocco try-catch

        return entityValida;
    }// end of method

    /**
     * All fields errors
     *
     * @return errors
     */
    @Override
    public List<ValidationResult> getEntityErrors() {
        return binder != null ? binder.validate().getValidationErrors() : new ArrayList<>();
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
        int a = 87;
        try { // prova ad eseguire il codice
            binder.writeBean(entityBean);
        } catch (Exception unErrore) { // intercetta l'errore
            int errore = 87;
        }// fine del blocco try-catch

//        AlgosField field = fieldList.get(3);
//        if (field instanceof AlgosComboClassField) {
//            Object obj= ((AlgosComboClassField) field).getValue();
//            ((Indirizzo) entityBean).setStato((Stato)obj );
////            ((AlgosComboClassField) field).setValue(((Indirizzo) entityBean).getStato());
//        }// end of if cycle

        closeWindow();

        return entityBean;
    }// end of method


    /**
     * Usa tutto lo schermo
     *
     * @param presenter di riferimento per gli eventi
     * @param fields    del form da visualizzare
     */
    protected void usaAllScreen(AlgosPresenterImpl presenter, List<String> fields) {
        String caption = "";
        Label label;
        this.removeAllComponents();

        caption = fixCaption(entityBean);
        label = new LabelRosso(caption);
        this.addComponent(label);

        creaAddBindFields(presenter, this, fields);

        this.addComponent(new Label());
        toolbar.inizia();
        toolbar.regolaBottoni(presenter);
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
        String caption = entityBean.getClass().getSimpleName() + " - ";

        if (entityBean != null && entityBean.getId() != null) {
            caption += CAPTION_EDIT;
        } else {
            caption += CAPTION_CREATE;
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

    /**
     * Abilita il bottone Accetta del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableAccetta(boolean status) {
        toolbar.enableAccetta(status);
    }// end of method

    /**
     * Registra eventuali dipendenze di un field del Form
     */
    @Override
    public void saveSons() {
        for (AlgosField field : fieldList) {
            field.saveSon();
        }// end of for cycle
    }// end of method

    public void setUsaSeparateFormDialog(boolean usaSeparateFormDialog) {
        this.usaSeparateFormDialog = usaSeparateFormDialog;
    }// end of method

}// end of class
