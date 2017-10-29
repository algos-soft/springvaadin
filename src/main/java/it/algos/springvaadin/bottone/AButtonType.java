package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.lib.Cost;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 20-ago-2017
 * Time: 19:12
 * <p>
 * Bottoni della toolbar di una Grid
 * Bottoni della toolbar di un Form
 * <p>
 * Enumeration utilizzata per 'marcare' un evento, in fase di generazione
 * Enumeration utilizzata per 'riconoscerlo' nel metodo onApplicationEvent()
 */
public enum AButtonType {


    accetta(Cost.BOT_ACCETTA, VaadinIcons.CHECK, false, AButton.NORMAL_WIDTH, Cost.STYLE_BLUE),
    annulla(Cost.BOT_ANNULLA, VaadinIcons.ARROW_BACKWARD, true, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    back(Cost.BOT_ANNULLA, VaadinIcons.ARROW_BACKWARD, true, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    chooser(Cost.BOT_CHOOSER, VaadinIcons.SEARCH, true, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    create(Cost.BOT_CREATE, VaadinIcons.PLUS, true, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    delete(Cost.BOT_DELETE, VaadinIcons.CLOSE, false, AButton.NORMAL_WIDTH, Cost.STYLE_RED),
    deleteLink("", VaadinIcons.CLOSE, true, AButton.ICON_WIDTH, Cost.STYLE_RED),
    edit(Cost.BOT_EDIT, VaadinIcons.EDIT, false, AButton.NORMAL_WIDTH, Cost.STYLE_BLUE),
    show(Cost.BOT_SHOW, VaadinIcons.ALIGN_JUSTIFY, false, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    editLinkDBRef("", VaadinIcons.EDIT, true, AButton.ICON_WIDTH, Cost.STYLE_BLUE),
    editLinkNoDBRef("", VaadinIcons.EDIT, true, AButton.ICON_WIDTH, Cost.STYLE_BLUE),
    image("", VaadinIcons.EDIT, true, AButton.ICON_WIDTH, ""),
    importa(Cost.BOT_IMPORT, VaadinIcons.EXTERNAL_BROWSER, true, AButton.NORMAL_WIDTH, Cost.STYLE_RED),
    linkAccetta(Cost.BOT_ACCETTA, VaadinIcons.EDIT, true, AButton.NORMAL_WIDTH, Cost.STYLE_BLUE),
    linkRegistra(Cost.BOT_REGISTRA, VaadinIcons.EDIT, true, AButton.NORMAL_WIDTH, Cost.STYLE_BLUE),
    registra(Cost.BOT_REGISTRA, VaadinIcons.DATABASE, false, AButton.NORMAL_WIDTH, Cost.STYLE_BLUE),
    revert(Cost.BOT_REVERT, VaadinIcons.REFRESH, false, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    search(Cost.BOT_SEARCH, VaadinIcons.SEARCH, true, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN),
    showAll(Cost.BOT_SHOW_ALL, VaadinIcons.ALIGN_JUSTIFY, false, AButton.NORMAL_WIDTH, Cost.STYLE_GREEN);


    private String caption;
    private Resource icon;
    private boolean enabled;
    private String width;
    private String style;


    /**
     * Costruttore interno dell'Enumeration
     */
    AButtonType(String caption, Resource icon, boolean enabled, String width, String style) {
        this.setCaption(caption);
        this.setIcon(icon);
        this.setEnabled(enabled);
        this.setWidth(width);
        this.setStyle(style);
    }// fine del costruttore interno


    public String getCaption() {
        return caption;
    }// end of method

    public void setCaption(String caption) {
        this.caption = caption;
    }// end of method

    public Resource getIcon() {
        return icon;
    }// end of method

    public void setIcon(Resource icon) {
        this.icon = icon;
    }// end of method

    public boolean isEnabled() {
        return enabled;
    }// end of method

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }// end of method

    public String getWidth() {
        return width;
    }// end of method

    public void setWidth(String width) {
        this.width = width;
    }// end of method

    public String getStyle() {
        return style;
    }// end of method

    public void setStyle(String style) {
        this.style = style;
    }// end of method

}// end of Enumeration
