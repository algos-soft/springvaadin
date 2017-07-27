package it.algos.springvaadin.azione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClientConnector;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.TAG_AZ_ATTACH)
public class AzioneAttach extends Azione {


    public AzioneAttach(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.tipo = TipoAzione.attach;
    }// end of method

    /**
     * Aggiunge alla Grid il listener per la singola azione
     * Sovrascritto
     */
    @Override
    public void addListener(AlgosGrid grid) {
        grid.addAttachListener(new ClientConnector.AttachListener() {
            @Override
            public void attach(ClientConnector.AttachEvent attachEvent) {
                fire(attachEvent);
            }// end of inner method
        });// end of anonymous inner class
    }// end of constructor


}// end of class
