package it.algos.springvaadin.entity.versione;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gac on 13/06/17
 * .
 */
//@Transactional
//@Repository()
public interface VersioneRepository<Versione, id extends Serializable> extends CrudRepository<Versione, Long> {
    List<Versione> findByTitolo(String titolo);
}// end of class
