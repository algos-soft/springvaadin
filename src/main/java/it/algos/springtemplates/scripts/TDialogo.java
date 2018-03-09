package it.algos.springtemplates.scripts;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springtemplates.enumeration.Chiave;
import it.algos.springtemplates.enumeration.Progetto;
import it.algos.springvaadin.button.AButton;
import it.algos.springvaadin.button.AButtonFactory;
import it.algos.springvaadin.component.AHorizontalLayout;
import it.algos.springvaadin.enumeration.EATypeButton;
import it.algos.springvaadin.label.ALabelRosso;
import it.algos.springvaadin.label.ALabelVerdeBold;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 07-mar-2018
 * Time: 07:47
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class TDialogo extends Window implements ApplicationListener {

    private static Progetto PROGETTO_STANDARD_SUGGERITO = Progetto.templates;
    private static String NOME_PACKAGE_STANDARD_SUGGERITO = "prova";
    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
//    @Autowired
    public ATextService text;

    private static String CAPTION_A = "Creazione di un nuovo package";
    private static String CAPTION_B = "Modifica di un package esistente";

    private TRecipient recipient;

    private ComboBox fieldComboProgetti;
    private TextField fieldTextPackage;
    private TextField fieldTextEntity; // suggerito
    private TextField fieldTextTag; // suggerito
    private CheckBox fieldCheckBoxCompany;
    private CheckBox fieldCheckBoxSovrascrive;

    /**
     * Factory per la creazione dei bottoni
     */
    private AButtonFactory buttonFactory;

    private final Window winDialog = this;
    private VerticalLayout layoutRoot;
    private VerticalLayout layoutCaption;
    private VerticalLayout layoutBody;
    private AHorizontalLayout layoutFooter;
    private AButton buttonAnnulla;
    private AButton buttonAccetta;

    private Map<Chiave, Object> mappaInput = new HashMap<>();

    public TDialogo(AButtonFactory buttonFactory, ATextService text) {
        this.buttonFactory = buttonFactory;
        this.text = text;
    }// end of constructor

    public void start(TRecipient recipient) {
        this.start("", "", recipient);
    }// end of method

    public void start(String message, String promptPackage, TRecipient recipient) {
        this.recipient = recipient;

        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);
        this.setWidth("22em");
        this.setHeight("34em");

        layoutRoot = new VerticalLayout();
        layoutRoot.setMargin(true);
        layoutRoot.setSpacing(true);

        layoutRoot.addComponent(creaCaption(message));
        layoutRoot.addComponent(creaBody(promptPackage));
        layoutRoot.addComponent(creaFooter());

        fieldTextPackage.focus();
        fieldTextPackage.selectAll();
        this.center();
        this.setContent(layoutRoot);

        try { // prova ad eseguire il codice
            LibVaadin.getUI().addWindow(this);
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
        }// fine del blocco try-catch
        this.setVisible(true);
    }// end of method


    public VerticalLayout creaCaption(String message) {
        layoutCaption = new VerticalLayout();
        layoutCaption.setMargin(false);
        layoutCaption.setSpacing(false);

        if (message.equals("")) {
            layoutCaption.addComponent(new ALabelVerdeBold(CAPTION_A));
        } else {
            layoutCaption.addComponent(new ALabelRosso(message));
        }// end of if/else cycle

        return layoutCaption;
    }// end of method


    public VerticalLayout creaBody(String promptPackage) {
        layoutBody = new VerticalLayout();
        layoutBody.setMargin(false);
        layoutBody.setSpacing(true);
        VerticalLayout bodyLayout = new VerticalLayout();
        bodyLayout.setMargin(false);
        bodyLayout.setSpacing(true);

        bodyLayout.addComponent(new Label());
        bodyLayout.addComponent(creaCombo());
        bodyLayout.addComponent(creaPackage(promptPackage));
        bodyLayout.addComponent(creaEntity());
        bodyLayout.addComponent(creaTag());
        bodyLayout.addComponent(creaCompany());
        bodyLayout.addComponent(creaSovrascrive());

        sincronizza();

        layoutBody.addComponent(bodyLayout);
        layoutBody.setExpandRatio(bodyLayout, 1);
        return layoutBody;
    }// end of method


    public ComboBox creaCombo() {
        fieldComboProgetti = new ComboBox();
        Progetto[] progetti = Progetto.values();
        String caption = "Progetto";

        fieldComboProgetti.setEmptySelectionAllowed(false);
        fieldComboProgetti.setCaption(caption);
        fieldComboProgetti.setItems(progetti);
        fieldComboProgetti.setValue(PROGETTO_STANDARD_SUGGERITO);

        return fieldComboProgetti;
    }// end of method


    public TextField creaPackage(String promptPackage) {
        fieldTextPackage = new TextField();
        String caption = "Package";

        fieldTextPackage.setCaption(caption);
        fieldTextPackage.setValue(promptPackage.equals("") ? NOME_PACKAGE_STANDARD_SUGGERITO : promptPackage);

        // Handle changes in the value
        fieldTextPackage.addValueChangeListener(event -> sincronizza());

        return fieldTextPackage;
    }// end of method


    public TextField creaEntity() {
        fieldTextEntity = new TextField();
        fieldTextEntity.setCaption("Entity");
        return fieldTextEntity;
    }// end of method


    public TextField creaTag() {
        fieldTextTag = new TextField();
        fieldTextTag.setCaption("Tag");
        return fieldTextTag;
    }// end of method


    public CheckBox creaCompany() {
        fieldCheckBoxCompany = new CheckBox();
        fieldCheckBoxCompany.setCaption("Utilizza MultiCompany");
        return fieldCheckBoxCompany;
    }// end of method


    public CheckBox creaSovrascrive() {
        fieldCheckBoxSovrascrive = new CheckBox();
        fieldCheckBoxSovrascrive.setCaption("Sovrascrive tutti i files esistenti del package");
        return fieldCheckBoxSovrascrive;
    }// end of method


    public AHorizontalLayout creaFooter() {
        buttonAnnulla = buttonFactory.crea(EATypeButton.annulla, null, null, null, null);
        buttonAccetta = buttonFactory.crea(EATypeButton.accetta, null, null, null, null);

        layoutFooter = new AHorizontalLayout(buttonAnnulla, new Label(), buttonAccetta);
        layoutFooter.setSpacing(true);
        layoutFooter.setMargin(true);

        buttonAnnulla.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                winDialog.close();
            }// end of inner method
        });// end of anonymous inner class

        buttonAccetta.setEnabled(true);
        buttonAccetta.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                setMappa();
                recipient.gotInput(mappaInput);
                winDialog.close();
            }// end of inner method
        });// end of anonymous inner class

        return layoutFooter;
    }// end of method

    public void sincronizza() {
        String valueFromPackage = fieldTextPackage.getValue();
        String valueForEntity = text.primaMaiuscola(valueFromPackage);
        String valueForTag = "";

        if (valueFromPackage.length() < 3) {
            valueForTag = valueFromPackage;
        } else {
            valueForTag = valueFromPackage.substring(0, 3);
        }// end of if/else cycle
        valueForTag = valueForTag.toUpperCase();

        fieldTextEntity.setValue(valueForEntity);
        fieldTextTag.setValue(valueForTag);

        setMappa();
    }// end of method


    public void setMappa() {
        if (mappaInput != null) {
            mappaInput.put(Chiave.nameProject, fieldComboProgetti.getValue());
            mappaInput.put(Chiave.namePackageLower, fieldTextPackage.getValue().toLowerCase());
            mappaInput.put(Chiave.nameEntityFirstUpper, text.primaMaiuscola(fieldTextEntity.getValue()));
            mappaInput.put(Chiave.tagBreveTreChar, fieldTextTag.getValue());
            mappaInput.put(Chiave.usaCompany, fieldCheckBoxCompany.getValue());
            mappaInput.put(Chiave.usaSovrascrive, fieldCheckBoxSovrascrive.getValue());
        }// end of if cycle
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
