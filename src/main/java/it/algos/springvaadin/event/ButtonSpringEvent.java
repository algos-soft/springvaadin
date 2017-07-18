package it.algos.springvaadin.event;


import it.algos.springvaadin.presenter.AlgosPresenter;

/**
 * Created by gac on 03/06/17.
 * Link: http://www.baeldung.com/spring-events
 */
public class ButtonSpringEvent extends AlgosSpringEvent {

    private Bottone bottonePremuto;
    private String referenceViewName;

    public ButtonSpringEvent(AlgosPresenter source, Bottone bottonePremuto) {
        super(source);
        this.bottonePremuto = bottonePremuto;
    }// end of constructor

    public ButtonSpringEvent(AlgosPresenter source, Bottone bottonePremuto, String referenceViewName) {
        super(source);
        this.bottonePremuto = bottonePremuto;
        this.referenceViewName = referenceViewName;
    }// end of constructor

    public Bottone getBottone() {
        return bottonePremuto;
    }// end of method

    public String getView() {
        return referenceViewName;
    }// end of method

}// end of class