package it.algos.springvaadintest.view;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import it.algos.springvaadin.view.AView;
import lombok.extern.slf4j.Slf4j;

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
     * Costruttore @Autowired (nella superclasse)
     */
    public VaadintestView() {
        super(null,null);
    }// end of Spring constructor


    /**
     * Metodo invocato (da SpringBoot) ogni volta che si richiama la view dallo SpringNavigator
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);
        this.addComponent(new Label(htlm.setRossoBold("Test"), ContentMode.HTML));
    }// end of method

}// end of class
