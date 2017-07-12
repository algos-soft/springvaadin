package it.algos.springvaadin.event;

import com.vaadin.event.Action;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.event.selection.MultiSelectionListener;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gac on 04/06/17
 * Azioni di una Grid
 */
public enum Azione {

    attach("Aggiunge") {
        @Override
        Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
            return grid.addAttachListener(new ClientConnector.AttachListener() {
                @Override
                public void attach(ClientConnector.AttachEvent attachEvent) {
                    azione.publish(presenter);
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    click("Singolo click") {
        @Override
        Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
            return grid.addItemClickListener(new ItemClickListener() {
                @Override
                public void itemClick(Grid.ItemClick itemClick) {
                    if (!itemClick.getMouseEventDetails().isDoubleClick()) {
                        azione.publish(presenter);
                    }// end of if cycle
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    doppioClick("Doppio click") {
        @Override
        Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
            return grid.addItemClickListener(new ItemClickListener() {
                @Override
                public void itemClick(Grid.ItemClick itemClick) {
                    Object entityBean = itemClick.getItem();
                    if (itemClick.getMouseEventDetails().isDoubleClick()) {
                        azione.publish(presenter, entityBean);
                    }// end of if cycle
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    selectionChanged("Modifica") {
        @Override
        Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
            switch (LibParams.gridSelectionMode()) {
                case SINGLE:
                    SingleSelectionModel modelloSingolo = (SingleSelectionModel) grid.getSelectionModel();
                    return modelloSingolo.addSingleSelectionListener(new SingleSelectionListener() {
                        @Override
                        public void selectionChange(SingleSelectionEvent singleSelectionEvent) {
                            azione.publish(presenter);
                        }// end of inner method
                    });// end of anonymous inner class
                case MULTI:
                    MultiSelectionModel modelloMultiplo = (MultiSelectionModel) grid.getSelectionModel();
                    return modelloMultiplo.addMultiSelectionListener(new MultiSelectionListener() {
                        @Override
                        public void selectionChange(MultiSelectionEvent multiSelectionEvent) {
                            azione.publish(presenter);
                        }// end of inner method
                    });// end of anonymous inner class
                case NONE:
                    return null;
                default: // caso non definito
                    return null;
            } // fine del blocco switch
        }// end of method
    },// end of single enumeration
    listener("Listener") {
        @Override
        Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
            return grid.addListener(new Component.Listener() {
                @Override
                public void componentEvent(Component.Event event) {
                    azione.publish(presenter);
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    };// end of single enumeration

    private String caption;
    private static ArrayList<Registration> regs = new ArrayList();

    Azione(String caption) {
        this.caption = caption;
    }// end of general constructor


    /**
     * Costruisce il listener per la singola azione
     */
    Registration addListener(AlgosPresenter presenter, AlgosGrid grid, Azione azione) {
        return null;
    }// end of constructor

    /**
     * Costruisce tutti i listener possibili per una grid
     */
    public static void addAllListeners(AlgosPresenter presenter, AlgosGrid grid) {
        Registration reg;
        int quantiListeners = regs.size();
        int a = 87;

        for (Registration oldReg : regs) {
            oldReg.remove();
            oldReg=null;
        }// end of for cycle
        int quantiListeners2 = regs.size();
        int ab = 87;

        for (Azione azione : Azione.values()) {
            reg = azione.addListener(presenter, grid, azione);
            regs.add(reg);
        }// end of for cycle
    }// end of constructor


    /**
     * L'evento invece arriva dalla grid e quindi risalgo alla AlgosView che contiene la grid
     */
    private void publish(AlgosPresenter presenter) {
        publish(presenter, null);
    }// end of method

    /**
     * L'evento invece arriva dalla grid e quindi risalgo alla AlgosView che contiene la grid
     */
    private void publish(AlgosPresenter presenter, Object entityBean) {
        AlgosSpringEvent actionSpringEvent = null;

        if (presenter != null) {
            if (entityBean != null && entityBean instanceof AlgosEntity) {
                actionSpringEvent = new ActionSpringEvent(presenter, this, (AlgosEntity) entityBean);
            } else {
                actionSpringEvent = new ActionSpringEvent(presenter, this);
            }// end of if/else cycle
            presenter.getApplicationEventPublisher().publishEvent(actionSpringEvent);
        }// end of if cycle

    }// end of method


}// end of enumeration
