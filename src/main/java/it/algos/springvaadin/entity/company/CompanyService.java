package it.algos.springvaadin.entity.company;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.log.Log;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gac on 01/06/17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@Service
@Qualifier(Cost.TAG_COMP)
@Slf4j
public class CompanyService extends AlgosServiceImpl {

    private CompanyRepository repository;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public CompanyService(@Qualifier(Cost.TAG_COMP) MongoRepository repository) {
        super(repository);
        this.repository = (CompanyRepository) repository; //casting per uso locale
    }// end of Spring constructor


    /**
     * Ricerca e nuovo di una entity (la crea se non la trova)
     * Properties obbligatorie
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     *
     * @return la entity trovata o appena creata
     */
    public Company findOrCrea(String sigla, String descrizione) {
        return findOrCrea(sigla, descrizione, (Persona) null, "", (Indirizzo) null);
    }// end of method


    /**
     * Ricerca e nuovo di una entity (la crea se non la trova)
     * All properties
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param contact     persona di riferimento (facoltativo)
     * @param email       delal company (facoltativo)
     * @param indirizzo   della company (facoltativo)
     *
     * @return la entity trovata o appena creata
     */
    public Company findOrCrea(String sigla, String descrizione, Persona contact, String email, Indirizzo indirizzo) {
        if (nonEsiste(sigla)) {
            try { // prova ad eseguire il codice
                return (Company) save(newEntity(sigla, descrizione, contact, email, indirizzo));
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
                return null;
            }// fine del blocco try-catch
        } else {
            return repository.findBySigla(sigla);
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
    public Company newEntity() {
        return newEntity("", "", (Persona) null, "", (Indirizzo) null);
    }// end of method

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param contact     persona di riferimento (facoltativo)
     * @param email       delal company (facoltativo)
     * @param indirizzo   della company (facoltativo)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Company newEntity(String sigla, String descrizione, Persona contact, String email, Indirizzo indirizzo) {
        return new Company(sigla, descrizione, contact, email, indirizzo);
    }// end of method


    /**
     * Controlla che non esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean nonEsiste(String sigla) {
        return findBySigla(sigla) == null;
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della key ID
     *
     * @return istanza della Entity, null se non trovata
     */
    public Company find(ObjectId id) {
        return repository.findById(id);
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Company findBySigla(String sigla) {
        return repository.findBySigla(sigla);
    }// end of method


    /**
     * Returns all instances of the type
     * Non usa MultiCompany, quindi senza filtri
     *
     * @return lista di tutte le entities
     */
    public List findAll() {
        return repository.findAllByOrderBySiglaAsc();
    }// end of method

}// end of class