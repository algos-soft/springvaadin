package it.algos.springvaadin.lib;

import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.ui.AlgosUIParams;

/**
 * Created by gac on 19/06/17
 * Restituisce i valori di tutti i parametri dell'applicazione
 * sia quelli gestiti da AlgosApp (business logic)
 * sia quelli gestiti da AlgosUIParams (interfaccia utente)
 * I relativi valori sono stati regolati in AlgosApp, AlgosSpringBoot, SpringVaadinSpringBoot, AlgosFixParams, SpringVaadinUI
 */
public abstract class LibParams {

    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useDebug() {
        return AlgosApp.USE_DEBUG;
    }// end of static method


    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Use company.<br>
     * Not final<br>
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useMultiCompany() {
        return AlgosApp.USE_MULTI_COMPANY;
    }// end of static method


    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Use security access.
     * Not final flag<br>
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useSecurity() {
        return AlgosApp.USE_SECURITY;
    }// end of static method


    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Flag per mostrare il Login obbligatorio alla partenza.
     * Ha senso solo se il flag usaSecurity è attivato
     * Se è true, parte inizialmente col Login (a tutto schermo?)
     * Se è false, permette di visualizzare uno screenShot o un menu iniziale
     * o comunque una parte dell'applicazione che non necessita di essere protetta
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useLoginObbligatorio() {
        return AlgosApp.USE_LOGIN_OBBLIGATORIO;
    }// end of static method


    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Flag controllo parametri nella web request.
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useCheckParams() {
        return AlgosApp.USE_CHECK_PARAMS;
    }// end of static method


    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Flag controllo cookies.
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useCheckCookies() {
        return AlgosApp.USE_CHECK_COOKIES;
    }// end of static method

    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Use versione entity.
     * Not final flag
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useVers() {
        return AlgosApp.USE_VERS;
    }// end of static method

    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Use preferences entity.
     * Not final flag
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean useLog() {
        return AlgosApp.USE_LOG;
    }// end of static method

    /**
     * Costante globale dell'applicazione. Business logic. Modificabile.
     * Use logo entity.
     * Not final flag
     * Fixed in AlgosSpringBoot.afterPropertiesSet()
     * Can be overwritten on local xxxSpringBoot.afterPropertiesSet() method
     */
    public static boolean usePref() {
        return AlgosApp.USE_PREF;
    }// end of static method


    /**
     * Flag di utilizzo di tre componenti per la view (top, body e footer)
     * In alternativa usa un solo componente (panel)
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaViewTreComponenti() {
        return ((AlgosUIParams) LibVaadin.getUI()).usaViewTreComponenti;
    }// end of static method


    /**
     * Flag per un main layout verticale piuttosto che orizzontale.
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaRootLayoutVerticale() {
        return ((AlgosUIParams) LibVaadin.getUI()).usaRootLayoutVerticale;
    }// end of static method


    /**
     * Flag di utilizzo di itemMenuHome (il primo da sinistra di firstMenuBar)
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaItemMenuHome() {
        return ((AlgosUIParams) LibVaadin.getUI()).usaItemMenuHome;
    }// end of static method


    /**
     * Flag di utilizzo di itemMenuHelp (l'ultimo a destra di firstMenuBar)
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaItemMenuHelp() {
        return ((AlgosUIParams) LibVaadin.getUI()).usaItemMenuHelp;
    }// end of static method


    /**
     * Flag.
     * Display only the new record in the table, after successful editing (persisted).<br>
     * Not final flag
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean displayNewRecordOnly() {
        return ((AlgosUIParams) LibVaadin.getUI()).displayNewRecordOnly;
    }// end of static method


    /**
     * Flag.
     * Display tooltips on rollover the field
     * Not final flag
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean displayToolTips() {
        return ((AlgosUIParams) LibVaadin.getUI()).displayToolTips;
    }// end of static method


    /**
     * Flag.
     * Null selection allowed in combobox.
     * Not final
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean comboBoxNullSelectionAllowed() {
        return ((AlgosUIParams) LibVaadin.getUI()).comboBoxNullSelectionAllowed;
    }// end of static method


    /**
     * Flag.
     * Multi selection modo in Grid.<br>
     * Not final<br>
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static Grid.SelectionMode gridSelectionMode() {
        return LibVaadin.getUI() != null ? ((AlgosUIParams) LibVaadin.getUI()).gridSelectionMode : null;
//        return ((AlgosUIParams) LibVaadin.getUI()).gridSelectionMode;
    }// end of static method


    /**
     * Flag.
     * Form in un Dialog modale.
     * Se è falso, visualizza i dati del Form nel viewPlaceholder, senza aprire un Dialog
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaSeparateFormDialog() {
        return LibVaadin.getUI() != null ? ((AlgosUIParams) LibVaadin.getUI()).usaSeparateFormDialog : false;
//        return ((AlgosUIParams) LibVaadin.getUI()).usaSeparateFormDialog;
    }// end of static method


    /**
     * Flag.
     * Dialogo di conferma prima della cancellazione
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean chiedeConfermaPrimaDiCancellare() {
        return ((AlgosUIParams) LibVaadin.getUI()).chiedeConfermaPrimaDiCancellare;
    }// end of static method


    /**
     * Flag.
     * Utilizzo di bottoni con colore indicativo della 'priorità'
     * Priority, friendly, danger
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaBottoniColorati() {
        AlgosUIParams uiParams = (AlgosUIParams) LibVaadin.getUI();
        return uiParams != null && uiParams.usaBottoniColorati;
    }// end of static method


    /**
     * Flag.
     * Utilizzo di bottoni sempre con la prima lettera maiuscola
     * Priority, friendly, danger
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaBottoniPrimaMaiuscola() {
        AlgosUIParams uiParams = (AlgosUIParams) LibVaadin.getUI();
        return uiParams != null && uiParams.usaBottoniPrimaMaiuscola;
    }// end of static method


    /**
     * Flag.
     * Utilizzo di dialoghi 'verbosi' (con maggiori informazioni)
     * Priority, friendly, danger
     * Regolato nella classe AlgosUIParams con @PostConstruct inizia()->genericFixAndPrint()
     * Modificato nella sottoclasse concreta xxxVaadinUI con @PostConstruct inizia()->specificFixAndPrint()
     */
    public static boolean usaDialoghiVerbosi() {
        return ((AlgosUIParams) LibVaadin.getUI()).usaDialoghiVerbosi;
    }// end of static method


}// end of static class
