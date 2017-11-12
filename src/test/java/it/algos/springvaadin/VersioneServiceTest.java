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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 12-ott-2017
 * Time: 22:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MocksApplication.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VersioneServiceTest {

    private static String TITOLO = "pippoz";
    private Versione vers1;
    private Versione vers2;

    @TestConfiguration
    static class VersioneServiceTestContextConfiguration {

//        @Bean
//        public VersioneService versioneService() {
//            return new VersioneService(null, null);
//        }// end of method


        @Bean
        @Primary
        public VersioneRepository versioneRepository() {
            return Mockito.mock(VersioneRepository.class);
        }// end of method
    }// end of class

    @MockBean
    private VersioneService service;

    @MockBean
    private VersioneRepository repository;


////    @Before
//    public void setUp() {
//        service.repository = repository;
////        versione = new Versione();
////        versione.setTitolo(TITOLO);
//        Mockito.when(repository.findByOrdine(24)).thenReturn(vers1);
//        Object al = vers1;
//        int a = 87;
//    }// end of method

//    @Test
//    public void primo() {
//        service.repository = repository;
//        Object a= service;
//        vers1 = new Versione("", 3,"alfa", "testino", LocalDate.now());
//        vers2 = new Versione("", 4,"titoli", "prova",LocalDate.now());
//        Mockito.when(service.findAll()).thenReturn(Arrays.asList(vers1));
//        Mockito.when(service.findByOrdine(3)).thenReturn(vers1);
////        versione = service.findOrCrea(24, "alfa", "testino");
//        int aaa=87;
//        List<Versione> allVersioni = service.findAll();
//        int aawwwa=87;
//
////        String titolo = TITOLO;
////        Versione found = service.findByTitolo(titolo);
////
////        assertEquals(found.getTitolo(), TITOLO);
//    }// end of method


}// end of test class
