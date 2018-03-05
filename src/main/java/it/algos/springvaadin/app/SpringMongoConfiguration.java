package it.algos.springvaadin.app;

import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: dom, 04-mar-2018
 * Time: 23:24
 */
@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = "it.algos.springvaadin.entity")
public class SpringMongoConfiguration extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Bean
    public MongoClient mongo() throws Exception {

        MongoClient client = new MongoClient("localhost");
        return client;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}// end of class
