package it.algos.springvaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 21:23
 */
@Slf4j
public abstract class AView extends VerticalLayout implements IAView {

    /**
     * Contenitore grafico per la barra di menu principale e per il menu/bottone del Login
     */
    @Autowired
    protected MenuLayout menuLayout;

    @Autowired
    protected ATextService text;

    @Autowired
    protected AHtmlService htlm;

    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void inizia() {
        //@todo RIMETTERE
//        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
        this.addStyleName("blueBg");
//        }// end of if cycle

        this.setMargin(false);
        this.setWidth("100%");
        this.setHeight("100%");

        menuLayout.start();
        this.addComponent(menuLayout);
    }// end of method

}// end of class
