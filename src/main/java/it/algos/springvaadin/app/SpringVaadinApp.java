package it.algos.springvaadin.app;

/**
 * Contenitore di costanti della applicazione
 * Regola alcuni flag di comportamento del framwork di base
 */
public class SpringVaadinApp extends AlgosApp {

    /**
     * Static initialisation block
     *
     * Sovrascrive alcune variabili statiche della classe generale,
     * per modificarne il comportamento solo in questa applicazione
     */
    static {
        USE_SECURITY = true;
        USE_LOG = false; //usa una propria sottoclasse del modulo
        USE_PREF = false; //lo uso, ma lo creo in VaadbioUI per averlo nell'ordine voluto
        USE_VERS = false; //lo uso, ma lo creo in VaadbioUI per averlo nell'ordine voluto
    }// end of static method

}// end of  static class
