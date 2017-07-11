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


    /**
     * Controlla l'esistenza di una entity con gli stessi valori delle property indicate, uniche prese insieme
     *
     * @param sigla sigla di riferimento interna (interna, obbligatoria ed unica)
     */
    public boolean isEsisteBySigla(String sigla) {
        String query;
        int numRec = 0;

        query = "SELECT COUNT(*) FROM versione WHERE titolo='" + sigla + "'";

        try { // prova ad eseguire il codice
            numRec = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return numRec > 0;
    }// end of method

}// end of class