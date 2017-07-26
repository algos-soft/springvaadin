package it.algos.springvaadin.azione;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.event.ActionSpringEvent;
import it.algos.springvaadin.lib.LibVaadin;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by gac on 26/7/17
 * <p>
 * Classe astratta per le azioni generate da grid e Fields
 * Nel costruttore viene iniettato il riferimento al singleton ApplicationEventPublisher
 * Nel @PostConstruct vengono regolati i parametri specifici di questo bottone
 * Nella superclasse viene aggiunto il listener per il click sul bottone
 * Il click recupera il presenter attivo al momento, costruisce un evento e lo lancia
 */
public abstract class Azione {

    /**
     * Property iniettata nel costruttore
     */
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Enumeration utilizzata per 'marcare' una azione, in fase di generazione
     * Enumeration utilizzata per 'riconoscerla' nel metodo onApplicationEvent()
     */
    public TipoAzione tipo;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public Azione(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }// end of @Autowired constructor


    /**
     * Recupera il presenter dalla 'catena' grafica attiva
     * Costruisce e lancia l'evento che viene pubblicato dal singleton ApplicationEventPublisher
     */
    private void fire(Button.ClickEvent clickEvent) {
        AlgosPresenter presenter = LibVaadin.getCurrentPresenter();
        applicationEventPublisher.publishEvent(new ActionSpringEvent(presenter, null,null));
    }// end of method

}// end of abstract class
