package it.algos.springvaadin.entity.preferenza;

import com.vaadin.spring.annotation.SpringComponent;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.algos.springvaadin.lib.Cost;

import java.util.List;

/**
 * Created by gac on 16-ott-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_PRE)
public interface PreferenzaRepository extends MongoRepository<Preferenza, String> {


    public Preferenza findById(ObjectId id);

    public Preferenza findBySigla(String sigla);

    public List<Preferenza> findAllByOrderBySiglaAsc();

    public Preferenza save(Preferenza entity);

}// end of class
