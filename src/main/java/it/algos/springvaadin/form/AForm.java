package it.algos.springvaadin.form;

import it.algos.springvaadin.grid.IAGrid;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.view.AView;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 23:23
 */
@Slf4j
public abstract class AForm extends AView implements IAForm {

    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     *
     * @param presenter iniettato da Spring
     */
    public AForm(IAPresenter presenter) {
        super(presenter);
    }// end of Spring constructor

}// end of class
