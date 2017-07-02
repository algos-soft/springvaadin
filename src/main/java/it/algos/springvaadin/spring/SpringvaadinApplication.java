package it.algos.springvaadin.spring;

import com.vaadin.ui.Label;
import it.algos.springvaadin.app.AlgosApp;
import it.algos.springvaadin.controller.AppErrorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * Created by gac on 30/05/17.
 * <p>
 * Questa classe contiene il metodo 'main' che è il punto di ingresso dell'applicazione Java
 * In fase di sviluppo si possono avere diverse configurazioni, ognuna delle quali punta un ''main' diverso
 * Nel JAR finale (runtime) si può avere una sola classe col metodo 'main'.
 * Nel WAR finale (runtime) occorre (credo) inserire dei servlet di context diversi
 * Senza @ComponentScan, SpringBoot non 'vede' le classi con @SpringView
 * che sono in una directory diversa da questo package
 * <p>
 * Questa classe non fa praticamente niente se non avere le Annotation riportate qui
 */
@SpringBootApplication
@EnableJpaRepositories("it.algos.springvaadin.entities.versione.*")
@ComponentScan("it.algos.springvaadin.*")
@EntityScan("it.algos.springvaadin.entities.versione.*")
public class SpringvaadinApplication {

    /**
     * Constructor
     *
     * @param args eventuali parametri in ingresso
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringvaadinApplication.class, args);
    }// end of constructor

    /**
     * Sovrascrive alcune variabili statiche della classe generale,
     * per modificarne il comportamento solo in questa applicazione
     */
    @PostConstruct
    protected void inizia() {
        AlgosApp.USE_MULTI_COMPANY = true;
    }// end of method


//	@Autowired
//	private ErrorAttributes errorAttributes;
//
//	@Bean
//	public AppErrorController appErrorController(){return new AppErrorController(errorAttributes);}

}// end of main class
