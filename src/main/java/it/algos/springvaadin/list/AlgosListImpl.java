package it.algos.springvaadin.list;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 20/06/17
 * Presenta i dati di una lista usando una Grid
 * Aggiunge una ToolBar in basso coi bottoni di comando (contenenti già i listener per lanciare gli eventi)
 */
@SpringComponent
public class AlgosListImpl extends VerticalLayout implements AlgosList {

    private Label label;


    //--eventuali intestazioni informative per List
    //--valori standard che possono essere sovrascritti nella classi specifiche
    private final static String CAPTION_DEFAULT = "Elenco di tutte le entities";
    protected String captionList;


    //--un eventuale Grid specifico verrebbe iniettato dal costruttore della sottoclasse concreta
    private AlgosGrid grid;

    //--un eventuale Toolbar specifica verrebbe iniettata dal costruttore della sottoclasse concreta
    private ListToolbar toolbar;

    /**
     * Costruttore @Autowired (nella superclasse)
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public AlgosListImpl(AlgosGrid grid, ListToolbar toolbar) {
        this.grid = grid;
        this.toolbar = toolbar;
    }// end of @Autowired constructor


    /**
     */
    @Override
    public void restart(Class<? extends AlgosEntity> clazz, List items, List<String> columns) {
        this.setMargin(false);

        this.removeAllComponents();

        if (captionList == null || captionList.equals("")) {
            captionList = CAPTION_DEFAULT;
        }// end of if cycle

        label = new Label(LibText.setRossoBold(captionList), ContentMode.HTML);
        this.addComponent(label);

        grid.inizia(clazz, items, columns);
        this.addComponent(grid);

        toolbar.inizia();
        this.addComponent(toolbar);

        if (AlgosApp.USE_DEBUG) {
            this.addStyleName("rosso");
            grid.addStyleName("verde");
        }// fine del blocco if
    }// end of method


    public static List<Field> getFields(List<String> fieldsName) {
        List<Field> lista = new ArrayList<>();
        Field field;

        for (String publicFieldName : fieldsName) {
            field = LibReflection.getField(Versione.class, publicFieldName);
            if (field != null) {
                lista.add(field);
            }// end of if cycle
        }// end of for cycle

        return lista;
    }// end of method


    /**
     * Righe selezionate nella Grid
     *
     * @return numero di righe selezionate
     */
    @Override
    public int numRigheSelezionate() {
        return grid.numRigheSelezionate();
    }// end of method

    /**
     * Una riga selezionata nella grid
     *
     * @return true se è selezionata una ed una sola riga nella Grid
     */
    @Override
    public boolean isUnaRigaSelezionata() {
        return grid.unaRigaSelezionata();
    }// end of method

    /**
     * Abilita il bottone Create dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableNew(boolean status) {
        toolbar.enableNew(status);
    }// end of method

    /**
     * Abilita il bottone Edit dela Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableEdit(boolean status) {
        toolbar.enableEdit(status);
    }// end of method

    /**
     * Abilita il bottone Delet della Grid
     *
     * @param status true se abilitato, false se disabilitato
     */
    @Override
    public void enableDelete(boolean status) {
        toolbar.enableDelete(status);
    }// end of method

    /**
     * Una lista di entity selezionate nella Grid, in funzione di Grid.SelectionMode()
     * Lista nulla, se nessuna riga è selezionata
     * Lista di un elemento, se è Grid.SelectionMode.SINGLE
     * Lista di più elementi, se è Grid.SelectionMode.MULTI
     *
     * @return lista di una o più righe selezionate, null se nessuna riga è selezionata
     */
    @Override
    public List<AlgosEntity> getEntityBeans() {
        return grid.getEntityBeans();
    }// end of method

    public Label getLabel() {
        return label;
    }

    public AlgosGrid getGrid() {
        return grid;
    }

    public ListToolbar getToolbar() {
        return toolbar;
    }

    @Override
    public Component getComponent() {
        return this;
    }// end of method

    //    public static Annotation getAnnotationForm(String publicFieldName) {
//        return LibReflection.getField(Versione.class, publicFieldName).getAnnotation(AIField.class);
//    }// end of method
//
//    public static Annotation getAnnotationForm(Field field) {
//        return field.getAnnotation(AIField.class);
//    }// end of method
//
//    public static Annotation getAnnotationList(Field field) {
//        return field.getAnnotation(AIColumn.class);
//    }// end of method
//
//    public ListToolbar getToolbar() {
//        return toolbar;
//    }

}// end of class
