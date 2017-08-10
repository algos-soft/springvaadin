package it.algos.springvaadin.bottone;

import com.vaadin.ui.Button;
import it.algos.springvaadin.event.ButtonSpringEvent;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationEventPublisher;

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
public abstract class Bottone extends Button {


    /**
     * Property iniettata nel costruttore
     */
    protected ApplicationEventPublisher applicationEventPublisher;

    /**
     * Property iniettata manualmente DOPO il costruttore
     */
    protected AlgosPresenterImpl presenter;


    /**
     * Enumeration utilizzata per 'marcare' un evento, in fase di generazione
     * Enumeration utilizzata per 'riconoscerlo' nel metodo onApplicationEvent()
     */
    public TipoBottone tipo;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public Bottone(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }// end of @Autowired constructor


    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     * Chiamato dalla sottoclasse
     * Aggiunge il listener
     */
    protected void inizia() {
        // Handle the event with an anonymous class
        this.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                fire(clickEvent);
            }// end of inner method
        });// end of anonymous inner class
    }// end of method


    /**
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    protected void fire(Button.ClickEvent clickEvent) {
        applicationEventPublisher.publishEvent(new ButtonSpringEvent(presenter, this));
    }// end of method


    public void setPresenter(AlgosPresenterImpl presenter) {
        this.presenter = presenter;
    }// end of method

}// end of abstract class
