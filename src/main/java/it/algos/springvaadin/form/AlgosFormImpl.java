package it.algos.springvaadin.form;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.FormToolbar;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gac on 10/07/17
 * .
 */
public class AlgosFormImpl extends VerticalLayout implements AlgosForm {

    private Window window;
    private Label label;
    protected AlgosEntity entity;

    //--eventuali intestazioni informative per List e Form
    //--valori standard che possono essere sovrascritti nella classi specifiche
    protected String captionCreate;
    protected String captionEdit;
    private final static String CAPTION_CREATE_DEFAULT = "Nuova scheda";
    private final static String CAPTION_EDIT_DEFAULT = "Modifica scheda";

    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    private FormToolbar toolbar;

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    @Autowired //@todo in realtà funziona anche senza @Autowired. Non capisco :-(
    public AlgosFormImpl(FormToolbar toolbar) {
        this.toolbar = toolbar;
    }// end of Spring constructor

    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param entity
     * @param fields
     */
    @Override
    public void restart(AlgosEntity entity, List<String> fields) {
        this.entity = entity;

        if (LibParams.usaSeparateFormDialog()) {
            usaSeparateFormDialog(fields);
        } else {
            usaAllScreen(fields);
        }// end of if/else cycle
    }// end of method


    private void usaSeparateFormDialog(List<String> fields) {
        String caption = "";
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

        caption = fixCaption(entity);
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


    public void creaFields(Layout layout, List<String> fields) {
    }// end of method


    private void usaAllScreen(List<String> fields) {
        String caption = "";
        this.removeAllComponents();

        caption = fixCaption(entity);
        label = new Label(LibText.setRossoBold(caption), ContentMode.HTML);
        this.addComponent(label);

        creaFields(this, fields);

        this.addComponent(new Label());
        toolbar.inizia();
        this.addComponent(toolbar);
    }// end of method


    protected String fixCaption(AlgosEntity entity) {
        String caption = "";

        if (entity != null && entity.getId() != null && entity.getId() > 0) {
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

    @Override
    public Component getComponent() {
        return this;
    }// end of method

    /**
     * Restituisce la entity utilizzata
     */
    @Override
    public AlgosEntity getEntity() {
        return entity;
    }// end of method

}// end of class
