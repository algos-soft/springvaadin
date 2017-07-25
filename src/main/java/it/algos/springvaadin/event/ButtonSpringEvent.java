package it.algos.springvaadin.event;


import it.algos.springvaadin.bottone.AlgosBottone;
import it.algos.springvaadin.bottone.Bottone;
import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 03/06/17.
 * Link: http://www.baeldung.com/spring-events
 */
public class ButtonSpringEvent extends AlgosSpringEvent {

    private AlgosBottone bottonePremuto;
    private String referenceViewName;

    public ButtonSpringEvent(AlgosPresenter source, AlgosBottone bottonePremuto) {
        super(source);
        this.bottonePremuto = bottonePremuto;
    }// end of constructor

    public ButtonSpringEvent(AlgosPresenter source, AlgosBottone bottonePremuto, String referenceViewName) {
        super(source);
        this.bottonePremuto = bottonePremuto;
        this.referenceViewName = referenceViewName;
    }// end of constructor

    public AlgosBottone getBottone() {
        return bottonePremuto;
    }// end of method

    public String getView() {
        return referenceViewName;
    }// end of method

}// end of class