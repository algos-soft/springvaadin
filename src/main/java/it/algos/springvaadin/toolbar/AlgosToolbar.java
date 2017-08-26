package it.algos.springvaadin.toolbar;

import com.vaadin.ui.HorizontalLayout;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Created by gac on 03/06/17.
 * .
 * Superclasse per le barre di comando con bottoni
 */
public abstract class AlgosToolbar extends HorizontalLayout {


    /**
     * Metodo invocato da Form
     * Aggiunge i bottoni al contenitore grafico
     */
    public void inizia() {
    }// end of method



    public void addButton(Bottone bottone) {
        addComponent(bottone);
    }// end of method


    /**
     * Metodo invocato DOPO la chiamata del browser, da AlgosList e da AlgosForm
     * I bottoni vengono creati da Spring in una fase iniziale 'statica' e non sanno chi li 'userà'
     * Quando parte la UI ed il corrispondente xxxPresenter, questo viene iniettato nel bottone
     * Il bottone usa il presenter per identificare 'dove' gestire l'evento generato
     */
    public void regolaBottoni(AlgosPresenterImpl presenter) {
        for (int k = 0; k < getComponentCount(); k++) {
            ((Bottone) getComponent(k)).regolaBottone(presenter);
        }// end of for cycle
    }// end of method


//    public void addButtonWithPresenter(Bottone bottone, AlgosPresenterImpl presenter) {
//        addButtonWithPresenter(bottone, "", presenter);
//    }// end of method
//
//    public void addButtonWithPresenter(Bottone bottone, String styleName, AlgosPresenterImpl presenter) {
//        addButton(bottone, styleName);
//        bottone.setPresenter(presenter);
//    }// end of method
//
//    public void setPresenter(AlgosPresenterImpl presenter) {
//        for (int k = 0; k < getComponentCount(); k++) {
//            ((Bottone) getComponent(k)).setPresenter(presenter);
//        }// end of for cycle
//    }// end of method

    public void enableAnnulla(boolean status) {
    }// end of method

    public void enableRevert(boolean status) {
    }// end of method

    public void enableRegistra(boolean status) {
    }// end of method

    public void enableAccetta(boolean status) {
    }// end of method

}// end of class
