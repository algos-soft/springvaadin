package it.algos.springvaadin.event;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by gac on 03/06/17.
 * Bottoni della toolbar di una Grid
 */
public enum Bottone {
    create("Nuovo", VaadinIcons.PLUS, true),
    edit("Modifica", VaadinIcons.EDIT, true),
    delete("Elimina", VaadinIcons.SCISSORS, true),
    search("Ricerca", VaadinIcons.SEARCH, false),//@todo rimettere a true
    showAll("Tutto", VaadinIcons.ALIGN_JUSTIFY, true),
    annulla("Annulla", VaadinIcons.CLOSE, false),
    accetta("Conferma", VaadinIcons.CHECK, false),
    back("Annulla", VaadinIcons.ARROW_BACKWARD, true),
    revert("Ripristina", VaadinIcons.REFRESH, false),
    registra("Registra", VaadinIcons.DATABASE, false);


    private String caption;
    private Resource icona;
    private Button bottone;
    private boolean enabled;


    Bottone(String caption, Resource icona, boolean enabled) {
        this.caption = caption;
        this.icona = icona;
        this.enabled = enabled;
        this.creaBottone();
    }// end of general constructor


    /**
     * Crea il bottone
     * Usa il VaadinIcons; deprecato l'uso dell'icona del Theme corrente
     * Aggiunge il listener
     */
    private void creaBottone() {
        bottone = new Button(caption, icona);
        bottone.setEnabled(enabled);

        // Handle the event with an anonymous class
        bottone.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fire(clickEvent);
            }// end of inner method
        });// end of anonymous inner class

    }// end of method


    /**
     * Quando costruisco il bottone non ho la minima idea di dove verrà usato
     * L'evento invece arriva dalla UI e quindi risalgo alla AlgosView che contiene il bottone
     */
    private void fire(Button.ClickEvent clickEvent) {
//        AlgosView view = null;
//        Button bottoneSelezionatoCheHaGeneratoEvento = (Button) clickEvent.getSource();
//        Component parent = null;
//        AlgosPresenter presenter;
//        AlgosUI algosUI;
//
//        view = (AlgosView) LibVaadin.getUI().getNavigator().getCurrentView();
//
//        if (bottoneSelezionatoCheHaGeneratoEvento != null) {
//            parent = bottoneSelezionatoCheHaGeneratoEvento.getParent();
//        }// end of if cycle
//
//        if (parent != null) {
//            if (parent instanceof AlgosView) {
//                view = (AlgosView) parent;
//            } else {
//                parent = parent.getParent();
//            }// end of if/else cycle
//
//            if (parent instanceof AlgosView) {
//                view = (AlgosView) parent;
//            } else {
//                parent = parent.getParent();
//            }// end of if/else cycle
//
//            if (parent instanceof AlgosView) {
//                view = (AlgosView) parent;
//            } else {
//                view = (AlgosView) LibVaadin.getUI().getNavigator().getCurrentView();
//                if (parent instanceof Window) {
//                    parent = parent.getParent();
//                    if (parent instanceof AlgosUI) {
//                        algosUI = (AlgosUI) parent;
//                        view = (AlgosView) algosUI.getNavigator().getCurrentView();
//                    }// end of if cycle
//                }// end of if cycle
//            }// end of if/else cycle
//
//        }// end of if cycle

//        if (view != null) {
//            presenter = view.getPresenter();
//        }// end of if cycle

        publish();
    }// end of method

    private void publish() {
        AlgosPresenter presenter = LibVaadin.getCurrentPresenter();

        if (presenter != null) {
            AlgosSpringEvent buttonSpringEvent = new ButtonSpringEvent(presenter, this);
            presenter.getApplicationEventPublisher().publishEvent(buttonSpringEvent);
        }// end of if cycle
    }// end of method

    public Button get() {
        return bottone;
    }// end of method

}// end of enumeration
