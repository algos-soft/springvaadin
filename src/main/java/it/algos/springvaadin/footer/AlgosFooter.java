package it.algos.springvaadin.footer;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.lib.LibText;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 12/06/17
 * .
 */
@Lazy
@SpringComponent
public class AlgosFooter extends HorizontalLayout {

    private String appMessage = "";

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
        this.setWidth("100%");
        this.setMargin(new MarginInfo(false, false, true, false));
        this.setSpacing(true);
        this.setHeight("30px");
        String message = "Algos® ";
        String companyCode = "";
        String debugMessage = " (footerLayout)";

        message += this.appMessage;

        boolean vero=LibSession.isCompanyValida();
        Company company= LibSession.getCompany();
        companyCode = LibSession.getCompany() != null ? LibSession.getCompany().getSigla() : "";
        if (AlgosApp.USE_MULTI_COMPANY && LibText.isValid(companyCode)) {
            message += " - " + companyCode;
        }// end of if cycle

        if (AlgosApp.USE_DEBUG) {
//            this.addStyleName("redBg");
            message += debugMessage;
        }// fine del blocco if

        //--colore
//        message = "<strong style=\"color:blue;font-family:verdana;font-size:80%;\">" + message + "</strong>";
        Label label = new Label(message, ContentMode.HTML);
//        label.addStyleName("rosso");

        this.addComponent(label);
    }// end of method

    public void setAppMessage(String appMessage) {
        this.removeAllComponents();
        this.appMessage = appMessage;
        this.inizia();
    }// end of method

}// end of class
