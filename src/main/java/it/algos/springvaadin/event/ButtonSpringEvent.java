package it.algos.springvaadin.event;


import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 03/06/17.
 * Eventi generati dai bottoni
 * Link: http://www.baeldung.com/spring-events
 */
public class ButtonSpringEvent extends AlgosSpringEvent {


    private final Bottone bottonePremuto;


    public ButtonSpringEvent(AlgosPresenter source, Bottone bottonePremuto) {
        super(source);
        this.bottonePremuto = bottonePremuto;
    }// end of constructor


    public Bottone getBottone() {
        return bottonePremuto;
    }// end of method


}// end of classZSA >aq A<za<Q