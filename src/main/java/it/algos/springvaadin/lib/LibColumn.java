package it.algos.springvaadin.lib;

import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.*;
import it.algos.springvaadin.field.AFieldType;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.renderer.IconRenderer;
import it.algos.springvaadin.renderer.ByteStringRenderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

/**
 * Created by gac on 14/06/17
 * .
 */
public abstract class LibColumn {


    private final static int WIDTH_TEXT_SMALL = 80;
    private final static int WIDTH_TEXT_NORMAL = 100;
    private final static int WIDTH_TEXT_LARGE = 120;
    public final static int WIDTH_BYTE = 160;
    private final static int WIDTH_LOCAL_DATE = 130;
    private final static int WIDTH_LOCAL_DATE_TIME = 160;


    public static Annotation getAnnotation(Field field) {
        return field.getAnnotation(AIColumn.class);
    }// end of method


    public static AIColumn getColumnAnnotation(final Class<? extends AEntity> clazz, String publicFieldName) {
        Field field = LibReflection.getField(clazz, publicFieldName);

        if (publicFieldName.equals(Cost.PROPERTY_ID)) {
            return null;
        } else {
            if (field != null) {
                return field.getAnnotation(AIColumn.class);
            } else {
                return null;
            }// end of if/else cycle
        }// end of if/else cycle

    }// end of method


    public static AIField getFieldAnnotation(final Class<? extends AEntity> clazz, String publicFieldName) {
        return LibReflection.getField(clazz, publicFieldName).getAnnotation(AIField.class);
    }// end of method


    /**
     * Aggiunge una colonna
     * Se ci sono Annotazioni, la regola
     */
    public static int addColumn(final Class<? extends AEntity> clazz, Grid grid, String publicFieldName) {
        AIColumn columnAnnotation = getColumnAnnotation(clazz, publicFieldName);
        Grid.Column colonna = null;
        AFieldType type = LibAnnotation.getTypeColumn(clazz, publicFieldName);
        String name = LibAnnotation.getNameColumn(clazz, publicFieldName);
        int width = LibAnnotation.getColumnWith(clazz, publicFieldName);

        DateRenderer render = new DateRenderer("%1$te-%1$tb-%1$tY", Locale.ITALIAN);
        LocalDateRenderer renderDate = new LocalDateRenderer("d-MMM-u", Locale.ITALIAN);
        LocalDateTimeRenderer renderTime = new LocalDateTimeRenderer("d-MMM-uu HH:mm", Locale.ITALIAN);
        IconRenderer renderIcon = new IconRenderer();
        ByteStringRenderer renderByte = new ByteStringRenderer();

        if (grid == null || LibText.isEmpty(publicFieldName)) {
            return 0;
        }// end of if cycle

        colonna = grid.addColumn(publicFieldName);
        colonna.setCaption(name);
        colonna.setWidth(width > 0 ? width : WIDTH_TEXT_NORMAL);

        if (type == AFieldType.localdate) {
            colonna.setRenderer(renderDate);
            colonna.setWidth(WIDTH_LOCAL_DATE);
        }// end of if cycle

        if (type == AFieldType.localdatetime) {
            colonna.setRenderer(renderTime);
            colonna.setWidth(WIDTH_LOCAL_DATE_TIME);
        }// end of if cycle

        if (type == AFieldType.icon) {
            colonna.setRenderer(renderIcon);
            colonna.setWidth(WIDTH_TEXT_NORMAL);
        }// end of if cycle

        if (type == AFieldType.json) {
            colonna.setRenderer(renderByte);
            colonna.setWidth(WIDTH_BYTE);
        }// end of if cycle

        if (columnAnnotation == null && publicFieldName.equals(Cost.PROPERTY_ID)) {
            colonna.setWidth(LibAnnotation.getListWidthID(clazz));
        }// end of if cycle

        return ((Double) colonna.getWidth()).intValue();
    }// end of method


    /**
     * Aggiunge le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(final Class<? extends AEntity> clazz, Grid grid, List<String> columns) {
        int lar = 0;
        grid.removeAllColumns();

        if (columns != null) {
            for (String titolo : columns) {
                if (LibAnnotation.isColumnVisibile(clazz, titolo)) {
                    lar += addColumn(clazz, grid, titolo);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        return lar;
    }// end of method

    /**
     * Aggiunge tutte le colonne
     * Se ci sono Annotazioni, le regola
     */
    public static int addColumns(final Class<? extends AEntity> beanType, Grid grid) {
        return addColumns(beanType, grid, LibReflection.getAllFieldNames(beanType, false, false));
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
        return addColumns(grid, LibReflection.getAllFieldNames(grid.getBeanType()));
    }// end of method

}// end of static class
