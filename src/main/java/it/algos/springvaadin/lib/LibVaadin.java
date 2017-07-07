package it.algos.springvaadin.lib;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;

import java.io.File;

/**
 * Created by gac on 26/05/17.
 * Accessing UI, Page, Session, and Service
 */
public class LibVaadin {

    /**
     * Get the current View
     */
    public static AlgosPresenter getCurrentPresenter() {
        AlgosPresenter presenter = null;
        AlgosView view = getCurrentView();

        if (view != null) {
//            presenter = view.getPresenter();
        }// end of if cycle

        return presenter;
    }// end of static method

    /**
     * Get the current View
     */
    public static AlgosView getCurrentView() {
        AlgosView algosView = null;
        View vaadinView = null;
        Navigator navigator = getNavigator();

        if (navigator != null) {
            vaadinView = navigator.getCurrentView();
            if (vaadinView instanceof AlgosView) {
                algosView = (AlgosView) vaadinView;
            }// end of if cycle
        }// end of if cycle

        return algosView;
    }// end of static method

    /**
     * Get the Navigator
     */
    public static Navigator getNavigator() {
        Navigator navigator = null;
        UI ui = getUI();

        if (ui != null) {
            navigator = ui.getNavigator();
        }// end of if cycle

        return navigator;
    }// end of static method

    /**
     * Get the UI
     */
    public static UI getUI() {
        return UI.getCurrent();
    }// end of static method


    /**
     * Get the Page
     */
    public static Page getPage() {
        return Page.getCurrent();
    }// end of static method


    /**
     * Get the Session
     */
    public static VaadinSession getSession() {
        return VaadinSession.getCurrent();
    }// end of static method


    /**
     * Get the baseDir path
     */
    public static File getBaseDir() {
        return VaadinService.getCurrent().getBaseDirectory();
    }// end of static method


    /**
     * Get the real path
     */
    public static String getPath() {
        String path = "";
        File baseDir = LibVaadin.getBaseDir();

        if (baseDir != null) {
            path = baseDir.getAbsolutePath();
        }// end of if cycle

        return path;
    }// end of static method


}// end of abstract class
