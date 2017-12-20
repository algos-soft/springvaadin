package it.algos.springvaadin.service;

import com.sun.deploy.panel.ExceptionListDialog;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import it.algos.springvaadin.annotation.AIColumn;
import it.algos.springvaadin.annotation.AIField;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.user.User;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.renderer.ByteStringRenderer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 09-dic-2017
 * Time: 11:42
 */
@SpringComponent
@Scope("singleton")
public class AColumnService {


    @Autowired
    public ATextService text;

    @Autowired
    public AAnnotationService annotation;

    @Autowired
    public AReflectionService reflection;

    public final static int WIDTH_CHECK_BOX = 65;
    public final static int WIDTH_ICON = 90;
    public final static int WIDTH_TEXT_SMALL = 80;
    public final static int WIDTH_TEXT_NORMAL = 100;
    public final static int WIDTH_TEXT_LARGE = 120;
    public final static int WIDTH_BYTE = 160;
    public final static int WIDTH_LOCAL_DATE = 160;
    public final static int WIDTH_LOCAL_DATE_TIME = 200;


//    public AIField getFieldAnnotation(final Class<? extends AEntity> clazz, String publicFieldName) {
//        return reflection.getField(clazz, publicFieldName).getAnnotation(AIField.class);
//    }// end of method


    /**
     * Aggiunge una colonna
     * Nella Grid le colonne possono essere di due tipi: Text o Component
     *
     * @param grid                a cui aggiungere la colonna
     * @param reflectionJavaField di riferimento per estrarre la Annotation
     *
     * @return la colonna appena creata
     */
    public Grid.Column add(final Grid grid, final Field reflectionJavaField) {
        Grid.Column colonna = null;
        EAFieldType type = annotation.getColumnType(reflectionJavaField);

        switch (type) {
            case checkbox:

//                 colonna = grid.addComponentColumn(
//                        entity -> {
//                            Object value = ((User)entity).isEnabled();
//                            Component comp = null;
//                             if (value instanceof Boolean) {
//                                            comp = new CheckBox();
//                                            (comp).setEnabled(false);
//                                            ((CheckBox) comp).setValue((Boolean) value);
//                                        }// end of if cycle
//                            return comp;
//                        });//end of lambda expressions
//                colonna = grid.addColumn(reflectionJavaField.getName());
                break;
            default:
                colonna = grid.addColumn(reflectionJavaField.getName());
                break;
        } // end of switch statement

        return colonna;
    }// end of method

    /**
     * Regola una colonna
     * Caption, renderer e width
     * Restituisce la larghezza dopo le regolazioni
     *
     * @param colonna         appena costruita, da regolare se ci sono Annoattion diverse dallo standard
     * @param reflectionField di riferimento per estrarre le Annotation
     *
     * @return la larghezza della colonna come regolata
     */
    public int regolaAnnotationAndGetLarghezza(Grid.Column colonna, Field reflectionField) {
        if (colonna == null) {
            return 0;
        }// end of if cycle

        String caption = annotation.getColumnName(reflectionField);
        EAFieldType type = annotation.getColumnType(reflectionField);
        int width = annotation.getColumnWith(reflectionField);

        DateRenderer render = new DateRenderer("%1$te-%1$tb-%1$tY", Locale.ITALIAN);
        LocalDateRenderer renderDate = new LocalDateRenderer("d-MMM-u", Locale.ITALIAN);
        LocalDateTimeRenderer renderTime = new LocalDateTimeRenderer("d-MMM-uu HH:mm", Locale.ITALIAN);
        ByteStringRenderer renderByte = new ByteStringRenderer();

        colonna.setCaption(caption);
        colonna.setWidth(width > 0 ? width : WIDTH_TEXT_NORMAL);

        if (type == EAFieldType.localdate) {
            colonna.setRenderer(renderDate);
            colonna.setWidth(WIDTH_LOCAL_DATE);
        }// end of if cycle

        if (type == EAFieldType.localdatetime) {
            colonna.setRenderer(renderTime);
            colonna.setWidth(WIDTH_LOCAL_DATE_TIME);
        }// end of if cycle

        if (type == EAFieldType.icon) {
//            colonna.setRenderer(renderIcon);
//            colonna.setWidth(WIDTH_TEXT_NORMAL);
        }// end of if cycle

        if (type == EAFieldType.json) {
            colonna.setRenderer(renderByte);
            colonna.setWidth(WIDTH_BYTE);
        }// end of if cycle

        if (caption.equals(ACost.PROPERTY_ID)) {
            colonna.setWidth(290);
        }// end of if cycle

        return ((Double) colonna.getWidth()).intValue();
    }// end of method


}// end of class
