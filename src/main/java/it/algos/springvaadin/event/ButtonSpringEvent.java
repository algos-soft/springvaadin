package it.algos.springvaadin.event;


import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;

/**
 * Created by gac on 03/06/17.
 * Eventi generati dai bottoni
 * Link: http://www.baeldung.com/spring-events
 */
public class ButtonSpringEvent extends AlgosSpringEvent {


    private final Bottone bottonePremuto;

    //--L'entityBean viene inserita come parametro opzionale
    private AlgosEntity entityBean;


    public ButtonSpringEvent(AlgosPresenterImpl source, Bottone bottonePremuto) {
        this(source,bottonePremuto,(AlgosEntity)null);
    }// end of constructor


    public ButtonSpringEvent(AlgosPresenterImpl source, Bottone bottonePremuto,AlgosEntity entityBean) {
        super(source);
        this.bottonePremuto = bottonePremuto;
        this.entityBean = entityBean;
    }// end of constructor


    public Bottone getBottone() {
        return bottonePremuto;
    }// end of method

    public AlgosEntity getEntityBean() {
        return entityBean;
    }// end of method
}// end of classZSA >aq A<za<Q