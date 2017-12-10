package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.label.LabelBold;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.panel.APanel;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.service.AArrayService;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import it.algos.springvaadin.toolbar.ListToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:23
 * Implementazione standard dell'interfaccia IAView
 * Questa vista 'normalmente' può essere di due tipi:
 * AList - Grid per presentare la lista sintetica di tutte le entoties
 * AForm - Per presentare una serie di fields coi valori della singola entity
 */
@Slf4j
public abstract class AView extends VerticalLayout implements IAView {


    /**
     * Service (@Scope = 'singleton'). Unica per tutta l'applicazione. Usata come libreria.
     */
    @Autowired
    protected ATextService text;


    /**
     * Service (@Scope = 'singleton'). Unica per tutta l'applicazione. Usata come libreria.
     */
    @Autowired
    protected AHtmlService htlm;


    /**
     * Service (@Scope = 'singleton'). Unica per tutta l'applicazione. Usata come libreria.
     */
    @Autowired
    protected AArrayService array;


    /**
     * Gestore principale per la 'business logic' modulo, iniettato da Spring nel costruttore
     */
    protected IAPresenter presenter;


    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * Un eventuale menuBar specifica può essere iniettata dalla sottoclasse concreta
     * Le sottoclassi possono aggiungere/modificare i menu che verranno ripristinati all'uscita della view
     * Componente grafico obbligatorio
     */
    @Autowired
    protected MenuLayout menuLayout;


    /**
     * Contenitore grafico per la caption
     * Eventuali scritte esplicative come collezione usata, records trovati, ecc
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     */
    protected VerticalLayout topLayout;

    @Autowired
    public LabelRosso labelRosso;

    /**
     * Caption sovrastante il body della view
     * Valore che può essere regolato nella classe specifica
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     */
    protected String caption;


    /**
     * Corpo centrale della view
     * Espanso. Usando un Panel si inserisce automaticamente uno scorrevole quando serve.
     * Nel Panel viene inserita una Grid oppure una lista di Fields... a seconda delle sottoclassi di tipo view
     * Componente grafico obbligatorio
     */
    @Autowired
    protected APanel bodyLayout;


    /**
     * Barra dei bottoni inferiore
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     */
    protected VerticalLayout bottomLayout;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AView(IAPresenter presenter) {
        super();
        this.presenter = presenter;
    }// end of Spring constructor


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizia() {
        //@todo RIMETTERE
//        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
        this.addStyleName("blueBg");
//        }// end of if cycle

        this.setMargin(false);
        this.setWidth("100%");
        this.setHeight("100%");
    }// end of method


    /**
     * Metodo invocato (dalla SpringNavigator di SpringBoot) ogni volta che la view diventa attiva
     * Elimina il riferimento al menuLayout nella view 'uscente' (oldView) perché il menuLayout è un 'singleton'
     * Elimina tutti i componenti della view 'entrante' (this)
     * Aggiunge il riferimento al menuLayout nella view 'entrante' (this)
     * Aggiunge il body di questa view (this)
     * Aggiunge il bottom di questa view (this)
     * Passa il controllo al Presenter (nella sottoclasse)
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        View oldView = event.getOldView();

        if (oldView instanceof IAView) {
            ((IAView) oldView).removeComponents();
        }// end of if cycle
    }// end of method


    /**
     * Metodo invocato dal Presenter (dopo che ha elaborato i dati da visualizzare)
     * Ricrea tutto ogni volta che la view diventa attiva
     * La view comprende:
     * 1) Menu: Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * 2) Top: Contenitore grafico per la caption
     * 3) Body: Corpo centrale della view. Utilizzando un Panel, si ottine l'effetto scorrevole
     * 4) Bottom - Barra dei bottoni inferiore
     *
     * @param source      di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     * @param typeButtons lista di (tipi di) bottoni visibili nella toolbar della view AList
     */
    public void start(IAPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items, List<EAButtonType> typeButtons) {
        this.removeAllComponents();
        this.addComponent(menuLayout);


        //--componente grafico obbligatorio
        menuLayout = creaMenu();
        this.addComponent(menuLayout);

        //--componente grafico obbligatorio
        menuLayout = creaMenu();
        this.addComponent(menuLayout);

        //--componente grafico facoltativo
        topLayout = creaTop(entityClazz, items);
        if (topLayout != null) {
            this.addComponent(topLayout);
        }// end of if cycle

        //--componente grafico obbligatorio
        bodyLayout = creaBody(entityClazz, columns, items);
        this.addComponent(bodyLayout);

        //--componente grafico facoltativo
        bottomLayout = creaBottom(source, typeButtons);
        if (topLayout != null) {
            this.addComponent(bottomLayout);
        }// end of if cycle

        this.setExpandRatio(bodyLayout, 1);
    }// end of method


    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     * Un eventuale menuBar specifica può essere iniettata dalla sottoclasse concreta
     * Le sottoclassi possono aggiungere/modificare i menu che verranno ripristinati all'uscita della view
     * Componente grafico obbligatorio
     *
     * @return MenuLayout
     */
    protected MenuLayout creaMenu() {
        menuLayout.start();
        return menuLayout;
    }// end of method


