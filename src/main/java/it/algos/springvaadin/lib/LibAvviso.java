package it.algos.springvaadin.lib;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.model.AlgosEntity;
import org.springframework.dao.DuplicateKeyException;

public abstract class LibAvviso {


    public static void info(String message) {
        show(message,Notification.Type.HUMANIZED_MESSAGE);
    }// end of static method

    public static void warn(String message) {
        show(message,Notification.Type.WARNING_MESSAGE);
    }// end of static method

    public static void error(String message) {
        show(message,Notification.Type.ERROR_MESSAGE);
    }// end of static method

    private static void show(String message,Notification.Type type) {
        Notification nota = new Notification("Avviso", message, type, true);
        nota.show(Page.getCurrent());
    }// end of static method


}// end of abstract static class
