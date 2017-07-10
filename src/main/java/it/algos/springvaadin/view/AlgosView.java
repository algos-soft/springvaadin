package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 07/07/17
 * <p>
 * Contratto d'interfaccia con i metodi che la View rende disponibili all'applicazione,
 * in particolare ad AlgosPresenter
 * Implementati nella classe concreta AlgosViewImpl
 */
public interface AlgosView extends View{


    /**
     * Costruisce una Grid
     *
     * @param clazz   di riferimento, sottoclasse concreta di AlgosEntity
     * @param items   da visualizzare nella Grid
     * @param columns visibili ed ordinate della lista
     */
    public void setList(Class<? extends AlgosEntity> clazz, List items, List<String> columns);


    /**
     * Costruisce un Form
     *
     * @param entity di riferimento
     * @param fields visibili ed ordinati del Form
     */
    public void setForm(AlgosEntity entity, List<String> fields);



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
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableRegistra(boolean status);


    /**
     * Abilita il bottone Edit dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableEdit(boolean status);


    /**
     * Abilita il bottone Delet della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    public void enableDelete(boolean status);


    /**
     * Chiude l'eventuale finestra separata nel Form
     */
    public void closeFormWindow();


    /**
     * La entity del Form
     *
     * @return la entity del Form
     */
    public AlgosEntity getEntityForm();


    /**
     * Una entity selezionata nella Grid
     *
     * @return la entity della singola riga selezionata o null se nessuna riga è selezionata
     */
    public AlgosEntity getEntityList();


}// end of interface
