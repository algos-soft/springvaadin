package it.algos.springtemplates.application;

import it.algos.springvaadintest.application.SpringvaadinApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: lun, 05-mar-2018
 * Time: 16:02
 * Questa classe contiene il metodo 'main' che è il punto di ingresso dell'applicazione Java
 * In fase di sviluppo si possono avere diverse configurazioni, ognuna delle quali punta un ''main' diverso
 * Nel JAR finale (runtime) si può avere una sola classe col metodo 'main'.
 * Nel WAR finale (runtime) occorre (credo) inserire dei servlet di context diversi
 * Senza @ComponentScan, SpringBoot non 'vede' le classi con @SpringView
 * che sono in una directory diversa da questo package
 * <p>
 * Questa classe non fa praticamente niente se non avere le Annotation riportate qui
 */
@Slf4j
@SpringBootApplication
@ComponentScan({"it.algos.springtemplates","it.algos.springvaadin"})
@EntityScan({"it.algos.springtemplates","it.algos.springvaadin.entity"})
@EnableMongoRepositories({"it.algos.springvaadin.entity", "it.algos.springvaadintest.entity"})
public class SpringtemplatesApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(it.algos.springtemplates.application.SpringtemplatesApplication.class);
    }// end of method

    /**
     * Constructor
     *
     * @param args eventuali parametri in ingresso
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(it.algos.springtemplates.application.SpringtemplatesApplication.class, args);
    }// end of constructor


}// end of main class
