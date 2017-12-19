package it.algos.springvaadin.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.MenuBar;
import it.algos.springvaadin.annotation.AIView;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.event.ALoginEvent;
import it.algos.springvaadin.event.ALogoutEvent;
import it.algos.springvaadin.event.AProfileChangeEvent;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.listener.ALoginListener;
import it.algos.springvaadin.listener.ALogoutListener;
import it.algos.springvaadin.listener.AProfileChangeListener;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.toolbar.IAToolbar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Menubar di gestione login
 * <p>
 * Se si è loggati, mostra il nome dell'utente ed un popup per modifica e logout <br>
 * Se non si è loggati, mostra un bottone per fare il login <br>
 */
@SpringComponent
@Scope("session")
public class ALoginButton extends MenuBar {

    private MenuItem loginItem; // il menuItem di login


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     * Aggiunge i listener al Login
     */
    @PostConstruct
    private void inizia() {

        getLogin().addLoginListener(new ALoginListener() {
            @Override
            public void onUserLogin(ALoginEvent e) {
                updateUI();
            }// end of inner method
        });// end of anonymous inner class


        getLogin().addLogoutListener(new ALogoutListener() {
            @Override
            public void onUserLogout(ALogoutEvent e) {
                updateUI();
            }// end of inner method
        });// end of anonymous inner class


        getLogin().setProfileListener(new AProfileChangeListener() {
            @Override
            public void profileChanged(AProfileChangeEvent e) {
                updateUI();
            }// end of inner method
        });// end of anonymous inner class


        loginItem = this.addItem("", null, null);
        this.addStyleName("blue");

        updateUI();
    }// end of method


    /**
     * Updates the UI based on the current Login state
     */
    private void updateUI() {
        IAUser user = getLogin().getUser();
        Resource exitIcon;

        if (user == null) {

            loginItem.setText("Login");
            loginItem.removeChildren();
            loginItem.setIcon(VaadinIcons.SIGN_IN);
            loginItem.setCommand(new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
                    loginCommandSelected();
                }// end of inner method
            });// end of anonymous inner class
        } else {
            String username = user.toString();
            loginItem.setCommand(null);
            loginItem.setText(username);
            loginItem.setIcon(VaadinIcons.USER);
            loginItem.removeChildren();

            loginItem.addItem("Il mio profilo...", VaadinIcons.USER, new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
//                    myProfileCommandSelected();@todo rimettere
                }// end of inner method
            });// end of anonymous inner class

            exitIcon = VaadinIcons.SIGN_OUT;
            loginItem.addItem("Logout", exitIcon, new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
//                    logoutCommandSelected();@todo rimettere
                }// end of inner method
            });// end of anonymous inner class
        }// end of if/else cycle


    }// end of method


    /**
     * Login button pressed
     */
    protected void loginCommandSelected() {
        getLogin().showLoginForm();
    }// end of method

//    /**
//     * Logout button pressed
//     */
//    private void logoutCommandSelected() {
//        getLogin().logout();
//        updateUI();
//    }// end of method
//
//    private void myProfileCommandSelected() {
//        getLogin().showUserProfile();
//    }// end of method


    public ALogin getLogin() {
        return ALogin.getLogin();
    }// end of method


}// end of class

