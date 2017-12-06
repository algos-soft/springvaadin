package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:58
 */
public interface IAlgosView extends View {



    /**
     * Creazione di una Grid
     * Ricrea tutto ogni volta che la View diventa attiva
     * Chiamato dal Presenter
     *
     * @param source      presenter di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       beans da visualizzare nella Grid
     */
    public void setList(ApplicationListener source, Class<? extends AEntity> entityClazz, List<Field> columns, List items);


    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent();

}// end of interface
