package it.algos.springvaadin.entity.company;

import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.list.AlgosListImpl;
import it.algos.springvaadin.toolbar.AToolbarImpl;
import it.algos.springvaadin.toolbar.ListToolbar;
import it.algos.springvaadin.ui.AlgosUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.Property;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 13/06/17.
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_COMP)
public class CompanyList extends AlgosListImpl {

    @Autowired
    private CompanyService service;

    /**
     * Costruttore @Autowired (nella superclasse)
     */
    public CompanyList(AlgosGrid grid, ListToolbar toolbar) {
        super(grid, toolbar);
    }// end of Spring constructor

    /**
     * Chiamato ogni volta che la finestra diventa attiva
     * Può essere sovrascritto per un'intestazione (caption) della grid
     */
    @Override
    protected void inizializza() {
        if (LibSession.isDeveloper()) {
            super.caption = "";
            super.caption += "</br>Lista visibile solo all'admin. Filtrata su una sola company";
            super.caption += "</br>NON usa la company (ovvio)";
            super.caption += "</br>Usabile direttamente, ma anche estendendo la classe";
            super.caption += "</br>Solo il developer vede queste note";
        }// end of if cycle
    }// end of method



    protected void fixToolbar() {
        if (LibSession.isDeveloper()) {
            this.addChangeCompanyButton();
        }// end of if cycle
    }// end of method


    private void addChangeCompanyButton() {
        List<String> companyList = service.findAll();

        if (companyList == null) {
            return;
        }// end of if cycle
        companyList.add(0, "Tutte");

        final ComboBox country = new ComboBox("", companyList);
        ((AToolbarImpl) toolbar).addComponent(country);

        country.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                Object event = valueChangeEvent.getValue();

                if (event != null && event instanceof Company) {
                    LibSession.setCompany((Company) event);
                } else {
                    LibSession.setCompany(null);
                }// end of if/else cycle

                Object ui = getUI();
                if (ui instanceof AlgosUI) {
                    ((AlgosUI) ui).footer.setAppMessage("SpringVaadin 1.0");
                }// end of if cycle

            }// end of inner method
        });// end of anonymous inner class
    }// end of method

}// end of class
