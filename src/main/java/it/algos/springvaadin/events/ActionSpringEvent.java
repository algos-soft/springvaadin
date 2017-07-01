package it.algos.springvaadin.events;


import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;

/**
 * Created by gac on 04/06/17.
 * Link: http://www.baeldung.com/spring-events
 */
public class ActionSpringEvent extends AlgosSpringEvent {

    private Azione azioneRichiesta;
    private AlgosModel entityBean;

    public ActionSpringEvent(AlgosPresenter source, Azione azioneRichiesta) {
        super(source);
        this.azioneRichiesta = azioneRichiesta;
    }// end of constructor

    public ActionSpringEvent(AlgosPresenter source, Azione azioneRichiesta, AlgosModel entityBean) {
        super(source);
        this.azioneRichiesta = azioneRichiesta;
        this.entityBean = entityBean;
    }// end of constructor

    public Azione getAzione() {
        return azioneRichiesta;
    }// end of method

    public AlgosModel getEntityBean() {
        return entityBean;
    }// end of method

}// end of class