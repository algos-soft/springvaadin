package it.algos.springvaadin.entity.company;


import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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



}// end of class
