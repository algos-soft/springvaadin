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
     * Abilita il bottone Create dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableNew(boolean status);


    /**
     * Abilita il bottone Edit dela Grid
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
     * Una entity selezionata nella Grid
     *
     * @return la entity della singola riga selezionata o null se nessuna riga è selezionata
     */
    public AlgosEntity getEntity() ;

}// end of interfacev
