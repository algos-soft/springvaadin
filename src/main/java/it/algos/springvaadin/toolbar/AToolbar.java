package it.algos.springvaadin.toolbar;

import it.algos.springvaadin.bottone.AButton;
import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.model.AEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 22:46
 * <p>
 * Interfaccia per le barre di comando con bottoni
 * Le toolbar costruiscono i bottoni ("prototype") usando la factory AButtonFactory
 * Nel ciclo restart() di Form e List, viene poi iniettato il parametro obbligatorio (source)
 * Ulteriori parametri (target, entityBean), vengono iniettati direttamente solo in alcuni bottoni
 * Eventuali bottoni aggiuntivi, oltre quelli standard, possono essere aggiunti in AListImpl.toolbarInizializza()
 * Tutti i bottoni possono essere abilitati/disabilitati
 */
public interface AToolbar {


    /**
     * Metodo invocato da restart() di Form e List
     * Crea i bottoni (iniettandogli il publisher)
     * Aggiunge i bottoni al contenitore grafico
     * Inietta nei bottoni il parametro obbligatorio (source)
     *
     * @param source dell'evento generato dal bottone
     */
    public void inizializza(ApplicationListener source);


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
     * Crea il bottone nella factory AButtonFactory (iniettandogli il publisher)
     * Inietta nei bottoni il parametro obbligatorio (source)
     * Aggiunge il bottone al contenitore grafico
     */
    public AButton creaAddButton(TypeButton type, ApplicationListener source);


    public void enableNew(boolean status);

    public void enableEdit(boolean status);

    public void enableDelete(boolean status);

    public void enableRevert(boolean status);

    public void enableRegistra(boolean status);

    public void enableAccetta(boolean status);

    public void enableAnnulla(boolean status);

    public void enableSearch(boolean status);

}// end of interface
