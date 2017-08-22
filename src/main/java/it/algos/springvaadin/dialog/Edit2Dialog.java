package it.algos.springvaadin.dialog;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.LibVaadin;

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
        this(recipient, CAPTION);
    }// end of constructor

    public Edit2Dialog(Recipient recipient, String message) {
        super();
        this.recipient = recipient;
        this.message = message;
        inizia();
    }// end of constructor


    private void inizia() {
        final Window winDialog = this;

        this.setWidth("18em");
        this.setHeight("9em");
        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new LabelRosso(message));
        layout.addComponent(field);
        this.center();

//        VerticalLayout layout = new VerticalLayout();
//        r = recipient;
////        setCaption(question);
////        layout.setModal(true);
//        layout.setSizeUndefined();
//        LibVaadin.getUI().addWindow(winDialog);
//
        layout.addComponent(new Button("Ok", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                r.gotInput(tf.getValue(), winDialog
                );
            }
        }));
//        winDialog.setContent(layout);
//        winDialog.center();
        this.setContent(layout);
        LibVaadin.getUI().addWindow(this);
    }// end of method


    public interface Recipient {
        public void gotInput(String input, Window win);
    }
}// end of class

