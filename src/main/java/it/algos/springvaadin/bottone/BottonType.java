package it.algos.springvaadin.bottone;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
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
public enum BottonType {


    accetta(Cost.BOT_ACCETTA, VaadinIcons.CHECK, false, Bottone.NORMAL_WIDTH, Cost.STYLE_BLUE),
    annulla(Cost.BOT_ANNULLA, VaadinIcons.CLOSE, true, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    back(Cost.BOT_ANNULLA, VaadinIcons.ARROW_BACKWARD, true, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    chooser(Cost.BOT_CHOOSER, VaadinIcons.SEARCH, true, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    create(Cost.BOT_CREATE, VaadinIcons.PLUS, true, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    delete(Cost.BOT_DELETE, VaadinIcons.CLOSE, true, Bottone.NORMAL_WIDTH, Cost.STYLE_RED),
    edit(Cost.BOT_EDIT, VaadinIcons.EDIT, false, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    image("", VaadinIcons.EDIT, true, Bottone.ICON_WIDTH, Cost.STYLE_GREEN),
    importa(Cost.BOT_IMPORT, VaadinIcons.EXTERNAL_BROWSER, true, Bottone.NORMAL_WIDTH, Cost.STYLE_RED),
    registra(Cost.BOT_REGISTRA, VaadinIcons.DATABASE, false, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    revert(Cost.BOT_REVERT, VaadinIcons.REFRESH, false, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    search(Cost.BOT_SEARCH, VaadinIcons.SEARCH, true, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN),
    show(Cost.BOT_SHOW_ALL, VaadinIcons.ALIGN_JUSTIFY, false, Bottone.NORMAL_WIDTH, Cost.STYLE_GREEN);


    private String caption;
    private Resource icon;
    private boolean enabled;
    private String width;
    private String style;


    /**
     * Costruttore interno dell'Enumeration
     */
    BottonType(String caption, Resource icon, boolean enabled, String width, String style) {
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
