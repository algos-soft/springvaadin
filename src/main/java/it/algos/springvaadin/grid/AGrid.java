package it.algos.springvaadin.grid;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.presenter.IAPresenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 07:51
 */
@Slf4j
@SpringComponent
@Scope("prototype")
public class AGrid extends Grid implements IAGrid {

    /**
     * Property iniettata nel costruttore
     */
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * Gestore principale del modulo, regolato nel metodo Inizia
     */
    protected IAPresenter presenter;


//    /**
//     * Metodo invocato da AList
//     *
//     * @param beanType    modello dei dati
//     * @param columns     visibili ed ordinate della Grid
//     * @param items       da visualizzare nella Grid
//     * @param numeroRighe da visualizzare nella Grid
//     */
//    @Override
//    public void inizia(Class<? extends AEntity> beanType, List<Field> columns, List items, int numeroRighe) {
//        this.setBeanType(beanType);
//        this.setRowHeight(50);
//        this.addColumn("Alfa");
//
//        if (items != null && items.size() > 0) {
//            this.setItems(items);
//            this.setHeightMode(HeightMode.ROW);
//            this.setHeightByRows(items.size());
//        }// end of if cycle
//
//        //--Aggiunge alla grid tutti i listener previsti
////        addAllListeners();
//    }// end of method

    /**
     * Metodo invocato da AlgosListImpl
     *
     * @param beanType    modello dei dati
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     * @param numeroRighe da visualizzare nella Grid
     */
    public void inizia(IAPresenter presenter, Class<? extends AEntity> beanType, List<Field> columns, List items, int numeroRighe) {
        this.presenter=presenter;
        this.setBeanType(beanType!=null?beanType: Role.class);
        this.setRowHeight(50);
        this.addColumn("code");

        if (items != null && items.size() > 0) {
            this.setItems(items);
            this.setHeightMode(HeightMode.ROW);
            this.setHeightByRows(items.size());
        }// end of if cycle

        //--Aggiunge alla grid tutti i listener previsti
//        addAllListeners();
    }// end of method
}// end of class
