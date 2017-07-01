package it.algos.springvaadin.grid;

import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import it.algos.springvaadin.entities.versione.Versione;
import it.algos.springvaadin.lib.LibColumn;
import it.algos.springvaadin.lib.LibSession;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by gac on 02/06/17.
 * Semplifica la costruzione di una Grid
 * Costruisce i listener che lanciano (fire) gli eventi
 */
@Lazy
@SpringComponent
public class AlgosGridOld extends VerticalLayout {
    public Grid grid;
    Class<?> beanType;

    private List<String> colonneVisibili;

    public AlgosGridOld() {
        int a=87;
    }

    public AlgosGridOld(Class<?> beanType) {
        this("", beanType, null, (List<String>) null);
    }// end of constructor

    public AlgosGridOld(Class<?> beanType, List items) {
        this("", beanType, items, (List<String>) null);
    }// end of constructor

    public AlgosGridOld(String caption, Class<?> beanType, List items) {
        this(caption, beanType, items, (List<String>) null);
    }// end of constructor

    public AlgosGridOld(Class<?> beanType, List items, List<String> colonneVisibili) {
        this("", beanType, items, colonneVisibili);
    }// end of constructor

    public AlgosGridOld(String caption, Class<?> beanType, List items, List<String> colonneVisibili) {
        super();
        grid=new Grid(beanType);
        this.beanType=beanType;
        grid.setHeightByRows(20);
        grid.setCaption(caption);
        if (items!=null) {
            grid.setItems(items);
        }// end of if cycle
        if (colonneVisibili!=null) {
            this.colonneVisibili = colonneVisibili;
        }// end of if cycle
        this.addComponent(grid);
        inizia();
    }// end of constructor

    /**
     * Metodo invocato subito DOPO il costruttore
     * <p>
     * Performing the initialization in a constructor is not suggested
     * as the state of the UI is not properly set up when the constructor is invoked.
     * <p>
     * Ci possono essere diversi metodi con @PostConstruct e firme diverse e funzionano tutti,
     * ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    protected void inizia() {
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.onlyColumnSelected();
        this.setColumns(colonneVisibili);
        this.setStyleName("rossoa");//@todo non funziona

//        Azione.addAllListeners(this);//@todo rimettere

        SingleSelectionModel modelloSingolo = (SingleSelectionModel) grid.getSelectionModel();
        modelloSingolo.addSingleSelectionListener(new SingleSelectionListener() {
            @Override
            public void selectionChange(SingleSelectionEvent singleSelectionEvent) {
                int a=87;
            }// end of inner method
        });// end of anonymous inner class

    }// end of method


    public void setColumns(List<String> colonneVisibili) {
        int lar = 0;

        if (LibSession.isDeveloper()) {
            lar = LibColumn.addColumns(Versione.class, grid);
        } else {
            lar = LibColumn.addColumns(Versione.class, grid, colonneVisibili);
        }// end of if/else cycle

        grid.setWidth(lar + "px");
    }// end of method

    /**
     * Lascia visibili solo le colonne selezionate nel costruttore o dalla sottoclasse
     */
    protected void onlyColumnSelected() {

        grid.removeAllColumns();
        if (colonneVisibili!=null) {
            for (String propertyName : colonneVisibili) {
                grid.addColumn(propertyName);
            }// end of for cycle
        }// end of if cycle

    }// end of method

}// end of class
