package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 10:58
 */
public interface IAlgosView extends View {

    /**
     * Restituisce il componente concreto
     *
     * @return il componente (window o panel)
     */
    public Component getComponent();

}// end of interface
