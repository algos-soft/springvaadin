package it.algos.springvaadin.entity.role;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.service.AService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 08:41
 */
@Service
@Scope("session")
@Qualifier(Cost.TAG_ROL)
@Slf4j
public class RoleService extends AService {


    /**
     * Costruttore @Autowired
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param repository iniettato da Spring come sottoclasse concreta specificata dal @Qualifier
     */
    public RoleService(@Qualifier(Cost.TAG_ROL) MongoRepository repository) {
        super(repository);
        super.entityClass = Role.class;
    }// end of Spring constructor


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata
     * Eventuali regolazioni iniziali delle property
     * Senza properties per compatibilità con la superclasse
     *
     * @return la nuova entity appena creata (non salvata)
     */
    @Override
    public Role newEntity() {
        return new Role(0, "");
    }// end of method


}// end of class
