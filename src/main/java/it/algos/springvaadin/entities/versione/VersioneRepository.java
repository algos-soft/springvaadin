package it.algos.springvaadin.entities.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entities.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import it.algos.springvaadin.entities.versione.Versione;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gac on 13/06/17
 * .
 */
@Transactional
public  interface VersioneRepository  {

}// end of class
