package it.algos.springvaadin.repository;

import com.vaadin.spring.annotation.SpringComponent;

import java.io.Serializable;

/**
 * Created by gac on 02/07/17
 * <p>
 * Repository che utilizza JdbcTemplate (basso livello, architettura più semplice, uso più macchinoso)
 * Possono essere aggiunti metodi specifici, necessari solo per Spring Boot JDBC
 * Questa Repository è alternativa a AlgosJPARepository
 */
@SpringComponent
public interface AlgosJDBCRepository extends AlgosRepository {

}// end of interface
