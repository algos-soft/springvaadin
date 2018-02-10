package it.algos.springvaadin.entity.user;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import it.algos.springvaadin.annotation.AIScript;
import it.algos.springvaadin.annotation.AIView;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.enumeration.EARoleType;
import it.algos.springvaadin.label.LabelRosso;
import it.algos.springvaadin.label.LabelVerde;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.list.AList;
import it.algos.springvaadin.presenter.IAPresenter;
import it.algos.springvaadin.toolbar.IAToolbar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by gac on TIMESTAMP
 * Estende la Entity astratta AList di tipo AView per visualizzare la Grid
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Scope (obbligatorio = 'session')
 * Annotated with @Qualifier (obbligatorio) per permettere a Spring di istanziare la sottoclasse specifica
 * Annotated with @SpringView (obbligatorio) per gestire la visualizzazione di questa view con SprinNavigator
 * Annotated with @AIScript (facoltativo) per controllare la ri-creazione di questo file nello script del framework
 * Costruttore con un link @Autowired al IAPresenter, di tipo @Lazy per evitare un loop nella injection
 */
@SpringComponent
@Scope("session")
@Qualifier(ACost.TAG_USE)
@SpringView(name = ACost.VIEW_USE_LIST)
@AIScript(sovrascrivibile = false)
public class UserList extends AList {


    /**
     * Label del menu (facoltativa)
     * SpringNavigator usa il 'name' della Annotation @SpringView per identificare (internamente) e recuperare la view
     * Nella menuBar appare invece visibile il MENU_NAME, indicato qui
     * Se manca il MENU_NAME, di default usa il 'name' della view
     */
    public static final String MENU_NAME = ACost.TAG_USE;


    /**
     * Icona visibile nel menu (facoltativa)
     * Nella menuBar appare invece visibile il MENU_NAME, indicato qui
     * Se manca il MENU_NAME, di default usa il 'name' della view
     */
    public static final Resource VIEW_ICON = VaadinIcons.ASTERISK;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     * Use @Lazy to avoid the Circular Dependency
     * A simple way to break the cycle is saying Spring to initialize one of the beans lazily.
     * That is: instead of fully initializing the bean, it will create a proxy to inject it into the other bean.
     * The injected bean will only be fully created when it’s first needed.
     *
     * @param presenter iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     * @param toolbar   iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public UserList(
            @Lazy @Qualifier(ACost.TAG_USE) IAPresenter presenter,
            @Qualifier(ACost.BAR_LIST) IAToolbar toolbar) {
        super(presenter, toolbar);
    }// end of Spring constructor


    /**
     * Crea il corpo centrale della view
     * Componente grafico obbligatorio
     * Sovrascritto nella sottoclasse della view specifica (AList, AForm, ...)
     *
     * @param source
     * @param entityClazz di riferimento, sottoclasse concreta di AEntity
     * @param columns     visibili ed ordinate della Grid
     * @param items       da visualizzare nella Grid
     */
    @Override
    protected void creaBody(IAPresenter source, Class<? extends AEntity> entityClazz, List<Field> columns, List items) {
        super.creaBody(source, entityClazz, columns, items);

        this.addColonna(entityClazz, "enabled");
    }// end of method


    /**
     * Aggiunge una collan di tipo Component
     * Il recupero del valore è specifico per ogni singola colonna (non riesco a generalizzare)
     *
     * @param entityClazz     di riferimento, sottoclasse concreta di AEntity
     * @param publicFieldName property statica e pubblica
     */
    private void addColonna(Class<? extends AEntity> entityClazz, String publicFieldName) {
        Grid.Column colonna;
        Field field = reflection.getField(entityClazz, publicFieldName);
        EAFieldType type = annotation.getColumnType(field);

        if (type == EAFieldType.checkbox || type == EAFieldType.checkboxlabel) {
            colonna = grid.getGrid().addComponentColumn(
                    entity -> {
                        Object value = ((User) entity).isEnabled();
                        Component comp = null;
                        if (value instanceof Boolean) {

                            //--test per provare le due possibilità
                            if (type == EAFieldType.checkbox) {
                                comp = new CheckBox();
                                (comp).setEnabled(false);
                                ((CheckBox) comp).setValue((Boolean) value);
                            } else {
                            }// end of if/else cycle

                            if (type == EAFieldType.checkboxlabel) {
                                if ((Boolean) value) {
                                    comp = new LabelVerde(VaadinIcons.CHECK);
                                } else {
                                    comp = new LabelRosso(VaadinIcons.CLOSE);
                                }// end of if/else cycle
                            }// end of if cycle

                        }// end of if cycle
                        return comp;
                    });//end of lambda expressions
            column.add(grid.getGrid(), field, EAFieldType.checkbox, colonna);
        }// end of if cycle

    }// end of method

}// end of class
