package it.algos.springvaadin.entity.versione;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 01/06/17
 * <p>
 * Classe che gestisce la business logic di un 'modulo'
 * Viene creata la prima volta dalla xxxNavView (injected) che a sua volta viene creata dallo SpringNavigator
 * Classe specifica per costruire i riferimenti usati dalla superclasse
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersionePresenter extends AlgosPresenter {

    @Autowired
    private VersioneRepository repo;

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersionePresenter(@Qualifier(Cost.TAG_VERS) AlgosView view) {
        super(view, null);
    }// end of Spring constructor

    /**
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     * oppure
     * metodo invocato da un Evento (azione) che necessita di aggiornare e ripresentare la Lista
     * tipo dopo un delete, dopo un nuovo record, dopo la modifica di un record
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Passa il controllo alla view con i dati necessari
     */
    protected void presentaLista() {
        List items = new ArrayList();
        items= repo.findAll();
        List<String> columns = new ArrayList<>();
        columns.add("ordine");
        columns.add("titolo");
        columns.add("descrizione");
        columns.add("modifica");

        view.setList(Versione.class, items, columns);
    }// end of method

}// end of class
