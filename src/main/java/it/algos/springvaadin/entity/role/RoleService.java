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
@Slf4j
@Service
@Scope("session")
@Qualifier(Cost.TAG_ROL)
public class RoleService extends AService {

    /**
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
     * Si usa un @Qualifier(), per avere la sottoclasse specifica
     * Si usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     */
    public RoleService(@Qualifier(Cost.TAG_ROL) MongoRepository repository) {
        super(repository);
        super.entityClass = Role.class;
    }// end of Spring constructor


}// end of class
