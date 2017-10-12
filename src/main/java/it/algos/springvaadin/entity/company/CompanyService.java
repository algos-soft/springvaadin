package it.algos.springvaadin.entity.company;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.entity.persona.Persona;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gac on 01/06/17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@Service
@Qualifier(Cost.TAG_COMP)
public class CompanyService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public CompanyService(@Qualifier(Cost.TAG_COMP) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     *
     * @return la nuova entity appena creata
     */
    public Company crea(String sigla, String descrizione, Indirizzo indirizzo) {
        Company comp = ((CompanyRepository) repository).findBySigla(sigla);

        if (comp == null) {
            comp = (Company) repository.save(newEntity(sigla, descrizione, indirizzo));
        }// end of if cycle

        return comp;
    }// end of method

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @return la nuova entity appena creata
     */
    public Company newEntity() {
        return newEntity("", "", (Indirizzo) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param indirizzo   (facoltativo)
     *
     * @return la nuova entity appena creata
     */
    public Company newEntity(String sigla, String descrizione, Indirizzo indirizzo) {
        return newEntity(sigla, descrizione, indirizzo, "", null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param sigla       di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param indirizzo   (facoltativo)
     *
     * @return la nuova entity appena creata
     */
    public Company newEntity(String sigla, String descrizione, Indirizzo indirizzo, String email, Persona contact) {
        return new Company(sigla, descrizione, contact, email, indirizzo);
    }// end of method


    /**
     * Controlla che esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean isEsiste(String sigla) {
        return findBySigla(sigla) != null;
    }// end of method


    /**
     * Controlla che non esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean isNonEsiste(String sigla) {
        return findBySigla(sigla) == null;
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query di una property specifica
     *
     * @return istanza della Entity, null se non trovata
     */
    public Company find(ObjectId id) {
        return ((CompanyRepository) repository).findById(id);
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query di una property specifica
     *
     * @return istanza della Entity, null se non trovata
     */
    public Company findBySigla(String sigla) {
        return ((CompanyRepository) repository).findBySigla(sigla);
    }// end of method


    /**
     * Returns all instances of the type
     *
     * @return lista di tutte le entities
     */
    public List findAll() {
//        return null;
        return ((CompanyRepository) repository).findAll();
    }// end of method

}// end of class