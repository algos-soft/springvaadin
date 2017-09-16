package it.algos.springvaadin.field;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.event.TypeField;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 27-ago-2017
 * Time: 11:14
 * <p>
 * Field di un Form
 * Sequenza alla creazione:
 * AlgosPresenterImpl.edit() -> AlgosPresenterImpl.modifica()
 * AlgosViewImpl.setForm() ->
 * AlgosFormImpl.restart() -> AlgosFormImpl.creaAddBindFields() -> AlgosFormImpl.creaFields() ->
 * ViewField.create() ->
 * AFieldFactoryImpl.create() ->
 * AlgosConfiguration.Function<Class<? extends AField>, AField> FieldFactory() -> AlgosConfiguration.getField() ->
 * AField.<init> -> AField.inizia() ->
 * inizializza() -> creaContent() -> setName() -> setSource() -> addListener() -> regolaParametri() -> setWidth() -> setFocus() ->
 * AlgosFormImpl.bindFields() -> AField.initContent() -> AField.getValue() -> AField.doSetValue
 */
@SpringComponent
public abstract class AField<T> extends CustomField<Object> {


    //--Obbligatorio publicFieldName
    protected String name;


    //--Obbligatorio presenter che gestisce l'evento
    protected ApplicationListener source;


    //--Opzionale (window, dialog, presenter) a cui indirizzare l'evento
    protected ApplicationListener target;


    //--Opzionale (entityBean) in elaborazione
    protected AEntity entityBean;


    /**
     * Alcuni fields usano un bottone come componente interno
     * Property iniettata nel costruttore usato da Spring PRIMA della chiamata del browser
     * Property iniettata nel costruttore della sottoclasse concreta
     */
    protected AButton button;


    /**
     * Property iniettata da Spring
     */
    @Autowired
    protected ApplicationEventPublisher publisher;


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
     * Componente principale
     */
    public TextField textField;

    /**
     * Costruttore base senza parametri
     * Viene utilizzato dalla Funzione -> FieldFactory in AlgosConfiguration
     */
    public AField() {
    }// end of constructor


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizia() {
    }// end of method


    /**
     * Metodo invocato da parte di AFieldFactory subito dopo la creazione del field
     * Non parte dal costruttore, perché AFieldFactory usa un costruttore SENZA parametri
     *
     * @param publicFieldName nome visibile del field
     * @param source          del presenter che gestisce questo field
     * @param target          a cui indirizzare l'evento generato dal bottone
     */
    void inizializza(String publicFieldName, ApplicationListener source, ApplicationListener target) {
        this.creaContent();
        this.setName(publicFieldName);
        this.setSource(source);
        this.setTarget(target != null ? target : source);
        this.addListener();
        this.regolaParametri();
    }// end of method


    /**
     * Crea (o ricrea dopo una clonazione) il componente base
     */
    public void creaContent() {
        textField = new TextField();
    }// end of method


    @Override
    public Component initContent() {
        return textField;
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
    public void doSetValue(Object value) {
        if (textField != null) {
            textField.setValue((String) value);
        }// end of if cycle
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


    public AlgosPresenterImpl getFormPresenter() {
        return null;
    }// end of method


    public void setWidth(String width) {
        if (textField != null) {
            textField.setWidth(width);
        }// end of if cycle
    }// end of method


    public void setFocus(boolean focus) {
        if (textField != null && focus) {
            textField.focus();
        }// end of if cycle
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
     * Aggiunge il listener al field
     */
    protected void addListener() {
        if (textField != null) {
            textField.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
                @Override
                public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                    publish();
                }// end of inner method
            });// end of anonymous inner class
        }// end of if cycle
    }// end of method


    public void setEntityBean(AEntity entityBean) {
        this.entityBean = entityBean;
    }// end of method

    public void setButton(AButton button) {
        this.button = button;
    }// end of method


    /**
     * Fire event
     * source     Obbligatorio questo field
     * target     Obbligatorio (window, dialog, presenter) a cui indirizzare l'evento
     * entityBean Opzionale (entityBean) in elaborazione
     */
    void publish() {
        if (source != null) {
            publisher.publishEvent(new AFieldEvent(TypeField.valueChanged, source, target, entityBean));
        }// end of if cycle
    }// end of method


}// end of class

