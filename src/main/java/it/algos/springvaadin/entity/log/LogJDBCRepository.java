package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.lib.LibSql;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 02/07/17
 * .
 */
@SpringComponent
@Qualifier("log")
public class LogJDBCRepository extends AlgosJDBCRepository {


    /**
     * Costruttore indispensabile per compatibilita con la superclasse
     */
    public LogJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore (Autowired nella superclasse)


    @Override
    protected void regolaParametri() {
        super.tableName = "log";

        super.createQuery = "CREATE TABLE log (" +
                " id INT NOT NULL AUTO_INCREMENT," +
                " company_id int NULL," +
                " livello TEXT NULL," +
                " titolo TEXT NULL," +
                " descrizione TEXT NULL," +
                " modifica DATE NULL," +
                " PRIMARY KEY (id))";

        super.rowMapper = new BeanPropertyRowMapper(Log.class);
    }// end of method


    /**
     * Modifica di una entity esistente
     * parameter values from bean properties of a given JavaBean object
     *
     * @param entity da registrare/modificare
     *
     * @return entity DOPO la registrazione (the save operation might have changed the entity instance completely)
     */
    public AlgosEntity update(AlgosEntity entity) {
        AlgosEntity entityUpdated = entity;
        int ritorno;
        long key = entity.getId();
        String query = LibSql.getQueryUpdate(entity);
        LinkedHashMap<String, Object> mappa = LibReflection.getBeanMap(entity);
        List lista = new ArrayList();

        Object value;

        if (mappa != null && mappa.size() > 0) {
            for (String property : mappa.keySet()) {
                value = mappa.get(property);
                if (value != null) {
                    if (property.equals("livello")) {
                        value = value.toString();
                    }// end of if cycle
                    lista.add(value);
                }// end of if cycle
            }// end of for cycle
        }// end of if cycle

        Object[] values = lista.toArray();

        ritorno = jdbcTemplate.update(query, values);

        if (ritorno == 1) {
            entityUpdated = findOne(key);
        }// end of if cycle

        return entityUpdated;
    }// end of method


}// end of class