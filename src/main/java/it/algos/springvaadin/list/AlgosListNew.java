package it.algos.springvaadin.list;

import com.vaadin.ui.Component;
import it.algos.springvaadin.view.AlgosViewNew;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:56
 * <p>
 * Implementazione standard dell'annotation AlgosList
 * Questa vista 'normalmente' si compone di:
 * Top - Eventuali scritte esplicative come collezione usata, records trovati, ecc
 * Body - Grid. Scorrevole
 * Bottom - Barra dei bottoni
 */
@Slf4j
public class AlgosListNew extends AlgosViewNew {

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent(){
        return null;
    }// end of method

}// end of class
