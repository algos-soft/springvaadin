package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.event.AlgosSpringEvent;
import it.algos.springvaadin.event.FieldSpringEvent;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 11:14
 */
public abstract class AField<T> extends CustomField<Object> implements Cloneable {

    private String name;
    protected AlgosEntity entityBean;
    private AlgosPresenterImpl source;

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


    @Override
    public Component initContent() {
        return null;
    }// end of method

    /**
     * Regolazioni varie DOPO aver creato l'istanza
     * L'istanza può essere creata da Spring o con clone(), ma necessita comunque di questi due parametri
     */
    public void inizia(String publicFieldName, AlgosPresenterImpl source) {
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


    @Override
    protected void doSetValue(Object o) {
    }// end of method


    @Override
    public Object getValue() {
        return null;
    }// end of method

    public void doValue(AlgosEntity entityBean) {
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


    public void setSource(AlgosPresenterImpl source) {
        this.source = source;
    }// end of method

    /**
     * Aggiunge il listener al componente base del field
     */
    protected void addListener() {
    }// end of method


    public void setEntityBean(AlgosEntity entityBean) {
        this.entityBean = entityBean;
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



    protected AField clone(String publicFieldName, AlgosPresenterImpl source) {
        AField fieldClonato = null;
        Object objClonato = null;

        try { // prova ad eseguire il codice
            objClonato = super.clone();
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        if (objClonato != null) {
            fieldClonato = (AField) objClonato;
            fieldClonato.inizia(publicFieldName, source);
        }// end of if cycle

        return fieldClonato;
    }// end of method


    /**
     * Fire event
     */
    protected void publish() {
        if (source != null) {
            applicationEventPublisher.publishEvent(new FieldSpringEvent(source));
        }// end of if cycle
    }// end of method


}// end of class

