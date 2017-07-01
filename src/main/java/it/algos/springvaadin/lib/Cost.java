package it.algos.springvaadin.lib;

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

    public final static String PROPERTY_ID = "id";
    public final static String PROPERTY_EXCLUDED = "serialVersionUID";

}// end of static class
