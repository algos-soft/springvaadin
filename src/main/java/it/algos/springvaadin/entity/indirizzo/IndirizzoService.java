package it.algos.springvaadin.entity.indirizzo;

import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AEntity;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gac on 07-ago-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
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
     * @param localita:  località (obbligatoria, non unica)
     * @param cap:       codice di avviamento postale (obbligatoria, non unica)
     * @param stato:     stato (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata
     */
    public AEntity crea(String indirizzo, String localita, String cap, Stato stato) {
        AEntity entity = ((IndirizzoRepository) repository).findByIndirizzo(indirizzo);
        if (entity == null) {
            entity = (AEntity) repository.save(newEntity(indirizzo, localita, cap, stato));
        }// end of if cycle

        return entity;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @return la nuova entity appena creata
     */
    public AEntity newEntity() {
        return newEntity("", "", "", (Stato) null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param indirizzo: via, nome e numero (obbligatoria, non unica)
     * @param localita:  località (obbligatoria, non unica)
     * @param cap:       codice di avviamento postale (obbligatoria, non unica)
     * @param stato:     stato (obbligatoria, non unica)
     *
     * @return la nuova entity appena creata
     */
    public AEntity newEntity(String indirizzo, String localita, String cap, Stato stato) {
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


    /**
     * Returns selected instances of the type.
     *
     * @return selected entities
     */
    public List findAllByLocalita(String localita) {
        return ((IndirizzoRepository) repository).findAllByLocalita(localita);
    }// end of method


    /**
     * Returns selected instances of the type.
     *
     * @return selected entities
     */
    public AEntity findFirstByLocalita(String localita) {
        AEntity trovato = null;
        List lista = findAllByLocalita(localita);

        if (lista != null && lista.size() == 1) {
            trovato = (AEntity) lista.get(0);
        }// end of if cycle

        return trovato;
    }// end of method


    /**
     * Returns entity
     *
     * @return entity
     */
    public AEntity findById(String id) {
        return ((IndirizzoRepository) repository).findOne(id);
    }// end of method

}// end of class
