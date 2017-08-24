package it.algos.springvaadin.dialog;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibVaadin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import java.util.Collection;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 22-ago-2017
 * Time: 08:48
 */
@SpringComponent
@SuppressWarnings("serial")
public class Edit2Dialog extends Window {

    private static String CAPTION = "Inserimento di un valore";

    Recipient recipient;

    String message = "";

    TextField field = new TextField();
    Bottone buttonBack;
    Bottone buttonAccetta;

//    public Edit2Dialog() {
//    }// end of constructor


    public Edit2Dialog(@Qualifier(Cost.BOT_BACK) Bottone buttonBack,
                       @Qualifier(Cost.BOT_ACCETTA) Bottone buttonAccetta) {
        super();
        this.buttonBack = buttonBack;
        this.buttonAccetta = buttonAccetta;
    }// end of constructor


    public void inizia(ApplicationListener<AlgosSpringEvent> source, Recipient recipient) {
        this.recipient = recipient;
        final Window winDialog = this;
        buttonBack.regolaBottone(source);
        buttonAccetta.regolaBottone(source);
        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);
        this.setWidth("18em");
        this.setHeight("9em");
        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new LabelRosso(CAPTION));
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
            }// end of inner method
        });// end of anonymous inner class

        this.setContent(layout);
        LibVaadin.getUI().addWindow(this);
        this.setVisible(true);
    }// end of method


    public interface Recipient {
        public void gotInput(String input, Window win);
    }// end of method

}// end of class

