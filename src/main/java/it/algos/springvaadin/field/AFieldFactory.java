package it.algos.springvaadin.field;

import it.algos.springvaadin.entity.AEntity;
import org.springframework.context.ApplicationListener;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 30-ago-2017
 * Time: 11:14
 * <p>
 * Factory interface per la nuovo dei fields di un Form
 * Crea ogni field del tpo richiesto e previsto nella Enumeration AFieldType
 * Nella nuovo viene iniettato il parametro obbligatorio publicFieldName che serve sia per UI,
 * sia per la gestione interna dei fields
 * Nella nuovo viene iniettato il parametro obbligatorio del presenter che gestisce il field,
 * secondo lo schema: Presenter -> View -> Form -> Field
 * Eventuali altri parametri facoltativi, possono essere aggiunti.
 */
public interface AFieldFactory {

    /**
     * Creazione di un field
     *
     * @param type            del field, secondo la Enumeration AFieldType
     * @param publicFieldName nome visibile del field
     * @param source          del presenter che gestisce questo field
     *
     * @return il field creato
     */
    public AField crea(final Class<? extends AEntity> clazz,AFieldType type, ApplicationListener source, String publicFieldName,AEntity entityBean);


}// end of interface
