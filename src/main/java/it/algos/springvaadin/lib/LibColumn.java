package it.algos.springvaadin.lib;

import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.model.AlgosModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

/**
 * Created by gac on 14/06/17
 * .
 */
public abstract class LibColumn {

    public static Annotation getAnnotation(Field field) {
        return field.getAnnotation(AIColumn.class);
    }// end of method


    public static Annotation getAnnotation(final Class<? extends AlgosModel> clazz, String publicFieldName) {
        return LibReflection.getField(clazz, publicFieldName).getAnnotation(AIColumn.class);
    }// end of method


    /**
     * Aggiunge una colonna
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumn(final Class<? extends AlgosModel> clazz, Grid grid, String publicFieldName) {
        AIColumn annotation;
        Grid.Column colonna = null;
        DateRenderer render = new DateRenderer("%1$te-%1$tb-%1$tY", Locale.ITALIAN);
//        DateTimeFormatter formatterOld = DateTimeFormatter
//                .ofLocalizedDateTime(FormatStyle.MEDIUM)
//                .withLocale(Locale.ITALY);

        if (grid == null && LibText.isEmpty(publicFieldName)) {
            return 0;
        }// end of if cycle

        colonna = grid.addColumn(publicFieldName);
        annotation = (AIColumn) getAnnotation(clazz, publicFieldName);

        if (annotation != null) {
            colonna.setCaption(annotation.header());
            colonna.setWidth(annotation.width());

            if (annotation.type() == AFType.date) {
                colonna.setRenderer(render);
            }// end of if cycle
            if (annotation.type() == AFType.localdatetime) {
                //@todo dobbiamo aspettare vaadin 8.1

//                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//                Grid.Column<Versione, Date> dateColumn = grid.addColumn(publicFieldName, new DateRenderer(df));
//                dateColumn.setCaption(publicFieldName);

            }// end of if cycle
        } else {
            if (publicFieldName.equals(Cost.PROPERTY_ID)) {
                colonna.setWidth(50);
            }// end of if cycle
        }// end of if/else cycle

        return ((Double) colonna.getWidth()).intValue();
    }// end of method


    /**
     * Aggiunge le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(final Class<? extends AlgosModel> clazz, Grid grid, List<String> colonneVisibili) {
        int lar = 0;
        grid.removeAllColumns();

        if (colonneVisibili != null) {
            for (String titolo : colonneVisibili) {
                lar += addColumn(clazz, grid, titolo);
            }// end of for cycle
        }// end of if cycle

        return lar;
    }// end of method

    /**
     * Aggiunge tutte le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(final Class<? extends AlgosModel> beanType, Grid grid) {
        return addColumns(beanType, grid, LibReflection.getAllPropertyName(beanType));
    }// end of method

    /**
     * Aggiunge le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(Grid grid, List<String> colonneVisibili) {

        for (String titolo : colonneVisibili) {
            grid.addColumn(titolo);
        }// end of for cycle

        return 400;
//        return addColumns(grid.getBeanType(), grid, LibReflection.getAllPropertyName(grid.getBeanType()));
    }// end of method

    /**
     * Aggiunge tutte le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(Grid grid) {
        return addColumns(grid, LibReflection.getAllPropertyName(grid.getBeanType()));
    }// end of method

}// end of static class
