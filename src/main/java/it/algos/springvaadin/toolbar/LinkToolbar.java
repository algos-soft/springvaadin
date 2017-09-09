package it.algos.springvaadin.toolbar;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonFactory;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

/**
 * Created by gac on 23/06/17
 * <p>
 * Barra di comando con bottoni, specializzata per il form chiamato da un altro modulo
 * Nel ciclo restart() di Form, la toolbar costruisce i bottoni ("prototype") usando la factory AButtonFactory
 * Viene poi iniettato il parametro obbligatorio (source)
 * Ulteriori parametri (target, entityBean), vengono iniettati direttamente solo in alcuni bottoni
 * Tutti i bottoni possono essere abilitati/disabilitati
 * I bottoni standard sono 3
 * Eventuali bottoni aggiuntivi, possono essere aggiunti nella classe specifica xxxForm.toolbarInizializza()
 * Tutti i bottoni possono essere abilitati/disabilitati
 */
@SpringComponent
@Scope("prototype")
@Qualifier(Cost.BAR_LINK)
public class LinkToolbar extends AToolbarImpl {


    /**
     * Flag per visualizzare o meno il bottone Registra al posto di Accetta - di default true
     */
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


    /**
     * Metodo invocato da restart() di Form e List
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    @Override
    public void inizializza(ApplicationListener source) {
        this.removeAllComponents();

        super.creaAddButton(AButtonType.annulla, source);
        super.creaAddButton(AButtonType.revert, source);

        if (usaBottoneRegistra) {
            super.creaAddButton(AButtonType.registra, source);
        } else {
            super.creaAddButton(AButtonType.accetta, source);
        }// end of if/else cycle
    }// end of method


    public void setUsaBottoneRegistra(boolean usaBottoneRegistra) {
        this.usaBottoneRegistra = usaBottoneRegistra;
    }// end of method


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
