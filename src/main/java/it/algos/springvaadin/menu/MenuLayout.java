package it.algos.springvaadin.menu;

import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.lib.LibText;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by gac on 01/06/17.
 * .
 */
@Lazy
@SpringComponent
public class MenuLayout extends CssLayout {

    public static String MENU_ABILITATO = "highlight";
    public static String MENU_DISABILITATO = "disabilitato";

    private MenuBar firstMenuBar;

    public MenuLayout() {
        super();
    }

    @PostConstruct
    void init() {
        firstMenuBar = new MenuBar();
        firstMenuBar.setAutoOpen(true);
        this.addComponent(firstMenuBar);
    }// end of method

    /**
     * Adds a lazy view to the firstMenuBar
     *
     * @param viewClass the view class to adds
     */
    public void addView(Class<? extends View> viewClass) {
        MenuBar.Command viewCommand = null;
        Object obj = LibReflection.getMethodButton(viewClass, "getButtoneMenu");
        String viewName = LibReflection.getPropertyStr(viewClass, "VIEW_NAME");
        String viewCaption = LibReflection.getPropertyStr(viewClass, "VIEW_CAPTION");
        Resource viewIcon = LibReflection.getPropertyRes(viewClass, "VIEW_ICON");

        if (obj instanceof MenuBar.Command) {
            viewCommand = (MenuBar.Command) obj;
        } else {
            viewCommand = new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    deselezionaAllItemButOne(menuItem);
                    getUI().getNavigator().navigateTo(viewName);
                }// end of inner method
            };// end of anonymous inner class
        }// end of if/else cycle

        if (LibText.isEmpty(viewCaption)) {
            viewCaption = LibText.primaMaiuscola(viewName);
        }// end of if cycle

        firstMenuBar.addItem(viewCaption, viewIcon, viewCommand);
    }// end of method

    /**
     * Regola l'aspetto di tutti i menu <br>
     */
    public void deselezionaAllItemButOne(MenuBar.MenuItem selectedItem) {
        List<MenuBar.MenuItem> lista = firstMenuBar.getItems();

        for (MenuBar.MenuItem menuItem : lista) {
            if (menuItem != selectedItem) {
                menuItem.setStyleName(MENU_DISABILITATO);
            }// end of if cycle
        }// fine del ciclo for

        if (selectedItem != null) {
            selectedItem.setStyleName(MENU_ABILITATO);
        }// end of if cycle

    }// end of method

    /**
     * The item has been selected.
     * Navigate to the View and select the item in the menubar
     */
    public void menuSelezionato(MenuBar.MenuItem selectedItem) {

        // de-selects all the items in the menubar
        if (firstMenuBar != null) {
            List<MenuBar.MenuItem> items = firstMenuBar.getItems();
            for (MenuBar.MenuItem item : items) {
                deselectItem(item);
            } // fine del ciclo for
        }// fine del blocco if

        // highlights the selected item
        // the style name will be prepended automatically with "v-menubar-menuitem-"
        selectedItem.setStyleName("highlight");

        // it this item is inside another item, highlight also the parents in the chain
        MenuBar.MenuItem item = selectedItem;
        while (item.getParent() != null) {
            item = item.getParent();
            item.setStyleName("highlight");
        } // fine del ciclo while

    }// end of method

    /**
     * Recursively de-selects one item and all its children
     */
    private void deselectItem(MenuBar.MenuItem item) {
        item.setStyleName(null);
        List<MenuBar.MenuItem> items = item.getChildren();
        if (items != null) {
            for (MenuBar.MenuItem child : items) {
                deselectItem(child);
            } // fine del ciclo for
        }// fine del blocco if
    }// end of method

}// end of class

