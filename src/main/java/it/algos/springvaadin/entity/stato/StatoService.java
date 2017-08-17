package it.algos.springvaadin.entity.stato;

import com.mongodb.DuplicateKeyException;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by gac on 10-ago-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_STA)
public class StatoService extends AlgosServiceImpl {


    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public StatoService(@Qualifier(Cost.TAG_STA) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione di una entity
     *
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     *
     * @return true se è stata creata una nuova entity
     */
    public boolean isCreata(String nome) {
        boolean creata = false;
        Stato entity = ((StatoRepository) repository).findByNome(nome);
        if (entity == null) {
            crea(nome);
            creata = true;
        }// end of if cycle

        return creata;
    }// end of method


    /**
     * Creazione di una entity
     *
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     *
     * @return la nuova entity appena creata
     */
    public Stato crea(String nome) {
        Stato entity = ((StatoRepository) repository).findByNome(nome);
        if (entity == null) {
            entity = newEntity(0, nome);
            entity = (Stato) repository.save(newEntity(0, nome));
            int a = 87;
        }// end of if cycle

        return entity;
    }// end of method

    /**
     * Saves a given entity.
     * Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param entityBean da salvare
     *
     * @return the saved entity
     */
    @Override
    public AlgosEntity save(AlgosEntity entityBean) throws Exception {
        String nomeOriginale=((Stato) entityBean).getNome();
        String nome = nomeOriginale.toLowerCase();
        String sigla = ((Stato) entityBean).getAlfaTre().toLowerCase();
        if (sigla.equals("")) {
            int k = 3;
            do {
                sigla = nome.substring(0, k++);
            }// end of do cycle
            while (repository.exists(sigla) && sigla.length() < 6);// end of while cycle
        }// end of if cycle

        if (repository.exists(sigla)) {
            LibAvviso.error(nomeOriginale+" non è stato creato, perché l'ID risultante esiste già");
            return entityBean;
        }// end of if cycle

        if (sigla.length() > 5) {
            LibAvviso.warn("Controlla l'ultimo stato inserito, perché l'ID sembra eccessivamente lungo");
        }// end of if cycle

        boolean esiste = entityBean.id != null && repository.exists(entityBean.id);
        if (!esiste) {
            entityBean.id = sigla;
        }// end of if cycle

        return super.save(entityBean);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @return la nuova entity appena creata
     */
    public Stato newEntity() {
        return newEntity(0, "");
    }// end of method

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @return la nuova entity appena creata
     */
    public Stato newEntity(int ordine, String nome) {
        return newEntity(ordine, nome, "", "", "");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     *
     * @return la nuova entity appena creata
     */
    public Stato newEntity(int ordine, String nome, String alfaDue, String alfaTre, String numerico) {
        return new Stato(ordine == 0 ? this.getNewOrdine() : ordine, nome, alfaDue, alfaTre, numerico);
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((StatoRepository) repository).findAll();
    }// end of method

    /**
     * Find the entity by name.
     *
     * @return the entity
     */
    public Stato findByNome(String nome) {
        return ((StatoRepository) repository).findByNome(nome);
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

        List<Stato> lista = ((StatoRepository) repository).findTop1ByOrderByOrdineDesc();

        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine;
    }// end of method

    /**
     * Find the entity by name.
     *
     * @return the entity
     */
    public boolean isEsisteByNome(String nome) {
        return true;
    }// end of method

}// end of class
