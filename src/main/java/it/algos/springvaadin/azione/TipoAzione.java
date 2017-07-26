package it.algos.springvaadin.azione;

/**
 * Created by gac on 03/06/17.
 * <p>
 * Azioni generate dalla Grid
 * Azioni generate dai Fields di un Form
 * <p>
 * Enumeration utilizzata per 'marcare' una azione, in fase di generazione
 * Enumeration utilizzata per 'riconoscerla' nel metodo onApplicationEvent()
 */
public enum TipoAzione {

    attach,
    click,
    doppioClick,
    singleSelectionChanged,
    multiSelectionChanged,
    valueChange,
    listener;

}// end of enumeration
