package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonFactory;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Created by gac on 23/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per il form
 * Autowired nel costruttore e non nelle property
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BAR_LINK)
public class LinkToolbar extends AToolbarImpl {


    private AButton buttonAnnulla;
    private AButton buttonRevert;
    private AButton buttonLinkRegistra;
    private AButton buttonLinkAccetta;

    private boolean usaBottoneRegistra = true;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    public LinkToolbar(AButtonFactory buttonFactory) {
        super(buttonFactory);
    }// end of @Autowired constructor

//    /**
//     * Metodo invocato da Form
//     * Aggiunge i bottoni al contenitore grafico
//     */
//    public void inizia() {
//        super.addButton(buttonAnnulla);
//        super.addButton(buttonRevert);
//        if (usaBottoneRegistra) {
//            super.addButton(buttonLinkRegistra);
//        } else {
//            super.addButton(buttonLinkAccetta);
//        }// end of if/else cycle
//    }// end of method
//
//
//    public void enableAnnulla(boolean status) {
//        if (buttonAnnulla != null) {
//            buttonAnnulla.setEnabled(status);
//        }// end of if cycle
//    }// end of method
//
//    public void enableRevert(boolean status) {
//        if (buttonRevert != null) {
//            buttonRevert.setEnabled(status);
//        }// end of if cycle
//    }// end of method
//
//
//    public void enableRegistra(boolean status) {
//        if (buttonLinkRegistra != null) {
//            buttonLinkRegistra.setEnabled(status);
//        }// end of if cycle
//    }// end of method
//
//    public void enableAccetta(boolean status) {
//        if (buttonLinkAccetta != null) {
//            buttonLinkAccetta.setEnabled(status);
//        }// end of if cycle
//    }// end of method
//
//    public void setUsaBottoneRegistra(boolean usaBottoneRegistra) {
//        this.usaBottoneRegistra = usaBottoneRegistra;
//    }// end of method
//
//    /**
//     * Inserisce nei bottoni Registra o Accetta il Field che va notificato
//     *
//     * @param parentField che ha richiesto questo form
//     */
//    public void setParentField(AField parentField) {
//        if (buttonLinkRegistra != null) {
//            buttonLinkRegistra.setFieldParent(parentField);
//        }// end of if cycle
//        if (buttonLinkAccetta != null) {
//            buttonLinkAccetta.setFieldParent(parentField);
//        }// end of if cycle
//    }// end of method

}// end of class
