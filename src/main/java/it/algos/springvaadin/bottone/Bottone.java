package it.algos.springvaadin.bottone;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;
import java.util.Collection;

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
public abstract class Bottone extends Button implements Cloneable {


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
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public Bottone(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }// end of @Autowired constructor


    /**
     * Metodo invocato (automaticamente dalla annotation Spring) DOPO il costruttore
     * Metodo invocato PRIMA della chiamata del browser, per i componenti gestiti da @SpringComponent
     */
    @PostConstruct
    public void inizia() {
        this.fixParametri();
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
//    private void addListener() {
//        this.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent clickEvent) {
//                fire(clickEvent);
//            }// end of inner method
//        });// end of anonymous inner class
//    }// end of method
    protected void addListener() {
        prova = new Prova();
        regis = this.addClickListener(prova);
    }// end of method

    private Prova prova;
    private Registration regis;

    private class Prova implements Button.ClickListener {
        @Override
        public void buttonClick(ClickEvent clickEvent) {
            fire(clickEvent);
        }// end of method
    }// end of class

    /**
     * Metodo invocato DOPO la chiamata del browser, da AlgosToolbar->setPresenter()
     * I bottoni vengono creati da Spring in una fase iniziale 'statica' e non sanno chi li 'userà'
     * Quando parte la UI ed il corrispondente xxxPresenter, questo viene iniettato nel bottone
     * Il bottone usa il presenter per identificare 'dove' gestire l'evento generato
     * Regola lo style del bottone.
     * Forza a maiuscola la prima lettera del testo del bottone
     * Non si poteva fare prima perché la LibParams non è 'visibile' durante la fase iniziale gestita  da Spring
     */
    public void regolaBottone(ApplicationListener source) {
        regolaBottone(source, null);
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
    public void regolaBottone(ApplicationListener source, Window parentDialog) {
        this.setSource(source);

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

        if (source != null) {
            evento = new AButtonEvent(type, source, target, entityBean, fieldParent);
            if (parentDialog != null) {
                evento.setParentDialog(parentDialog);
            }// end of if cycle
            publisher.publishEvent(evento);
        } else {
            log.error("Bottone: manca il presenter nel bottone " + type);
        }// end of if/else cycle
    }// end of method


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


    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     *
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     *
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     *
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     *
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     *
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }// end of method


    /**
     * Deep clonazione
     * 1) I componenti iniettati tramite @Autowired di tipo Singletono, sono già stati clonati dal metodo standard
     * 2) I componenti iniettati di tipo 'prototype', devono essere clonati a loro volta
     * 3) I riferimenti ad altre classi, devono essere clonati a loro volta
     * 4) Tutte le regolazioni effettuate DOPO il ciclo del costruttore, devono essere eseguite sul nuovo objClonato
     */
    public Bottone clone(String pippo) {
        Bottone bottoneClonato = null;
        Object objClonato = null;

        try { // prova ad eseguire il codice
            objClonato = super.clone();
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        if (objClonato != null) {
            bottoneClonato = (Bottone) objClonato;
            bottoneClonato.inizia();

            //--@todo forse
            prova = null;
            this.setSource(null);

            //--@todo forsissimo
            regis.remove();
            //--@todo forsissimo

////            this.fireClick();
//            Object obj2=this.getParent();

//            Collection listeners = this.getListeners(Prova.class);
//            for (Object obj : listeners) {
//                this.removeListener(Button.ClickListener.class, obj);
//            }// end of for cycle
//            Collection listeners2 = this.getListeners(Prova.class);
//            int a = 87;
        }// end of if cycle

        return bottoneClonato;
    }// end of method


}// end of abstract class
