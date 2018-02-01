package it.algos.springvaadintest.application;

import com.vaadin.server.*;
import com.vaadin.spring.server.SpringVaadinServlet;
import it.algos.springvaadin.app.StaticContextAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.servlet.ServletException;

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
@Slf4j
@SpringBootApplication
@EnableMongoRepositories({"it.algos.springvaadin.entity", "it.algos.springvaadintest.entity"})
@ComponentScan({"it.algos.springvaadin", "it.algos.springvaadintest"})
@EntityScan({"it.algos.springvaadin.entity", "it.algos.springvaadintest.entity"})
public class SpringvaadinApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringvaadinApplication.class);
    }// end of method

    /**
     * Constructor
     *
     * @param args eventuali parametri in ingresso
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringvaadinApplication.class, args);
    }// end of constructor


}// end of main class
