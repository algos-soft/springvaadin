package it.algos.springvaadin.home;

import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.AlgosField;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.view.AlgosViewImpl;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by gac on 01/06/17
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * SpringBoot inietta le sottoclassi specifiche (xxxPresenter, xxxList e xxxForm)
 * Nel metodo @PostConstruct, viene effettuato il casting alle property più generiche
 * Passa il controllo alla classe AlgosPresenter che gestisce la business logic
 * <p>
 * Riceve i comandi ed i dati da xxxPresenter (sottoclasse di AlgosPresenter)
 * Gestisce due modalità di presentazione dei dati: List e Form
 * Presenta i componenti grafici passivi
 * Presenta i componenti grafici attivi: azioni associate alla Grid e bottoni coi listener
 */
@SpringComponent
@Qualifier(Cost.TAG_HOME)
public class HomeView extends AlgosViewImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public HomeView(@Qualifier(Cost.TAG_HOME) AlgosList list, @Qualifier(Cost.TAG_HOME) AlgosForm form) {
        super(list, form);
    }// end of Spring constructor


    /**
     * Costruisce una Grid
     *
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    @Override
    public void setList(Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
//        this.setSizeUndefined();
        this.setMargin(false);
        this.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        this.setHeight(100, Sizeable.UNITS_PERCENTAGE);

        if (pref.isTrue(Cost.KEY_USE_DEBUG, false)) {
            this.addStyleName("blueBg");
            this.setWidth(90, Sizeable.UNITS_PERCENTAGE);
            this.setHeight(90, Sizeable.UNITS_PERCENTAGE);
        }// end of if cycle
        removeAllComponents();

        Panel panel = new Panel();
//        panel.setSizeUndefined();
        panel.addStyleName("yellowBg");
//        panel.setHeight("30em");
        panel.setHeight(100, Sizeable.UNITS_PERCENTAGE);
        Layout layout = new VerticalLayout();
        layout.addStyleName("greenBg");
        layout.setWidth(90, Sizeable.UNITS_PERCENTAGE);
//        layout.setHeight(90, Sizeable.UNITS_PERCENTAGE);

//        layout.setSizeUndefined();
//        layout.setHeight(100, Sizeable.UNITS_PERCENTAGE);
        for (int k = 0; k < 18; k++) {
            layout.addComponent(new Button("Pippoz"));
        }// end of for cycle
        panel.setContent(layout);
        VerticalLayout sopra = new VerticalLayout(new LabelRosso("Pippoz"));
        sopra.setMargin(false);
        sopra.setHeight(100, Sizeable.UNITS_PERCENTAGE);
        this.addComponent(sopra);
        this.addComponent(panel);
        VerticalLayout sotto = new VerticalLayout(new Button("Chiudi"));
        sotto.setMargin(false);
        sotto.setHeight(100, Sizeable.UNITS_PERCENTAGE);
        this.addComponent(sotto);
    }// end of method

}// end of class
