package it.algos.springvaadin.list;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.IAlgosPresenter;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 20:58
 */
public interface IAlgosListNew {

    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param source      di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    public void start(IAlgosPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items);

}// end of interface
