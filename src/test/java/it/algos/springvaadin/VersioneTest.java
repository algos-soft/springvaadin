package it.algos.springvaadin;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.entity.versione.VersioneService;
import it.algos.springvaadin.repository.SpringMongoConfiguration;
import it.algos.springvaadintest.application.SpringvaadinApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 22-ott-2017
 * Time: 09:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringvaadinApplication.class)
public class VersioneTest {


    @Autowired
     VersioneService service;

    @Autowired
     VersioneRepository repository;

    @Test
//    @Transactional
    public void primo() {
        service.repository = repository;

        Versione vers1 = new Versione("",3, "alfa", "testino", LocalDate.now());
        Versione  vers2 = service.newEntity( "",4,"titoli", "prova",LocalDate.now());

        // Execute the method being tested
        try { // prova ad eseguire il codice
            service.save(vers1);
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        // Validation
        assertNotNull(vers1.getId());
    }// end of method


}// end of test class
