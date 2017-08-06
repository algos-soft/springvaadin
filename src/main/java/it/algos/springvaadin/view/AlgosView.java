package it.algos.springvaadin.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.navigator.View;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 07/07/17
 * <p>
 * Contratto d'interfaccia con i metodi che la View rende disponibili all'applicazione,
 * in particolare ad AlgosPresenter
 * Implementati nella classe concreta AlgosViewImpl
 *
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * SpringBoot inietta le sottoclassi specifiche (xxxPresenter, xxxList e xxxForm)
 * Nel metodo @PostConstruct, viene effettuato il casting alle property più generiche
 * Passa il controllo alla classe AlgosPresenter che gestisce la business logic
 * <p>
 * Riceve i comandi ed i dati da xxxPresenter (sottoclasse di AlgosPresenter)
 * Gestisce due modalità di presentazione dei dati: List e Form
 * Presenta i componenti grafici passivi
 * Presenta i componenti grafici attivi: azioni associate alla Grid e bottoni coi listener
 */
public interface AlgosView extends View {


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
     * Checks all current validation errors
     * Se ce ne sono, rimane nel form SENZA registrare
     *
     * @return ci sono errori in almeno una delle property della entity
     */
    public boolean entityHasError();


    /**
     * Checks if the entity has no current validation errors at all
     * Se la entity è OK, può essere registrata
     *
     * @return tutte le property della entity sono valide
     */
    public boolean entityIsOk();


    /**
     * All fields errors
     *
     * @return errors
     */
    public List<ValidationResult> getEntityErrors();


    /**
     * Esegue il 'rollback' nel Form
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    public void revertEntity();


    /**
     * Esegue il 'commit' nel Form.
     * Trasferisce i valori dai campi alla entityBean
     * Esegue la validazione dei dati
     * Esegue la trasformazione dei dati
     *
     * @return la entity del Form
     */
    public AlgosEntity commit();


    /**
     * Chiude la (eventuale) finestra utilizzata nel Form
     */
    public void closeFormWindow();


    /**
     * La entity del Form
     *
     * @return la entity del Form
     */
    public AlgosEntity getEntityForm();


    /**
     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
     * Lista nulla, se nessuna riga è selezionata
     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
     * Lista di più elementi, se è Grid.SelectionMode.MULTI
     *
     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
     */
    public List<AlgosEntity> getEntityBeans();


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
