package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibSql;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

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


//    protected LinkedHashMap<String, Object> getBeanMap(AlgosModel entityBean) {
//        LinkedHashMap<String, Object> map = new LinkedHashMap();
//        Versione vers = null;
//
//        if (entityBean != null) {//record esistente
//            vers = (Versione) entityBean;
//            map.put("ordine", vers.getOrdine() != 0 ? vers.getOrdine() : 27);//@todo errore
//            map.put("titolo", vers.getTitolo());
//            map.put("descrizione", vers.getDescrizione());
//            map.put("modifica", vers.getModifica() != null ? vers.getModifica() : LocalDateTime.now());
//        } else {//nuovo record
////            vers = versioneModel;
//            map.put("ordine", 28);//@todo errore
//            map.put("titolo", "");
//            map.put("descrizione", "");
//            map.put("modifica", LocalDateTime.now());
//        }// end of if/else cycle
//
//        return map;
//    }// end of method


    /**
     * Retrieves an entity by its titolo and descrizione.
     *
     * @param id must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal null} if none found
     *
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public AlgosEntity findOne(Serializable id) {
        String query = LibSql.getQueryFindOne(tableName);
        return (AlgosEntity) jdbcTemplate.queryForObject(query, new Object[]{id}, rowMapper);
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
