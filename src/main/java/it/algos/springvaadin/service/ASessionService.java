package it.algos.springvaadin.service;

import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.enumeration.EAAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.management.Attribute;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 16-dic-2017
 * Time: 22:31
 * Classe di Libreria
 */
@SpringComponent
@Scope("session")
@Slf4j
public class ASessionService {

    private boolean developer=true;
    private boolean admin;
    private boolean user;

    /**
     * Regola per questa libreria unica per la sessione, l'attributo developer
     */
    public void setDeveloper(boolean status) {
        this.developer = status;
    }// end of method

    /**
     * //     * Recupera dalla sessione l'attributo developer
     * Recupera da questa libreria unica per la sessione, l'attributo developer
     */
    public boolean isDeveloper() {
        //@todo NON funziona - da implementare
//        return isBool(EAAttribute.developer);
        return developer;
    }// end of method


    /**
     * Regola per questa libreria unica per la sessione, l'attributo admin
     */
    public void setAdmin(boolean status) {
        this.admin = status;
    }// end of method

    /**
     * //     * Recupera dalla sessione l'attributo admin
     * Recupera da questa libreria unica per la sessione, l'attributo admin
     */
    public boolean isAdmin() {
        //@todo NON funziona - da implementare
//        return isBool(EAAttribute.admin);
        return admin;
    }// end of method


    /**
     * Regola per questa libreria unica per la sessione, l'attributo user
     */
    public void setUser(boolean status) {
        this.user = status;
    }// end of method

    /**
     * //     * Recupera dalla sessione l'attributo user
     * Recupera da questa libreria unica per la sessione, l'attributo user
     */
    public boolean isUser() {
        //@todo NON funziona - da implementare
//        return isBool(EAAttribute.user);
        return user;
    }// end of method


    /**
     * Regola lo specifico attributo
     */
    public void setBool(Attribute attributo, boolean status) {
        VaadinSession sessione = VaadinSession.getCurrent();

        if (attributo != null && sessione != null) {
            sessione.setAttribute(attributo.toString(), status);
        }// fine del blocco if
    }// end of method


    /**
     * Recupera dalla sessione l'attributo specifico
     */
    public boolean isBool(EAAttribute attributo) {
        boolean status = true;
        Object devObj = null;
        VaadinSession sessione = VaadinSession.getCurrent();

        if (attributo != null && sessione != null) {
            devObj = sessione.getAttribute(attributo.toString());
            if (devObj != null) {
                if (devObj instanceof Boolean) {
                    status = (Boolean) devObj;
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        return status;
    }// end of method


}// end of class
