package it.algos.springvaadin.lib;

import java.util.Arrays;
import java.util.List;

/**
 * Repository di costanti della applicazione
 * <p>
 * Costanti per leggere/scrivere sempre uguale nelle mappe, negli attributi, nei cookies, nelle property
 * Altre costanti 'static' sono raggruppate nella classe it.algos.webbase.web.AlgosApp
 */
public abstract class Cost {

    public final static String LOGIN_INFO = "loginInfo";

    public final static String COOKIE_LOGIN_NICK = "cookieLoginNick";
    public final static String COOKIE_LOGIN_PASS = "cookieLoginPass";
    public final static String COOKIE_LOGIN_ROLE = "cookieLoginRole";

    public final static String KEY_MAP_COMPANY = "company";
    public final static String SIGLA_COMPANY_DEMO = "demo";

    public final static String PROPERTY_ID = "id";
    public final static String PROPERTY_COMPANY = "company";
    public final static String PROPERTY_SERIAL = "serialVersionUID";
    public final static String COMPANY_CODE = "code";
    public final static String COMPANY_UNICO = "codeCompanyUnico";

    private final static String[] esclusiMatrice = {Cost.PROPERTY_SERIAL, Cost.PROPERTY_ID, Cost.PROPERTY_COMPANY};
    public final static List<String> ESCLUSI = Arrays.asList(esclusiMatrice);
    private final static String[] companyMatrice = {Cost.COMPANY_CODE, Cost.COMPANY_UNICO};
    public final static List<String> COMPANY_OPTIONAL = Arrays.asList(companyMatrice);

    public final static String TAG_VERS = "versione";
    public final static String TAG_COMP = "company";
    public final static String TAG_LOG = "log";
    public final static String TAG_HELP = "help";
    public final static String TAG_HOME = "home";

    public final static String BOT_ACCETTA = "accetta";
    public final static String BOT_ANNULLA = "annulla";
    public final static String BOT_BACK = "back";
    public final static String BOT_CREATE = "nuovo";
    public final static String BOT_DELETE = "elimina";
    public final static String BOT_EDIT = "modifica";
    public final static String BOT_IMAGE = "immagine";
    public final static String BOT_IMPORT = "import";
    public final static String BOT_LINK_ACCETTA = "linkaccetta";
    public final static String BOT_LINK_REGISTRA = "linkregistra";
    public final static String BOT_REGISTRA = "registra";
    public final static String BOT_REVERT = "revert";
    public final static String BOT_SEARCH = "ricerca";
    public final static String BOT_SHOW_ALL = "tutto";
    public final static String BOT_LINK = "botlink";
    public final static String BOT_CHOOSER = "apri";

    public final static String FIELD_ID = "KeyID";
    public final static String FIELD_TEXT = "text";
    public final static String FIELD_INTEGER = "integer";
    public final static String FIELD_DATE_TIME = "datetime";
    public final static String FIELD_IMAGE = "image";
    public final static String FIELD_COMBO = "combobox";
    public final static String FIELD_LINK = "fieldlink";
    public final static String FIELD_CHEK_BOX = "fieldcheckbox";

    public final static String VIEW_IMAGE = "viewimage";

    public final static String BAR_LIST = "toolbarlist";
    public final static String BAR_FORM = "toolbarform";
    public final static String BAR_LINK = "toolbarlink";

    public final static String STYLE_GREEN = "buttonGreen";
    public final static String STYLE_BLUE = "buttonBlue";
    public final static String STYLE_RED = "buttonRed";

    public final static String TAG_AZ_ATTACH = "attach";
    public final static String TAG_AZ_CLICK = "click";
    public final static String TAG_AZ_DOPPIO_CLICK = "doppioClick";
    public final static String TAG_AZ_SINGLE_SELECTION = "singleSelectionChanged";
    public final static String TAG_AZ_MULTI_SELECTION = "multiSelectionChanged";
    public final static String TAG_AZ_VALUE_CHANGED = "valueChange";
    public final static String TAG_AZ_LISTENER = "listener";
    public final static String TAG_IND = "indirizzo";
    public final static String TAG_STA = "stato";
    public final static String TAG_PER = "persona";
    public final static String TAG_PRO = "prova";
    public final static String TAG_FAT = "fattura";
}// end of static class;
