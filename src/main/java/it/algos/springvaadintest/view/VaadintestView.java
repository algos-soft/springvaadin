package it.algos.springvaadintest.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.label.LabelBold;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.menu.MenuLayout;
import it.algos.springvaadin.view.AView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 07-dic-2017
 * Time: 20:47
 * Una vista 'normalmente' si compone di:
 * MenuBar - MenuLayout
 * Caption - Eventuali scritte esplicative come collezione usata, records trovati, ecc
 * Body - Grid. Scorrevole
 * Bottom - Barra dei bottoni
 */
@Slf4j
@SpringView(name = "test")
public class VaadintestView extends AView {


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       this.addComponent(new Label(htlm.setRossoBold("Test"), ContentMode.HTML));
    }// end of method


}// end of class
