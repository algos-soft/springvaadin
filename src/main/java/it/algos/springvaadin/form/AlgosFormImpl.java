package it.algos.springvaadin.form;

import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.AToolbarImpl;
import it.algos.springvaadin.toolbar.LinkToolbar;
import it.algos.springvaadin.view.ViewField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 10/07/17
 * Implementazione standard dell'annotation AlgosField
 */
@Slf4j
public class AlgosFormImpl extends VerticalLayout implements AlgosForm {

    @Autowired
    private ViewField viewField;

    //--eventuale finestra (in alternativa alla presentazione a tutto schermo)
    protected Window window;

    //--flag
//    private boolean usaSeparateFormDialog;

    //--L'entityBean viene inserita come parametro nel metodo restart, chiamato dal presenter
    protected AEntity entityBean;

    //--collegamento tra i fields e la entityBean
    private Binder binder;

    protected List<AField> fieldList;

    //--intestazioni informative per Form
    //--valori standard
    private final static String CAPTION_CREATE = "Nuova scheda";
    private final static String CAPTION_EDIT = "Modifica scheda";


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    protected AToolbar toolbar;
    protected AToolbar toolbarNormale;
    private AToolbar toolbarLink;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbarNormale iniettata da Spring
     * @param toolbarLink    iniettata da Spring
     */
    public AlgosFormImpl(@Qualifier(Cost.BAR_FORM) AToolbar toolbarNormale,
                         @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        this.toolbarNormale = toolbarNormale;
        this.toolbarLink = toolbarLink;
    }// end of Spring constructor


//    /**
//     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
//     * (si può usare qualsiasi firma)
//     * Regola il modello-dati specifico nel Service
//     */
//    @PostConstruct
//    protected void inizia() {
////        usaSeparateFormDialog = LibParams.usaSeparateFormDialog();
//    }// end of method


    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param source                presenter di riferimento da cui vengono generati gli eventi
     * @param entityBean            istanza da elaborare
     * @param fields                campi del form da visualizzare
     * @param usaSeparateFormDialog barra alternativa di bottoni per gestire il ritorno ad altro modulo
     */
    @Override
    public void restart(ApplicationListener source, AEntity entityBean, List<String> fields, boolean usaSeparateFormDialog) {
        this.entityBean = entityBean;
        toolbar = toolbarNormale;

        if (usaSeparateFormDialog) {
            usaSeparateFormDialog(source, null, null, null, fields);
        } else {
            usaAllScreen(source, fields);
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione del form di un altro modulo/collezione
     * Solo finestra popup
     *
     * @param source             presenter di riferimento per i componenti da cui vengono generati gli eventi
     * @param sourceField        di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     * @param entityBean         istanza da elaborare
     * @param fields             campi del form da visualizzare
     * @param usaBottoneRegistra utilizzo del ButtonRegistra, che registra subito
     *                           oppure ButtonAccetta, che demanda la registrazione alla scheda chiamante
     */
    public void restartLink(ApplicationListener source, ApplicationListener target, AField sourceField, AEntity entityBean, List<String> fields, boolean usaBottoneRegistra) {
        this.entityBean = entityBean;
        toolbar = toolbarLink;
        usaSeparateFormDialog(source, target, entityBean, sourceField, fields);
    }// end of method

    /**
     * Crea una finestra a se, che verrà chiusa alla dismissione del Form
     *
     * @param source      presenter di riferimento da cui vengono generati gli eventi
     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     * @param fields      del form da visualizzare
     */
    protected void usaSeparateFormDialog(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField, List<String> fields) {
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

        creaAddBindFields(source, target, layout, fields);

        layout.addComponent(new Label());
        toolbar.inizializza(source, target, entityBean, sourceField);
        fixToolbar();
        layout.addComponent((AToolbarImpl) toolbar);

        window.setContent(layout);
        window.center();
        LibVaadin.getUI().addWindow(window);
        window.bringToFront();
    }// end of method


    /**
     * Crea i campi, li aggiunge al layout, li aggiunge al binder
     *
     * @param source     presenter di riferimento da cui vengono generati gli eventi
     * @param layout     in cui inserire i campi (window o panel)
     * @param fieldsName del form da visualizzare
     */
    protected void creaAddBindFields(ApplicationListener source, ApplicationListener target, Layout layout, List<String> fieldsName) {
        creaFields(source, target, fieldsName);
        addFields(layout);
        fixFields();
        bindFields();
    }// end of method


    /**
     * Crea i campi
     *
     * @param source     presenter di riferimento da cui vengono generati gli eventi
     * @param fieldsName del form da visualizzare
     *
     * @return lista di fields
     */
    protected List<AField> creaFields(ApplicationListener source, ApplicationListener target, List<String> fieldsName) {
        List<AField> lista = new ArrayList<>();
        AField field;

        for (String publicFieldName : fieldsName) {
            field = viewField.create(source, target, entityBean.getClass(), publicFieldName);

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
     */
    private void addFields(Layout layout) {
        for (AField field : fieldList) {
            layout.addComponent(field);
        }// end of for cycle
    }// end of method


    /**
     * Eventuali regolazioni specifiche per i fields
     */
    protected void fixFields() {
    }// end of method


    /**
     * Costruisce il binder per questo Form e questa Entity
     * <p>
     * Aggiunge i campi al binder
     * Aggiunge eventuali validatori
     * Aggiunge eventuali convertitori
     * Aggiunge eventuali validatori (successivamente ai convertitori)
     * Legge la entity, inserendo i valori nei campi grafici
     */
    private void bindFields() {
        binder = new Binder(entityBean.getClass());
        String publicFieldName;

        for (AField field : fieldList) {
            publicFieldName = field.getName();

            Binder.BindingBuilder builder = binder.forField(field);
            for (AbstractValidator validator : LibField.creaValidatorsPre(entityBean, publicFieldName)) {
                builder = builder.withValidator(validator);
            }// end of for cycle
            for (Converter converter : LibField.creaConverters(entityBean, publicFieldName)) {
                builder = builder.withConverter(converter);
            }// end of for cycle
            for (AbstractValidator validator : LibField.creaValidatorsPost(entityBean, publicFieldName)) {
                builder = builder.withValidator(validator);
            }// end of for cycle
            builder.bind(publicFieldName);
            field.initContent();
        }// end of for cycle

        binder.readBean(entityBean);
    }// end of method


    /**
     * Recupera il filed dal nome
     */
    protected AField getField(String publicFieldName) {

        for (AField field : fieldList) {
            if (field.getName().equals(publicFieldName)) {
                return field;
            }// end of if cycle
        }// end of for cycle

        return null;
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
     * Trasferisce i valori dai campi dell'annotation alla entityBean
     * Esegue la (eventuale) validazione dei dati
     * Esegue la (eventuale) trasformazione dei dati
     *
     * @return la entityBean del Form
     */
    @Override
    public AEntity commit() {

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
     * @param source presenter di riferimento da cui vengono generati gli eventi
     * @param fields del form da visualizzare
     */
    protected void usaAllScreen(ApplicationListener source, List<String> fields) {
        String caption = "";
        Label label;
        this.removeAllComponents();

        caption = fixCaption(entityBean);
        label = new LabelRosso(caption);
        this.addComponent(label);

        creaAddBindFields(source, source, this, fields);

        this.addComponent(new Label());
        toolbar.inizializza(source);
        fixToolbar();
        this.addComponent((AToolbarImpl) toolbar);
    }// end of method


    protected void fixToolbar() {
    }// end of method


    /**
     * Label di informazione
     *
     * @param entityBean istanza da presentare
     *
     * @return la label a video
     */
    protected String fixCaption(AEntity entityBean) {
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
    public AEntity getEntityBean() {
        return entityBean;
    }// end of method

    /**
     * Abilita o disabilita lo specifico bottone della Toolbar
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param status abilitare o disabilitare
     */
    @Override
    public void enableButton(AButtonType type, boolean status) {
        toolbar.enableButton(type, status);
    }// end of method


    /**
     * Inserisce nei bottoni Registra o Accetta il Field che va notificato
     *
     * @param parentField che ha richiesto questo form
     */
    @Override
    public void setParentField(AField parentField) {
//        toolbar.setParentField(parentField);@todo rimettere
    }// end of method

    /**
     * Registra eventuali dipendenze di un field del Form
     */
    @Override
    public void saveSons() {
        for (AField field : fieldList) {
//            field.saveSon();
        }// end of for cycle
    }// end of method
//
//    public void setUsaSeparateFormDialog(boolean usaSeparateFormDialog) {
//        this.usaSeparateFormDialog = usaSeparateFormDialog;
//    }// end of method

}// end of class
