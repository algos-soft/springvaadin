package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.AlgosPresenter;
import it.algos.springvaadin.entity.role.IAlgosPresenter;
import it.algos.springvaadin.entity.role.RolePresenterNew;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.view.AlgosViewNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:56
 * <p>
 * Implementazione standard dell'annotation AlgosList
 * Questa vista 'normalmente' si compone di:
 * Top - Eventuali scritte esplicative come collezione usata, records trovati, ecc
 * Body - Grid. Scorrevole
 * Bottom - Barra dei bottoni
 */
@Slf4j
public class AlgosListNew extends AlgosViewNew implements IAlgosListNew {

    //--AlgosGrid, iniettata dal costruttore
    //--un eventuale Grid specifico verrebbe iniettato dal costruttore della sottoclasse concreta
    protected AlgosGrid grid;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AlgosListNew(IAlgosPresenter presenter,AlgosGrid grid) {
        super(presenter);
        this.grid=grid;
    }// end of Spring constructor


    /**
     * Creazione della grid
     * Ricrea tutto ogni volta che diventa attivo
     *
     * @param source      di riferimento per gli eventi
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    @Override
    public void start(IAlgosPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
//        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
//            this.addStyleName("blueBg");
//        }// end of if cycle
//        fixGUI();
//        fixMenu();
//        this.setMargin(false);
//        this.setWidth("100%");
//        this.setHeight("100%");
//        this.removeAllComponents();
//
//        topLayout = creaTop(entityClazz, items);
//        this.addComponent(topLayout);
//
//        bodyPanel = creaBody(entityClazz, columns, items);
//        this.addComponent(bodyPanel);
//
//        bottomLayout = creaBottom(source);
//        this.addComponent(bottomLayout);
//        this.setExpandRatio(bodyPanel, 1);
//        addComponent(list.getComponent());
        grid.inizia(source,entityClazz, columns, items);
this.addComponent(grid);
        this.addComponent(new Label("alfa beta gamma"));
    }// end of method

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
    @Override
    public void setList(ApplicationListener source, Class<? extends AEntity> entityClazz, List<Field> columns, List items) {

    }

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    @Override
    public Component getComponent() {
        return new Label("alfa");//@todo provvisorio
    }// end of method

}// end of class
