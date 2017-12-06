package it.algos.springvaadin.entity.role;

import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.lib.LibAnnotation;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.list.AlgosListNew;
import it.algos.springvaadin.search.AlgosSearch;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 06-dic-2017
 * Time: 18:10
 */
@Slf4j
public class AlgosPresenter implements IAlgosPresenter {

    /**
     * Il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
     */
    protected Class<? extends AEntity> entityClass;

    /**
     * Il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
     */
    public AlgosService service;

    protected AlgosListNew list;


    /**
     * Costruttore @Autowired (nella sottoclasse concreta)
     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation.
     * L' @Autowired (esplicito o implicito) funziona SOLO per UN costruttore
     * Se ci sono DUE o più costruttori, va in errore
     * Se ci sono DUE costruttori, di cui uno senza parametri, inietta quello senza parametri
     * La sottoclasse usa un @Qualifier(), per avere la sottoclasse specifica
     * La sottoclasse usa una costante statica, per essere sicuri di scrivere sempre uguali i riferimenti
     *
     * @param service iniettato da Spring
     * @param list    iniettato da Spring
     */
    public AlgosPresenter(AlgosService service, AlgosListNew list) {
        this.list = list;
        this.service = service;
    }// end of Spring constructor


    /**
     * Creazione di una Lista visualizzata con una Grid
     * Metodo invocato dalla view ogni volta che questa diventa attiva
     * oppure
     * metodo invocato da un Evento (azione) che necessita di aggiornare e ripresentare la Lista
     * tipo dopo un delete, dopo un nuovo record, dopo la edit di un record
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Recupera dal service le colonne da mostrare nella grid
     * Recupera dal service gli items (records) della collection, da mostrare nella grid
     * Passa il controllo alla view con i dati necessari
     */
    @Override
    public void setList() {
        List items = null;
        List<Field> columns = null;

//        if (service != null) {
//            columns = service.getListFields();
//            if (LibAnnotation.useCompany(entityClass)) {
//                items = service.findAllByCompany();
//            } else {
//                items = service.findAll();
//            }// end of if/else cycle
//        }// end of if cycle

        columns = service.getListFields();
        items = service.findAll();

        list.start(this, entityClass, columns, items);
    }// end of method


}// end of class
