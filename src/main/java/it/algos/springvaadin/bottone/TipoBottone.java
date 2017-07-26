package it.algos.springvaadin.bottone;

/**
 * Created by gac on 03/06/17.
 * <p>
 * Bottoni della toolbar di una Grid
 * Bottoni della toolbar di un Form
 * <p>
 * Enumeration utilizza per 'marcare' un evento, in fase di generazione
 * e 'riconoscerlo' nel metodo onListEvent()
 */
public enum TipoBottone {

    create,
    edit,
    delete,
    search,
    showAll,
    annulla,
    accetta,
    back,
    revert,
    registra;

}// end of enumeration
