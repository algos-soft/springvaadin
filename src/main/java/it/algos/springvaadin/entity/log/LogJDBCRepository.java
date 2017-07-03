package it.algos.springvaadin.entity.log;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.repository.AlgosJDBCRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gac on 02/07/17
 * .
 */
@SpringComponent
@Qualifier("log")
public class LogJDBCRepository extends AlgosJDBCRepositoryImpl {


    public LogJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore Autowired

    @Override
    protected void regolaParametri() {
        super.tableName = "log";
    }// end of method


}// end of class