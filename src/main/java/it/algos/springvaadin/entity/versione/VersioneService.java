package it.algos.springvaadin.entity.versione;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by gac on 01/06/17
 * <p>
 * La selezione del menu nella UI di partenza, invoca lo SpringNavigator che rimanda qui
 * SpringBoot inietta le sottoclassi specifiche (xxxPresenter, xxxList e xxxForm)
 * Nel metodo @PostConstruct, viene effettuato il casting alle property più generiche
 * Passa il controllo alla classe AlgosPresenter che gestisce la business logic
 * <p>
 * Riceve i comandi ed i dati da xxxPresenter (sottoclasse di AlgosPresenter)
 * Gestisce due modalità di presentazione dei dati: List e Form
 * Presenta i componenti grafici passivi
 * Presenta i componenti grafici attivi: azioni associate alla Grid e bottoni coi listener
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@Service
@Qualifier(Cost.TAG_VERS)
public class VersioneService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(@Qualifier(Cost.TAG_VERS) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata
     */
    public Versione crea(String titolo, String descrizione) {
        Versione vers = ((VersioneRepository) repository).findByTitoloAndDescrizione(titolo, descrizione);

        if (vers == null) {
            vers = (Versione) repository.save(newEntity(titolo, descrizione));
        }// end of if cycle

        return vers;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @return la nuova entity appena creata
     */
    public Versione newEntity() {
        return newEntity("", "");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata
     */
    public Versione newEntity(String titolo, String descrizione) {
        return newEntity(titolo, descrizione, 0, null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param ordine      di creazione (obbligatorio, unico)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata
     */
    public Versione newEntity(String titolo, String descrizione, int ordine, LocalDateTime modifica) {
        return new Versione(ordine == 0 ? this.getNewOrdine() : ordine, titolo, descrizione, modifica != null ? modifica : LocalDateTime.now());
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

        List<Versione> lista = ((VersioneRepository) repository).findTop1ByOrderByOrdineDesc();

        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine;
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((VersioneRepository) repository).findAllByOrderByOrdineAsc();
    }// end of method


    /**
     * Controlla se esiste il numero di versione da installare
     *
     * @return true se la versione non esiste
     */
    public boolean isVersioneInesistente(int numeroVersioneDaInstallare) {
        boolean installa = false;
        int numeroVersioneEsistente = getMax();

        if (numeroVersioneDaInstallare > numeroVersioneEsistente) {
            installa = true;
        }// fine del blocco if

        return installa;
    }// end of static method


}// end of class
