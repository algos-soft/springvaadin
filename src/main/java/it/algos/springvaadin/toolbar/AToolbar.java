package it.algos.springvaadin.toolbar;

import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.bottone.AButtonType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.entity.AEntity;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 22:46
 * <p>
 * Interfaccia per le barre di comando con bottoni
 * Nel ciclo restart() di Form e List, le toolbar costruiscono i bottoni ("prototype") usando la factory AButtonFactory
 * Viene poi iniettato il parametro obbligatorio (source)
 * Ulteriori parametri (target, entityBean), vengono iniettati direttamente solo in alcuni bottoni
 * Eventuali bottoni aggiuntivi, oltre quelli standard, possono essere aggiunti sovrascrivendo AListImpl.toolbarInizializza()
 * Tutti i bottoni possono essere abilitati/disabilitati
 */
public interface AToolbar {


    /**
     * Metodo invocato da restart() di Form e List
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     */
    public void inizializza(ApplicationListener source);

    /**
     * Metodo invocato da restart() di Form e List
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    public void inizializza(ApplicationListener source,ApplicationListener target);


    /**
     * Metodo invocato da restart() di Form, nella classe LinkToolbar
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source      dell'evento generato dal bottone
     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     */
    public void inizializza(ApplicationListener source, ApplicationListener target, AEntity entityBean,AField sourceField);


    /**
     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
     * Inietta nei bottoni il parametro obbligatorio (source)
     * Aggiunge il bottone al contenitore grafico
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param source dell'evento generato dal bottone
     */
    public AButton creaAddButton(AButtonType type, ApplicationListener source);


    /**
     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
     * Inietta nei bottoni il parametro obbligatorio (source)
     * Aggiunge il bottone al contenitore grafico
     *
     * @param type        del bottone, secondo la Enumeration AButtonType
     * @param source      dell'evento generato dal bottone
     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
     */
    public AButton creaAddButton(AButtonType type, ApplicationListener source, ApplicationListener target, AEntity entityBean,AField sourceField);


    /**
     * Inietta nel bottone il parametro
     * Il bottone è già stato inizializzato coi parametri standard
     *
     * @param entityBean in elaborazione
     */
    public void inizializzaEdit(AEntity entityBean);


    /**
     * Inietta nel bottone i parametri
     * Il bottone è già stato inizializzato coi parametri standard
     *
     * @param entityBean in elaborazione
     * @param target     (window, dialog, presenter) a cui indirizzare l'evento
     */
    public void inizializzaEditLink(AEntity entityBean, ApplicationListener target);


    /**
     * Abilita o disabilita lo specifico bottone
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param status abilitare o disabilitare
     */
    public void enableButton(AButtonType type, boolean status);

    /**
     * Seleziona i bottoni da mostrare nella toolbar
     *
     */
    public void selezionaBottoni(List<String> listaBottoni);


}// end of interface
