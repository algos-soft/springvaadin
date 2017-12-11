package it.algos.springvaadin.presenter;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.event.AButtonEvent;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.event.AFieldEvent;
import it.algos.springvaadin.field.AField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 07:28
 */
public interface IAPresenter extends ApplicationListener<AEvent> {

    /**
     * Gestione di una Lista visualizzata con una Grid
     * Metodo invocato da:
     * 1) una view quando diventa attiva
     * 2) un Evento (azione) che necessita di aggiornare e ripresentare la Lista;
     * tipo dopo un delete, dopo un nuovo record, dopo la edit di un record
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Recupera dal service le colonne da mostrare nella grid
     * Recupera dal service gli items (records) della collection, da mostrare nella grid
     * Passa il controllo alla view con i dati necessari
     */
    public void setList();


    /**
     * Gestione di un Form per presentare i fields
     * Metodo invocato da:
     * 1) una view quando diventa attiva
     * 2) un Evento (azione) che necessita di aggiornare e ripresentare il Form;
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Recupera l'entityBean da mostrare/modificare. Nullo per una nuova scheda
     * Recupera dal service i fields (property) della collection, da mostrare nella view
     * Recupera dal service i bottoni comando da mostrare nella toolbar del footer (sotto i fields)
     * Passa il controllo alla view con i dati necessari
     */
    public void setForm();

    public void fireForm();


    /**
     * Handle an application event.
     *
     * @param event to respond to
     */
    @Override
    public void onApplicationEvent(AEvent event) ;

}// end of interface
