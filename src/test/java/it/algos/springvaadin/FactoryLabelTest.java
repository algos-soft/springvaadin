package it.algos.springvaadin;

import it.algos.springvaadin.app.FactoryLabel;
import it.algos.springvaadin.app.FactoryLabelConfig;
import it.algos.springvaadin.app.Tool;
import it.algos.springvaadin.service.AHtmlService;
import it.algos.springvaadin.service.ATextService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 01-mar-2018
 * Time: 07:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FactoryLabelConfig.class)
public class FactoryLabelTest {

//    @InjectMocks
//    public AHtmlService service;
//
//    @Autowired
//    private Tool tool;
//
//    //    @Resource(name = "&tool")
////    @InjectMocks
//    @Mock(name = "tool")
//    private FactoryLabel toolFactory;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        MockitoAnnotations.initMocks(service);
//        MockitoAnnotations.initMocks(tool);
//        MockitoAnnotations.initMocks(toolFactory);
////        toolFactory.service = service;
//    }// end of method
//
//
//    @Test
//    public void testConstructWorkerByJava() {
//        assertEquals(tool.getId(), 2);
////        assertEquals(toolFactory.getFactoryId(), 7070);
//    }

}// end of class
