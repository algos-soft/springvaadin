package it.algos.springvaadin.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.bottone.*;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.event.ActionSpringEvent;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 20-ago-2017
 * Time: 12:02
 */
@SpringComponent
public class ImageDialog extends Window implements ApplicationListener<AlgosSpringEvent> {


    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout toolBar = new VerticalLayout();

    private AlgosEntity entityBean;
    private String nameImage;

    private final Bottone buttonBack;
    private final Bottone buttonCreate;
    private final Bottone buttonDelete;
    private final Bottone buttonAccetta;

    private Edit2Dialog editDialog;
    private Image image;

    public ImageDialog(
            @Qualifier(Cost.BOT_BACK) Bottone buttonBack,
            @Qualifier(Cost.BOT_CREATE) Bottone buttonCreate,
            @Qualifier(Cost.BOT_DELETE) Bottone buttonDelete,
            @Qualifier(Cost.BOT_ACCETTA) Bottone buttonAccetta,
            Edit2Dialog editDialog) {
        this.buttonBack = buttonBack;
        this.buttonCreate = buttonCreate;
        this.buttonDelete = buttonDelete;
        this.buttonAccetta = buttonAccetta;
        this.editDialog = editDialog;
    }// end of constructor


    /**
     * Metodo invocato (automaticamente dalla annotation Spring) DOPO il costruttore
     */
    @PostConstruct
    public void inizia() {
        setModal(true);
        setClosable(true);
        setResizable(false);
        center();

        buttonAccetta.setEnabled(true);
        toolBar.addComponent(new HorizontalLayout(buttonBack, buttonCreate));
        toolBar.addComponent(new HorizontalLayout(buttonDelete, buttonAccetta));

        mainLayout.addComponent(new LabelRosso("Gestione delle immagini"));
        mainLayout.addComponent(toolBar);

        setContent(mainLayout);
    }// end of method


    public void show(AlgosEntity entityBean, AlgosPresenterImpl presenter) {
        this.entityBean = entityBean;
        this.nameImage = ((Stato) entityBean).getAlfaTre();
        resetButtons(presenter);
        resetDialog();
        UI.getCurrent().addWindow(this);
    }// end of method


    private void resetButtons(AlgosPresenterImpl presenter) {
        buttonBack.regolaBottone(this, this);
        buttonCreate.regolaBottone(this, this);
        buttonDelete.regolaBottone(this, this);
        buttonAccetta.regolaBottone(this, this);


        try { // prova ad eseguire il codice
            mainLayout.removeComponent(image);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        getImage();
        mainLayout.addComponent(image, 1);
    }// end of method


    private void resetDialog() {
        try { // prova ad eseguire il codice
            mainLayout.removeComponent(image);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        getImage();
        nameImage = "";
        mainLayout.addComponent(image, 1);
    }// end of method

    //--@todo provvisorio
    private void getImage() {
        if (LibText.isValid(nameImage)) {
            image = LibResource.getImage(nameImage.toUpperCase() + ".png");
        }// end of if cycle

        if (image != null) {
            image.setWidth("24em");
            image.setHeight("12em");
        } else {
            LibAvviso.warn("Non esiste una immagine col nome selezionato");
        }// end of if/else cycle

    }// end of method


    private void create() {
        this.editDialog.inizia(this, new Pippo());
        int a=87;
    }// end of method


//    /**
//     * Evento
//     * Apre un dialodo standard di selezioni di files
//     * Create a file chooser
//     */
//    public void chooser(AlgosEntity entityBean, Window parentDialog) {
//
////        Window win=new Window();
////        LibVaadin.getUI().addWindow(win);
//        Edit2Dialog dialog= new Edit2Dialog(new Pippo());
//
////java.awt.Component comp = new java.awt.Panel();
////        win.setContent(new java.awt.Panel());
////        JTextField firstName = new JTextField();
////        final JComponent[] inputs = new JComponent[] {
////                new JLabel("First"),
////                firstName,
////        };
////        int result = JOptionPane.showConfirmDialog(comp, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
////        if (result == JOptionPane.OK_OPTION) {
////            System.out.println("You entered " + firstName.getText() );
////        } else {
////            System.out.println("User canceled / closed the dialog, result = " + result);
////        }
//
//
////        final JFileChooser fileChooser = new JFileChooser();
////        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
////        File folder = VaadinService.getCurrent().getBaseDirectory();
////        EditDialog dialog = new EditDialog("Nome del file da caricare",null);
////        dialog.show();
////        Object obj=dialog.getField();
////        java.awt.Window win=new java.awt.Window();
////        LibVaadin.getUI().addWindow(win);
////java.awt.Panel comp = new java.awt.Panel();
////        win.setContent(comp);
//        int a=87;
////        int result = fileChooser.showOpenDialog(null);
////        int returnVal = fc.showOpenDialog(aComponent);
//    }// end of method

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AlgosSpringEvent event) {
        if (!(event instanceof ButtonSpringEvent)) {
            return;
        }// end of if cycle

        BottonType type = ((ButtonSpringEvent) event).getBottone().getType();
        if (event.getSource().getClass() == this.getClass()) {

            if (event instanceof ButtonSpringEvent) {
                switch (type) {
                    case back:
                        this.close();
                        break;
                    case create:
                        this.create();
                        break;
                    case delete:
                        break;
                    case accetta:
                        this.close();
                        break;
                    default: // caso non definito
                        break;
                } // fine del blocco switch
            }// end of if/else cycle
        }// end of if cycle
    }// end of method

    public class Pippo implements Edit2Dialog.Recipient {

        @Override
        public void gotInput(String input, Window win) {
            nameImage = input;
            resetDialog();
            win.close();
        }// end of method
    }// end of inner class

}// end of class

