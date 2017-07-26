package it.algos.springvaadin.event;


import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 04/06/17.
 * Eventi generati da una azione (nella Grid, ad esempio)
 * Link: http://www.baeldung.com/spring-events
 */
public class ActionSpringEvent extends AlgosSpringEvent {

    private TipoAzione tipoAzioneRichiesta;
    private AlgosEntity entityBean;

    public ActionSpringEvent(AlgosPresenter source, TipoAzione tipoAzioneRichiesta) {
        super(source);
        this.tipoAzioneRichiesta = tipoAzioneRichiesta;
    }// end of constructor

    public ActionSpringEvent(AlgosPresenter source, TipoAzione tipoAzioneRichiesta, AlgosEntity entityBean) {
        super(source);
        this.tipoAzioneRichiesta = tipoAzioneRichiesta;
        this.entityBean = entityBean;
    }// end of constructor

    public TipoAzione getAzione() {
        return tipoAzioneRichiesta;
    }// end of method

    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method

}// end of class