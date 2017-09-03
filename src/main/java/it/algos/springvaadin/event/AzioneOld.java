package it.algos.springvaadin.event;

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
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

import java.util.ArrayList;

/**
 * Created by gac on 04/06/17
 * Azioni di una Grid
 */
public enum AzioneOld {

    attach("Aggiunge") {
        @Override
        void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
             grid.addAttachListener(new ClientConnector.AttachListener() {
                @Override
                public void attach(ClientConnector.AttachEvent attachEvent) {
                    azioneOld.publish(presenter);
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    click("Singolo click") {
        @Override
        void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
             grid.addItemClickListener(new ItemClickListener() {
                @Override
                public void itemClick(Grid.ItemClick itemClick) {
                    if (!itemClick.getMouseEventDetails().isDoubleClick()) {
                        azioneOld.publish(presenter);
                    }// end of if cycle
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    doppioClick("Doppio click") {
        @Override
        void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
             grid.addItemClickListener(new ItemClickListener() {
                @Override
                public void itemClick(Grid.ItemClick itemClick) {
                    Object entityBean = itemClick.getItem();
                    if (itemClick.getMouseEventDetails().isDoubleClick()) {
                        azioneOld.publish(presenter, entityBean);
                    }// end of if cycle
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    },// end of single enumeration
    selectionChanged("Modifica") {
        @Override
        void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
            switch (LibParams.gridSelectionMode()) {
                case SINGLE:
                    SingleSelectionModel modelloSingolo = (SingleSelectionModel) grid.getSelectionModel();
                     modelloSingolo.addSingleSelectionListener(new SingleSelectionListener() {
                        @Override
                        public void selectionChange(SingleSelectionEvent singleSelectionEvent) {
                            azioneOld.publish(presenter);
                        }// end of inner method
                    });// end of anonymous inner class
                    break;
                case MULTI:
                    MultiSelectionModel modelloMultiplo = (MultiSelectionModel) grid.getSelectionModel();
                     modelloMultiplo.addMultiSelectionListener(new MultiSelectionListener() {
                        @Override
                        public void selectionChange(MultiSelectionEvent multiSelectionEvent) {
                            azioneOld.publish(presenter);
                        }// end of inner method
                    });// end of anonymous inner class
                    break;
                case NONE:
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
        }// end of method
    },// end of single enumeration
    listener("Listener") {
        @Override
        void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
             grid.addListener(new Component.Listener() {
                @Override
                public void componentEvent(Component.Event event) {
                    azioneOld.publish(presenter);
                }// end of inner method
            });// end of anonymous inner class
        }// end of method
    };// end of single enumeration

    private String caption;
    private static ArrayList<Registration> regs = new ArrayList();

    AzioneOld(String caption) {
        this.caption = caption;
    }// end of general constructor


    /**
     * Costruisce il listener per la singola azioneOld
     */
    void addListener(AlgosPresenterImpl presenter, AlgosGrid grid, AzioneOld azioneOld) {
    }// end of constructor

    /**
     * Costruisce tutti i listener possibili per una grid
     */
    public static void addAllListeners(AlgosPresenterImpl presenter, AlgosGrid grid) {
        for (AzioneOld azioneOld : AzioneOld.values()) {
            azioneOld.addListener(presenter, grid, azioneOld);
        }// end of for cycle
    }// end of constructor


    /**
     * L'evento invece arriva dalla grid e quindi risalgo alla AlgosView che contiene la grid
     */
    private void publish(AlgosPresenterImpl presenter) {
        publish(presenter, null);
    }// end of method

    /**
     * L'evento invece arriva dalla grid e quindi risalgo alla AlgosView che contiene la grid
     */
    private void publish(AlgosPresenterImpl presenter, Object entityBean) {
        AEvent actionSpringEvent = null;

//        if (presenter != null) {
//            if (entityBean != null && entityBean instanceof AEntity) {
//                actionSpringEvent = new AActionEvent(presenter, this, (AEntity) entityBean);
//            } else {
//                actionSpringEvent = new AActionEvent(presenter, this);
//            }// end of if/else cycle
//            presenter.getApplicationEventPublisher().publishEvent(actionSpringEvent);
//        }// end of if cycle

    }// end of method


}// end of enumeration
