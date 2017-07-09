package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import it.algos.springvaadin.model.AlgosEntity;

import java.util.List;

/**
 * Created by gac on 09/07/17
 * .
 */
public interface AlgosList {

    /**
     */
    public void restart(Class<? extends AlgosEntity> clazz, List items, List<String> columns);


    public int numRigheSelezionate();


    public boolean unaRigaSelezionata();

    /**
     * Restituisce il componente concreto
     */
    public Component getComponent();


}// end of interfacev
