package it.algos.springvaadin.entities.versione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gac on 13/06/17
 * .
 */
//@Transactional
@Repository()
public interface VersioneRepository<Versione, id extends Serializable> extends CrudRepository<Versione, Long> {
    List<Versione> findByTitolo(String titolo);
}// end of class
