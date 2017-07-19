package it.algos.springvaadin.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.service.AlgosStartService;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 12/06/17
 * <p>
 * Superclasse astratta della UI di partenza dell'applicazione, selezionata da SpringBoot
 * Questa classe NON prevede l'Annotation @SpringViewDisplay()
 * Mantiene e regola i parametri che regolano la gestione della view
 * Lascia che la sottoclasse AlgosUI si occupi SOLO della parte grafica
 */
public abstract class AlgosUIParams extends UI {

    /**
     * Flag di utilizzo di tre componenti per la view (top, body e footer)
     * In alternativa usa un solo componente (panel)
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean usaViewTreComponenti;

    /**
     * Flag per un main layout verticale piuttosto che orizzontale.
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean usaRootLayoutVerticale;

    /**
     * Flag di utilizzo di itemMenuHome (il primo da sinistra di firstMenuBar)
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean usaItemMenuHome;

    /**
     * Flag di utilizzo di itemMenuHelp (l'ultimo a destra di firstMenuBar)
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean usaItemMenuHelp;


    /**
     * Flag.
     * Display only the new record in the table, after successful editing (persisted).<br>
     * Not final flag<br>
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean displayNewRecordOnly;


    /**
     * Flag.
     * Display tooltips on rollover the field
     * Not final flag
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean displayToolTips;


    /**
     * Flag.
     * Null selection allowed in combobox.
     * Not final
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean comboBoxNullSelectionAllowed;


    /**
     * Flag.
     * Multi selection or Single selection or None in Grid.
     * Not final
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public Grid.SelectionMode gridSelectionMode;


    /**
     * Flag.
     * Form in un Dialog modale.
     * Se è falso, visualizza i dati del Form nel viewPlaceholder, senza aprire un Dialog
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean usaSeparateFormDialog;


    /**
     * Flag.
     * Dialogo di conferma prima della cancellazione
     * Regolato nel metodo genericFixAndPrint() di questa classe
     * Può essere modificato in @PostConstruct.inizia() della sottoclasse concreta
     */
    public boolean chiedeConfermaPrimaDiCancellare;


    /**
     * Metodo invocato DOPO il costruttore e PRIMA del metodo init(VaadinRequest request)
     * Serve per regolare i parametri utilizzati nel metodo init(VaadinRequest request)
     * Stampa a video (productionMode) i valori per controllo
     */
    @PostConstruct
    protected void inizia() {
        this.printBefore(AlgosUI.InterfacciaUtente.generica);
        this.genericFixAndPrint();
        this.printAfter(AlgosUI.InterfacciaUtente.generica);
    }// end of method

    /**
     * Initializes this UI.
     * This method is intended to build the view and configure non-component functionality.
     * Performing the initialization in a constructor is not suggested as the state of the UI
     * is not properly set up when the constructor is invoked.
     * <p>
     * The {@link VaadinRequest} can be used to get information about the request that caused this UI to be created.
     * </p>
     * Se viene sovrascritto dalla sottoclasse, deve (DEVE) richiamare anche il metodo della superclasse
     * di norma DOPO aver effettuato alcune regolazioni <br>
     * Nella sottoclasse specifica viene eventualmente regolato il nome del modulo di partenza <br>
     *
     * @param request the Vaadin request that caused this UI to be created
     */
    @Override
    protected void init(VaadinRequest request) {
        //--Legge eventuali parametri passati nella request
        if (AlgosApp.USE_CHECK_PARAMS) {
            AlgosStartService.checkParams(request);
        }// end of if cycle

        //--Legge i cookies dalla request
        if (AlgosApp.USE_CHECK_COOKIES) {
            AlgosStartService.checkCookies(request);
        }// end of if cycle

        //--Controlla il login della security
        if (AlgosApp.USE_SECURITY) {
            AlgosStartService.checkSecurity(request);
        }// end of if cycle

        //--Controlla la company selezionata
        if (AlgosApp.USE_MULTI_COMPANY) {
            AlgosStartService.checkCompany(request);
        }// end of if cycle
    }// end of method

    /**
     * Stampa a video (productionMode) una info PRIMA dei valori
     */
    protected void printBefore(AlgosUI.InterfacciaUtente ui) {
        Class claz = this.getClass();
        String className = claz.getSimpleName();

        switch (ui) {
            case generica:
                System.out.println("##########################################");
                System.out.println("   application received the server requests   ");
                System.out.println("   start generic initializing code nella classe AlgosUIParams  ");
                System.out.println("..........................................");
                break;
            case specifica:
                System.out.println("   start specific initializing code nella classe " + className);
                System.out.println("..........................................");
                break;
        } // fine del blocco switch
    }// end of method

    /**
     * Regola alcuni flag generici dell'applicazione che riguardano l'interfaccia video
     * Valori di default
     * Can be overwritten on local xxxUI.specificFixAndPrint() method of subclass
     * Stampa a video (productionMode) i valori per controllo
     */
    protected void genericFixAndPrint() {
        this.usaViewTreComponenti = true;
        System.out.println("AlgosUIParams.usaViewTreComponenti: " + this.usaViewTreComponenti);

        this.usaRootLayoutVerticale = true;
        System.out.println("AlgosUIParams.usaRootLayoutVerticale: " + this.usaRootLayoutVerticale);

        this.usaItemMenuHome = true;
        System.out.println("AlgosUIParams.usaItemMenuHome: " + this.usaItemMenuHome);

        this.usaItemMenuHelp = false;
        System.out.println("AlgosUIParams.usaItemMenuHelp: " + this.usaItemMenuHelp);

        this.displayNewRecordOnly = false;
        System.out.println("AlgosUIParams.displayNewRecordOnly: " + this.displayNewRecordOnly);

        this.displayToolTips = false;
        System.out.println("AlgosUIParams.displayToolTips: " + this.displayToolTips);

        this.comboBoxNullSelectionAllowed = false;
        System.out.println("AlgosUIParams.comboBoxNullSelectionAllowed: " + this.comboBoxNullSelectionAllowed);

        this.gridSelectionMode = Grid.SelectionMode.SINGLE;
        System.out.println("AlgosUIParams.gridSelectionMode: " + this.gridSelectionMode);

        this.usaSeparateFormDialog = false;
        System.out.println("AlgosUIParams.usaSeparateFormDialog: " + this.usaSeparateFormDialog);

        this.chiedeConfermaPrimaDiCancellare = true;
        System.out.println("AlgosUIParams.chiedeConfermaPrimaDiCancellare: " + this.chiedeConfermaPrimaDiCancellare);

        System.out.println("All this params can be found in LibParams");

    }// end of method

    /**
     * Stampa a video (productionMode) una info DOPO i valori
     */
    protected void printAfter(AlgosUI.InterfacciaUtente ui) {
        switch (ui) {
            case generica:
                System.out.println("..........................................");
                System.out.println("   end generic initializing code             ");
                System.out.println("..........................................");
                break;
            case specifica:
                System.out.println("..........................................");
                System.out.println("   end specific initializing code            ");
                System.out.println("##########################################");
                break;
        } // fine del blocco switch
    }// end of method


    public enum InterfacciaUtente {
        generica, specifica
    }// end of internal enumeration

}// end of class
