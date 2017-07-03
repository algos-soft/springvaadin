package it.algos.springvaadin.bootstrap;

import it.algos.springvaadin.entity.company.CompanyService;
import it.algos.springvaadin.entity.log.LogService;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.repository.AlgosRepository;

/**
 * Created by gac on 22/06/17
 * Creazione di alcuni dati base per i test
 */
public class SpringVaadinData {


    /**
     * Si attiva SOLO se la tavola è vuota
     */
    public static void creaVersione(VersioneService service) {
        service.crea("Setup", "Setup iniziale del programma");
        service.crea("Update", "Prima versione");
        service.crea("Update", "Seconda versione");
        service.crea("Fixbug", "Blocco di numeri negativi");
        service.crea("Flag", "Aggiunta di un flag di controllo");
    }// end of method


    /**
     * Si attiva SOLO se la tavola è vuota
     */
    public static void creaCompany(CompanyService service) {
        service.crea("gaps", "Gruppo Accoglienza Pronto Soccorso");
        service.crea("crf", "CRI - Comitato Locale di Fidenza", "cl.fidenza@cri.it", "via la Bionda, 3 - 43036 Fidenza (PR)", "Alessandro Aniello");
        service.crea("pap", "Pubblica assistenza Pianoro", "presidente@pubblicapianoro.it", "Via del Lavoro 15 - 40065 Pianoro (BO)", "Silvano Piana");
        service.crea("crpt", "CRI - Comitato Locale di Ponte Taro", "presidente@cripontetaro.it", "Via Gramsci, 1 - 43010 Ponte Taro (PR)", "Mauro Michelini", "339 7894839", "339 7894839", "", null);
    }// end of method


    /**
     * Si attiva SOLO se la tavola è vuota
     */
    public static void creaLog(LogService service) {
    }// end of method

}// end of class
