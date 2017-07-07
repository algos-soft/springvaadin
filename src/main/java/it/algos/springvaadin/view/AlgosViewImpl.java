package it.algos.springvaadin.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;

import java.util.List;

/**
 * Created by gac on 07/07/17
 * .
 */
public abstract class AlgosViewImpl extends VerticalLayout implements AlgosView {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }// end of method

    /**
     * Costruisce una Grid
     *
     * @param clazz   di riferimento, sottoclasse concreta di AlgosEntity
     * @param items   da visualizzare nella Grid
     * @param columns visibili ed ordinate della lista
     */
    @Override
    public void setList(Class<? extends AlgosEntity> clazz, List items, List<String> columns) {
    }// end of method


    /**
     * Costruisce un Form
     *
     * @param entity di riferimento
     * @param fields visibili ed ordinati del Form
     */
    @Override
    public void setForm(AlgosEntity entity, List<String> fields) {
    }// end of method


    /**
     * Righe selezionate nella Grid
     *
     * @return numero di righe selezionate
     */
    @Override
    public int numRigheSelezionate() {
        return 0;
    }// end of method

    /**
     * Una riga selezionata nella grid
     *
     * @return true se è selezionata una ed una sola riga nella Grid
     */
    @Override
    public boolean isUnaRigaSelezionata() {
        return false;
    }// end of method

    /**
     * Abilita il bottone Registra del Form
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void setBottoneRegistra(boolean status) {

    }// end of method

    /**
     * Abilita il bottone Edit dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void setBottoneEdit(boolean status) {

    }// end of method

    /**
     * Abilita il bottone Delet della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void setBottoneDelete(boolean status) {

    }// end of method

    /**
     * Chiude l'eventuale finestra separata nel Form
     */
    @Override
    public void closeFormWindow() {

    }// end of method

    /**
     * La entity del Form
     *
     * @return la entity del Form
     */
    @Override
    public AlgosEntity getEntityForm() {
        return null;
    }// end of method

    /**
     * Una entity selezionata nella Grid
     *
     * @return la entity della singola riga selezionata o null se nessuna riga è selezionata
     */
    @Override
    public AlgosEntity getEntityList() {
        return null;
    }// end of method

}// end of class
