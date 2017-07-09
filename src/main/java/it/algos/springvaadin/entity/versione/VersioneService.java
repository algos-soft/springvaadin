package it.algos.springvaadin.entity.versione;

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
@Qualifier(Cost.TAG_VERS)
public class VersioneService extends AlgosServiceImpl {


    /**
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public VersioneService(@Qualifier(Cost.TAG_VERS) AlgosEntity entity, @Qualifier(Cost.TAG_VERS) AlgosRepository repository) {
        super(entity,repository);
    }// end of Spring constructor



    @Override
    public Class<? extends AlgosEntity> getEntityClass() {
        return Versione.class;
    }// end of method


}// end of class
