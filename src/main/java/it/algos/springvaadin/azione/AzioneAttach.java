package it.algos.springvaadin.azione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.Button;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;

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
//        this.addListener(null,null,null);
    }// end of method

//    /**
//     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
//     * Chiamato dalla sottoclasse
//     * Aggiunge il listener
//     */
//    void addListener(AlgosPresenter presenter, AlgosGrid grid, TipoAzione tipoAzione) {
//        grid.addAttachListener(new ClientConnector.AttachListener() {
//            @Override
//            public void attach(ClientConnector.AttachEvent attachEvent) {
//                tipoAzione.publish(presenter);
//            }// end of inner method
//        });// end of anonymous inner class
//    }// end of method


}// end of class
