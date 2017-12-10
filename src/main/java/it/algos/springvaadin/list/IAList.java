package it.algos.springvaadin.list;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.IAView;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 23:08
 */
public interface IAList extends IAView {

    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la view diventa attiva
     * La view comprende anche il menuLayout, una caption della Grid ed un footer di bottoni-comando
     *
     * @param source      di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     * @param typeButtons lista di (tipi di) bottoni visibili nella toolbar della view AList
     */
    public void start(IAPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items, List<EAButtonType> typeButtons);

}// end of interface
