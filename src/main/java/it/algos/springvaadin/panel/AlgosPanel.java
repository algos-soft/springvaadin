package it.algos.springvaadin.panel;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 26-nov-2017
 * Time: 15:24
 * <p>
 * Pannello scorrevole
 * Sottoclasse specifica per regolare i flag necessari
 */
@SpringComponent
@Scope("prototype")
public class AlgosPanel extends Panel {

    /**
     * Costruttore @Autowired
     */
    public AlgosPanel() {
        super();
    }// end of Spring constructor


    /**
     * Costruttore
     */
    public AlgosPanel(Component component) {
        super();
        inizia();
        this.setContent(component);
    }// end of Spring constructor


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    public void inizia() {
        this.addStyleName(ValoTheme.PANEL_BORDERLESS);
        this.setWidth("100%");
        this.setHeight("100%");
    }// end of method

}// end of class
