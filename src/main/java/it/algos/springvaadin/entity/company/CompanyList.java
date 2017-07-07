package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

/**
 * Created by gac on 25/06/17.
 * Presenta i dati di una lista usando una Grid
 * Aggiunge una ToolBar in basso coi bottoni di comando (contenenti già i listener per lanciare gli eventi)
 */
@Lazy
@Qualifier(Cost.TAG_COMP)
@SpringComponent
public class CompanyList extends AlgosList {

}// end of class
