package it.algos.springvaadin.entity.user;
import it.algos.springvaadin.lib.Cost;
import com.vaadin.spring.annotation.SpringComponent;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.algos.springvaadin.lib.Cost;

import java.util.List;

/**
 * Created by gac on TIMESTAMP
 * Estende la l'interaccia MongoRepository col casting alla Entity relativa di questa repository
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier (obbligatorio) per permettere a Spring di istanziare la sottoclasse specifica
 */
@SpringComponent
@Qualifier(Cost.TAG_USE)
public interface UserRepository extends MongoRepository<User, String> {

    public User findByCode(String code);

    public List<User> findByOrderByCodeAsc();

}// end of class
