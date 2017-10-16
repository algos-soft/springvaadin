package it.algos.springvaadin.entity.preferenza;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gac on 16-ott-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@Service
@Qualifier(Cost.TAG_PRE)
@Slf4j
public class PreferenzaService extends AlgosServiceImpl {

    private PreferenzaRepository repository;

    /**
     * Costruttore @Autowired (nella superclasse)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public PreferenzaService(@Qualifier(Cost.TAG_PRE) MongoRepository repository) {
        super(repository);
        this.repository = (PreferenzaRepository) repository; //casting per uso locale
    }// end of Spring constructor

    /**
     * Ricerca e creazione di una entity (la crea se non la trova)
     * All properties
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     *
     * @return la entity trovata o appena creata
     */
    public Preferenza findOrCrea(String sigla) {
        if (isNonEsiste(sigla)) {
            try { // prova ad eseguire il codice
                return (Preferenza) save(newEntity(sigla));
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
    public Preferenza newEntity() {
        return newEntity("");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param sigla di riferimento interna (interna, obbligatoria ed unica)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Preferenza newEntity(String sigla) {
        return new Preferenza(sigla);
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
     * Recupera una istanza della Entity usando la query della key ID
     *
     * @return istanza della Entity, null se non trovata
     */
    public Preferenza find(ObjectId id) {
        return repository.findById(id);
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Preferenza findBySigla(String sigla) {
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
