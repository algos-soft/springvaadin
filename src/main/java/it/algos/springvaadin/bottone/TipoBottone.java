package it.algos.springvaadin.bottone;

/**
 * Created by gac on 03/06/17.
 * <p>
 * Bottoni della toolbar di una Grid
 * Bottoni della toolbar di un Form
 * <p>
 * Enumeration utilizzata per 'marcare' un evento, in fase di generazione
 * Enumeration utilizzata per 'riconoscerlo' nel metodo onApplicationEvent()
 */
public enum TipoBottone {

    create,
    edit,
    editLink,
    delete,
    search,
    showAll,
    annulla,
    accetta,
    back,
    revert,
    registra;

}// end of enumeration
