package it.algos.springvaadin.menu;

import it.algos.springvaadin.list.AList;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 10-feb-2018
 * Time: 12:55
 */
public interface IAMenu {

    /**
     * Avvia la menubar, dopo aver aggiunto tutte le viste
     */
    public void start();


    /**
     * Componente concreto di questa interfaccia
     */
    public AMenu getMenu();

}// end of class
