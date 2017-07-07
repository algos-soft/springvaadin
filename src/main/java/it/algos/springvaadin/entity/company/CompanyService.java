package it.algos.springvaadin.entity.company;

import it.algos.springvaadin.bootstrap.SpringVaadinData;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import it.algos.springvaadin.repository.AlgosRepository;
import it.algos.springvaadin.service.AlgosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by gac on 25/06/17
 */
@Lazy
@Service
@Qualifier(Cost.TAG_COMP)
@Scope(value = "singleton")
public class CompanyService extends AlgosService {


    //--il modello dati viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    @Lazy
    private Company companyModel;


    @Autowired
    public CompanyService(@Qualifier("company") AlgosRepository repository) {
        super(repository);
    }// fine del metodo costruttore Autowired


    @Override
    protected void regolaParametri() {
        super.tableName = "company";

        //--casting per gestire la property generica
        super.modelClass = Company.class;
    }// end of method




    @Override
    protected void creaDatiIniziali() {
        SpringVaadinData.creaCompany(this);
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
