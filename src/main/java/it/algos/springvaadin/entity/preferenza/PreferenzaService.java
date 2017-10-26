package it.algos.springvaadin.entity.preferenza;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.lib.LibSession;
import it.algos.springvaadin.login.ARoleType;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * Properties obbligatorie
     * All properties
     *
     * @param ordine      (facoltativo, modificabile con controllo automatico prima del save se è zero)
     * @param code        sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     * @param type        di dato memorizzato (obbligatorio)
     * @param level       di accesso alla preferenza (obbligatorio)
     * @param descrizione visibile (obbligatoria)
     * @param value       valore della preferenza (obbligatorio)
     * @param riavvio     riavvio del programma per avere effetto (obbligatorio, di default false)
     *
     * @return la entity trovata o appena creata
     */
    public Preferenza findOrCrea(int ordine, String code, PrefType type, ARoleType level, String descrizione, byte[] value, boolean riavvio) {
        if (nonEsiste(code)) {
            try { // prova ad eseguire il codice
                return (Preferenza) save(newEntity(ordine, code, type, level, descrizione, value, riavvio));
            } catch (Exception unErrore) { // intercetta l'errore
                log.error(unErrore.toString());
                return null;
            }// fine del blocco try-catch
        } else {
            return repository.findByCode(code);
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
        return newEntity(0, "", (PrefType) null, (ARoleType) null, "", (byte[]) null, false);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * All properties
     * Gli argomenti (parametri) della new Entity DEVONO essere ordinati come nella Entity (costruttore lombok)
     *
     * @param ordine      (facoltativo, modificabile con controllo automatico prima del save se è zero)
     * @param code        sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     * @param type        di dato memorizzato (obbligatorio)
     * @param level       di accesso alla preferenza (obbligatorio)
     * @param descrizione visibile (obbligatoria)
     * @param value       valore della preferenza (obbligatorio)
     * @param riavvio     riavvio del programma per avere effetto (obbligatorio, di default false)
     *
     * @return la nuova entity appena creata (non salvata)
     */
    public Preferenza newEntity(int ordine, String code, PrefType type, ARoleType level, String descrizione, byte[] value, boolean riavvio) {
        return new Preferenza(
                ordine == 0 ? this.getNewOrdine() : ordine,
                code,
                type,
                level,
                descrizione,
                value,
                riavvio);
    }// end of method


    /**
     * Controlla che non esista una istanza della Entity usando la property specifica (obbligatoria ed unica)
     *
     * @param code sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     *
     * @return vero se esiste, false se non trovata
     */
    public boolean nonEsiste(String code) {
        return findByCode(code) == null;
    }// end of method


    /**
     * Recupera una istanza della Entity usando la query della property specifica (obbligatoria ed unica)
     * Filtrato sulla azienda corrente.
     *
     * @param code sigla di riferimento interna (interna, obbligatoria ed unica per la company)
     *
     * @return istanza della Entity, null se non trovata
     */
    public Preferenza findByCode(String code) {
        return repository.findByCode(code);
    }// end of method


    /**
     * Returns all instances of the type.
     * Usa MultiCompany
     * Filtrata sulla company indicata
     *
     * @param company facoltativaSenzaCodeUnico
     *
     * @return all entities
     */
    public List findAllByCompany(Company company) {
        ArrayList<Preferenza> listaAdmin;
        ArrayList<Preferenza> listaUser;

        if (LibSession.isDeveloper()) {
            return repository.findByCompanyOrderByOrdineAsc(company);
        } else {
            if (LibSession.isAdmin()) {
                listaAdmin = (ArrayList) repository.findByCompanyAndLivelloOrderByOrdineAsc(company, ARoleType.admin);
                listaUser = (ArrayList) repository.findByCompanyAndLivelloOrderByOrdineAsc(company, ARoleType.user);
                return LibArray.somma(listaAdmin, listaUser);
            } else {
                return repository.findByCompanyAndLivelloOrderByOrdineAsc(company, ARoleType.user);
            }// end of if/else cycle
        }// end of if/else cycle
    }// end of method

    /**
     * L'ordine di presentazione (obbligatorio, unico per tutte le company), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getNewOrdine() {
        int ordine = 0;

        List<Preferenza> lista = repository.findTop1ByOrderByOrdineDesc();
        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine();
        }// end of if cycle

        return ordine + 1;
    }// end of method

}// end of class
