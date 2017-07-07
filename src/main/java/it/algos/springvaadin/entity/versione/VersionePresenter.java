package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.form.AlgosForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.AlgosList;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.repository.AlgosJDBCRepository;
import it.algos.springvaadin.service.AlgosService;
import it.algos.springvaadin.view.AlgosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 01/06/17.
 * Classe che gestisce la business logic del modulo
 * Parla con la View
 * Parla col Service (che ha sua volta può usare una Repository)
 * Scope può essere
 * <p>
 * – We have some type of scope:
 * + singleton: only one instance is created (default scope)
 * + prototype: new instance is created everytime prototype bean is referenced.
 * + request: one instance for a single HTTP request.
 * + session: one instance for an HTTP Session
 * + globalSession: one instance for a global HTTP Session. Typically only valid when used in a Portlet context.
 * + application: Scopes a single bean definition to the lifecycle of a ServletContext (Only valid in the context of a web-aware Spring ApplicationContext).
 */
@SpringComponent
@Qualifier(Cost.TAG_VERS)
public class VersionePresenter extends AlgosPresenter {


    //--il modello dati (bean, entity) viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private Versione versioneModel;


    //--il service (dao, repository) viene iniettato in questa classe
    //--viene iniettato qui per avere la classe specifica. Nella superclasse viene gestito con la property generica.
    @Autowired
    private VersioneService versioneService;

    @Autowired
    private VersioneView versioneView;

//    @Autowired
//    @Qualifier(Cost.TAG_VERS)
//    private VersioneView versioneView;

    public VersionePresenter(){
    }// fine del metodo costruttore (Autowired nella superclasse)

    /**
     * Metodo invocato subito DOPO il costruttore
     * <p>
     * Performing the initialization in a constructor is not suggested
     * as the state of the UI is not properly set up when the constructor is invoked.
     * <p>
     * Ci possono essere diversi metodi con @PostConstruct e firme diverse e funzionano tutti,
     * ma l'ordine con cui vengono chiamati NON è garantito
     */
    @PostConstruct
    @Override
    protected void inizia() {
        super.inizia();

        //--casting per gestire la property generica
        model = versioneModel;

        //--casting per gestire la property generica
        service = versioneService;

        //--casting per gestire la property generica
        view = versioneView;
    }// end of method


//    /**
//     * Metodo invocato dalla view ogni volta che questa diventa attiva
//     * La partenza standard è quella di mostrare una lista
//     * Recupera i dati dal DB
//     * Passa i dati alla view
//     */
//    public void enter(AlgosView view) {
//        super.enter(view);
//    }// end of method


}// end of class
