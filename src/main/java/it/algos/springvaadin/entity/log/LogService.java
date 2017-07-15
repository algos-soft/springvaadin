package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by gac on 07/07/17
 * .
 */
@Service
@Qualifier(Cost.TAG_LOG)
public class LogService extends AlgosServiceImpl {


    /**
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public LogService(@Qualifier(Cost.TAG_LOG) AlgosEntity entity, @Qualifier(Cost.TAG_LOG) AlgosRepository repository) {
        super(entity, repository);
    }// end of Spring constructor


    @Override
    public Class<? extends AlgosEntity> getEntityClass() {
        return Log.class;
    }// end of method


    @Override
    protected void creaDatiIniziali() {
    }// end of method

    /**
     * Creazione iniziale di una entity
     * Vuota, coi valori di default
     */
    @Override
    public AlgosEntity newEntity() {
        return newEntity("", "");
    }// end of method


    public Log newEntity(String titolo, String descrizione) {
        return newEntity((Company) null, (Livello) null, titolo, descrizione, (LocalDateTime) null);
    }// end of method


    /**
     * Eventuali regolazioni iniziali delle property
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param company     di riferimento (puo essere nullo)
     * @param livello     di presentazione (obbligatorio, non unico)
     * @param titolo      codifica di gruppo per identificare la tipologia del log (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     * @param modifica    data di inserimento della versione (obbligatoria, non unica)
     */
    public Log newEntity(Company company, Livello livello, String titolo, String descrizione, LocalDateTime modifica) {
        Log log = new Log();

        log.setCompany(company);
        log.setLivello(livello != null ? livello : Livello.info);
        log.setTitolo(titolo);
        log.setDescrizione(descrizione);
        log.setModifica(modifica != null ? modifica : LocalDateTime.now());

        return log;
    }// end of method


    /**
     * Creazione di una Entiry
     *
     * @param titolo      codifica di gruppo per identificare la tipologia della versione (obbligatoria, non unica)
     * @param descrizione descrizione (obbligatoria, non unica)
     */
    public Log crea(String titolo, String descrizione) {
        Log log = newEntity(titolo, descrizione);
        return (Log) super.save(log);
    }// end of method

}// end of class
