package it.algos.springvaadin.entity.versione;

import com.vaadin.ui.Notification;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibParams;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosMongoRepository;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.service.AlgosServiceImpl;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Qualifier(Cost.TAG_VERS)
public class VersioneService extends AlgosServiceImpl {

//    @Autowired
//    private VersioneRepository versRepository;


    /**
     * Costruttore @Autowired
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(@Qualifier(Cost.TAG_VERS) MongoRepository repository) {
        super(repository);
    }// end of Spring constructor


    /**
     * Creazione dei dati iniziali
     */
    public void creaDatiIniziali() {
        crea("Setup", "Creazione ed installazione iniziale dell'applicazione");
        crea("Flag", "Regolazione dei flags di controllo");
    }// end of method


    /**
     * Creazione di una entity
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     */
    public Versione crea(String titolo, String descrizione) {
        Versione vers = ((VersioneRepository) repository).findByTitoloAndDescrizione(titolo, descrizione);

        if (vers == null) {
            vers = (Versione) repository.save(newEntity(titolo, descrizione));
        }// end of if cycle

        return vers;
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     */
    public Versione newEntity() {
        return newEntity("", "");
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     */
    public Versione newEntity(String titolo, String descrizione) {
        return newEntity(titolo, descrizione, 0, null);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param ordine      di creazione (obbligatorio, unico)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     */
    public Versione newEntity(String titolo, String descrizione, int ordine, LocalDateTime modifica) {
        return new Versione(ordine == 0 ? this.getOrdine() : ordine, titolo, descrizione, modifica != null ? modifica : LocalDateTime.now());
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getOrdine() {
        int ordine = 1;

        List<Versione> lista = ((VersioneRepository) repository).findTop1ByOrderByOrdineDesc();

        if (lista != null && lista.size() == 1) {
            ordine = lista.get(0).getOrdine() + 1;
        }// end of if cycle

        return ordine;
    }// end of method


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List findAll() {
        return ((VersioneRepository) repository).findAllByOrderByOrdineAsc();
    }// end of method




    /**
     * Cancella la singola entity
     *
     * @param entityBean da cancellare
     *
     * @return true, se la entity è stata effettivamente cancellata
     */
    public boolean delete(AlgosEntity entityBean) {
        repository.delete(entityBean.getId());

        if (repository.findOne(entityBean.getId()) == null) {
            Notification.show("Delete", "Cancellato il record: " + entityBean, Notification.Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Delete", "Non sono riuscito a cancellare il record: " + entityBean, Notification.Type.WARNING_MESSAGE);
        }// end of if/else cycle

        return true;
    }// end of method


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getListColumns() {
        return Arrays.asList("ordine", "titolo", "descrizione", "modifica");
    }// end of method

    /**
     * Ordine standard di presentazione dei fields nel form
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getFormFields() {
        return Arrays.asList("ordine", "titolo", "descrizione", "modifica");
    }// end of method

}// end of class
