package it.algos.springvaadin.azione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClientConnector;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.ItemClickListener;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("prototype")
@Qualifier(Cost.TAG_AZ_CLICK)
public class AzioneClick extends Azione {


    public AzioneClick(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }// end of @Autowired constructor

    /**
     * Metodo invocato (automaticamente dalla annotation) DOPO il costruttore
     */
    @PostConstruct
    protected void inizia() {
        super.tipo = TipoAzione.click;
    }// end of method

    /**
     * Aggiunge alla Grid il listener per la singola azione
     * Sovrascritto
     */
    @Override
    @SuppressWarnings("all")
    public void addListener(AlgosGrid grid) {
        grid.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(Grid.ItemClick itemClick) {
                if (!itemClick.getMouseEventDetails().isDoubleClick()) {
                    Object obj = itemClick.getItem();
                    if (obj instanceof AlgosEntity) {
                        fire(itemClick, (AlgosEntity) obj);
                    }// end of if cycle
                }// end of if cycle
            }// end of inner method
        });// end of anonymous inner class
    }// end of constructor


}// end of class
