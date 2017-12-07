package it.algos.springvaadin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 08-ott-2017
 * Time: 11:29
 */
@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
public class IndirizzoServiceTest {

//    @InjectMocks
//    private IndirizzoService service;
//
//    @InjectMocks
//    private StatoService serviceStato;
//
//    @Mock
//    private Indirizzo indirizzo;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        MockitoAnnotations.initMocks(service);
//        service.statoService = serviceStato;
//    }
//
//    /**
//     * Creazione in memoria di una nuova entity che NON viene salvata
//     * Eventuali regolazioni iniziali delle property
//     *
//     * @param indirizzoOld: via, nome e numero - località - cap
//     *
//     * @return la nuova entity appena creata (vuota e non salvata)
//     */
//    @Test
//    public void newEntity() {
//        String indirizzoOld;
//        String ind;
//        String loc;
//        String cap;
//        Stato stato;
//
//        indirizzoOld = "Via la Bionda, 3 - 43036 Fidenza (PR)";
//        ind = "Via la Bionda, 3";
//        loc = "Fidenza";
//        cap = "43036";
//        indirizzo = service.newEntity(indirizzoOld);
//        checkOld(ind,loc,cap);
//
//        indirizzoOld = "Via Gramsci, 1 - 43010 Ponte Taro (PR)";
//        ind = "Via Gramsci, 1";
//        loc = "Ponte Taro";
//        cap = "43010";
//        indirizzo = service.newEntity(indirizzoOld);
//        checkOld(ind,loc,cap);
//
//        indirizzoOld = "Via del Lavoro 15 - 40065 Pianoro (BO)";
//        ind = "Via del Lavoro 15";
//        loc = "Pianoro";
//        cap = "40065";
//        indirizzo = service.newEntity(indirizzoOld);
//        checkOld(ind,loc,cap);
//
//        indirizzoOld = "Via Casteggio, 40- 29122 Piacenza";
//        ind = "Via Casteggio, 40";
//        loc = "Piacenza";
//        cap = "29122";
//        indirizzo = service.newEntity(indirizzoOld);
//        checkOld(ind,loc,cap);
//    }// end of single test
//
//
//    private void checkOld(String ind, String loc, String cap) {
//        assertNotNull(indirizzo);
//        assertEquals(indirizzo.getIndirizzo(), ind);
//        assertEquals(indirizzo.getLocalita(), loc);
//        assertEquals(indirizzo.getCap(), cap);
//        assertEquals(indirizzo.getStato().getNome(), "Italia");
//    }//// end of method

}// end of class
