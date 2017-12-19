package it.algos.springvaadin.login;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import it.algos.springvaadin.field.ATextField;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by alex on 26/05/16.
 */
@SpringComponent
@Scope("session")
public class DALoginForm extends ALoginForm {

    private ATextField nameField;


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     * Aggiunge i listener al Login
     */
    @PostConstruct
    private void inizia() {
        getPassField().setWidth("15em");
    }// end of method

    /**
     * @return the selected user
     */
    public IAUser getSelectedUser(){
        String nome = nameField.getValue();
        IAUser user = null;
//         user = Utente.read(nome);//@todo da sviluppare
        return user;
    }// end of method

    /**
     * Create the component to input the username.
     * @return the username component
     */
    public Component createUsernameComponent(){
        nameField = new ATextField("Username");
//        nameField.setWidthUndefined();
        nameField.setWidth("15em");
        return nameField;
    }// end of method

    public void setUsername(String name) {
        nameField.setValue(name);
    }// end of method



}// end of class
