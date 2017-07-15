package it.algos.springvaadin.service;

import it.algos.springvaadin.lib.LibArray;
import it.algos.springvaadin.model.AlgosEntity;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.repository.AlgosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gac on 14/06/17
 * .
 */
@Service
public abstract class AlgosServiceOld {


    protected AlgosRepository repository;


    @Autowired
    @Lazy
    protected JdbcTemplate jdbcTemplate;


    //--tavola di riferimento
    //--regolata nella sottoclasse concreta
    protected String tableName;


    //--mappatura del modello dati
    //--regolata nella sottoclasse concreta
    protected RowMapper rowMapper;


    //--classe del modello dati
    //--regolata nella sottoclasse concreta
    protected Class modelClass;


    public AlgosServiceOld(AlgosRepository repository) {
        this.repository = repository;
    }// fine del metodo costruttore Autowired


    /**
     * Indicates a method to be invoked AFTER a bean has been created and dependency injection is complete.
     * Used to perform any initialization work necessary.
     * Performing the initialization in a constructor is not suggested
     * Possono esserci diversi metodi con @PostConstruct ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    public void inizia() {
        //--indispensabile passare dalla sottoclasse, se non altro per il nome della tavola
        regolaParametri();

        //--test di esistenza per invocare il metodo specifico di creazione della tavola
        if (!esisteTable()) {
            repository.creaTable();
        }// end of if cycle

        //--test per popolare (opzionale) la tavola specifica se è vuota
        if (vuota()) {
            creaDatiIniziali();
        }// end of if cycle
    }// end of method


    protected boolean esisteTable() {
        return repository.exists();
    }// end of method


    protected boolean vuota() {
        return count() == 0;
    }// end of method


    protected void regolaParametri() {
    }// end of method


    protected void creaDatiIniziali() {
    }// end of method

    /**
     * Conteggio di tutti i records della tavola
     * Senza filtri
     */
    public int count() {
        int totaleInt = 0;
        Long totaleLong = repository.count();

        if (totaleLong != null && totaleLong > 0) {
            totaleInt = totaleLong.intValue();
        }// end of if cycle

        return totaleInt;
    }// end of method


    /**
     * Recupera il singolo bean
     */
    public AlgosModel findById(long id) {
        return (AlgosModel) repository.findOne(id);
    }// end of method


    /**
     * Returns all instances of the type.
     * <p>
     * Senza filtri
     * Ordinati per ID
     * <p>
     * Methods of this library return Iterable<T>, while the rest of my code expects Collection<T>
     * L'interfaccia standard di JPA prevede un ritorno di tipo Iterable, mentre noi usiamo List
     * Eseguo qui la conversione, che rimane trasparente al resto del programma
     *
     * @return all entity
     */
    public List findAll() {
        List target = new ArrayList();
        Iterable iterable = repository.findAll();

        if (iterable != null) {
            if (iterable instanceof List) {
                target = (List) iterable;
            } else {
                iterable.forEach(target::add);
            }// end of if/else cycle
        }// end of if cycle

        return target;
    }// end of method


    /**
     * Creazione di un nuovo bean
     * Utilizza la mappa della sottoclasse
     */
    public void insert(AlgosModel entityBean) {
        repository.save(entityBean);
    }// end of method


    public void update(AlgosModel entityBean) {
        repository.save(entityBean);
    }// end of method


    /**
     * Cancella il singolo bean
     */
    public boolean delete(AlgosModel entity) {
        boolean cancellato = false;
        int totalePrima;
        int totaleDopo;

        totalePrima = count();
        repository.delete(entity.getId());
        totaleDopo = count();

        if (totaleDopo != totalePrima) {
            cancellato = true;
        }// end of if cycle

        return cancellato;
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
    public List<String> getListColumns() {
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

    public Class<? extends AlgosEntity> getEntityClass() {
        return null;
    }// end of method
}// end of class
