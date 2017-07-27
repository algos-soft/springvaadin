package it.algos.springvaadin.event;


import it.algos.springvaadin.azione.Azione;
import it.algos.springvaadin.azione.TipoAzione;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 04/06/17.
 * Eventi generati da una azione (nella Grid, ad esempio)
 * Link: http://www.baeldung.com/spring-events
 */
public class ActionSpringEvent extends AlgosSpringEvent {

    //--property obbligatoria
    private final TipoAzione tipo;

    //--property facoltativa; ha senso solo per alcune azioni
    private final AlgosEntity entityBean;

    public ActionSpringEvent(AlgosPresenter source, TipoAzione tipo) {
        this(source, tipo, (AlgosEntity) null);
    }// end of constructor

    public ActionSpringEvent(AlgosPresenter source, TipoAzione tipo, AlgosEntity entityBean) {
        super(source);
        this.tipo = tipo;
        this.entityBean = entityBean;
    }// end of constructor

    public TipoAzione geTipo() {
        return tipo;
    }// end of method

    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method

}// end of class