package it.algos.springvaadin.service;

import com.sun.deploy.util.StringUtils;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 14/06/17
 * .
 */
@Service
public abstract class AlgosService {


    @Autowired
    @Lazy
    protected JdbcTemplate jdbcTemplate;

    //--tavola di riferimento
    //--regolata nella sottoclasse concreta
    protected String tableName;


    //--mappatura del modello dati
    //--regolata nella sottoclasse concreta
    protected RowMapper rowMapper;


    //--classem del modello dati
    //--regolata nella sottoclasse concreta
    protected Class modelClass;


    protected boolean nonEsiste() {
        boolean nonEsiste = false;

        String query = "SELECT 1 FROM " + tableName + " LIMIT 1;";

        try { // prova ad eseguire il codice
            jdbcTemplate.execute(query);
        } catch (Exception unErrore) { // intercetta l'errore
            nonEsiste = true;
        }// fine del blocco try-catch

        return nonEsiste;
    }// end of method


    protected boolean vuota() {
        return count() == 0;
    }// end of method


    /**
     * Conteggio di tutti i records della tavola
     * Senza filtri
     */
    public int count() {
        String query = "select count(*) from " + tableName;
        return jdbcTemplate.queryForObject(query, Integer.class);
    }// end of method


    /**
     * Recupera il singolo bean
     */
    public AlgosModel findById(long id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        return (AlgosModel) jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper(modelClass));
    }// end of method


    /**
     * Recupera tutti i records della tavola
     * Senza filtri
     * Ordinati per ID
     */
    public List findAll() {
        String query = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(modelClass));
    }// end of method


    /**
     * Cancella il singolo bean
     */
    public void delete(AlgosModel bean) {
        jdbcTemplate.update("DELETE FROM " + tableName + " WHERE id = ?", new Object[]{bean.getId()});
    }// end of method


    /**
     * Creazione di un nuovo bean
     * Utilizza la mappa della sottoclasse
     */
    public int insert(AlgosModel entityBean) {
        LinkedHashMap<String, Object> map = this.getBeanMap(entityBean);
        String campi = StringUtils.join(map.keySet(), ",");
        String valori = LibText.repeat("?", ",", map.size());
        campi = LibText.setTonde(campi);
        valori = " values" + LibText.setTonde(valori);
        String query = "INSERT INTO " + tableName + campi + valori;

        return jdbcTemplate.update(query, map.values().toArray());
    }// end of method


    public void update(AlgosModel entityBean) {
        String query = "UPDATE " + tableName + " SET ";
        String campi = "";
        LinkedHashMap<String, Object> map = this.getBeanMap(entityBean);

        for (String campo : map.keySet()) {
            campi += campo + "=?, ";
        }// end of for cycle
        campi= LibText.levaCoda(campi,",");

        query += campi + " WHERE id=?";
        map.put("id",entityBean.getId());
        jdbcTemplate.update(query, map.values().toArray());
    }// end of method


    /**
     * Recupera il valore massimo della property indicata
     */
    public int getMax(String propertyName) {
        int ordine = 0;
        Map<String, Object> mappa = null;
        Object value;
        String query = "SELECT * FROM " + tableName + " order by " + propertyName + " desc limit 1";
        List<Map<String, Object>> lista = jdbcTemplate.queryForList(query);

        if (lista != null && lista.size() == 1) {
            mappa = lista.get(0);
            if (mappa.containsKey(propertyName)) {
                value = mappa.get(propertyName);
                if (value != null && value instanceof Integer) {
                    ordine = (Integer) value;
                }// end of if cycle
            }// end of if cycle
        }// end of if cycle

        return ordine;
    }// end of method


    protected LinkedHashMap getBeanMap(AlgosModel entityBean) {
        return null;
    }// end of method


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    //        String[] array = {"ordine", "titolo", "descrizione", "modifica"};//@todo esempio per la sottoclasse
    //        return LibArray.fromString(array);
    public  List<String> getListColumns() {
        return LibArray.getKeyFromMap(getBeanMap(null));
    }// end of method


    /**
     * Ordine standard di presentazione dei fields nel form
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    public List<String> getFormFields() {
        return LibArray.getKeyFromMap(getBeanMap(null));
    }// end of method


    /**
     * Conteggio di tutti i records della tavola
     * Senza filtri
     */
    @Deprecated
    public int countOld() {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
        jdbcTemplate.query("select * from " + tableName, countCallback);
        return countCallback.getRowCount();
    }// end of method

    /**
     * Recupera il singolo bean
     */
    @Deprecated
    public AlgosModel findByIdOld(long id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        return (AlgosModel) jdbcTemplate.queryForObject(query, new Object[]{id}, rowMapper);
    }// end of method


}// end of class
