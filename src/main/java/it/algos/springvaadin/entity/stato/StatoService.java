package it.algos.springvaadin.entity.stato;

import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibAvviso;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.LibFile;
import it.algos.springvaadin.lib.LibResource;
import it.algos.springvaadin.service.AlgosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by gac on 10-ago-17
 * Annotated with @Service (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@Service
@Slf4j
@Qualifier(Cost.TAG_STA)
public class StatoService extends AlgosServiceImpl {

    private final static String DEFAULT = "Italia";

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
        Stato entity = null;

        if (repository != null) {
            entity = ((StatoRepository) repository).findByNome(nome);
        }// end of if cycle

        if (repository != null && entity == null) {
            entity = newEntity(0, nome);
            entity = (Stato) repository.save(newEntity(0, nome));
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
    public AEntity save(AEntity entityBean) throws Exception {
        boolean nuovaEntity = entityBean.id == null || entityBean.id.equals("");

        if (nuovaEntity) {
            String nomeOriginale = ((Stato) entityBean).getNome();
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
                LibAvviso.error(nomeOriginale + " non è stato creato, perché l'ID risultante esiste già");
                return entityBean;
            }// end of if cycle

            if (sigla.length() > 5) {
                LibAvviso.warn("Controlla l'ultimo stato inserito, perché l'ID sembra eccessivamente lungo");
            }// end of if cycle

            boolean esiste = entityBean.id != null && repository.exists(entityBean.id);
            if (!esiste) {
                entityBean.id = sigla;
            }// end of if cycle
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
        return newEntity(0, DEFAULT);
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
        return newEntity(ordine, nome, alfaDue, alfaTre, numerico, new byte[23]);
    }// end of method


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     *
     * @param nome corrente completo, non ufficiale (obbligatorio ed unico)
     *
     * @return la nuova entity appena creata
     */
    public Stato newEntity(int ordine, String nome, String alfaDue, String alfaTre, String numerico, byte[] bandiera) {
        return new Stato(ordine == 0 ? this.getNewOrdine() : ordine, nome, alfaDue, alfaTre, numerico, bandiera);
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
     * Find the default state.
     *
     * @return the entity
     */
    public Stato find() {
        return crea(DEFAULT);
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
        List<Stato> lista = null;

        if (repository != null) {
            lista = ((StatoRepository) repository).findTop1ByOrderByOrdineDesc();
        }// end of if cycle

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


    /**
     * Creazione di una collezione di stati
     */
    public void creaStati() {
        String fileName = "Stati";
        List<String> righe = LibFile.readResources(fileName);
        this.deleteAll();

        for (String riga : righe) {
            creaStato(riga);
        }// end of for cycle
    }// end of method


    /**
     * Creazione di un singolo stato
     */
    private boolean creaStato(String riga) {
        String[] parti = riga.split(",");
        Stato stato;
        int ordine = 0;
        String nome = "";
        String alfaDue = "";
        String alfaTre = "";
        String numerico = "";
        byte[] bandiera = null;

        if (parti.length > 0) {
            nome = parti[0];
        }// end of if cycle
        if (parti.length > 1) {
            alfaDue = parti[1];
        }// end of if cycle
        if (parti.length > 2) {
            alfaTre = parti[2];
            bandiera = LibResource.getImgBytes(alfaTre.toUpperCase() + ".png");
        }// end of if cycle
        if (parti.length > 3) {
            numerico = parti[3];
        }// end of if cycle

        stato = this.newEntity(ordine, nome, alfaDue, alfaTre, numerico, bandiera);

        try { // prova ad eseguire il codice
            stato = (Stato) this.save(stato);
            if (bandiera == null || bandiera.length == 0) {
                log.warn("Stato: " + riga + " - Manca la bandiera");
            } else {
                log.info("Stato: " + riga + " - Tutto OK");
            }// end of if/else cycle
        } catch (Exception unErrore) { // intercetta l'errore
            try { // prova ad eseguire il codice
                stato = this.newEntity(ordine, nome, alfaDue, alfaTre, numerico, new byte[0]);
                log.warn("Stato: " + riga + " - Dimensioni bandiera eccessive");
            } catch (Exception unErrore2) { // intercetta l'errore
                log.error("Stato: " + riga + " - Non sono riuscito a crearlo");
            }// fine del blocco try-catch
        }// fine del blocco try-catch

        return stato != null;
    }// end of method


}// end of class
