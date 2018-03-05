package it.algos.springvaadin.app;

import it.algos.springvaadin.service.AHtmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: gio, 01-mar-2018
 * Time: 15:35
 */
@Configuration
public class FactoryLabelConfig {


    @Bean(name = "tool")
    public FactoryLabel toolFactory() {
        FactoryLabel factory = new FactoryLabel();
        factory.setFactoryId(7070);
        factory.setToolId(2);
        return factory;
    }

    @Bean
    public Tool tool() throws Exception {
        return toolFactory().getObject();
    }
}