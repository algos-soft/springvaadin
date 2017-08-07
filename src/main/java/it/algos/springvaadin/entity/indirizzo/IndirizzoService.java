package it.algos.springvaadin.entity.indirizzo;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by gac on 07-ago-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_IND)
public class IndirizzoService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public IndirizzoService(@Qualifier(Cost.TAG_IND) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param indirizzo: via, nome e numero (obbligatoria, non unica)
     */
    public Indirizzo crea(String indirizzo) {
        Indirizzo entity = ((IndirizzoRepository) repository).findByIndirizzo(indirizzo);
        if (entity == null) {
            entity = (Indirizzo) repository.save(newEntity(indirizzo, "", "", ""));
        }// end of if cycle

        return entity;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     */
    public Indirizzo newEntity() {
        return newEntity("", "", "", "Italia");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param indirizzo: via, nome e numero (obbligatoria, non unica)
     * @param localita:  località (obbligatoria, non unica)
     * @param cap:       codice di avviamento postale (obbligatoria, non unica)
     * @param stato:     stato (obbligatoria, non unica)
     */
    public Indirizzo newEntity(String indirizzo, String localita, String cap, String stato) {
        return new Indirizzo(indirizzo, localita, cap, stato);
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((IndirizzoRepository) repository).findAll();
    }// end of method

}// end of class
