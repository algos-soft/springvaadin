package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.AView;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 23:07
 */
@Slf4j
public abstract class AList extends AView implements IAList {

    //--AlgosGrid, iniettata dal costruttore
    //--un eventuale Grid specifico verrebbe iniettato dal costruttore della sottoclasse concreta
    protected IAGrid grid;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     * @param grid iniettato da Spring
     */
    public AList(IAPresenter presenter, IAGrid grid) {
        super(presenter);
        this.grid = grid;
    }// end of Spring constructor


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la view diventa attiva
     * La view comprende anche il menuLayout, una caption della Grid ed un footer di bottoni-comando
     *
     * @param source      di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    public void start(IAPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
        grid.inizia(source, entityClazz, columns, items, 50);
        this.addComponent((Component) grid);
        this.addComponent(new Label("alfa beta gamma"));
    }// end of method

}// end of class
