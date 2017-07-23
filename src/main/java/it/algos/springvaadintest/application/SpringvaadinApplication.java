package it.algos.springvaadintest.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
@EnableJpaRepositories("it.algos.springvaadin.entity")
@ComponentScan({"it.algos.springvaadin", "it.algos.springvaadintest"})
@EntityScan("it.algos.springvaadin.entity")
public class SpringvaadinApplication {

    /**
     * Constructor
     *
     * @param args eventuali parametri in ingresso
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringvaadinApplication.class, args);
    }// end of constructor

}// end of main class