package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

/**
 * Created by gac on 30-set-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_LOG)
@Slf4j
public class LogService extends AlgosServiceImpl {

    private LogRepository repository;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public LogService(@Qualifier(Cost.TAG_LOG) MongoRepository repository) {
        super(repository);
        this.repository = (LogRepository) repository; //casting per uso locale
    }// end of Spring constructor


    /**
     * Ricerca e creazione di una entity (la crea se non la trova)
     * Properties obbligatorie
     * Le entites di questa collezione non sono uniche, quindi non ha senso controllare se esiste già nella collezione
     * Metodo tenuto per 'omogeneità di firma'. In realtà si potrebbe chiamare 'crea'
     *
     * @param livello:     rilevanza del log
     * @param gruppo:      raggruppamento logico dei log per categorie di eventi
     * @param descrizione: completa in forma testuale
     *
     * @return la entity trovata o appena creata
     */
    public Log findOrCrea(String livello, String gruppo, String descrizione) {
        return findOrCrea(livello, gruppo, descrizione, (LocalDateTime) null);
    }// end of method


    /**
     * Ricerca e creazione di una entity (la crea se non la trova)
     * All properties
     * Le entites di questa collezione non sono uniche, quindi non ha senso controllare se esiste già nella collezione
     * Metodo tenuto per 'omogeneità di firma'. In realtà si potrebbe chiamare 'crea'
     *
     * @param livello:     rilevanza del log
     * @param gruppo:      raggruppamento logico dei log per categorie di eventi
     * @param descrizione: completa in forma testuale
     * @param evento:      data dell'evento di log
     *
     * @return la entity trovata o appena creata
     */
    public Log findOrCrea(String livello, String gruppo, String descrizione, LocalDateTime evento) {
        try { // prova ad eseguire il codice
            return (Log) save(newEntity(livello, gruppo, descrizione, evento));
        } catch (Exception unErrore) { // intercetta l'errore
            log.error(unErrore.toString());
            return null;
        }// fine del blocco try-catch
    }// end of method

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * Senza properties per compatibilità con la superclasse
     *
     * @return la nuova entity appena creata (non salvata)
     */
    @Override
    public Log newEntity() {
        return newEntity("", "", "", (LocalDateTime) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param livello:     rilevanza del log
     * @param gruppo:      raggruppamento logico dei log per categorie di eventi
     * @param descrizione: completa in forma testuale
     * @param evento:      data dell'evento di log
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Log newEntity(String livello, String gruppo, String descrizione, LocalDateTime evento) {
        return new Log(livello, gruppo, descrizione, evento != null ? evento : LocalDateTime.now());
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
        return repository.findAll();
    }// end of method


    /**
     * Returns all instances of the type.
     * Usa MultiCompany
     * Filtrata sulla company indicata
     *
     * @param company obbligatoriaSenzaCodeUnico
     *
     * @return all entities
     */
    public List findAllByCompany(Company company) {
        return repository.findAllByCompany(company);
    }// end of method

}// end of class
