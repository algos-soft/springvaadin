package it.algos.springvaadin.event;


import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 04/06/17.
 * Eventi generati da una azione (nella Grid, ad esempio)
 * Link: http://www.baeldung.com/spring-events
 */
public class ActionSpringEvent extends AlgosSpringEvent {

    private Azione azioneRichiesta;
    private AlgosEntity entityBean;

    public ActionSpringEvent(AlgosPresenter source, Azione azioneRichiesta) {
        super(source);
        this.azioneRichiesta = azioneRichiesta;
    }// end of constructor

    public ActionSpringEvent(AlgosPresenter source, Azione azioneRichiesta, AlgosEntity entityBean) {
        super(source);
        this.azioneRichiesta = azioneRichiesta;
        this.entityBean = entityBean;
    }// end of constructor

    public Azione getAzione() {
        return azioneRichiesta;
    }// end of method

    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method

}// end of class