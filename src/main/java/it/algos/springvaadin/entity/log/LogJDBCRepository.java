package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gac on 02/07/17
 * .
 */
@SpringComponent
@Qualifier("log")
public class LogJDBCRepository extends AlgosJDBCRepository {


    public LogJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore Autowired

    @Override
    protected void regolaParametri() {
        super.tableName = "log";

        super.createQuery  = "CREATE TABLE log (" +
                " id INT NOT NULL AUTO_INCREMENT," +
                " company_id int NULL," +
                " livello TEXT NULL," +
                " titolo TEXT NULL," +
                " descrizione TEXT NULL," +
                " modifica DATE NULL," +
                " PRIMARY KEY (id))";

        super.rowMapper = new BeanPropertyRowMapper(Log.class);
    }// end of method


}// end of class