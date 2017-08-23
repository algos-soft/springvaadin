package it.algos.springvaadin.dialog;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibVaadin;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mar, 22-ago-2017
 * Time: 08:48
 */
@SuppressWarnings("serial")
public class Edit2Dialog extends Window {

    private static String CAPTION = "Inserimento di un valore";

    Recipient recipient;

    String message = "";

    TextField field = new TextField();

    public Edit2Dialog(Recipient recipient) {
        this(recipient, CAPTION,null,null);
    }// end of constructor

    public Edit2Dialog(Recipient recipient, String message,
                       @Qualifier(Cost.BOT_BACK) Bottone buttonBack,
                       @Qualifier(Cost.BOT_ACCETTA) Bottone buttonAccetta) {
        super();
        this.recipient = recipient;
        this.message = message;
        inizia();
    }// end of constructor


    private void inizia() {
        final Window winDialog = this;

        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);
        this.setWidth("18em");
        this.setHeight("9em");
        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new LabelRosso(message));
        layout.addComponent(field);
        field.focus();
        this.center();

//        layout.addComponent(new Button("Ok", new Button.ClickListener() {
//            public void buttonClick(Button.ClickEvent event) {
//                recipient.gotInput(field.getValue(), winDialog);
//            }// end of inner method
//        }));// end of anonymous inner class

        this.setContent(layout);
        LibVaadin.getUI().addWindow(this);
    }// end of method


    public interface Recipient {
        public void gotInput(String input, Window win);
    }// end of method

}// end of class

