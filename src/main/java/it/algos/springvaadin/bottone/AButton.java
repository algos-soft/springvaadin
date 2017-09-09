package it.algos.springvaadin.bottone;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 26/7/17
 * <p>
 * Bottoni utilizzati in List e Form
 * Nel costruttore viene iniettato il riferimento al singleton ApplicationEventPublisher
 * Nel @PostConstruct vengono regolati i parametri specifici di questo bottone
 * Nella superclasse viene aggiunto il listener per il click sul bottone
 * Il click recupera il presenter attivo al momento, costruisce un evento e lo lancia
 * I bottoni sono ''prototype'', cioè ne viene generato uno per ogni xxxPresenter -> xxxView
 */
@Slf4j
public class AButton extends Button {


    final static String NORMAL_WIDTH = "8em";
    final static String ICON_WIDTH = "3em";


    /**
     * Enumeration utilizzata per 'marcare' un evento, in fase di generazione
     * Enumeration utilizzata per 'riconoscerlo' nel metodo onApplicationEvent()
     */
    protected AButtonType type;


    /**
     * Classe che gestisce gli eventi a livello Application
     */
    protected ApplicationEventPublisher publisher;


    /**
     * Source (presenter, window, dialog) dell'evento generato dal bottone
     */
    protected ApplicationListener source;


    /**
     * Target facoltativo (presenter, window, dialog) a cui indirizzare l'evento generato dal bottone
     */
    protected ApplicationListener target;


    /**
     * EntityBean (facoltativa), necessaria per alcuni bottoni
     */
    protected AEntity entityBean;


    /**
     * Window (facoltativa), necessaria per alcuni bottoni
     */
    private Window parentDialog;


    /**
     * Field (facoltativo), che che contiene il bottone (solo alcuni campi come Link, Image, ...)
     */
    AField fieldParent;


    /**
     * Costruttore base senza parametri
     * Viene utilizzato dalla Funzione -> BottoneFactory in AlgosConfiguration
     */
    public AButton() {
    }// fine del metodo costruttore base


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     * Aggiunge il listener al bottone
     */
    @PostConstruct
    private void inizia() {
        this.addListener();
    }// end of method


    /**
     * Aggiunge il listener al bottone
     * Handle the event with an anonymous class
     */
    private void addListener() {
        this.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fire(clickEvent);
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    /**
     * Metodo invocato da parte di AButtonFactory subito dopo la creazione del bottone
     * Non parte dal costruttore, perché AButtonFactory usa un costruttore SENZA parametri
     * <p>
     * Regola il 'type' di bottone
     * Inietta il publisher
     * Il bottone usa il sorce (di tipo presenter, di solito) per identificare 'dove' gestire l'evento generato
     * Regola lo style del bottone.
     * Forza a maiuscola la prima lettera del testo del bottone
     * Non si poteva fare prima perché la LibParams non è 'visibile' durante la fase iniziale gestita  da Spring
     *
     * @param type      del bottone, secondo la Enumeration AButtonType
     * @param publisher degli eventi a livello Application
     * @param source    dell'evento generato dal bottone
     */
     void inizializza(AButtonType type, ApplicationEventPublisher publisher, ApplicationListener source) {
        this.setType(type);
        this.setPublisher(publisher);
        this.setSource(source);

        fixParametri();

        if (LibParams.usaBottoniColorati()) {
            this.addStyleName(type.getStyle());
        }// end of if cycle

        if (LibParams.usaBottoniPrimaMaiuscola()) {
            this.setCaption(LibText.primaMaiuscola(getCaption()));
        }// end of if cycle
    }// end of method


    /**
     * Regola i parametri del bottone specifico (usando la Enumeration dei bottoni)
     */
    private void fixParametri() {
        super.setCaption(type.getCaption());
        super.setCaption(type.getCaption());
        super.setIcon(type.getIcon());
        super.setEnabled(type.isEnabled());
        super.setWidth(type.getWidth());
    }// end of method



    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     * L'evento viene intercettato nella classe AlgosPresenterEvents->onApplicationEvent(AEvent event)
     * Bottoni specifici possono costruire un evento con informazioni aggiuntive
     */
    protected void fire(Button.ClickEvent clickEvent) {
        AButtonEvent evento;

        if (publisher == null) {
            log.error("AButton: manca il publisher nel bottone " + type);
            return;
        }// end of if cycle

        if (source == null) {
            log.error("AButton: manca il presenter nel bottone " + type);
            return;
        }// end of if cycle

        evento = new AButtonEvent(type, source, target, entityBean, fieldParent);
        if (parentDialog != null) {
            evento.setParentDialog(parentDialog);
        }// end of if cycle
        publisher.publishEvent(evento);
    }// end of method



    private void setPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void setEntityBean(AEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method


    public void setSource(ApplicationListener source) {
        this.source = source;
    }// end of method

    public void setTarget(ApplicationListener target) {
        this.target = target;
    }// end of method


    public void setFieldParent(AField fieldParent) {
        this.fieldParent = fieldParent;
    }// end of method

    public void setType(AButtonType type) {
        this.type = type;
    }// end of method


    public AButtonType getType() {
        return type;
    }// end of method

}// end of abstract class
