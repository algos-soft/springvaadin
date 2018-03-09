package it.algos.springvaadintest.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.VaadinSessionScope;
import it.algos.springvaadin.app.FactoryLabel;
import it.algos.springvaadin.app.Tool;
import it.algos.springvaadin.footer.AFooter;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.service.AStartService;
import it.algos.springvaadin.ui.AUI;
import it.algos.springvaadintest.entity.milite.Milite;
import it.algos.springvaadintest.entity.milite.MiliteList;
import it.algos.springvaadintest.entity.milite.MiliteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import it.algos.springvaadintest.entity.prova.Prova;
import it.algos.springvaadintest.entity.prova.ProvaList;

/**
 * Created by gac on 30/05/17.
 * <p>
 * UI di partenza dell'applicazione, selezionata da SpringBoot con @SpringUI()
 * Questa classe DEVE estendere AUIParams
 * Questa classe DEVE prevedere l'Annotation @SpringUI()
 * All'interno dell'applicazionbe, @SpringUI() deve essere utilizzata in una sola classe che estenda UI
 */
@Theme("algos")
@SpringUI()
@SpringViewDisplay()
@Slf4j
@VaadinSessionScope
public class SpringvaadintestUI extends AUI {


    @Autowired
    private TestLoginForm loginForm;


    @Autowired
    private MiliteService militeService;


    @Autowired
    private AStartService startService;


    @Autowired
    private Tool tool;

//    @Resource(name = "&tool")
//    private FactoryLabel toolFactory;

    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void cambiaLogin() {
        if (login != null && loginForm != null) {
            login.loginForm = loginForm;
            if (militeService != null) {
                login.userService = militeService;
                login.loginForm.userService = militeService;
                startService.userService = militeService;
            }// end of if cycle
        }// end of if cycle
    }// end of method


    /**
     * Metodo @PostConstruct invocato (da Spring) subito DOPO il costruttore (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void fixProjectFooter() {
        Object alfa= tool;
        AFooter.PROJECT = "Springvaadin";
        AFooter.VERSION = "0.1";
    }// end of method

    /**
     * Creazione delle viste (moduli) specifiche dell'applicazione.
     * La superclasse AlgosUIParams crea (flag true/false) le viste (moduli) usate da tutte le applicazioni
     * I flag si regolano in @PostConstruct:init()
     * <p>
     * Aggiunge al menu generale, le viste (moduli) disponibili alla partenza dell'applicazione
     * Ogni modulo può eventualmente modificare il proprio menu
     * <p>
     * Deve (DEVE) essere sovrascritto dalla sottoclasse
     * Chiama il metodo  addView(...) della superclasse per ogni vista (modulo)
     * La vista viene aggiunta alla barra di menu principale (di partenza)
     * La vista viene aggiunta allo SpringViewProvider usato da SpringNavigator
     */
    protected void addVisteSpecifiche() {
		menuLayout.addView(Prova.class, ProvaList.class);
        menuLayout.addView(Milite.class, MiliteList.class);
    }// end of method


    /**
     * Lancio della vista iniziale
     * Chiamato DOPO aver finito di costruire il MenuLayout e la AlgosUI
     * Deve (DEVE) essere sovrascritto dalla sottoclasse
     */
    @Override
    protected void startVistaIniziale() {
        getNavigator().navigateTo(ACost.VIEW_ROL_LIST);
    }// end of method

}// end of class