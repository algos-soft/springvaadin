package it.algos.springvaadin.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.event.ActionSpringEvent;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
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


    private VerticalLayout mainLayout = new VerticalLayout();
    private Component detail;
    private VerticalLayout toolBar = new VerticalLayout();


    private final Bottone buttonBack;
    private final Bottone buttonCreate;
    private final Bottone buttonDelete;
    private final Bottone buttonAccetta;

    private Image image;

    public ImageDialog(
            @Qualifier(Cost.BOT_BACK) Bottone buttonBack,
            @Qualifier(Cost.BOT_CHOOSER) Bottone buttonCreate,
            @Qualifier(Cost.BOT_DELETE) Bottone buttonDelete,
            @Qualifier(Cost.BOT_ACCETTA) Bottone buttonAccetta) {
        this.buttonBack = buttonBack;
        this.buttonCreate = buttonCreate;
        this.buttonDelete = buttonDelete;
        this.buttonAccetta = buttonAccetta;
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


    public void show(AlgosPresenterImpl presenter) {
        resetButtons(presenter);
        resetDialog();
        UI.getCurrent().addWindow(this);
    }// end of method


    private void resetButtons(AlgosPresenterImpl presenter) {
        buttonBack.regolaBottone(presenter, this);
        buttonCreate.regolaBottone(presenter, this);
        buttonDelete.regolaBottone(presenter, this);
        buttonAccetta.regolaBottone(presenter, this);


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

    //--@todo provvisorio
    private void getImage() {
        image = LibResource.getImage("AUS.png");
        image.setWidth("24em");
        image.setHeight("12em");
    }// end of method

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AlgosSpringEvent event) {
        if (event.getSource().getClass() == this.getClass()) {

            if (event instanceof ButtonSpringEvent) {
int a=87;
            }// end of if/else cycle

            if (event instanceof ActionSpringEvent) {
                int a=87;
            }// end of if cycle

            if (event instanceof FieldSpringEvent) {
                int a=87;
            }// end of if cycle

        }// end of if cycle
    }// end of method
}// end of class

