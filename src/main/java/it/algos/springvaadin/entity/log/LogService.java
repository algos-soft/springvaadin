package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

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
        Log entity = ((LogRepository) repository).findBySigla(sigla);
        if (entity == null) {
            entity = (Log) repository.save(newEntity(sigla));
        }// end of if cycle

        return entity;
    }// end of method


     /**
      * Creazione in memoria di una nuova entity che NON viene salvata
      * Eventuali regolazioni iniziali delle property
      */
     public Log newEntity() {
         return newEntity("");
     }// end of method


     /**
      * Creazione in memoria di una nuova entity che NON viene salvata
      * Eventuali regolazioni iniziali delle property
      *
      * @param sigla di riferimento interna (interna, obbligatoria ed unica)
      */
     public Log newEntity(String sigla) {
         return new Log(sigla);
     }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((LogRepository) repository).findAll();
    }// end of method

}// end of class
