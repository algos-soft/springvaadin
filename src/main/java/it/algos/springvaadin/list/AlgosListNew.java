package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.role.AlgosPresenter;
import it.algos.springvaadin.entity.role.IAlgosPresenter;
import it.algos.springvaadin.entity.role.RolePresenterNew;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.toolbar.AToolbar;
import it.algos.springvaadin.toolbar.ListToolbar;
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
public abstract class AlgosListNew extends AlgosViewNew implements IAlgosListNew {

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
    public AlgosListNew(IAlgosPresenter presenter, AlgosService service, @Qualifier(Cost.BAR_LIST) AToolbar toolbar, AlgosGrid grid) {
        super(presenter, service, toolbar);
        this.grid = grid;
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
        super.fixGUI();
        super.fixMenu();

        grid.setCaption("Adesso ci provo");
        grid.inizia(source, entityClazz, columns, items);
        this.addComponent(grid);

//        VerticalLayout bottomLayout = creaBottom(source);
//        this.addComponent(bottomLayout);
    }// end of method

//    /**
//     * Prepara la barra dei bottoni di comando
//     * Chiamato ogni volta che la finestra diventa attiva
//     */
//    protected VerticalLayout creaBottom(IAlgosPresenter source) {
//        VerticalLayout bottomLayout = new VerticalLayout();
//        bottomLayout.setMargin(false);
//        bottomLayout.setHeightUndefined();
//        List<AButtonType> typeButtons = service.getListTypeButtons();
//        inizializzaToolbar(source, typeButtons);
//        fixToolbar();
//
////        if (pref.isTrue(Cost.KEY_USE_DEBUG)) {
////            this.addStyleName("rosso");
////            grid.addStyleName("verde");
////        }// fine del blocco if
//
//        bottomLayout.addComponent((ListToolbar) toolbar);
//        return bottomLayout;
//    }// end of method


    /**
     * Prepara la toolbar
     * <p>
     * Seleziona i bottoni da mostrare nella toolbar
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source      dell'evento generato dal bottone
     * @param typeButtons da visualizzare
     */
    protected void inizializzaToolbar(ApplicationListener source, List<AButtonType> typeButtons) {
        toolbar.inizializza(source, typeButtons);
    }// end of method


    protected void fixToolbar() {
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
    }// end of method

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
