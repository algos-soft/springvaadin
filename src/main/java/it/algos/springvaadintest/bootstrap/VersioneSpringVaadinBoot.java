package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bootstrap.VersioneBoot;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyData;
import it.algos.springvaadin.entity.preferenza.PrefType;
import it.algos.springvaadin.entity.preferenza.Preferenza;
import it.algos.springvaadin.entity.stato.StatoData;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 21-ott-2017
 * Time: 22:06
 * <p>
 * Log delle versioni, modifiche e patch installate
 * Setup non-UI logic here
 * This class will be executed on container startup when the application is ready to server requests.
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat)
 * Eseguita quindi ad ogni avvio/attivazione del server e NON ad ogni sessione
 * <p>
 * In order to create a class that acts like a bootstrap for the application,
 * that class needs to implements the @EventListener annotation
 * Any class that implements the @EventListener annotation will be executed before the application is up
 * and its onApplicationEvent method will be called
 * <p>
 * ATTENZIONE: in questa fase NON sono disponibili le Librerie e le classi che dipendono dalla UI e dalla Session
 */
@SpringComponent
@Slf4j
public class VersioneSpringVaadinBoot extends VersioneBoot {


    @Autowired
    private StatoData statoData;

    @Autowired
    protected CompanyData companyData;

//    @Autowired
//    private PreferenzaService preferenzaService;

    private final static String DEMO = "demo";

    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneSpringVaadinBoot(
            @Qualifier(Cost.TAG_VERS) AlgosService service,
            @Qualifier(Cost.TAG_COMP) AlgosService companyService,
            @Qualifier(Cost.TAG_PRE) AlgosService preferenzaService,
            @Qualifier(Cost.TAG_LOG) AlgosService logger) {
        super(service, companyService, preferenzaService, logger);
    }// end of Spring constructor


    /**
     * Running logic after the Spring context has been initialized
     * Any class that use this @EventListener annotation,
     * will be executed before the application is up and its onApplicationEvent method will be called
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.cronistoriaVersioni();
        companyData.updatePreferenze();
    }// end of method


    /**
     * Cronistoria delle versioni istallate nell'applicazione
     */
    private void cronistoriaVersioni() {
        int k = 1;

        //--prima installazione del programma
        //--non fa nulla, solo informativo
        if (service.versioneNonAncoraUsata(k)) {
            creaVersioneAndLog(k,
                    "Setup",
                    "Creazione ed installazione iniziale dell'applicazione",
                    "Senza company specifica, perché è un'operazione valida per tutte le companies");
        }// fine del blocco if
        k++;


        if (service.versioneNonAncoraUsata(k)) {
            statoData.creaAll();
            creaVersioneAndLog(k,
                    "Stato",
                    "Creati alcuni stati base, usati nel popup degli indirizzi");
        }// fine del blocco if
        k++;

        if (service.versioneNonAncoraUsata(k)) {
            Company company = companyData.creaCompanyDemo();
            creaAndLogCompany(k,
                    company.getSigla(),
                    "Company",
                    "Creata una company demo",
                    "Creazione iniziale con alcuni dati fittizi\nCreate anche alcune preferenze specifiche per questa company");
        }// fine del blocco if
        k++;

        if (service.versioneNonAncoraUsata(k)) {
            Company company = companyData.creaCompanyTest();
            creaAndLogCompany(k,
                    company.getSigla(),
                    "Company",
                    "Creata una company test",
                    "Creazione iniziale con alcuni dati fittizi\nCreate anche alcune preferenze specifiche per questa company");
        }// fine del blocco if
        k++;

        if (service.versioneNonAncoraUsata(k)) {
            Preferenza preferenza;
            preferenza = creaPreferenzaAndVersioneAndLog(k,
                    Cost.KEY_USE_DEBUG,
                    PrefType.bool,
                    "Flag di debug",
                    false);

            preferenza.note = "Flag generale di debug (ce ne possono essere di specifici, validi solo se questo è vero)";
            try { // prova ad eseguire il codice
                preferenzaService.save(preferenza);
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
            }// fine del blocco try-catch
        }// fine del blocco if
        k++;

        if (service.versioneNonAncoraUsata(k)) {
            creaPreferenzaAndVersioneAndLog(k,
                    Cost.KEY_DISPLAY_NEW_RECORD_ONLY,
                    PrefType.bool,
                    "Display only the new record in the grid, after successful editing (persisted).",
                    false);
        }// fine del blocco if
        k++;

    }// end of method

}// end of class
