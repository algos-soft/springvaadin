package it.algos.springvaadin.home;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 02-ott-2017
 * Time: 18:18
 */
@Slf4j
@SpringComponent
@Qualifier(Cost.TAG_HOME)
public class HomeList extends AlgosListImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public HomeList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor

    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che la finestra diventa attiva
     *
     * @param source      di riferimento per gli eventi
     * @param entityClass del modello dati
     * @param items       da visualizzare nella grid
     * @param columns     da visualizzare nella grid
     */
    @Override
    public void restart(AlgosPresenterImpl source, Class<? extends AEntity> entityClass, List items, List<String> columns) {
        this.removeAllComponents();
        this.addComponent(new Label("Home page"));
    }// end of method

}// end of class
