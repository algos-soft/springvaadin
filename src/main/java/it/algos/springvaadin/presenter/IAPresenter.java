package it.algos.springvaadin.presenter;

import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 07:28
 */
public interface IAPresenter {

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

}// end of interface
