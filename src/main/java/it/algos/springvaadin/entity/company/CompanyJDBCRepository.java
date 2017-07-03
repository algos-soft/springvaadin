package it.algos.springvaadin.entity.company;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.repository.AlgosJDBCRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gac on 02/07/17
 * .
 */
@SpringComponent
@Qualifier("company")
public class CompanyJDBCRepository extends AlgosJDBCRepositoryImpl {


    public CompanyJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore Autowired

    @Override
    protected void regolaParametri() {
        super.tableName = "company";
    }// end of method


}// end of class