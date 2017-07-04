package it.algos.springvaadin.entity.company;

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
@Qualifier("company")
public class CompanyJDBCRepository extends AlgosJDBCRepository {


    public CompanyJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore Autowired

    @Override
    protected void regolaParametri() {
        super.tableName = "company";

        super.createQuery  = "CREATE TABLE company (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  sigla TEXT NULL," +
                "  descrizione TEXT NULL," +
                "  email TEXT NULL," +
                "  indirizzo TEXT NULL," +
                "  contatto TEXT NULL," +
                "  telefono TEXT NULL," +
                "  cellulare TEXT NULL," +
                "  note TEXT NULL," +
                "  partenza DATE NULL," +
                "  PRIMARY KEY (`id`))";

        super.rowMapper = new BeanPropertyRowMapper(Company.class);
    }// end of method


}// end of class