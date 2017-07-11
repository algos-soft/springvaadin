package it.algos.springvaadin.form;

import com.vaadin.ui.Component;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 10/07/17
 * .
 */
public interface AlgosForm {

    /**
     * Creazione del form
     * Pannello a tutto schermo, oppure finestra popup
     * Ricrea tutto ogni volta che diventa attivo
     */
    public void restart(AlgosEntity entity, List<String> fields);


    /**
     * Restituisce il componente concreto
     */
    public Component getComponent();


    /**
     * Restituisce la entity utilizzata
     */
    public AlgosEntity getEntity();

    /**
     * Chiude la (eventuale) finestra utilizzata
     */
    public void closeWindow();

}// end of interface
