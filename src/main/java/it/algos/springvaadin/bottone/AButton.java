package it.algos.springvaadin.bottone;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import it.algos.springvaadin.entity.company.CompanyForm;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.form.AlgosFormImpl;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 26/7/17
 * <p>
 * Classe astratta per i bottoni utilizzati in List e Form
 * Nel costruttore viene iniettato il riferimento al singleton ApplicationEventPublisher
 * Nel @PostConstruct vengono regolati i parametri specifici di questo bottone
 * Nella superclasse viene aggiunto il listener per il click sul bottone
 * Il click recupera il presenter attivo al momento, costruisce un evento e lo lancia
 * I bottoni sono ''prototype'', cioè ne viene generato uno per ogni xxxPresenter -> xxxView
 */
@Slf4j
public class AButton extends Button {


    public final static String NORMAL_WIDTH = "8em";
    public final static String ICON_WIDTH = "3em";


    /**
     * Property iniettata nel costruttore usato da Spring PRIMA della chiamata del browser
     */
    protected ApplicationEventPublisher publisher;


    /**
     * Property iniettata manualmente DOPO il costruttore e DOPO la chiamata del browser
     */
    protected ApplicationListener source;


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    protected ApplicationListener target;


    /**
     * Property regolata DOPO la chiamata del browser
     */
    private Window parentDialog;


    /**
     * Enumeration utilizzata per 'marcare' un evento, in fase di generazione
     * Enumeration utilizzata per 'riconoscerlo' nel metodo onApplicationEvent()
     */
    protected TypeButton type;


    /**
     * Property (facoltativa), necessaria per alcuni bottoni
     * Property regolata DOPO la chiamata del browser
     */
    protected AEntity entityBean;


    //--Opzionale (field) che che contiene il bottone (solo alcuni campi come Link, Image, ...)
    protected AField fieldParent;


    /**
     * Costruttore base senza parametri.
     * Viene utilizzato dalla Funzione -> BottoneFactory in AlgosConfiguration
     * Il publisher viene iniettato successivamente
     */
    public AButton() {
        int a = 87;
    }// fine del metodo costruttore base


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     */
    @Deprecated //@todo utilizzo la Funzione -> BottoneFactory in AlgosConfiguration
    public AButton(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }// end of @Autowired constructor


    /**
     * Metodo invocato (automaticamente dalla annotation Spring) DOPO il costruttore
     * Metodo invocato PRIMA della chiamata del browser, per i componenti gestiti da @SpringComponent
     */
    @PostConstruct
    public void inizia() {
        if (type != null) {
            this.fixParametri();
        }// end of if cycle

        this.addListener();
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
     * Aggiunge il listener al bottone
     * Handle the event with an anonymous class
     * Metodo invocato PRIMA della chiamata del browser, per i componenti gestiti da @SpringComponent
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
     * Metodo invocato alla creazione del bottone da parte di AButtonFactory
     * Il bottone usa il presenter per identificare 'dove' gestire l'evento generato
     * Regola lo style del bottone.
     * Forza a maiuscola la prima lettera del testo del bottone
     * Non si poteva fare prima perché la LibParams non è 'visibile' durante la fase iniziale gestita  da Spring
     */
    public void inizializza(ApplicationEventPublisher publisher, ApplicationListener source) {
        regolaBottone(publisher, source, null);
    }// end of method


    /**
     * Metodo invocato DOPO la chiamata del browser, da AlgosToolbar->setPresenter()
     * I bottoni vengono creati da Spring in una fase iniziale 'statica' e non sanno chi li 'userà'
     * Quando parte la UI ed il corrispondente xxxPresenter, questo viene iniettato nel bottone
     * Il bottone usa il presenter per identificare 'dove' gestire l'evento generato
     * Regola lo style del bottone.
     * Forza a maiuscola la prima lettera del testo del bottone
     * Non si poteva fare prima perché la LibParams non è 'visibile' durante la fase iniziale gestita  da Spring
     */
    public void regolaBottone(ApplicationEventPublisher publisher, ApplicationListener source, Window parentDialog) {
        this.setPublisher(publisher);
        this.setSource(source);
        this.fixParametri();

        if (parentDialog != null) {
            this.parentDialog = parentDialog;
        }// end of if cycle

        if (LibParams.usaBottoniColorati()) {
            this.addStyleName(type.getStyle());
        }// end of if cycle

        if (LibParams.usaBottoniPrimaMaiuscola()) {
            this.setCaption(LibText.primaMaiuscola(getCaption()));
        }// end of if cycle
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

    public void setPublisher(ApplicationEventPublisher publisher) {
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

    public void setType(TypeButton type) {
        this.type = type;
    }// end of method


    public TypeButton getType() {
        return type;
    }// end of method

}// end of abstract class
