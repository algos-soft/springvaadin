package it.algos.springvaadin.entity.versione;


import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gac on 02/07/17
 * <p>
 * Sottoclasse specifica per la tavola Versione
 * Regola alcuni parametri specifici che la superclasse utilizza per i metodi generali
 * Può implementare ulteriori metodi non di uso generalizzato
 * (sconsigliato perché obbliga un casting dell'interfaccia)
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersioneJDBCRepository extends AlgosJDBCRepository {


    /**
     * Costruttore indispensabile per compatibilita con la superclasse
     */
    public VersioneJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }// fine del metodo costruttore (Autowired nella superclasse)


    /**
     * Regolazione specifica dei parametri generali
     */
    @Override
    protected void regolaParametri() {
        super.tableName = "versione";

        super.createQuery = "CREATE TABLE versione (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  ordine INT NULL," +
                "  titolo TEXT NULL," +
                "  descrizione TEXT NULL," +
                "  modifica DATE NULL," +
                "  PRIMARY KEY (id))";

        super.rowMapper = new BeanPropertyRowMapper(Versione.class);
    }// end of method


    /**
     * Controlla l'esistenza di una entity con gli stessi valori delle property indicate, uniche prese insieme
     *
     * @param titolo      (obbligatorio, non unico singolarmente)
     * @param descrizione (obbligatoria, non unica singolarmente)
     */
    public boolean isEsisteByTitoloAndDescrizione(String titolo, String descrizione) {
        String query;
        int numRec = 0;

        titolo= titolo.replaceAll("'","''");
        descrizione= descrizione.replaceAll("'","''");

        query = "SELECT COUNT(*) FROM versione WHERE titolo='" + titolo + "' AND descrizione=" + "'" + descrizione + "'";

        try { // prova ad eseguire il codice
            numRec = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return numRec > 0;
    }// end of method

}// end of class
