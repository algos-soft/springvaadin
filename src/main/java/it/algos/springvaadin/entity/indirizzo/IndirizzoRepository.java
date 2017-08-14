package it.algos.springvaadin.entity.indirizzo;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.algos.springvaadin.lib.Cost;

import java.util.List;

/**
 * Created by gac on 07-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_IND)
public interface IndirizzoRepository extends MongoRepository<Indirizzo, String> {

    public Indirizzo findByIndirizzo(String indirizzo);

    public List<Indirizzo> findTop1AllByLocalita(String localita);

    public List<Indirizzo> findAllByLocalita(String localita);

}// end of class
