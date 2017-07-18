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
     * Esegue il 'rollback' nel Form
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    public void revertEntity();


    /**
     * Esegue il 'commit'.
     * Trasferisce i valori dai campi alla entityBean
     * Esegue la validazione dei dati
     * Esegue la trasformazione dei dati
     *
     * @return la entity del Form
     */
    public AlgosEntity writeBean();


    /**
     * Chiude la (eventuale) finestra utilizzata nel Form
     */
    public void closeWindow();


    /**
     * Restituisce il componente concreto
     */
    public Component getComponent();


    /**
     * Restituisce la entity utilizzata
     */
    public AlgosEntity getEntity();


    /**
     * Abilita il bottone Revert del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRevert(boolean status);

    /**
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRegistra(boolean status);


}// end of interface
