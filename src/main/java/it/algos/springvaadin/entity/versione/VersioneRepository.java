package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.algos.springvaadin.lib.Cost;

import java.util.List;

/**
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come annotation
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public interface VersioneRepository extends MongoRepository<Versione, String> {

    public Versione findByOrdine(int ordine);

    public List<Versione> findByOrderByOrdineAsc();

    public List<Versione> findByCompanyOrderByOrdineAsc(Company company);

    public List<Versione> findTop1ByOrderByOrdineDesc();

}// end of class
