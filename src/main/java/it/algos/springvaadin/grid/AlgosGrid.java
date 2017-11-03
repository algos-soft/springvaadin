package it.algos.springvaadin.grid;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MultiSelect;
import com.vaadin.ui.SingleSelect;
import it.algos.springvaadin.event.TypeAction;
import it.algos.springvaadin.event.*;
import it.algos.springvaadin.lib.*;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 02/06/17.
 * Semplifica la costruzione di una Grid
 * Costruisce i listener che lanciano (fire) gli eventi
 */
@SpringComponent
@Scope("prototype")
@Slf4j
public class AlgosGrid extends Grid {

    private final static int NUMERO_RIGHE_DEFAULT = 12;
    private final GridToolSet gridToolSet;

    /**
     * Property iniettata nel costruttore
     */
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     */
    public AlgosGrid(GridToolSet gridToolSet, ApplicationEventPublisher applicationEventPublisher) {
        this.gridToolSet = gridToolSet;
        this.applicationEventPublisher = applicationEventPublisher;
    }// end of @Autowired constructor


    /**
     * Metodo invocato da AlgosListImpl
     */
    public void inizia(Class<? extends AEntity> model, List items, List<String> visibleColumns) {
        this.inizia(model, items, visibleColumns, NUMERO_RIGHE_DEFAULT);
    }// end of method


    /**
     * Metodo invocato da AlgosListImpl
     */
    public void inizia(Class<? extends AEntity> beanType, List items, List<String> visibleColumns, int numeroRighe) {
        this.setBeanType(beanType);
        if (items != null) {
            this.setItems(items);
        }// end of if cycle
        this.setHeightByRows(numeroRighe);
        this.addColumns(visibleColumns);

        //--Aggiunge alla grid tutti i listener previsti
        addAllListeners();

    }// end of method

    /**
     * Aggiunge alla grid tutti i listener previsti
     * Le azioni verranno intercettate e gestite dal presenter (recuperato nel 'fire' dell'azione)
     */
    public void addAllListeners() {
        Grid.SelectionMode modelloSelezione = LibParams.gridSelectionMode();
        this.setSelectionMode(modelloSelezione);
        gridToolSet.addAllListeners(this);

        //--recupera il presenter
        AlgosPresenterImpl presenter = LibSpring.getPresenter();

        //--lancia (fire) un evento per la condizione iniziale di default della selezione nella Grid
        if (modelloSelezione == SelectionMode.SINGLE) {
            applicationEventPublisher.publishEvent(new AActionEvent(TypeAction.singleSelectionChanged, presenter));
        } else {
            applicationEventPublisher.publishEvent(new AActionEvent(TypeAction.multiSelectionChanged, presenter));
        }// end of if/else cycle

    }// end of method


    /**
     * Utilizza l'Annotation @AIColumn del Model per regolare le caratteristiche delle colonne
     * type() default AFieldType.text;
     * header() default "";
     * width() default 80;
     * prompt() default "";
     * help() default "";
     *
     * @param visibleColumns visibili ed ordinate della lista
     */
    public void addColumns(List<String> visibleColumns) {
        Grid.Column colonna = null;
        Class<? extends AEntity> clazz = this.getBeanType();
        int lar = 0;

        if (this.getColumns() != null && this.getColumns().size() > 0) {
            this.removeAllColumns();
        }// end of if cycle

        for (String publicFieldName : visibleColumns) {
            try { // prova ad eseguire il codice
                colonna = this.addColumn(publicFieldName);
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
            lar += LibColumn.fixColumn(colonna, clazz, publicFieldName);
        }// end of for cycle

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
        boolean selezionata = false;
        HasValue selezione;

        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                try { // prova ad eseguire il codice
                    selezione = this.asSingleSelect();
                    if (selezione instanceof SingleSelect) {
                        selezionata = !selezione.isEmpty();
                    }// end of if cycle
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
                break;
            case MULTI:
                try { // prova ad eseguire il codice
                    selezione = this.asMultiSelect();
                    if (selezione instanceof MultiSelect) {
                        selezionata = ((MultiSelect) selezione).getSelectedItems().size() == 1;
                    }// end of if cycle
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
                break;
            case NONE:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return selezionata;
    }// end of method


    public List<AEntity> getEntityBeans() {
        List<AEntity> beanList = null;
        AEntity entityBean;
        Object[] matrice;

        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                try { // prova ad eseguire il codice
                    entityBean = (AEntity) this.asSingleSelect().getValue();
                    beanList = new ArrayList();
                    beanList.add(entityBean);
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
                return beanList;
            case MULTI:
                try { // prova ad eseguire il codice
                    matrice = this.asMultiSelect().getSelectedItems().toArray();
                    beanList = new ArrayList();
                    for (Object obj : matrice) {
                        beanList.add((AEntity) obj);
                    }// end of for cycle
                } catch (Exception unErrore) { // intercetta l'errore
                }// fine del blocco try-catch
                return beanList;
            case NONE:
                return null;
            default: // caso non definito
                return null;
        } // fine del blocco switch

    }// end of method


    public AEntity getEntityBean() {
        AEntity entityBean = null;
        Object[] matrice;

        switch (LibParams.gridSelectionMode()) {
            case SINGLE:
                entityBean = (AEntity) this.asSingleSelect().getValue();
                break;
            case MULTI:
                matrice = this.asMultiSelect().getSelectedItems().toArray();
                if (matrice.length == 1) {
                    entityBean = (AEntity) matrice[0];
                }// end of if cycle
                break;
            case NONE:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return entityBean;
    }// end of method

//    public void setBeanType(Class beanType) {
//        this.setBeanType(beanType);
//    }// end of method

}// end of class
