package it.algos.springvaadin.event;


import it.algos.springvaadin.azione.TipoAzione;
import it.algos.springvaadin.model.AEntity;
import org.springframework.context.ApplicationListener;

/**
 * Created by gac on 04/06/17.
 * Eventi generati da una azione (nella Grid, ad esempio)
 * Link: http://www.baeldung.com/spring-events
 */
public class ActionSpringEvent extends AEvent {

    //--property obbligatoria
    private final TipoAzione tipo;

    //--property facoltativa; ha senso solo per alcune azioni
    private final AEntity entityBean;

    public ActionSpringEvent(ApplicationListener source, TipoAzione tipo) {
        this(source, tipo, (AEntity) null);
    }// end of constructor

    public ActionSpringEvent(ApplicationListener source, TipoAzione tipo, AEntity entityBean) {
        super(source);
        this.tipo = tipo;
        this.entityBean = entityBean;
    }// end of constructor

    public TipoAzione geTipo() {
        return tipo;
    }// end of method

    public AEntity getEntityBean() {
        return entityBean;
    }// end of method

}// end of class