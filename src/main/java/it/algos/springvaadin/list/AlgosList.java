package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 09/07/17
 * .
 */
public interface AlgosList {

    /**
     */
    public void restart(Class<? extends AlgosEntity> clazz, List items, List<String> columns);


    /**
     * Restituisce il componente concreto
     */
    public Component getComponent();


    /**
     * Righe selezionate nella Grid
     *
     * @return numero di righe selezionate
     */
    public int numRigheSelezionate();


    /**
     * Una riga selezionata nella grid
     *
     * @return true se è selezionata una ed una sola riga nella Grid
     */
    public boolean isUnaRigaSelezionata();


    /**
     * Abilita il bottone Create della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableNew(boolean status);


    /**
     * Abilita il bottone Edit della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableEdit(boolean status);


    /**
     * Abilita il bottone Delete della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableDelete(boolean status);


    /**
     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
     * Lista nulla, se nessuna riga è selezionata
     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
     * Lista di più elementi, se è Grid.SelectionMode.MULTI
     *
     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
     */
    public List<AlgosEntity> getEntity() ;

}// end of interfacev
