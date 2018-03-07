package it.algos.springvaadin.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.button.AButton;
import it.algos.springvaadin.button.AButtonFactory;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.field.ATextField;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.IAPresenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 22-ago-2017
 * Time: 08:48
 */
@Slf4j
@SpringComponent
@Scope("prototype")
public class AEditDialog extends Window implements ApplicationListener {

    private static String CAPTION = "Inserimento di un valore";

    Recipient recipient;

    TextField field = new TextField();

    /**
     * Factory per la creazione dei bottoni
     */
    private AButtonFactory buttonFactory;

    private AButton buttonBack;
    private AButton buttonAccetta;

    public AEditDialog() {
    }// end of constructor


    public AEditDialog(AButtonFactory buttonFactory, String message, Recipient recipient) {
        this(buttonFactory,message, "", recipient);
    }// end of constructor


    public AEditDialog(AButtonFactory buttonFactory, String message, String prompt, Recipient recipient) {
        super();
        this.buttonFactory = buttonFactory;
        inizia(message, prompt, recipient);
    }// end of constructor


    public void inizia(String message, String prompt, Recipient recipient) {
        this.recipient = recipient;
        final Window winDialog = this;

//        buttonBack = new AButton();
//        buttonBack.setCaption("Baccko");
//        buttonAccetta = new AButton();;
//        buttonAccetta.setCaption("Vabbbe");

        buttonBack = buttonFactory.crea(EATypeButton.annulla, null, null, null, null);
        buttonAccetta = buttonFactory.crea(EATypeButton.accetta, null, null, null, null);

        field.setValue(prompt);
//        buttonBack.fixCaption(null,source);
//        buttonAccetta.fixCaption(null,source);
        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);
        this.setWidth("18em");
        this.setHeight("11em");
        VerticalLayout layout = new VerticalLayout();

        if (message.equals("")) {
            layout.addComponent(new Label(CAPTION));
        } else {
            layout.addComponent(new Label(message));
        }// end of if/else cycle

        layout.addComponent(field);
        layout.addComponent(new HorizontalLayout(buttonBack, buttonAccetta));
        field.focus();
        this.center();


        buttonBack.setSource(null);
        buttonBack.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                winDialog.close();
            }// end of inner method
        });// end of anonymous inner class

        buttonAccetta.setEnabled(true);
        buttonAccetta.setSource(null);

//        Collection collezione = buttonAccetta.getListeners(Button.ClickListener.class);
//        if (collezione != null) {
//            for (Object obj : collezione) {
//                if (obj instanceof Button.ClickListener) {
//                    buttonAccetta.removeListener((Listener) obj);
//                }// end of if cycle
//            }// end of for cycle
//        }// end of if cycle
//        Collection collezione2 = buttonAccetta.getListeners(Button.ClickListener.class);
        buttonAccetta.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                recipient.gotInput(field.getValue(), winDialog);
                winDialog.close();
            }// end of inner method
        });// end of anonymous inner class

        this.setContent(layout);
        try { // prova ad eseguire il codice
            LibVaadin.getUI().addWindow(this);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch
        this.setVisible(true);
    }// end of method


    public interface Recipient {
        public void gotInput(String input, Window win);
    }// end of method

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    }// end of method

}// end of class

