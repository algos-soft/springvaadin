package it.algos.springvaadin.entity.versione;


import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

//@Lazy
@Service
@Scope(value = "singleton")
public class VersioneService extends AlgosService {


    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Versione versioneModel;

//    @Autowired
//    private VersioneRepository versioneRepository;

    @Override
    protected void regolaParametri() {
        super.tableName = "versione";

        //--casting per gestire la property generica
        super.modelClass = Versione.class;

    }// end of method

    @Override
    public void creaTable() {
        String query = "CREATE TABLE versione (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  ordine INT NULL," +
                "  titolo TEXT NULL," +
                "  descrizione TEXT NULL," +
                "  modifica DATE NULL," +
                "  PRIMARY KEY (id))";

        jdbcTemplate.execute(query);
    }// end of method


    @Override
    protected void creaDatiIniziali() {
        SpringVaadinData.creaVersione(this);
    }// end of method


    public void crea(String titolo, String descrizione) {
        crea(titolo, descrizione, 0, (LocalDateTime) null);
    }// end of method

    public void crea(String titolo, String descrizione, int ordine) {
        crea(titolo, descrizione, ordine, (LocalDateTime) null);
    }// end of method

    public void crea(String titolo, String descrizione, LocalDateTime modifica) {
        crea(titolo, descrizione, 0, modifica);
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico prima del persist
     */
    public void crea(String titolo, String descrizione, int ordine, LocalDateTime modifica) {
        Versione vers = new Versione(this, ordine, titolo, descrizione, modifica);
        super.insert(vers);
    }// end of method


    protected LinkedHashMap<String, Object> getBeanMap(AlgosModel entityBean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap();
        Versione vers = null;

        if (entityBean != null) {//record esistente
            vers = (Versione) entityBean;
            map.put("ordine", vers.getOrdine() != 0 ? vers.getOrdine() : getOrdine());
            map.put("titolo", vers.getTitolo());
            map.put("descrizione", vers.getDescrizione());
            map.put("modifica", vers.getModifica() != null ? vers.getModifica() : LocalDateTime.now());
        } else {//nuovo record
            vers = versioneModel;
            map.put("ordine", getOrdine());
            map.put("titolo", "");
            map.put("descrizione", "");
            map.put("modifica", LocalDateTime.now());
        }// end of if/else cycle

        return map;
    }// end of method


    /**
     * Ripristina i valori base; vuoti o coi valori standard iniziali
     */
    public Versione reset() {
        if (versioneModel != null) {
            versioneModel.setOrdine(getOrdine());
            versioneModel.setTitolo("");
            versioneModel.setDescrizione("");
            versioneModel.setModifica(LocalDateTime.now());
        }// end of if cycle

        return versioneModel;
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * Recupera il valore massimo della property
     * Incrementa di uno il risultato
     */
    public int getOrdine() {
        return super.getMax("ordine") + 1;
    }// end of method

}// end of class
