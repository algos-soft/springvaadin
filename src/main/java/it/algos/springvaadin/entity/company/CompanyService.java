package it.algos.springvaadin.entity.company;

import it.algos.springvaadin.entity.indirizzo.Indirizzo;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return new Company(sigla, descrizione, indirizzo);
    }// end of method


    /**
     * Returns entity
     *
     * @return entity
     */
    public Company find(ObjectId id) {
        return ((CompanyRepository) repository).findById(id);
    }// end of method

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((CompanyRepository) repository).findAllByOrderBySiglaAsc();
    }// end of method

}// end of class