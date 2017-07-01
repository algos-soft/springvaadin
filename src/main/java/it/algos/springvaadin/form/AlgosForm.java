package it.algos.springvaadin.form;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entities.versione.Versione;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.FormToolbar;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by gac on 20/06/17
 * .
 */
public class AlgosForm extends VerticalLayout {


    @Autowired
    private FormToolbar toolbar;

    //    private Class<? extends AlgosModel> modelClass;
    private Label label;

    Window window;

    protected AlgosModel entityBean;
    protected AlgosService service;

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
    public void inizia() {
        this.setMargin(false);

        if (AlgosApp.USE_DEBUG) {
            this.addStyleName("greenBg");
        } else {
            this.addStyleName("colorebase");
        }// end of if/else cycle

    }// end of method


    public void iniziaCreate(AlgosService service, String caption, List<String> campiVisibili) {
        this.service = service;
        inizia(true, (AlgosModel) null, caption, campiVisibili);
    }// end of method


    public void iniziaEdit(AlgosModel entityBean, AlgosService service, String caption, List<String> campiVisibili) {
        this.service = service;
        inizia(false, entityBean, caption, campiVisibili);
    }// end of method


    private void inizia(boolean newRecord, AlgosModel entityBean, String caption, List<String> campiVisibili) {

        if (LibParams.usaSeparateFormDialog()) {
            usaSeparateFormDialog(newRecord, entityBean, caption, campiVisibili);
        } else {
            usaAllScreen(newRecord, entityBean, caption, campiVisibili);
        }// end of if/else cycle

    }// end of method


    private void usaSeparateFormDialog(boolean newRecord, AlgosModel entityBean, String caption, List<String> campiVisibili) {
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

        label = new Label(LibText.setRossoBold(caption), ContentMode.HTML);
        layout.addComponent(label);
        if (AlgosApp.USE_DEBUG) {
            label.addStyleName("greenBg");
        }// fine del blocco if

        creaFields(layout, newRecord, entityBean, campiVisibili);

        layout.addComponent(new Label());
        toolbar.inizia();
        layout.addComponent(toolbar);

        window.setContent(layout);
        window.center();
        LibVaadin.getUI().addWindow(window);
        window.bringToFront();
    }// end of method

    public void closeWindow() {
        window.close();
        window = null;
    }// end of method


    private void usaAllScreen(boolean newRecord, AlgosModel entityBean, String caption, List<String> campiVisibili) {
        this.removeAllComponents();

        label = new Label(LibText.setRossoBold(caption), ContentMode.HTML);
        this.addComponent(label);

        creaFields(this, newRecord, entityBean, campiVisibili);

        this.addComponent(new Label());
        toolbar.inizia();
        this.addComponent(toolbar);
    }// end of method


    public void creaFields(Layout layout, boolean newRecord, AlgosModel entityBean, List<String> campiVisibili) {
    }// end of method


    protected AbstractField getField(VerticalLayout layout, String publicFieldName) {
        AbstractField field = null;
        Component comp;
        int tot = layout.getComponentCount();

        for (int k = 0; k < tot; k++) {
            comp = layout.getComponent(k);
            if (comp instanceof AbstractField) {
                field = (AbstractField) comp;
                if (field.getCaption().toLowerCase().equals(publicFieldName.toLowerCase())) {
                    return field;
                }// end of if cycle
            }// end of if cycle
        }// end of for cycle

        return field;
    }// end of method


    protected boolean setValue(VerticalLayout layout, String publicFieldName, Object value) {
        boolean fatto = false;
        AbstractField field = this.getField(layout, publicFieldName);

        if (field != null) {

            try { // prova ad eseguire il codice
                field.setValue(value);
                fatto = true;
            } catch (Exception unErrore) { // intercetta l'errore
                System.out.println(unErrore);
            }// fine del blocco try-catch
        }// end of if cycle

        return fatto;
    }// end of method


    public FormToolbar getToolbar() {
        return toolbar;
    }// end of method

//    /**
//     * Registra le modifiche nel DB
//     */
//    public void registraModifiche() {
//
//        if (entityBean != null && entityBean.getId() != null && entityBean.getId() > 0) {
//            service.update(entityBean);
//        } else {
//            service.insert(entityBean);
//        }// end of if/else cycle
//
//    }// end of method

    /**
     * Restituisce il bean corrente nel Form
     */
    public AlgosModel getBean() {
        return entityBean;
    }// end of method

}// end of class
