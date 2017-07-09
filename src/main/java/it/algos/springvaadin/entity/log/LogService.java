package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

}// end of class
