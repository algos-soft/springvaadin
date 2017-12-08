package it.algos.springvaadin.footer;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 12/06/17
 * VerticalLayout has 100% width and undefined height by default.
 * Barra inferiore di messaggi all'utente.
 * Può essere visibile o nascosta a seconda del flag booleano KEY_DISPLAY_FOOTER_INFO
 * La visibilità viene gestita da AlgosUI
 * Tipicamente dovrebbe mostrare:
 * Copyright di Algos
 * Nome dell'applicazione
 * Versione dell'applicazione
 * Livello di accesso dell'utente loggato (developer, admin, utente) eventualmente oscurato per l'utente semplice
 * Company selezionata (nel caso di applicazione multiCompany)
 */
@SpringComponent
public class AFooter extends VerticalLayout {

    private final static String DEVELOPER_NAME = "Algos® ";
    private String message = "";
    private Label label;

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
    protected void inizia() {
        this.setMargin(false);
        this.setSpacing(true);
        this.addStyleName("greenBg");
        this.fixMessage();
    }// end of method

    public void setAppMessage(String message) {
        this.message = message;
        this.fixMessage();
    }// end of method


    private void fixMessage() {
        String tag = "all companies";
        String companyCode = "";
        this.removeAllComponents();

    //@todo RIMETTERE
//        companyCode = LibSession.getCompany() != null ? LibSession.getCompany().getCode() : "";
//        if (AlgosApp.USE_MULTI_COMPANY) {
//            if (LibText.isValid(companyCode)) {
//                message += " - " + companyCode;
//            } else {
//                message += " - " + tag;
//            }// end of if/else cycle
//        }// end of if cycle

    //@todo RIMETTERE
//        if (LibSession.isDeveloper()) {
//            message += " (dev)";
//        } else {
//            if (LibSession.isAdmin()) {
//                message += " (admin)";
//            } else {
//                message += " (buttonUser)";
//            }// end of if/else cycle
//        }// end of if/else cycle
//
//        label = new LabelRosso(DEVELOPER_NAME + message);

        this.addComponent(new Label(DEVELOPER_NAME));
    }// end of method

}// end of class