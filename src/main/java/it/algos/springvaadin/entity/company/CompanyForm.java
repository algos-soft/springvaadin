package it.algos.springvaadin.entity.company;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.entity.indirizzo.IndirizzoPresenter;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.field.ALinkField;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.mail.SpringEmailService;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.AToolbarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.Cost;

/**
 * Created by gac on 13/06/17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_COMP)
public class CompanyForm extends AlgosFormImpl {

    @Autowired
    private IndirizzoPresenter target;

    @Autowired
    private SpringEmailService mail;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param toolbar iniettata da Spring
     */
    public CompanyForm(@Qualifier(Cost.BAR_FORM) AToolbar toolbar,
                       @Qualifier(Cost.BAR_LINK) AToolbar toolbarLink) {
        super(toolbar, toolbarLink);
    }// end of Spring constructor


    /**
     * Eventuali regolazioni specifiche per i fields
     * <p>
     * Indirizzo è un linkField che usa DBRef e quindi il bottone deve essere BottoneRegistraLink
     */
    @Override
    protected void fixFields() {
        String fieldName = "indirizzo";
        AField field = getField(fieldName);
        AEntity entityIndirizzo = ((Company) entityBean).getIndirizzo();

        if (field != null && field instanceof ALinkField) {

            //--Se il link field non è 'DBRef', cambia il bottone da Registra ad Accetta:
            if (!LibAnnotation.isDBRef(Company.class, fieldName)) {
                ((ALinkField) field).setTypeLink(AButtonType.editLinkNoDBRef);
            }// end of if cycle

            field.setEntityBean(entityIndirizzo);
            field.setTarget(target);
        }// end of if cycle

    }// end of method

    protected void fixToolbar() {
        this.addTestButton();
    }// end of method


    /**
     * Aggiunge un bottone alla toolbar
     */
    private void addTestButton() {
        AButton button = new AButton();
        button.setIcon(VaadinIcons.MAILBOX);
        button.setCaption("Test");
        ((AToolbarImpl) toolbar).addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                esegueTest();
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    /**
     * Esegue un test di spedizione
     */
    private void esegueTest() {
        spedisceMail();
    }// end of method


    /**
     * SpedisceMail
     */
    private void spedisceMail() {
        try {
            String to = "gac@algos.it";
            String from = "pippoz";
            String subject = "Your PDF";
            String text = "Here there is your <b>PDF</b> file!";

            mail.send(from, to, subject, text);
            LibAvviso.info("Lettera spedita");
        } catch (Exception error) {
            LibAvviso.error(error.getMessage());
        }
    }// end of method

}// end of class
