package it.algos.springvaadin.entity.log;

import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * Created by gac on 30/06/17
 */
@Lazy
@Service
@Qualifier(Cost.TAG_LOG)
@Scope(value = "singleton")
public class LogService extends AlgosService {

    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Log logModel;


    @Autowired
    public LogService(@Qualifier("log") AlgosRepository repository) {
        super(repository);
    }// fine del metodo costruttore Autowired

    @Override
    protected void regolaParametri() {
        super.tableName = "log";

        //--casting per gestire la property generica
        super.modelClass = Log.class;
    }// end of method




    @Override
    protected void creaDatiIniziali() {
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
