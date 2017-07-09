package it.algos.springvaadin.grid;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.event.*;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by gac on 02/06/17.
 * Semplifica la costruzione di una Grid
 * Costruisce i listener che lanciano (fire) gli eventi
 */
@SpringComponent
public class AlgosGrid extends Grid {

    private final static int NUMERO_RIGHE_DEFAULT = 15;

    /**
     * Metodo invocato DOPO che la classe è stata iniettata da SpringBoot
     */
    public void inizia(Class<? extends AlgosEntity> model, List items, List<String> columns) {
        this.inizia(model, items, columns, NUMERO_RIGHE_DEFAULT);
    }// end of method


    /**
     * Metodo invocato DOPO che la classe è stata iniettata da SpringBoot
     */
    public void inizia(Class<? extends AlgosEntity> beanType, List items, List<String> columns, int numeroRighe) {
        this.setBeanType(beanType);
        if (items != null) {
            this.setItems(items);
        }// end of if cycle
        this.setHeightByRows(numeroRighe);
        this.setSelectionMode(LibParams.gridSelectionMode());
        this.setColumns(columns);
        AlgosPresenter  presenter = LibSpring.getPresenter();

        //--aggiunge alla Grid tutte le azioni possibili; verranno intercettate e gestire dal presenter
        Azione.addAllListeners(presenter, this);

        //--lancia (fire) un evento per la condizione iniziale di default della selezione nella Grid
        AlgosSpringEvent actionSpringEvent = new ActionSpringEvent(presenter, Azione.selectionChanged);
        presenter.getApplicationEventPublisher().publishEvent(actionSpringEvent);

    }// end of method


    /**
     * Utilizza l'Annotation @AIColumn del Model per regolare le caratteristiche delle colonne
     * type() default AFType.text;
     * header() default "";
     * width() default 80;
     * prompt() default "";
     * help() default "";
     */
    public void setColumns(List<String> colonneVisibili) {
        int lar = 0;

        if (LibSession.isDeveloper()) {
            lar = LibColumn.addColumns(this);
        } else {
            lar = LibColumn.addColumns(this.getBeanType(), this, colonneVisibili);
        }// end of if/else cycle

        //--spazio per la colonna automatica di selezione
        if (LibParams.gridSelectionMode() == SelectionMode.MULTI) {
            lar += 50;
        }// end of if cycle

        this.setWidth(lar + "px");
    }// end of method


    public int numRigheSelezionate() {
        int numRighe = 0;

        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                if (!this.asSingleSelect().isEmpty()) {
                    numRighe = 1;
                }// end of if cycle
                break;
            case MULTI:
                numRighe = this.asMultiSelect().getSelectedItems().size();
                break;
            case NONE:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return numRighe;
    }// end of method


    public boolean unaRigaSelezionata() {
        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                return !this.asSingleSelect().isEmpty();
            case MULTI:
                return this.asMultiSelect().getSelectedItems().size() == 1;
            case NONE:
                return false;
            default: // caso non definito
                return false;
        } // fine del blocco switch
    }// end of method


    public AlgosModel getBean() {
        AlgosModel bean = null;

        if (unaRigaSelezionata()) {
            Set alfa = this.getSelectedItems();
            if (alfa.size() == 1) {
                Object beta = alfa.toArray()[0];
                if (beta instanceof AlgosModel) {
                    bean = (AlgosModel) beta;
                }// end of if cycle
            }// end of if cycle
        }// end of if cycle

        return bean;
    }// end of method

    public List<AlgosModel> getBeans() {
        List<AlgosModel> beanList = null;
        AlgosModel entityBean;
        Object[] matrice;

        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                entityBean = (AlgosModel) this.asSingleSelect().getValue();
                beanList = new ArrayList<>();
                beanList.add(entityBean);
                return beanList;
            case MULTI:
                matrice = this.asMultiSelect().getSelectedItems().toArray();
                beanList = new ArrayList<>();
                for (Object obj : matrice) {
                    beanList.add((AlgosModel) obj);
                }// end of for cycle
                return beanList;
            case NONE:
                return null;
            default: // caso non definito
                return null;
        } // fine del blocco switch

    }// end of method

//    public void setBeanType(Class beanType) {
//        this.setBeanType(beanType);
//    }// end of method

}// end of class
