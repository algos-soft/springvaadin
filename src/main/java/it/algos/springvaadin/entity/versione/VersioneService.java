package it.algos.springvaadin.entity.versione;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by gac on 01/06/17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_VERS)
@Slf4j
public class VersioneService extends AlgosServiceImpl {


    //--il service (contenente la repository) viene iniettato nel costruttore
    public VersioneRepository repository;


    //--il service (contenente la repository) viene iniettato nel costruttore
    private LogService logger;


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(
            @Qualifier(Cost.TAG_VERS) MongoRepository repository,
            @Qualifier(Cost.TAG_LOG) AlgosService logger) {
        super(repository);
        this.repository = (VersioneRepository) repository;
        this.logger = (LogService) logger;
    }// end of Spring constructor

//    /**
//     * Creazione di una company demo
//     * <p>
//     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
//     * (si può usare qualsiasi firma)
//     */
//    @PostConstruct
//    public void test() {
//        boolean falso = this.esiste(17);
//        boolean vero = this.esiste(2);
////        boolean zero = this.isVersioneNonEsiste(0);
////        boolean uno = this.isVersioneNonEsiste(1);
////        boolean due = this.isVersioneNonEsiste(2);
////        boolean tre = this.isVersioneNonEsiste(3);
////        boolean quattro = this.isVersioneNonEsiste(4);
////        boolean cinque = this.isVersioneNonEsiste(5);
////        boolean sei = this.isVersioneNonEsiste(6);
//        List listAll = this.findAll();
//        List listAlllCompany = this.findAllByCompany(null);
//        Versione vers1 = this.findByOrdine(1);
//        Versione vers2 = this.findByOrdine(2);
//        Versione vers3 = this.findByOrdine(3);
//        Versione vers4 = this.findByOrdine(4);
//        Versione vers5 = this.findByOrdine(5);
//        int ord2 = this.getNewOrdine();
//        int a = 87;
//    }// end of method


    /**
     * Ricerca di una entity (la crea se non la trova)
     * Properties obbligatorie
     *
     * @param ordine      (obbligatorio, unico indipendentemente dalla company,
     *                    con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     *
     * @return la entity trovata o appena creata
     */
    public Versione findOrCrea(int ordine, String gruppo, String descrizione) {
        if (ordine == 0) {
            try { // prova ad eseguire il codice
                return (Versione) save(newEntity(gruppo, descrizione));
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
                return null;
            }// fine del blocco try-catch
        } else {
            if (nonEsiste(ordine)) {
                try { // prova ad eseguire il codice
                    return (Versione) save(newEntity(gruppo, descrizione));
                } catch (Exception unErrore) { // intercetta l'errore
                    log.error(unErrore.toString());
                    return null;
                }// fine del blocco try-catch
            } else {
                return repository.findByOrdine(ordine);
            }// end of if/else cycle
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
    public Versione newEntity() {
        return newEntity(0, "", "", (LocalDate) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * Properties obbligatorie
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Versione newEntity(String gruppo, String descrizione) {
        return newEntity(0, gruppo, descrizione, (LocalDate) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param ordine      (obbligatorio, unico indipendentemente dalla company,
     *                    con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     * @param evento      momento in cui si effettua la edit della versione (obbligatoria, non unica, non modificabile)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Versione newEntity(int ordine, String gruppo, String descrizione, LocalDate evento) {
        return new Versione(
                ordine == 0 ? this.getNewOrdine() : ordine,
                gruppo,
                descrizione,
                evento != null ? evento : LocalDate.now());
    }// end of method


    /**
     * Controlla che esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @param ordine (obbligatorio, unico indipendentemente dalla company,
     *               con controllo automatico prima del save se è zero, non modificabile)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean esiste(int ordine) {
        return findByOrdine(ordine) != null;
    }// end of method


    /**
     * Controlla che non esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @param ordine (obbligatorio, unico indipendentemente dalla company,
     *               con controllo automatico prima del save se è zero, non modificabile)
     *
     * @return vero se non esiste, false se trovata
     */
    public boolean nonEsiste(int ordine) {
        return findByOrdine(ordine) == null;
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     *
     * @param ordine di versione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Versione findByOrdine(int ordine) {
        return repository.findByOrdine(ordine);
    }// end of method


    /**
     * Returns all instances of the type
     * Usa MultiCompany, ma il developer può vedere anche tutto
     * Lista ordinata
     *
     * @return lista di tutte le entities
     */
    public List findAll() {
        if (LibSession.isDeveloper()) {
            return repository.findByOrderByOrdineAsc();
        }// end of if cycle

        return null;
    }// end of method


    /**
     * Returns all instances of the type
     * Usa MultiCompany, ma non obbligatoria -> ACompanyRequired.facoltativa
     * Filtrata sulla company indicata
     * Se la company è nulla, prende solo le entities che hanno la property company=null
     * (questo perché la property company NON è obbligatoria; se lo fosse, prenderebbe tutte le entities)
     * Lista ordinata
     *
     * @param company ACompanyRequired.facoltativa
     *
     * @return entities filtrate
     */
    public List findAllByCompany(Company company) {
        return repository.findByCompanyOrderByOrdineAsc(company);
    }// end of method



    /**
     * Controlla se esiste il numero di versione da installare
     * I numeri delle versione eventualmente cancellate NON vengono sostituiti
     * Non cerca quindi numeroVersioneDaInstallare ma lo confronta col massimo esistente
     *
     * @param numeroVersioneDaInstallare per vedere che sia superiore al massimo attuale
     *
     * @return true se la versione non è mai esistita (ne adesso ne dopo cancellazione)
     */
    public boolean versioneNonAncoraUsata(int numeroVersioneDaInstallare) {
        boolean installa = false;
        int numeroVersioneEsistente = getNewOrdine()-1;

        if (numeroVersioneDaInstallare > numeroVersioneEsistente) {
            installa = true;
        }// fine del blocco if

        return installa;
    }// end of static method


    /**
     * L'ordine di presentazione (obbligatorio, unico per tutte le company), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getNewOrdine() {
        int ordine = 0;

        List<Versione> lista = repository.findTop1ByOrderByOrdineDesc();
        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine + 1;
    }// end of method

}// end of class
