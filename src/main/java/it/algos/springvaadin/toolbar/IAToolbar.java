package it.algos.springvaadin.toolbar;

import com.vaadin.ui.Component;
import it.algos.springvaadin.entity.AEntity;
import it.algos.springvaadin.field.AField;
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
public interface IAToolbar {


//    /**
//     * Metodo invocato da restart() di Form e List
//     * Seleziona i bottoni da mostrare nella toolbar
//     * Crea i bottoni (iniettandogli il publisher)
//     * Aggiunge i bottoni al contenitore grafico
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     *
//     * @param source       dell'evento generato dai bottoni
//     * @param typeButtons da visualizzare
//     */
//    public void inizializza(ApplicationListener source, List<AButtonType> typeButtons);
//
//
//    /**
//     * Metodo invocato da restart() di Form, nella classe LinkToolbar
//     * Crea i bottoni (iniettandogli il publisher)
//     * Aggiunge i bottoni al contenitore grafico
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     *
//     * @param source      dell'evento generato dal bottone
//     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
//     */
//    public void inizializza(ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField);
//
//
//    /**
//     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     * Aggiunge il bottone alla prima riga (default) del contenitore grafico
//     *
//     * @param type   del bottone, secondo la Enumeration AButtonType
//     * @param source dell'evento generato dal bottone
//     */
//    public AButton creaAddButton(AButtonType type, ApplicationListener source);
//
//
//    /**
//     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     * Aggiunge il bottone alla prima riga (default) del contenitore grafico
//     *
//     * @param type        del bottone, secondo la Enumeration AButtonType
//     * @param source      dell'evento generato dal bottone
//     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
//     */
//    public AButton creaAddButton(AButtonType type, ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField);
//
//
//    /**
//     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     * Aggiunge il bottone alla seconda riga (opzionale) del contenitore grafico
//     *
//     * @param type   del bottone, secondo la Enumeration AButtonType
//     * @param source dell'evento generato dal bottone
//     */
//    public AButton creaAddButtonSecondaRiga(AButtonType type, ApplicationListener source);
//
//
//    /**
//     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
//     * Inietta nei bottoni il parametro obbligatorio (source)
//     * Aggiunge il bottone alla seconda riga (opzionale) del contenitore grafico
//     *
//     * @param type        del bottone, secondo la Enumeration AButtonType
//     * @param source      dell'evento generato dal bottone
//     * @param sourceField di un altro modulo che ha richiesto, tramite bottone, la visualizzazione del form
//     */
//    public AButton creaAddButtonSecondaRiga(AButtonType type, ApplicationListener source, ApplicationListener target, AEntity entityBean, AField sourceField);
//
//
//    /**
//     * Inietta nel bottone il parametro
//     * Il bottone è già stato inizializzato coi parametri standard
//     *
//     * @param entityBean in elaborazione
//     */
//    public void inizializzaEdit(AEntity entityBean);
//
//
//    /**
//     * Inietta nel bottone i parametri
//     * Il bottone è già stato inizializzato coi parametri standard
//     *
//     * @param entityBean in elaborazione
//     * @param target     (window, dialog, presenter) a cui indirizzare l'evento
//     */
//    public void inizializzaEditLink(AEntity entityBean, ApplicationListener target);
//
//
//    /**
//     * Abilita o disabilita lo specifico bottone
//     *
//     * @param type   del bottone, secondo la Enumeration AButtonType
//     * @param status abilitare o disabilitare
//     */
//    public void enableButton(AButtonType type, boolean status);
//
//
//    /**
//     * Aggiunge un componente (di solito un Button) alla prima riga (default) del contenitore grafico
//     *
//     * @param component da aggiungere
//     */
//    public void addComp(Component component);
//
//
//    /**
//     * Aggiunge un componente (di solito un Button) alla prima riga (default) del contenitore grafico
//     *
//     * @param component da aggiungere
//     */
//    public void addCompSecondaRiga(Component component);
//
//
//    /**
//     * Restituisce questa toolbar
//     *
//     * @return il componente concreto di questa interfaccia
//     */
//    public AToolbarImpl get();


}// end of interface
