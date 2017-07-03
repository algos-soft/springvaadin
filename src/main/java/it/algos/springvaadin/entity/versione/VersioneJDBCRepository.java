package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.repository.AlgosJDBCRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gac on 02/07/17
 *
 */
@SpringComponent
@Qualifier("versione")
public class VersioneJDBCRepository extends AlgosJDBCRepositoryImpl {


    /**
     * Costruttore indispensabile per compatibilita con la superclasse
     */
    public VersioneJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore Autowired (nella superclasse)


    /**
     * Regolazione specifica dei parametri generali
     */
    @Override
    protected void regolaParametri() {
        super.tableName = "versione";
    }// end of method


    /**
     * Creazione specifica della tavola
     */
    @Override
    protected void creaTable() {
        String query = "CREATE TABLE versione (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  ordine INT NULL," +
                "  titolo TEXT NULL," +
                "  descrizione TEXT NULL," +
                "  modifica DATE NULL," +
                "  PRIMARY KEY (id))";

        jdbcTemplate.execute(query);
    }// end of method


    /**
     * Creazione specifica dei dati iniziali
     */
    @Override
    protected void creaDatiIniziali() {
    }// end of method

}// end of class
