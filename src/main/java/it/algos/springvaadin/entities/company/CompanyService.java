package it.algos.springvaadin.entities.company;

import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.entities.versione.Versione;
import it.algos.springvaadin.lib.DateConvertUtils;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 25/06/17
 */
@Lazy
@Service
@Scope(value = "singleton")
public class CompanyService extends AlgosService {


    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Company companyModel;


    @PostConstruct
    public void inizia() {
        super.tableName = "company";

        //--casting per gestire la property generica
        super.modelClass = Company.class;

        if (nonEsiste()) {
            creaTable();
        }// end of if cycle
        if (vuota()) {
            SpringVaadinData.creaCompany(this);
        }// end of if cycle
    }// end of method


    public void creaTable() {
        String query = "CREATE TABLE `algostest`.`company` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `sigla` TEXT NULL," +
                "  `descrizione` TEXT NULL," +
                "  `email` TEXT NULL," +
                "  `indirizzo` TEXT NULL," +
                "  `contatto` TEXT NULL," +
                "  `telefono` TEXT NULL," +
                "  `cellulare` TEXT NULL," +
                "  `note` TEXT NULL," +
                "  `partenza` DATE NULL," +
                "  PRIMARY KEY (`id`))";

        jdbcTemplate.execute(query);
    }// end of method


    public void crea(String sigla, String descrizione) {
        crea(sigla, descrizione, "", "", "");
    }// end of method


    public void crea(String sigla, String descrizione, String email, String indirizzo, String contatto) {
        crea(sigla, descrizione, email, indirizzo, contatto, "", "", "", (LocalDate) null);
    }// end of method


    /**
     * L'ordine di presentazione (obbligatorio, unico), viene calcolato in automatico prima del persist
     * La data di modifica (obbligatoria, non unica), viene inserita in automatico prima del persist
     */
    public void crea(String sigla, String descrizione, String email, String indirizzo, String contatto, String telefono, String cellulare, String note, LocalDate partenza) {
        Company comp = new Company(this, sigla, descrizione, email, indirizzo, contatto, telefono, cellulare, note, partenza);
        super.insert(comp);
    }// end of method


    protected LinkedHashMap getBeanMap(AlgosModel entityBean) {
        Company comp = null;
        if (entityBean != null) {
            comp = (Company) entityBean;
        } else {
            comp = companyModel;
        }// end of if/else cycle
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("sigla", comp.getSigla());
        map.put("descrizione", comp.getDescrizione());
        map.put("email", comp.getEmail());
        map.put("indirizzo", comp.getIndirizzo());
        map.put("email", comp.getEmail());
        map.put("contatto", comp.getContatto());
        map.put("telefono", comp.getTelefono());
        map.put("cellulare", comp.getCellulare());
        map.put("note", comp.getNote());
        map.put("partenza", comp.getPartenza() != null ? comp.getPartenza() : LocalDateTime.now());

        return map;
    }// end of method


    /**
     * Ordine standard di presentazione delle colonne nella grid
     * Può essere modificato
     * La colonna ID normalmente non si visualizza
     */
    @Override
    public List<String> getListColumns() {
        String[] array = {"sigla", "descrizione", "indirizzo", "email", "contatto", "telefono"};
        return LibArray.fromString(array);
    }// end of method


//    /**
//     * Ordine standard di presentazione dei fields nel form
//     * Può essere modificato
//     * La colonna ID normalmente non si visualizza
//     */
//    @Override
//    public List<String> getFormFields() {
//        String[] array = {"sigla", "descrizione", "email", "indirizzo", "contatto", "telefono", "cellulare", "note", "partenza"};
//        return LibArray.fromString(array);
//    }// end of method


}// end of class
