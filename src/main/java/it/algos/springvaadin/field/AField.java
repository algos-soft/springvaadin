package it.algos.springvaadin.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.TypeField;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 11:14
 */
public abstract class AField<T> extends CustomField<Object> implements Cloneable {


    //--Obbligatorio publicFieldName
    private String name;


    //--Obbligatorio presenter che gestisce l'evento
    protected ApplicationListener source;


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    protected ApplicationListener target;


    //--Opzionale (entityBean) in elaborazione
    private AEntity entityBean;


    /**
     * Alcuni fields usano un bottone come componente interno
     * Property iniettata nel costruttore usato da Spring PRIMA della chiamata del browser
     * Property iniettata nel costruttore della sottoclasse contreta
     */
    protected Bottone button;

    /**
     * Property iniettata da Spring PRIMA della chiamata del browser
     */
    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;


    //--default che può essere sovrascritto nella sottoclasse specifica ed ulteriormente modificato da una @Annotation
    //--si applica al field
    private boolean STANDARD_ENABLED = true;

    //--default che può essere sovrascritto nella sottoclasse specifica ed ulteriormente modificato da una @Annotation
    //--si applica al field
    private boolean STANDARD_REQUIRED_VISIBLE = false;

    //--default che può essere sovrascritto nella sottoclasse specifica ed ulteriormente modificato da una @Annotation
    //--si applica al field
    private boolean STANDARD_VISIBLE = true;

    //--default utilizzato nella sottoclasse specifica ed ulteriormente modificabile da una @Annotation
    //--si applica al Component
    protected String STANDARD_SHORT_TEXT_WITH = "6em";
    protected String STANDARD_MEDIUM_TEXT_WITH = "15em";
    protected String STANDARD_LONG_TEXT_WITH = "20em";
    protected String STANDARD_INT_WITH = "6em";
    protected String STANDARD_DATE_WITH = "8em";


    /**
     * Default constructor
     */
    public AField() {
    }// end of constructor


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     *
     * @param button iniettato da Spring
     */
    public AField(Bottone button) {
        this.button = button;
    }// end of Spring constructor


    /**
     * Regolazioni varie DOPO aver creato l'istanza
     * L'istanza può essere creata da Spring o con clone(), ma necessita comunque di questi due parametri
     */
    protected void inizia(String publicFieldName, ApplicationListener source) {
        this.creaContent();
        this.setName(publicFieldName);
        this.setSource(source);
        this.addListener();
        this.regolaParametri();
    }// end of method


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
    }// end of method


    @Override
    public Component initContent() {
        return null;
    }// end of method


    /**
     * Regola i parametri base per la visualizzazione del field nel form
     * Possono essere sovrascritti nella sottoclasse specifica
     * Possono essere successivamente modificati da una @Annotation
     */
    protected void regolaParametri() {
        this.setEnabled(STANDARD_ENABLED);
        this.setRequiredIndicatorVisible(STANDARD_REQUIRED_VISIBLE);
        this.setVisible(STANDARD_VISIBLE);
        this.setWidth(STANDARD_MEDIUM_TEXT_WITH);
    }// end of method


    /**
     * Visualizza graficamente nella UI i componenti grafici (uno o più)
     * Riceve il valore dal DB Mongo, già col casting al typo previsto
     */
    @Override
    protected void doSetValue(Object o) {
    }// end of method


    /**
     * Recupera dalla UI il valore (eventualmente) selezionato
     * Alcuni fields (ad esempio quelli non enabled, ed altri) non modificano il valore
     * Elabora le (eventuali) modifiche effettuate dalla UI e restituisce un valore del typo previsto per il DB mongo
     */
    @Override
    public Object getValue() {
        return null;
    }// end of method

    public void doValue(AEntity entityBean) {
    }// end of method

    public String getName() {
        return name;
    }// end of method

    public void setName(String name) {
        this.name = name;
    }// end of method


//    public void saveSon() {
//    }// end of method

    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method


    public void setWidth(String width) {
    }// end of method

    public void setFocus(boolean focus) {
    }// end of method


    public void setSource(ApplicationListener source) {
        this.source = source;
        if (button != null) {
            if (source != null) {
                button.setSource(source);
            }// end of if cycle

            if (entityBean != null) {
                button.setEntityBean(entityBean);
            }// end of if cycle
        }// end of if cycle
    }// end of method

    public void setTarget(ApplicationListener target) {
        this.target = target;
        if (button != null) {
            if (target != null) {
                button.setTarget(target);
            }// end of if cycle

            if (entityBean != null) {
                button.setEntityBean(entityBean);
            }// end of if cycle
        }// end of if cycle
    }// end of method

    /**
     * Aggiunge il listener al componente base del field
     */
    protected void addListener() {
    }// end of method


    public void setEntityBean(AEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method

    protected void fixCombo(Object[] items, boolean nullSelectionAllowed) {
    }// end of method

    protected void subClonazione(AField oldField) {
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
    public AField clone(String publicFieldName, ApplicationListener source) {
        AField fieldClonato = null;
        Object objClonato = null;

        try { // prova ad eseguire il codice
            objClonato = super.clone();
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        if (objClonato != null) {
            fieldClonato = (AField) objClonato;
            fieldClonato.inizia(publicFieldName, source);
            fieldClonato.subClonazione(this);
        }// end of if cycle

        return fieldClonato;
    }// end of method

    public AField clone(String publicFieldName, ApplicationListener source, Object[] items, boolean nullSelectionAllowed) {
        AField objClonato = clone(publicFieldName, source);

        if (objClonato != null) {
            objClonato.fixCombo(items, nullSelectionAllowed);
        }// end of if cycle

        return objClonato;
    }// end of method


    /**
     * Fire event
     * source     Obbligatorio presenter che gestisce l'evento
     * target     Opzionale (window, dialog, presenter) a cui indirizzare l'evento
     * entityBean Opzionale (entityBean) in elaborazione
     * field      Opzionale (field) che ha generato l'evento
     */
    protected void publish() {
        if (source != null) {
            applicationEventPublisher.publishEvent(new AFieldEvent(TypeField.valueChanged, source, target, entityBean, this));
        }// end of if cycle
    }// end of method


}// end of class

