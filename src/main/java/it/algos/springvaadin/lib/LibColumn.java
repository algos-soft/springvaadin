package it.algos.springvaadin.lib;

import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.model.AEntity;

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


    public static AIColumn getColumnAnnotation(final Class<? extends AEntity> clazz, String publicFieldName) {
        if (publicFieldName.equals(Cost.PROPERTY_ID)) {
            return null;
        } else {
            return LibReflection.getField(clazz, publicFieldName).getAnnotation(AIColumn.class);
        }// end of if/else cycle
    }// end of method


    public static AIField getFieldAnnotation(final Class<? extends AEntity> clazz, String publicFieldName) {
        return LibReflection.getField(clazz, publicFieldName).getAnnotation(AIField.class);
    }// end of method


    /**
     * Aggiunge una colonna
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumn(final Class<? extends AEntity> clazz, Grid grid, String publicFieldName) {
        AIColumn columnAnnotation = getColumnAnnotation(clazz, publicFieldName);
        Grid.Column colonna = null;
        AFieldType type = LibAnnotation.getTypeColumn(clazz, publicFieldName);
        String name = LibAnnotation.getNameColumn(clazz, publicFieldName);
        int width = LibAnnotation.getColumnWith(clazz, publicFieldName);
        DateRenderer render = new DateRenderer("%1$te-%1$tb-%1$tY", Locale.ITALIAN);
        LocalDateTimeRenderer renderLocal = new LocalDateTimeRenderer("d-MMM-u", Locale.ITALIAN);

        if (grid == null && LibText.isEmpty(publicFieldName)) {
            return 0;
        }// end of if cycle

        if (columnAnnotation != null) {
            switch (type) {
                case date:
                    colonna = grid.addColumn(publicFieldName, render);
                    break;
                case localdatetime:
                    colonna = grid.addColumn(publicFieldName, renderLocal);
                    break;
                default: // caso non definito
                    colonna = grid.addColumn(publicFieldName);
                    break;
            } // fine del blocco switch
            colonna.setCaption(name);
            colonna.setWidth(width);
        } else {
            colonna = grid.addColumn(publicFieldName);
            if (publicFieldName.equals(Cost.PROPERTY_ID)) {
                colonna.setWidth(LibAnnotation.getListWithID(clazz));
            }// end of if cycle
        }// end of if/else cycle

        return ((Double) colonna.getWidth()).intValue();
    }// end of method


    /**
     * Aggiunge le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(final Class<? extends AEntity> clazz, Grid grid, List<String> colonneVisibili) {
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
    public static int addColumns(final Class<? extends AEntity> beanType, Grid grid) {
        return addColumns(beanType, grid, LibReflection.getAllFieldName(beanType, false));
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
        return addColumns(grid, LibReflection.getAllFieldName(grid.getBeanType(), false));
    }// end of method

}// end of static class
