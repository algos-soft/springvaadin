package it.algos.springvaadin.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gac on 07/07/17
 * Implementazione standard dell'annotation AlgosView
 */
public abstract class AlgosViewImpl extends VerticalLayout implements AlgosView {

    //--la lista specifica viene iniettata dal costruttore della sottoclasse concreta
    private AlgosList list;


    //--il form specifico viene iniettato dal costruttore della sottoclasse concreta
    private AlgosForm form;


    private AlgosPresenterImpl presenter;

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    @Autowired //@todo in realtà funziona anche senza @Autowired. Non capisco :-(
    public AlgosViewImpl(AlgosList list, AlgosForm form) {
        this.list = list;
        this.form = form;
    }// end of Spring constructor

    /**
     * Metodo inserito per compatibilità con l'annotation View, ma non utilizzato
     * Il flusso dello SpringNavigator passa dal corrispondente metodo della classe AlgosNavView
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     * Passa il controllo alla classe xxxPresenter che gestisce la business logic
     */
    @Override
    @Deprecated
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }// end of method


    /**
     * Metodo invocato da AlgosPresenter
     * Regola la lista (che usa una Grid)
     * Visualizza la lista
     *
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param items       da visualizzare nella Grid
     * @param columns     visibili ed ordinate della lista
     */
    @Override
    public void setList(Class<? extends AEntity> entityClazz, List items, List<String> columns) {
        removeAllComponents();
        list.restart(presenter, entityClazz, items, columns);
        addComponent(list.getComponent());
    }// end of method


    /**
     * Costruisce un Form
     *
     * @param entity di riferimento
     * @param fields visibili ed ordinati del Form
     * @param usaToolbarLink barra alternativa di bottoni per gestire il ritorno ad altro modulo
     */
    @Override
    public void setForm(AEntity entity, List<String> fields, boolean usaToolbarLink) {
        removeAllComponents();
        form.restart(presenter, entity, fields, usaToolbarLink);
        addComponent(form.getComponent());
        form.enableRevert(false);
        form.enableRegistra(false);
    }// end of method


    /**
     * Righe selezionate nella Grid
     * Rimanda al metodo della Lista
     *
     * @return numero di righe selezionate
     */
    @Override
    public int numRigheSelezionate() {
        return list.numRigheSelezionate();
    }// end of method

    /**
     * Una riga selezionata nella grid
     *
     * @return true se è selezionata una ed una sola riga nella Grid
     */
    @Override
    public boolean isUnaRigaSelezionata() {
        return list.isUnaRigaSelezionata();
    }// end of method


    /**
     * Abilita il bottone Revert del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableRevert(boolean status) {
        form.enableRevert(status);
    }// end of method

    /**
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableRegistra(boolean status) {
        form.enableRegistra(status);
    }// end of method


    /**
     * Abilita il bottone Accetta del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableAccetta(boolean status) {
        form.enableAccetta(status);
    }// end of method

    /**
     * Abilita il bottone Edit dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableEdit(boolean status) {
        list.enableEdit(status);
    }// end of method

    /**
     * Abilita il bottone Delet della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableDelete(boolean status) {
        list.enableDelete(status);
    }// end of method


    /**
     * Chiude la (eventuale) finestra utilizzata nel Form
     */
    @Override
    public void closeFormWindow() {
        form.closeWindow();
    }// end of method

    /**
     * Esegue il 'rollback' nel Form
     * Revert (ripristina) button pressed in form
     * Rimane nel form SENZA registrare e ripristinando i valori iniziali della entity
     */
    @Override
    public void revertEntity() {
        form.revert();
    }// end of method

    /**
     * Checks all current validation errors
     * Se ce ne sono, rimane nel form SENZA registrare
     *
     * @return ci sono errori in almeno una delle property della entity
     */
    @Override
    public boolean entityHasError() {
        return form.entityHasError();
    }// end of method

    /**
     * Checks if the entity has no current validation errors at all
     * Se la entity è OK, può essere registrata
     *
     * @return tutte le property della entity sono valide
     */
    @Override
    public boolean entityIsOk() {
        return form.entityIsOk();
    }// end of method


    /**
     * All fields errors
     *
     * @return errors
     */
    @Override
    public List<ValidationResult> getEntityErrors() {
        return form.getEntityErrors();
    }// end of method

    /**
     * Esegue il 'commit' nel Form.
     * Trasferisce i valori dai campi alla entityBean
     * Esegue la validazione dei dati
     * Esegue la trasformazione dei dati
     *
     * @return la entity del Form
     */
    @Override
    public AEntity commit() {
        return form.commit();
    }// end of method

    /**
     * La entity del Form
     *
     * @return la entity del Form
     */
    @Override
    public AEntity getEntityForm() {
        return form.getEntity();
    }// end of method

    /**
     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
     * Lista nulla, se nessuna riga è selezionata
     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
     * Lista di più elementi, se è Grid.SelectionMode.MULTI
     *
     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
     */
    @Override
    public List<AEntity> getEntityBeans() {
        return list.getEntityBeans();
    }// end of method


    /**
     * Elemento selezionato nella Grid
     *
     * @return entityBean
     */
    public AEntity getEntityBean() {
        return list.getEntityBean();
    }// end of method


    public void setPresenter(AlgosPresenterImpl presenter) {
        this.presenter = presenter;
        list.setPresenter(presenter);
    }// end of method

    /**
     * Registra eventuali dipendenze di un field del Form
     */
    @Override
    public void saveSons() {
        form.saveSons();
    }// end of method

    public AlgosForm getForm() {
        return form;
    }
}// end of class
