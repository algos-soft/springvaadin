package it.algos.springvaadin.entities.versione;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.field.AIColumn;
import it.algos.springvaadin.field.AIField;
import it.algos.springvaadin.grid.AlgosGrid;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.toolbar.ListToolbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by gac on 13/06/17.
 * Presenta i dati di una lista usando una Grid
 * Aggiunge una ToolBar in basso coi bottoni di comando (contenenti già i listener per lanciare gli eventi)
 */
@Lazy
@SpringComponent
public class VersioneList extends AlgosList {

}// end of class
