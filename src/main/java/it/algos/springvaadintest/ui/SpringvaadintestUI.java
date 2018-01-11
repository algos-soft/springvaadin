package it.algos.springvaadintest.ui;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import it.algos.springvaadin.entity.address.AddressList;
import it.algos.springvaadin.entity.logtype.LogtypeList;
import it.algos.springvaadin.entity.log.LogList;
import it.algos.springvaadin.entity.stato.StatoList;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.company.CompanyList;
import it.algos.springvaadin.entity.user.UserList;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.home.AHomeView;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.ui.AUI;
import it.algos.springvaadintest.view.VaadintestView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

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
        menuLayout.addView(AddressList.class);
        menuLayout.addView(LogtypeList.class);
        menuLayout.addView(LogList.class);
        menuLayout.addView(StatoList.class);
        if (AlgosApp.USE_MULTI_COMPANY) {
            menuLayout.addView(CompanyList.class);
        }// end of if cycle
    }// end of method

    /**
     * Lancio della vista iniziale
     * Chiamato DOPO aver finito di costruire il MenuLayout e la AlgosUI
     * Deve (DEVE) essere sovrascritto dalla sottoclasse
     */
    @Override
    protected void startVistaIniziale() {
        getNavigator().navigateTo(ACost.VIEW_HOME);
    }// end of method

}// end of class
