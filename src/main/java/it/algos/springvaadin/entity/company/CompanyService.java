package it.algos.springvaadin.entity.company;


import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneJDBCRepository;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by gac on 07/07/17
 * .
 */
@Service
@Qualifier(Cost.TAG_COMP)
public class CompanyService extends AlgosServiceImpl {

    /**
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public CompanyService(@Qualifier(Cost.TAG_COMP) AlgosEntity entity, @Qualifier(Cost.TAG_COMP) AlgosRepository repository) {
        super(entity, repository);
    }// end of Spring constructor


    @Override
    public Class<? extends AlgosEntity> getEntityClass() {
        return Company.class;
    }// end of method


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    @Override
    public List<String> getListColumns() {
        String[] array = {"sigla", "descrizione", "indirizzo", "email", "contatto", "telefono"};
        return LibArray.fromString(array);
    }// end of method


    /**
     * Creazione iniziale di una entity
     * Vuota, coi valori di default
     */
    @Override
    public AlgosEntity newEntity() {
        return newEntity("","");
    }// end of method


    public Company newEntity(String sigla, String descrizione) {
        return newEntity(sigla, descrizione, "", "", "", "", "", "", (LocalDate) null);
    }// end of method


    /**
     * Eventuali regolazioni iniziali delle property
     * L'ordine di creazione (obbligatorio, unico) viene calcolato in automatico (se manca)
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico (se manca)
     *
     * @param sigla       sigla di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     * @param email       posta elettronica (facoltativo)
     * @param indirizzo   indirizzo postale (facoltativo)
     * @param contatto    persona di riferimento (facoltativo)
     * @param telefono    telefono fisso ufficio (facoltativo)
     * @param cellulare   cellulare del contatto (facoltativo)
     * @param note        eventuali note (facoltativo)
     * @param partenza    inserimento iniziale (facoltativo)
     */
    public Company newEntity(
            String sigla,
            String descrizione,
            String email,
            String indirizzo,
            String contatto,
            String telefono,
            String cellulare,
            String note,
            LocalDate partenza) {
        Company comp = new Company();

        comp.setSigla(sigla);
        comp.setDescrizione(descrizione);
        comp.setEmail(email);
        comp.setIndirizzo(indirizzo);
        comp.setContatto(contatto);
        comp.setTelefono(telefono);
        comp.setCellulare(cellulare);
        comp.setNote(note);
        comp.setPartenza(partenza != null ? partenza : LocalDate.now());

        return comp;
    }// end of method

    /**
     * Creazione di una Entiry
     *
     * @param sigla       sigla di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     */
    public void crea(String sigla, String descrizione) {

        //--controllo di univocità dei parametri
        if (!isEsisteBySigla(sigla)) {
            Company comp = newEntity(sigla, descrizione);
            super.save(comp);
        }// end of if cycle

    }// end of method

    /**
     * Controlla l'esistenza di una entity con gli stessi valori delle property indicate, uniche prese insieme
     *
     * @param sigla sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    private boolean isEsisteBySigla(String sigla) {
        return ((CompanyJDBCRepository) repository).isEsisteBySigla(sigla);
    }// end of method

}// end of class
