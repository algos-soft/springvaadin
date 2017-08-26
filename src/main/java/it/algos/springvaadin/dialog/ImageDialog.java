package it.algos.springvaadin.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.event.ActionSpringEvent;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 20-ago-2017
 * Time: 12:02
 */
@SpringComponent
public class ImageDialog extends Window implements ApplicationListener<AlgosSpringEvent> {


    /**
     * Property iniettata nel costruttore usato da Spring PRIMA della chiamata del browser
     */
    protected ApplicationEventPublisher applicationEventPublisher;
    private AlgosPresenterImpl presenter;

    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout toolBar = new VerticalLayout();

    private AlgosEntity entityBean;

    private final Bottone buttonBack;
    private final Bottone buttonCreate;
    private final Bottone buttonDelete;
    private final Bottone buttonAccetta;

    private Edit2Dialog editDialog;
    private Image image;

    public ImageDialog(
            ApplicationEventPublisher applicationEventPublisher,
            @Qualifier(Cost.BOT_BACK) Bottone buttonBack,
            @Qualifier(Cost.BOT_CREATE) Bottone buttonCreate,
            @Qualifier(Cost.BOT_DELETE) Bottone buttonDelete,
            @Qualifier(Cost.BOT_ACCETTA) Bottone buttonAccetta,
            Edit2Dialog editDialog) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.buttonBack = buttonBack;
        this.buttonCreate = buttonCreate;
        this.buttonDelete = buttonDelete;
        this.buttonAccetta = buttonAccetta;
        this.editDialog = editDialog;
    }// end of constructor


    /**
     * Metodo invocato (automaticamente dalla annotation Spring) DOPO il costruttore
     */
    @PostConstruct
    public void inizia() {
        setModal(true);
        setClosable(true);
        setResizable(false);
        center();

        buttonAccetta.setEnabled(true);
        toolBar.addComponent(new HorizontalLayout(buttonBack, buttonCreate));
        toolBar.addComponent(new HorizontalLayout(buttonDelete, buttonAccetta));

        mainLayout.addComponent(new LabelRosso("Gestione delle immagini"));
        mainLayout.addComponent(toolBar);

        setContent(mainLayout);
    }// end of method


    public void show(AlgosEntity entityBean, AlgosPresenterImpl presenter) {
        this.entityBean = entityBean;
        this.presenter = presenter;
        resetButtons(presenter);
        resetDialog();
        UI.getCurrent().addWindow(this);
    }// end of method


    private void resetButtons(AlgosPresenterImpl presenter) {
        buttonBack.regolaBottone(this, this);
        buttonCreate.regolaBottone(this, this);
        buttonDelete.regolaBottone(this, this);
        buttonAccetta.regolaBottone(this, this);


        try { // prova ad eseguire il codice
            mainLayout.removeComponent(image);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        getImage();
        mainLayout.addComponent(image, 1);
    }// end of method


    private void resetDialog() {
        try { // prova ad eseguire il codice
            mainLayout.removeComponent(image);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        getImage();
        mainLayout.addComponent(image, 1);
    }// end of method

    private void getImage() {
            byte[] imgBytes = ((Stato)entityBean).getBandiera();
            image = LibResource.getImage(imgBytes);

        if (image != null) {
            image.setWidth("24em");
            image.setHeight("12em");
        } else {
            LibAvviso.warn("Non esiste una immagine col nome selezionato");
        }// end of if/else cycle

    }// end of method


    private void create() {
        this.editDialog.inizia(this, new Pippo());
    }// end of method


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AlgosSpringEvent event) {
        if (!(event instanceof ButtonSpringEvent)) {
            return;
        }// end of if cycle

        BottonType type = ((ButtonSpringEvent) event).getType();
        if (event.getSource().getClass() == this.getClass()) {

            if (event instanceof ButtonSpringEvent) {
                switch (type) {
                    case back:
                        this.close();
                        break;
                    case create:
                        this.create();
                        break;
                    case delete:
                        ((Stato) entityBean).setBandiera(new byte[0]);
                        resetDialog();
                        break;
                    case accetta:
                        fireRevert();
                        this.close();
                        break;
                    default: // caso non definito
                        break;
                } // fine del blocco switch
            }// end of if/else cycle
        }// end of if cycle
    }// end of method


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AlgosSpringEvent event)
     */
    private void fireRevert() {
        applicationEventPublisher.publishEvent(new ButtonSpringEvent(presenter, BottonType.revert));
    }// end of method

    public class Pippo implements Edit2Dialog.Recipient {
        @Override
        public void gotInput(String input, Window win) {
            byte[] imageBytes = LibResource.getImgBytes(input.toUpperCase() + ".png");
            ((Stato)entityBean).setBandiera(imageBytes);
            resetDialog();
            win.close();
        }// end of method
    }// end of inner class

}// end of class

