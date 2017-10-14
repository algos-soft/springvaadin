package it.algos.springvaadin.entity.persona;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gac on 11-ott-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_PER)
public class PersonaService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public PersonaService(@Qualifier(Cost.TAG_PER) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    public Persona crea(String nome, String cognome) {
//        Persona entity = ((PersonaRepository) repository).findBySigla(nome);@todo rimettere
        Persona entity = null;
        if (entity == null) {
            entity = (Persona) repository.save(newEntity(nome,cognome));
        }// end of if cycle

        return entity;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     */
    public Persona newEntity() {
        return newEntity("","");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    public Persona newEntity(String nome, String cognome) {
        return new Persona(nome, cognome,null,"","");
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((PersonaRepository) repository).findAll();
    }// end of method

}// end of class
