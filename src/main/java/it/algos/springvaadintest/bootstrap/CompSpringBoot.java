package it.algos.springvaadintest.bootstrap;

import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.entity.company.Company;
import it.algos.springvaadin.entity.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Company demo
 * Executed on container startup
 * Setup non-UI logic here
 * <p>
 * Classe eseguita solo quando l'applicazione viene caricata/parte nel server (Tomcat) <br>
 * Eseguita quindi ad ogni avvio/riavvio del server e NON ad ogni sessione <br>
 */
@SpringComponent
@Slf4j
public class CompSpringBoot {


    //--il service (contenente la repository) viene iniettato qui
    @Autowired
    protected CompanyService service;


//    /**
//     * Costruttore
//     * In the newest Spring release, it’s constructor does not need to be annotated with @Autowired annotation
//     */
//    public CompSpringBoot() {
//    }// end of Spring constructor


    /**
     * Metodo invocato subito DOPO il costruttore (chiamato da Spring)
     * (si può usare qualsiasi firma)
     */
    @PostConstruct
    private void demoCompany() {
        creaAndLog("crf","Croce Rossa Fidenza");
        creaAndLog("pap","Pubblica Assistenza Pianoro");
        creaAndLog("crpt","Croce Rossa Ponte Taro");
        creaAndLog("gaps","Gruppo Accoglienza Pronto Soccorso");
    }// end of method


    /**
     * Creazione di una entity
     * Log a video
     *
     * @param sigla       sigla di riferimento interna (interna, obbligatoria ed unica)
     * @param descrizione ragione sociale o descrizione della company (visibile - obbligatoria)
     */
    private void creaAndLog(String sigla, String descrizione) {
        Company comp = service.crea(sigla, descrizione);
        log.warn("Company: " + comp);
    }// end of method

}// end of class
