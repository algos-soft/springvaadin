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
    private boolean usaSeparateFormDialog;

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
    private AToolbar toolbarLink;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar     iniettata da Spring
     * @param toolbarLink iniettata da Spring
     */
    public AlgosFormImpl(@Qualifier(Cost.BAR_FORM) AToolbar toolbar,
                         @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        this.toolbar = toolbar;
        this.toolbarLink = toolbarLink;
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
     * @param presenter      di riferimento per gli eventi
     * @param entityBean     istanza da presentare
     * @param fields         del form da visualizzare
     * @param usaToolbarLink barra alternativa di bottoni per gestire il ritorno ad altro modulo
     */
    @Override
    public void restart(AlgosPresenterImpl presenter, AEntity entityBean, List<String> fields, boolean usaToolbarLink, boolean usaBottoneRegistra) {
        this.entityBean = entityBean;

        if (usaToolbarLink) {
//            ((LinkToolbar) toolbarLink).setUsaBottoneRegistra(usaBottoneRegistra);@todo rimettere
            toolbar = toolbarLink;
        }// end of if cycle

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
        toolbar.inizializza(presenter);
//        toolbar.regolaBottoni(presenter);
        layout.addComponent((AToolbarImpl) toolbar);

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
        creaFields(presenter, fieldsName);
        addFields(layout);
        fixFields();
        bindFields();
    }// end of method


    /**
     * Crea i campi
     *
     * @param presenter  di riferimento per gli eventi
     * @param fieldsName del form da visualizzare
     *
     * @return lista di fields
     */
    protected List<AField> creaFields(AlgosPresenterImpl presenter, List<String> fieldsName) {
        List<AField> lista = new ArrayList<>();
        AField field;

        for (String publicFieldName : fieldsName) {
            field = viewField.create(presenter, entityBean.getClass(), publicFieldName);

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
        toolbar.inizializza(presenter);
//        toolbar.regolaBottoni(presenter);
        this.addComponent((AToolbarImpl) toolbar);
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
    public AEntity getEntity() {
        return entityBean;
    }// end of method


    /**
     * Abilita il bottone Annulla del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableAnnulla(boolean status) {
        toolbar.enableButton(AButtonType.annulla, status);
    }// end of method


    /**
     * Abilita il bottone Revert del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRevert(boolean status) {
        toolbar.enableButton(AButtonType.revert, status);
    }// end of method


    /**
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRegistra(boolean status) {
        toolbar.enableButton(AButtonType.registra, status);
    }// end of method

    /**
     * Abilita il bottone Accetta del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableAccetta(boolean status) {
        toolbar.enableButton(AButtonType.accetta, status);
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

    public void setUsaSeparateFormDialog(boolean usaSeparateFormDialog) {
        this.usaSeparateFormDialog = usaSeparateFormDialog;
    }// end of method

}// end of class
