package it.algos.springvaadin.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.enumeration.EAButtonType;
import it.algos.springvaadin.event.AEvent;
import it.algos.springvaadin.form.IAForm;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.list.IAList;
import it.algos.springvaadin.service.IAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-dic-2017
 * Time: 07:32
 */
@Slf4j
@SpringComponent
@Scope("session")
public abstract class APresenter implements IAPresenter {


    /**
     * Il modello-dati specifico viene regolato dalla sottoclasse nel costruttore
     */
    protected Class<? extends AEntity> entityClass;


    /**
     * Il service (contenente la repository) viene iniettato dal costruttore della sottoclasse concreta
     */
    public IAService service;


    /**
     * La view viene iniettata dal costruttore della sottoclasse concreta
     */
    protected IAList list;


    /**
     * La view viene iniettata dal costruttore della sottoclasse concreta
     */
    protected IAForm form;


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
     * @param form    iniettato da Spring
     */
    public APresenter(IAService service, IAList list, IAForm form) {
        this.service = service;
        this.list = list;
        this.form = form;
    }// end of Spring constructor


    /**
     * Gestione di una Lista visualizzata con una Grid
     * Metodo invocato da:
     * 1) una view quando diventa attiva
     * 2) un Evento (azione) che necessita di aggiornare e ripresentare la Lista;
     * tipo dopo un delete, dopo un nuovo record, dopo la edit di un record
     * <p>
     * Recupera dal service tutti i dati necessari (aggiornati)
     * Recupera dal service le colonne da mostrare nella grid
     * Recupera dal service gli items (records) della collection, da mostrare nella grid
     * Recupera dal service i bottoni comando da mostrare nella toolbar del footer (sotto la Grid)
     * Passa il controllo alla view con i dati necessari
     */
    @Override
    public void setList() {
        List items = null;
        List<Field> columns = null;
        List<EAButtonType> typeButtons = null;

        columns = service.getListFields();
        items = service.findAll();
        typeButtons = service.getListTypeButtons();

        list.start(this, entityClass, columns, items, typeButtons);
    }// end of method


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AEvent event) {
    }// end of method


}// end of class
