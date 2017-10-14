package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosServiceImpl;
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
public class LogService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public LogService(@Qualifier(Cost.TAG_LOG) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    public Log crea(String sigla) {
//        Log entity = ((LogRepository) repository).findBySigla(sigla);
        Log entity = null;
        if (entity == null) {
            entity = (Log) repository.save(newEntity(sigla,"",null));
        }// end of if cycle

        return entity;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     */
    public Log newEntity() {
        return newEntity("","",null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    public Log newEntity(String evento, String descrizione, LocalDateTime data) {
        return new Log("",
                descrizione,
                data != null ? data : LocalDateTime.now());
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((LogRepository) repository).findAll();
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAllByCompany(Company company) {
        return ((LogRepository) repository).findAllByCompany(company);
    }// end of method

}// end of class
