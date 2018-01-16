package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.user.User;
import it.algos.springvaadin.exception.NullCompanyException;
import it.algos.springvaadin.lib.ACost;
import it.algos.springvaadin.service.AService;
import it.algos.springvaadin.service.ASessionService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 08:41
 * Estende la Entity astratta AService. Layer di collegamento tra il Presenter e la Repository.
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Service (ridondante)
 * Annotated with @Scope (obbligatorio = 'session')
 * Annotated with @Qualifier (obbligatorio) per permettere a Spring di istanziare la sottoclasse specifica
 * Annotated with @@Slf4j (facoltativo) per i logs automatici
 */
@Slf4j
@SpringComponent
@Service
@Scope("session")
@Qualifier(ACost.TAG_ROL)
public class RoleService extends AService {


    public final static String DEV = "developer";
    public final static String ADMIN = "admin";
    public final static String USER = "user";
    public final static String GUEST = "guest";


    /**
     * La repository viene iniettata dal costruttore, in modo che sia disponibile nella superclasse,
     * dove viene usata l'interfaccia MongoRepository
     * Spring costruisce al volo, quando serve, una implementazione di RoleRepository (come previsto dal @Qualifier)
     * Qui si una una interfaccia locale (col casting nel costruttore) per usare i metodi specifici
     */
    public RoleRepository repository;


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param repository iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public RoleService(@Qualifier(ACost.TAG_ROL) MongoRepository repository) {
        super(repository);
        this.repository = (RoleRepository) repository;
        super.entityClass = Role.class;
    }// end of Spring constructor


//    /**
//     * Creazione nel MongoDB di una entity (solo se non la trova)
//     * Properties obbligatorie
//     *
//     * @param code di riferimento (obbligatorio)
//     */
//    public Role newSave(String code) {
//        if (findByKeyUnica(code) == null) {
//            try { // prova ad eseguire il codice
//                return  (Role)save(newEntity(0, code));
//            } catch (Exception unErrore) { // intercetta l'errore
//                log.error(unErrore.toString());
//            }// fine del blocco try-catch
//        } else {
//            return null;
//        }// end of if/else cycle
//        return null;
//    }// end of method
//

    /**
     * Creazione nel MongoDB di una entity (solo se non la trova)
     * Properties obbligatorie
     *
     * @param code di riferimento (obbligatorio)
     */
    public Role newSave(String code) {
        if (findByKeyUnica(code) == null) {
            return (Role) save(newEntity(0, code));
        } else {
            return null;
        }// end of if/else cycle
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * Senza properties per compatibilità con la superclasse
     *
     * @return la nuova entity appena creata (non salvata)
     */
    @Override
    public Role newEntity() {
        return newEntity(0, "");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param ordine di rilevanza (obbligatorio, unico, con inserimento automatico se è zero, non modificabile)
     * @param code   di riferimento (obbligatorio)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Role newEntity(int ordine, String code) {
        Role entity = findByKeyUnica(code);

        if (entity == null) {
            entity = Role.builder().ordine(ordine != 0 ? ordine : this.getNewOrdine()).code(code).build();
        }// end of if cycle

        return entity;
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     *
     * @param code di riferimento (obbligatorio)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Role findByKeyUnica(String code) {
        return repository.findByCode(code);
    }// end of method


    /**
     * Returns all instances of the type
     * La Entity è EACompanyRequired.nonUsata. Non usa Company.
     * Lista ordinata
     *
     * @return lista ordinata di tutte le entities
     */
    @Override
    public List<Role> findAll() {
        return repository.findByOrderByOrdineAsc();
    }// end of method


    /**
     * Opportunità di controllare (per le nuove schede) che la key unica non esista già.
     * Invocato appena prima del save(), solo per una nuova entity
     *
     * @param entityBean nuova da creare
     */
    @Override
    protected boolean isEsisteEntityKeyUnica(AEntity entityBean) {
        return findByKeyUnica(((Role) entityBean).getCode()) == null;
    }// end of method


    /**
     * Ordine di presentazione (obbligatorio, unico per tutte le eventuali company),
     * viene calcolato in automatico prima del persist sul database
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getNewOrdine() {
        int ordine = 0;

        List<Role> lista = repository.findTop1ByOrderByOrdineDesc();
        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine + 1;
    }// end of method


    /**
     * Role developer
     *
     * @return entity richiesta
     */
    public Role getDev() {
        return findByKeyUnica(DEV);
    }// end of method


    /**
     * Role developer
     *
     * @return entity richiesta
     */
    public Role getAdmin() {
        return findByKeyUnica(ADMIN);
    }// end of method


    /**
     * Role developer
     *
     * @return entity richiesta
     */
    public Role getUser() {
        return findByKeyUnica(USER);
    }// end of method


    /**
     * Role developer
     *
     * @return entity richiesta
     */
    public Role getGuest() {
        return findByKeyUnica(GUEST);
    }// end of method

}// end of class
