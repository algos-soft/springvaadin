package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.data.AData;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleService;
import it.algos.springvaadin.entity.user.User;
import it.algos.springvaadin.entity.user.UserService;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.service.IAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 20-dic-2017
 * Time: 07:14
 */
@Slf4j
@SpringComponent
@Scope("singleton")
public class UserData extends AData {


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    private RoleService roleService;


    /**
     * Libreria di servizio. Inietta da Spring come 'singleton'
     */
    @Autowired
    private CompanyService companyService;

    /**
     * Il service iniettato dal costruttore, in modo che sia disponibile nella superclasse,
     * dove viene usata l'interfaccia IAService
     * Spring costruisce al volo, quando serve, una implementazione di IAService (come previsto dal @Qualifier)
     * Qui si una una interfaccia locale (col casting nel costruttore) per usare i metodi specifici
     */
    private UserService service;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param service iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public UserData(@Qualifier(ACost.TAG_USE) IAService service) {
        super(service);
        this.service = (UserService) service;
    }// end of Spring constructor


    /**
     * Creazione di una collezione
     * Solo se non ci sono records
     */
    public void findOrCrea() {
        int numRec = 0;

        if (nessunRecordEsistente()) {
            creaUsers();
            numRec = service.count();
            log.warn("Algos - Creazione dati iniziali @EventListener ABoot.onApplicationEvent() -> iniziaData.inizia() -> UserData.findOrCrea(): " + numRec + " schede");
        } else {
            numRec = service.count();
            log.info("Algos - Data. La collezion User è presente: " + numRec + " schede");
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione degli users di PROVA
     */
    public void creaUsers() {
        User entity;
        Company companyAlgos = companyService.findByKeyUnica(CompanyData.ALGOS);
        Company companyDemo = companyService.findByKeyUnica(CompanyData.DEMO);
        Company companyTest = companyService.findByKeyUnica(CompanyData.TEST);

        creaSingoloUserSenzaLaCompanyDellaSessione(companyAlgos, "gac", roleService.getDev());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyAlgos, "alex", roleService.getDev());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyDemo, "developer", roleService.getAdmin());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyDemo, "admin", roleService.getDev());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyTest, "volontario", roleService.getUser());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyTest, "ospite", roleService.getUser());
        creaSingoloUserSenzaLaCompanyDellaSessione(companyTest, "guest", roleService.getUser());
    }// end of method


    public void creaSingoloUserSenzaLaCompanyDellaSessione(Company company, String nickname, Role role) {
        User entity = null;

        if (company != null) {
            entity = User.builder().nickname(nickname).password(nickname).role(role).enabled(true).build();
            entity.company = company;
            service.save(entity);
        }// end of if cycle
    }// end of method

}// end of class