    /**
     * Caption sovrastante il body della view
     * Valore che può essere regolato nella classe specifica
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     */
    protected VerticalLayout creaTop(Class<? extends AEntity> entityClazz, List items) {
        VerticalLayout topLayout = null;

        //@todo RIMETTERE
        //--gestione delle scritte in rosso sopra il body
        this.fixCaption(entityClazz, items);
        if (text.isValid(caption)) {
//            Label label=new Label(caption);
//            label.setStyleName("rosso");
            labelRosso.setValue(caption);
            topLayout = new VerticalLayout();
            topLayout.setMargin(false);
            topLayout.setHeightUndefined();
            topLayout.addComponent(labelRosso);

            //@todo RIMETTERE
//            if (LibParams.usaAvvisiColorati()) {
//                topLayout.addComponent(new LabelRosso(caption));
//            } else {
//                topLayout.addComponent(new Label(caption));
//            }// end of if/else cycle
        }// end of if cycle

        return topLayout;
    }// end of method


    /**
     * Crea il corpo centrale della view
     * Componente grafico obbligatorio
     * Sovrascritto nella sottoclasse della view specifica (AList, AForm, ...)
     */
    protected APanel creaBody(Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
        return null;
    }// end of method


    /**
     * Crea la barra inferiore dei bottoni di comando
     * Chiamato ogni volta che la finestra diventa attiva
     * Componente grafico facoltativo. Normalmente presente (AList e AForm), ma non obbligatorio.
     * Sovrascritto nella sottoclasse della view specifica (AList, AForm, ...)
     */
    protected VerticalLayout creaBottom(IAPresenter source, List<EAButtonType> typeButtons) {
        VerticalLayout bottomLayout = new VerticalLayout();
        bottomLayout.setMargin(false);
        bottomLayout.setHeightUndefined();
//        inizializzaToolbar(source, typeButtons);
//        fixToolbar();

//        if (pref.isTrue(Cost.KEY_USE_DEBUG)) {
//            this.addStyleName("rosso");
//            grid.addStyleName("verde");
//        }// fine del blocco if

//        bottomLayout.addComponent((ListToolbar) toolbar);
        return bottomLayout;
    }// end of method


    /**
     * Crea la scritta esplicativa
     * Può essere sovrascritto per un'intestazione specifica (caption) della grid
     */
    protected void fixCaption(Class<? extends AEntity> entityClazz, List items) {
        String className = entityClazz != null ? entityClazz.getSimpleName() : null;

        caption = className != null ? className + " - " : "";

        if (array.isValid(items)) {
            if (items.size() == 1) {
                caption += "Elenco di 1 sola scheda ";
            } else {
                caption += "Elenco di " + items.size() + " schede ";
            }// end of if/else cycle

            //@todo RIMETTERE
//            if (LibSession.isCompanyValida()) {
//                caption += "della company " + LibSession.getCompany().getCode();
//            } else {
//                caption += "di tutte le company ";
//            }// end of if/else cycle
        } else {
            caption += "Al momento non c'è nessuna scheda. ";
        }// end of if/else cycle
    }// end of method


    /**
     * Elimina il menuLayout dalla vista 'uscente'
     */
    @Override
    public void removeComponents() {
        this.removeComponent(menuLayout);
    }// end of method

}// end of class
