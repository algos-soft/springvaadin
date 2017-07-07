package it.algos.springvaadin.list;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 20/06/17
 * .
 */
@SpringComponent
public class AlgosList extends VerticalLayout {

    private Label label;

    @Autowired
    @Lazy
    private AlgosGrid grid;


    @Autowired
    private ListToolbar toolbar;

    //--eventuali intestazioni informative per List e Form
    //--valori standard che possono essere sovrascritti nella classi specifiche
    protected String captionList;
    private final static String CAPTION_DEFAULT = "Elenco di tutte le entities";



    /**
     */
    public void inizia(Class<? extends AlgosModel> clazz, List items, List<String> colonneVisibili) {
        this.setMargin(false);

        this.removeAllComponents();

        if (captionList == null || captionList.equals("")) {
            captionList = CAPTION_DEFAULT;
        }// end of if cycle

        label = new Label(LibText.setRossoBold(captionList), ContentMode.HTML);
        this.addComponent(label);

        grid.inizia(clazz, items, colonneVisibili);
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


    public int numRigheSelezionate() {
        return grid.numRigheSelezionate();
    }// end of method

    public boolean unaRigaSelezionata() {
        return grid.unaRigaSelezionata();
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
