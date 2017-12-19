package it.algos.springvaadin;

import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 17-dic-2017
 * Time: 08:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class RoleServiceTest {


//    @InjectMocks
//    RoleRepository repository;
//
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(repository);
//        repository.deleteAll();
//    }// end of method
//
//
    @Test
    public void getSpringView() {
        int a=87;
        assertNotNull(a);
    }// end of single test

}// end of test class
