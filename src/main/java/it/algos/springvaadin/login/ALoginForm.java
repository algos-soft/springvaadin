package it.algos.springvaadin.login;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import it.algos.springvaadin.dialog.AConfirmDialog;
import it.algos.springvaadin.field.ACheckBoxField;
import it.algos.springvaadin.field.ATextField;
import it.algos.springvaadin.listener.ALoginListener;

/**
 * Abstract Login form.
 */
public abstract class ALoginForm extends AConfirmDialog {

    private Component usernameField;
//    private PasswordField passField; @todo ricambiare
    private ATextField passField;
    private ACheckBoxField rememberField;

    /**
     * Login gestisce il form ed alla chiusura controlla la validità del nuovo utente
     * Lancia il fire di questo evento, se l'utente è valido.
     * Si registra qui il solo listener di Login perché BaseLoginForm e Login sono 1=1
     * Login a sua volta rilancia l'evento per i propri listeners
     * (che si registrano a Login che è singleton nella sessione, mentre BaseLoginForm può essere instanziata diverse volte)
     */
    private ALoginListener loginListener;

    /**
     * Constructor
     */
    public ALoginForm() {
        super(null);
        init();
    }// end of constructor

    /**
     * Initialization <br>
     */
    protected void init() {
        FormLayout layout = new FormLayout();//@todo controllare la larghezza con AFormLayout
        layout.setWidthUndefined();
        layout.setSpacing(true);

        // crea i campi
        usernameField = createUsernameComponent();

        //        passField = new PasswordField("Password");@todo ricambiare
        passField = new ATextField("Password");
        passField.inizializza("alfa",null);
        passField.setCaption("Password");
        passField.setWidthUndefined();

//        passField = new TextField("Password");
        rememberField = new ACheckBoxField("Ricordami su questo computer");
        rememberField.inizializza("beta",null);
        rememberField.setCaption("Ricordami su questo computer");

        // aggiunge i campi al layout
//        layout.addComponent(usernameField);
        layout.addComponent(passField);
        layout.addComponent(rememberField);

        addComponent(layout);
    }// end of method


    /**
     * Create the component to input the username.
     * @return the username component
     */
    abstract Component createUsernameComponent();


    @Override
    protected void onConfirm() {
        IAUser user = getSelectedUser();
        if(user!=null){
            String password = passField.getValue();
            if(user.validatePassword(password)){
                super.onConfirm();
                utenteLoggato();
            }else{
                Notification.show("Login fallito", Notification.Type.WARNING_MESSAGE);
            }
        }
    }// end of method


    /**
     * @return the selected user
     */
    abstract IAUser getSelectedUser();

    /**
     * Evento generato quando si modifica l'utente loggato <br>
     * <p>
     * Informa (tramite listener) chi è interessato (solo la classe Login, che poi rilancia) <br>
     */
    protected void utenteLoggato() {
        if (loginListener != null) {
            loginListener.onUserLogin(null);
        }
    }

    public void setLoginListener(ALoginListener listener) {
        this.loginListener = listener;
    }

    public Window getWindow() {
        return this;
    }

    abstract void setUsername(String name);

    public void setPassword(String password) {
        passField.setValue(password);
    }

    public void setRemember(boolean remember) {
        rememberField.setValue(remember);
    }


    public Component getUsernameField() {
        return usernameField;
    }

    public ATextField getPassField() {
        return passField;
    }

    public ACheckBoxField getRememberField() {
        return rememberField;
    }
}// end of class

