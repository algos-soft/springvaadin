package it.algos.springvaadin.bottone;

import it.algos.springvaadin.event.TypeButton;
import it.algos.springvaadin.field.AFType;
import it.algos.springvaadin.field.AField;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 08-set-2017
 * Time: 15:50
 * <p>
 * Factory class per la creazione dei bottoni
 * Crea ogni bottone del tpo richiesto e previsto nella Enumeration TypeButton
 * Nella creazione viene iniettato il parametro obbligatorio della 'sorgente' dell'evento generato dal bottone
 * Eventuali altri parametri facoltativi (target, entityBean), possono essere aggiunti. Da altre classi.
 */
public interface AButtonFactory {

    /**
     * Creazione di un bottone
     *
     * @param type   del bottone, secondo la Enumeration TypeButton
     * @param source dell'evento generato dal bottone
     *
     * @return il bottone creato
     */
    public AButton crea(TypeButton type, ApplicationListener source);

}// end of interface
