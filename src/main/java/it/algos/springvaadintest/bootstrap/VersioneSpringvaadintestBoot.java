package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bootstrap.VersioneBoot;
import it.algos.springvaadin.bootstrap.VersioneSpringvaadinBoot;
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
public class VersioneSpringvaadintestBoot extends VersioneSpringvaadinBoot {


    private final static String PROGETTO = "springvaadintest";


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     */
    public VersioneSpringvaadintestBoot(
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
        super.inizializza();
    }// end of method


    /**
     * Cronistoria delle versioni istallate nel progetto specifico
     */
    @Override
    protected void cronistoriaVersioniProgetto() {
        int k = 0;

        if (service.versioneNonAncoraUsata(PROGETTO, ++k)) {
            Company company = companyData.creaCompanyDemo();
            creaVersione(
                    PROGETTO,
                    company,
                    "Demo",
                    "Creata una company demo",
                    "");
        }// fine del blocco if

    }// end of method

}// end of class
