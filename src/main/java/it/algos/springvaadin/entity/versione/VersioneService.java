package it.algos.springvaadin.entity.versione;

import it.algos.springvaadin.entity.ACompanyEntity;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyRepository;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private VersioneRepository repository;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(@Qualifier(Cost.TAG_VERS) MongoRepository repository) {
        super(repository);
        this.repository = (VersioneRepository) repository; //casting per uso locale
    }// end of Spring constructor


    /**
     * Ricerca e creazione di una entity (la crea se non la trova)
     * Properties obbligatorie
     *
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     *
     * @return la entity trovata o appena creata
     */
    public Versione findOrCrea(String nome, String gruppo, String descrizione) {
        return findOrCrea(0, gruppo, descrizione, (LocalDateTime) null);
    }// end of method


    /**
     * Ricerca e creazione di una entity (la crea se non la trova)
     * All properties
     *
     * @param ordine      di creazione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     * @param evento      momento in cui si effettua la modifica della versione (obbligatoria, non unica, non modificabile)
     *
     * @return la entity trovata o appena creata
     */
    public Versione findOrCrea(int ordine, String gruppo, String descrizione, LocalDateTime evento) {
        if (nonEsiste(gruppo, descrizione)) {
            try { // prova ad eseguire il codice
                return (Versione) save(newEntity(ordine, gruppo, descrizione, evento));
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
                return null;
            }// fine del blocco try-catch
        } else {
            return repository.findByGruppoAndDescrizione(gruppo, descrizione);
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
        return newEntity(0, "", "", (LocalDateTime) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param ordine      di creazione (obbligatorio, unico, con controllo automatico prima del save se è zero, non modificabile)
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     * @param evento      momento in cui si effettua la modifica della versione (obbligatoria, non unica, non modificabile)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Versione newEntity(int ordine, String gruppo, String descrizione, LocalDateTime evento) {
        return new Versione(
                ordine == 0 ? this.getNewOrdine() : ordine,
                gruppo,
                descrizione,
                evento != null ? evento : LocalDateTime.now());
    }// end of method


    /**
     * Controlla che non esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean nonEsiste(String gruppo, String descrizione) {
        return findByGruppoAndDescrizione(gruppo, descrizione) == null;
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getNewOrdine() {
        return getMax() + 1;
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     */
    private int getMax() {
        int ordine = 0;

        List<Versione> lista = repository.findTop1ByOrderByOrdineDesc();
        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine;
    }// end of method


    /**
     * Returns all instances of the type
     * Usa MultiCompany
     * Filtrata sulla company corrente
     * Se non c'è la company corrente, prende tutte le company
     * Non dovrebbe arrivare qui
     *
     * @return lista di tutte le entities
     */
    @Deprecated
    public List findAll() {
        return repository.findAllByOrderByOrdineAsc();
    }// end of method


    /**
     * Returns all instances of the type.
     * Usa MultiCompany
     * Filtrata sulla company indicata
     *
     * @param company facoltativaSenzaCodeUnico
     *
     * @return all entities
     */
    public List findAllByCompany(Company company) {
        return repository.findAllByOrderByOrdineAsc();//@todo NOT TRUE
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     *
     * @param gruppo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione (obbligatoria, non unica)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Versione findByGruppoAndDescrizione(String gruppo, String descrizione) {
        return repository.findByGruppoAndDescrizione(gruppo, descrizione);
    }// end of method


    /**
     * Controlla se esiste il numero di versione da installare
     *
     * @param numeroVersioneDaInstallare per vedere che non sia già stata installata
     *
     * @return true se la versione non esiste
     */
    public boolean isVersioneNonEsiste(int numeroVersioneDaInstallare) {
        boolean installa = false;
        int numeroVersioneEsistente = getMax();

        if (numeroVersioneDaInstallare > numeroVersioneEsistente) {
            installa = true;
        }// fine del blocco if

        return installa;
    }// end of static method


}// end of class
