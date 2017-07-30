package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.repository.AlgosMongoRepository;
import it.algos.springvaadin.repository.AlgosRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@SpringComponent
@Qualifier(Cost.TAG_VERS)
public interface VersioneRepository extends MongoRepository<Versione, String> {

    public Versione findByOrdine(int ordine);

    public Versione findByTitolo(String titolo);

    public Versione findByDescrizione(String descrizione);

    public Versione findByTitoloAndDescrizione(String titolo, String descrizione);

    public List<Versione> findTop1ByOrderByOrdineDesc();

    public List<Versione> findAllByOrderByOrdineAsc();

}// end of class
