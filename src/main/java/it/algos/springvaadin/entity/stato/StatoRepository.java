package it.algos.springvaadin.entity.stato;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.versione.Versione;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.algos.springvaadin.lib.Cost;

import java.util.List;

/**
 * Created by gac on 10-ago-17
 * Annotated with @SpringComponent (obbligatorio)
 * Annotated with @Qualifier, per individuare la classe specifica da iniettare come interfaccia
 */
@SpringComponent
@Qualifier(Cost.TAG_STA)
public interface StatoRepository extends MongoRepository<Stato, String> {

    public Stato findByNome(String nome);
    public Stato findByAlfaTre(String nome);
    public int countByAlfaTre(String alfaTre);

    public List<Stato> findTop1ByOrderByOrdineDesc();

    public List<Stato> findAllByOrderByOrdineAsc();

}// end of class
