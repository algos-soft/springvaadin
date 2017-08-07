package it.algos.springvaadin.form;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.Converter;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.toolbar.FormToolbar;

import java.util.List;

/**
 * Created by gac on 10/07/17
 * Implementazione standard dell'interfaccia AlgosForm
 */
public class AlgosFormImpl extends VerticalLayout implements AlgosForm {

    //--eventuale finestra (in alternativa alla presentazione a tutto schermo)
    private Window window;

    //--L'entityBean viene inserita come parametro nel metodo restart, chiamato dal presenter
    protected AlgosEntity entityBean;

    //--collegamento tra i fields e la entityBean
    private Binder binder;

    //--intestazioni informative per Form
    //--valori standard
    private final static String CAPTION_CREATE = "Nuova scheda";
    private final static String CAPTION_EDIT = "Modifica scheda";


    //--toolbar coi bottoni, iniettato dal costruttore
    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    private FormToolbar toolbar;

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
    protected void usaSeparateFormDialog(List<String> fields) {
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
        List<AbstractValidator> listaValidatorPre;
        List<Converter> listaConverter;
        List<AbstractValidator> listaValidatorPost;
        Object value = null;

        for (String publicFieldName : fields) {
            field = LibField.create(entityBean.getClass(), publicFieldName);
            listaValidatorPre = LibField.creaValidatorsPre(entityBean.getClass(), publicFieldName);
            listaConverter = LibField.creaConverters(entityBean.getClass(), publicFieldName);
            listaValidatorPost = LibField.creaValidatorsPost(entityBean.getClass(), publicFieldName);

            if (field != null) {
                layout.addComponent(field);

                if (field.isEnabled()) {

                    Binder.BindingBuilder builder = binder.forField(field);
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

//                            binder.forField(field).
//                                    withValidator(lista.get(0)).
//                                    withConverter(lista.get(1)).
//                                    withValidator(lista.get(1)).
//                                    bind(publicFieldName);

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
        return binder.validate().isOk();
    }// end of method

    /**
     * All fields errors
     *
     * @return errors
     */
    @Override
    public List<ValidationResult> getEntityErrors() {
        return binder.validate().getValidationErrors();
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

}// end of class
