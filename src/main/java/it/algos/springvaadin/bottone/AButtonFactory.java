package it.algos.springvaadin.bottone;

import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 15:50
 * <p>
 * Factory interface per la creazione dei bottoni
 * Crea ogni bottone del tpo richiesto e previsto nella Enumeration AButtonType
 * Nella creazione viene iniettato il parametro obbligatorio della 'sorgente' dell'evento generato dal bottone
 * Eventuali altri parametri facoltativi (target, entityBean), possono essere aggiunti. Da altre classi.
 */
public interface AButtonFactory {

    /**
     * Creazione di un bottone
     *
     * @param type   del bottone, secondo la Enumeration AButtonType
     * @param source dell'evento generato dal bottone
     * @param target a cui indirizzare l'evento generato dal bottone
     *
     * @return il bottone creato
     */
    public AButton crea(AButtonType type, ApplicationListener source, ApplicationListener target);

}// end of interface