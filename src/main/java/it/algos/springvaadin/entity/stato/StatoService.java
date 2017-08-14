package it.algos.springvaadin.entity.stato;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
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
            entity = (Stato) repository.save(newEntity(0, nome));
        }// end of if cycle

        return entity;
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
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     *
     * @return la nuova entity appena creata
     */
    public Stato newEntity(int ordine, String nome) {
        return new Stato(ordine == 0 ? this.getNewOrdine() : ordine, nome);
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

}// end of class
