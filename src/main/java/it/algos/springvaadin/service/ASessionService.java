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
@Scope("singleton")
@Slf4j
public class ASessionService {

    /**
     * Recupera dalla sessione l'attributo developer
     */
    public boolean isDeveloper() {
        return isBool(EAAttribute.developer);
    }// end of method


    /**
     * Recupera dalla sessione l'attributo specifico
     */
    public boolean isBool(EAAttribute attributo) {
        boolean status = false;
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
