package it.algos.springvaadin.entities.log;

import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.entities.company.Company;
import it.algos.springvaadin.entities.versione.Versione;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 30/06/17
 */
@Lazy
@Service
@Scope(value = "singleton")
public class LogService extends AlgosService {

    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Log logModel;


    @PostConstruct
    public void inizia() {
        super.tableName = "log";

        //--casting per gestire la property generica
        super.modelClass = Log.class;

        if (nonEsiste()) {
            creaTable();
        }// end of if cycle
        if (vuota()) {
            SpringVaadinData.creaLog(this);
        }// end of if cycle

    }// end of method


    public void creaTable() {
        String query = "CREATE TABLE algostest.log (" +
                " id INT NOT NULL AUTO_INCREMENT," +
                " company_id int NULL," +
                " livello TEXT NULL," +
                " titolo TEXT NULL," +
                " descrizione TEXT NULL," +
                " modifica DATE NULL," +
                " PRIMARY KEY (id))";

        jdbcTemplate.execute(query);
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico prima del persist
     */
    public void crea(Company company, Livello livello, String titolo, String descrizione, LocalDateTime modifica) {
        Log log = new Log(this, company, livello, titolo, descrizione, modifica);
        super.insert(log);
    }// end of method


    protected LinkedHashMap<String, Object> getBeanMap(AlgosModel entityBean) {
        Log log = null;
        if (entityBean != null) {
            log = (Log) entityBean;
        } else {
            log = logModel;
        }// end of if/else cycle
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("company", log.getCompany());
        map.put("livello", log.getLivello());
        map.put("titolo", log.getTitolo());
        map.put("descrizione", log.getDescrizione());
        map.put("modifica", log.getModifica() != null ? log.getModifica() : LocalDateTime.now());

        return map;
    }// end of method


}// end of class
