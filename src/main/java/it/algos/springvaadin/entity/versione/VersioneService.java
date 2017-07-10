package it.algos.springvaadin.entity.versione;

import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by gac on 07/07/17
 * Business logic specifica per il modulo Versione
 */
@Service
@Qualifier(Cost.TAG_VERS)
public class VersioneService extends AlgosServiceImpl {


    /**
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(@Qualifier(Cost.TAG_VERS) AlgosEntity entity, @Qualifier(Cost.TAG_VERS) AlgosRepository repository) {
        super(entity, repository);
    }// end of Spring constructor


    @Override
    public Class<? extends AlgosEntity> getEntityClass() {
        return Versione.class;
    }// end of method


    protected void creaDatiIniziali() {
        crea("Setup", "Creazione ed installazione iniziale dell'applicazione");
        crea("Flag", "Regolazione dei flags di controllo");
    }// end of method


    /**
     * Creazione iniziale di una entity
     * Vuota, coi valori di default
     */
    @Override
    public AlgosEntity newEntity() {
        return newEntity("", "");
    }// end of method


    public Versione newEntity(String titolo, String descrizione) {
        return newEntity(titolo, descrizione, 0, (LocalDateTime) null);
    }// end of method


    /**
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param ordine      di creazione (obbligatorio, unico)
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     */
    public Versione newEntity(String titolo, String descrizione, int ordine, LocalDateTime modifica) {
        Versione vers = new Versione();

        vers.setOrdine(ordine == 0 ? this.getOrdine() : ordine);
        vers.setTitolo(titolo);
        vers.setDescrizione(descrizione);
        vers.setModifica(modifica != null ? modifica : LocalDateTime.now());

        return vers;
    }// end of method

    /**
     *
     * Creazione di una Entiry
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     */
    public void crea(String titolo, String descrizione) {

        //--controllo di univocità dei parametri
        if (isEsisteByTitoloAndDescrizione(titolo, descrizione)) {
            Versione vers = newEntity(titolo, descrizione);
            super.save(vers);
        }// end of if cycle

    }// end of method


    /**
     * Controlla l'esistenza di una entity con gli stessi valori delle property indicate, uniche prese insieme
     *
     * @param titolo      (obbligatorio, non unico singolarmente)
     * @param descrizione (obbligatoria, non unica singolarmente)
     */
    private boolean isEsisteByTitoloAndDescrizione(String titolo, String descrizione) {
        return ((VersioneJDBCRepository) repository).isEsisteByTitoloAndDescrizione(titolo, descrizione);
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    private int getOrdine() {
        return super.getMax("ordine") + 1;
    }// end of method


}// end of class
