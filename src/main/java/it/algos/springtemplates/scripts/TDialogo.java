package it.algos.springtemplates.scripts;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springtemplates.enumeration.Progetto;
import it.algos.springvaadin.button.AButton;
import it.algos.springvaadin.button.AButtonFactory;
import it.algos.springvaadin.dialog.AEditDialog;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.lib.LibVaadin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 07-mar-2018
 * Time: 07:47
 */
@Slf4j
@SpringComponent
@Scope("prototype")
public class TDialogo extends Window implements ApplicationListener {

    private static String CAPTION_A = "Creazione di un nuovo package";
    private static String CAPTION_B = "Modifica di un package esistente";

    private TRecipient recipient;

    private ComboBox fieldComboProgetti;
    private TextField fieldTextPackage = new TextField();
    private CheckBox fieldCheckBoxCompany = new CheckBox();
    private TextField fieldTextEntity = new TextField(); // suggerito
    private TextField fieldTextTag = new TextField(); // suggerito
    private CheckBox fieldCheckBoxSovrascrive = new CheckBox();

    /**
     * Factory per la creazione dei bottoni
     */
    private AButtonFactory buttonFactory;

    private VerticalLayout layout = new VerticalLayout();
    private AButton buttonAnnulla;
    private AButton buttonAccetta;


    public TDialogo() {
    }// end of constructor


    public TDialogo(AButtonFactory buttonFactory, TRecipient recipient) {
        this(buttonFactory, "", "", recipient);
    }// end of constructor

    public TDialogo(AButtonFactory buttonFactory, String message, TRecipient recipient) {
        this(buttonFactory, message, "", recipient);
    }// end of constructor


    public TDialogo(AButtonFactory buttonFactory, String message, String promptPackage, TRecipient recipient) {
        super();
        this.buttonFactory = buttonFactory;
        inizia(message, promptPackage, recipient);
    }// end of constructor


    public void inizia(String message, String promptPackage, TRecipient recipient) {
        this.recipient = recipient;
        final Window winDialog = this;

        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);
        this.setWidth("24em");
        this.setHeight("16em");

        layout.setMargin(true);
        layout.setSpacing(true);

        if (message.equals("")) {
            layout.addComponent(new Label(CAPTION_A));
            layout.addComponent(new Label(CAPTION_B));
        } else {
            layout.addComponent(new Label(message));
        }// end of if/else cycle

        buttonAnnulla = buttonFactory.crea(EATypeButton.annulla, null, null, null, null);
        buttonAccetta = buttonFactory.crea(EATypeButton.accetta, null, null, null, null);

        layout.addComponent(creaCombo());
        layout.addComponent(new HorizontalLayout(buttonAnnulla, buttonAccetta));
//        field.focus();
        this.center();
        this.setContent(layout);
        try { // prova ad eseguire il codice
            LibVaadin.getUI().addWindow(this);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch
        this.setVisible(true);
    }// end of method


    public ComboBox creaCombo() {
        fieldComboProgetti = new ComboBox();

        return fieldComboProgetti;
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
