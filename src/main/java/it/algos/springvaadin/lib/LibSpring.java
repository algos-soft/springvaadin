package it.algos.springvaadin.lib;

import com.vaadin.ui.UI;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;

import static it.algos.springvaadin.lib.LibVaadin.getUI;

/**
 * Created by gac on 18/06/17
 * .
 */
public class LibSpring {


    public static AlgosPresenter getPresenter() {
        return getView().getPresenter();
    }// end of static method


    public static AlgosView getView() {
        return (AlgosView) getUI().getNavigator().getCurrentView();
    }// end of static method


}// end of static class
