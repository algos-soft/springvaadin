package it.algos.springvaadin;

import it.algos.springvaadin.entity.versione.Versione;
import it.algos.springvaadin.entity.versione.VersioneRepository;
import it.algos.springvaadin.entity.versione.VersioneService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 12-ott-2017
 * Time: 22:40
 */
@RunWith(SpringRunner.class)
public class VersioneServiceTest {

    private static String TITOLO = "pippoz";

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public VersioneService versioneService() {
            return new VersioneService(null);
        }// end of method
    }// end of class

    @Autowired
    private VersioneService service;

    @MockBean
    private VersioneRepository repository;


    @Before
    public void setUp() {
//        Versione versione = new Versione();
//        versione.setTitolo(TITOLO);
//        Mockito.when(repository.findByTitolo(versione.getTitolo())).thenReturn(versione);
//        service.repository=repository;
    }// end of method

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
//        String titolo = TITOLO;
//        Versione found = service.findByTitolo(titolo);
//
//        assertEquals(found.getTitolo(), TITOLO);
    }// end of method


}// end of test class
