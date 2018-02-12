package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.data.AData;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.role.RoleService;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.service.IAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 22-dic-2017
 * Time: 10:41
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class CompanyData extends AData {


    public final static String ALGOS = "algos";
    public final static String DEMO = "demo";
    public final static String TEST = "test";


    /**
     * Il service iniettato dal costruttore, in modo che sia disponibile nella superclasse,
     * dove viene usata l'interfaccia IAService
     * Spring costruisce al volo, quando serve, una implementazione di IAService (come previsto dal @Qualifier)
     * Qui si una una interfaccia locale (col casting nel costruttore) per usare i metodi specifici
     */
    private CompanyService service;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param service iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public CompanyData(@Qualifier(ACost.TAG_COM) IAService service) {
        super(service);
        this.service = (CompanyService) service;
    }// end of Spring constructor


    /**
     * Creazione di una collezione
     * Solo se non ci sono records
     */
    public void findOrCrea() {
        int numRec = 0;

        if (nessunRecordEsistente()) {
            creaCompanies();
            numRec = service.count();
            log.warn("Algos - Creazione dati iniziali @EventListener ABoot.onApplicationEvent() -> iniziaData.inizia() -> CompanyData.findOrCrea(): " + numRec + " schede");
        } else {
            numRec = service.count();
            log.info("Algos - Data. La collezion Company è presente: " + numRec + " schede");
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione delle compamies
     */
    public void creaCompanies() {
        service.findOrCrea(ALGOS, "Algos s.r.l.");
        service.findOrCrea(DEMO, "Company di prova");
        service.findOrCrea(TEST, "Altra company");
//        service.findOrCrea(RoleService.GUEST);
    }// end of method


}// end of class
