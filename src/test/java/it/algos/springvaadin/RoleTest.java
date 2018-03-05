package it.algos.springvaadin;

import com.vaadin.spring.annotation.SpringView;
import it.algos.springvaadin.app.SpringMongoConfiguration;
import it.algos.springvaadin.entity.role.Role;
import it.algos.springvaadin.entity.role.RoleList;
import it.algos.springvaadin.entity.role.RoleRepository;
import it.algos.springvaadin.enumeration.EAFieldType;
import it.algos.springvaadin.service.AAnnotationService;
import it.algos.springvaadin.service.AArrayService;
import it.algos.springvaadin.service.AReflectionService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 10-dic-2017
 * Time: 08:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMongoConfiguration.class)
public class RoleTest {

    @InjectMocks
    private Role entity;

    @Autowired
    private ApplicationContext applicationContext;



//    @InjectMocks
//    RoleRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        MockitoAnnotations.initMocks(repository);
//        MockitoAnnotations.initMocks(entity);
    }// end of method


    @SuppressWarnings("javadoc")
    @Test
    public void getSpringView()  {
        // specify mock behave when method called
//        when(entity.save(any(Invoice.class))).thenReturn(Long.valueOf(1)); }// end of single test
    }
}// end of class
