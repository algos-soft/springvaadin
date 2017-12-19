package it.algos.springvaadin.login;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.MenuBar;
import it.algos.springvaadin.event.ALoginEvent;
import it.algos.springvaadin.event.ALogoutEvent;
import it.algos.springvaadin.event.AProfileChangeEvent;
import it.algos.springvaadin.listener.ALoginListener;
import it.algos.springvaadin.listener.ALogoutListener;
import it.algos.springvaadin.listener.AProfileChangeListener;

/**
 * Menubar di gestione login
 * <p>
 * Se si è loggati, mostra il nome dell'utente ed un popup per modifica e logout <br>
 * Se non si è loggati, mostra un bottone per fare il login <br>
 */
public class ALoginButton extends MenuBar {

    private MenuItem loginItem; // il menuItem di login

    /**
     * Constructor
     */
    public ALoginButton() {

        getLogin().addLoginListener(new ALoginListener() {
            @Override
            public void onUserLogin(ALoginEvent e) {
                updateUI();
            }
        });

        getLogin().addLogoutListener(new ALogoutListener() {
            @Override
            public void onUserLogout(ALogoutEvent e) {
                updateUI();
            }
        });

        getLogin().setProfileListener(new AProfileChangeListener() {
            @Override
            public void profileChanged(AProfileChangeEvent e) {
                updateUI();
            }
        });


        loginItem = addItem("", null, null);
        this.addStyleName("blue");

        updateUI();
    }// end of constructor


    /**
     * Updates the UI based on the current Login state
     */
    private void updateUI() {
        IAUser user = getLogin().getUser();
        Resource exitIcon;

        if (user == null) {

            loginItem.setText("Login");
            loginItem.removeChildren();
            loginItem.setIcon(FontAwesome.SIGN_IN);
            loginItem.setCommand(new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
//                    loginCommandSelected();@todo rimettere
                }
            });

        } else {

            String username = user.toString();
            loginItem.setCommand(null);
            loginItem.setText(username);
            loginItem.setIcon(FontAwesome.USER);
            loginItem.removeChildren();

            loginItem.addItem("Il mio profilo...", FontAwesome.USER, new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
//                    myProfileCommandSelected();@todo rimettere
                }
            });

            exitIcon = FontAwesome.SIGN_OUT;
            loginItem.addItem("Logout", exitIcon, new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
//                    logoutCommandSelected();@todo rimettere
                }
            });



        }

    }// end of method


//    /**
//     * Login button pressed
//     */
//    protected void loginCommandSelected() {
//        getLogin().showLoginForm();
//    }// end of method
//
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

