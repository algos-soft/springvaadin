package it.algos.springvaadin.lib;

import com.mongodb.MongoClient;
import it.algos.springvaadin.entity.AEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: ven, 01-set-2017
 * Time: 06:32
 */
public class LibMongo {

    private static MongoClient mongo;
    private static MongoOperations mongoOps;


    static {
        mongo = new MongoClient("localhost", 27017);
        mongoOps = new MongoTemplate(mongo, "test");
    }// end of static method


    /**
     * Recupera tutte le entities di una collezione
     */
    public static List findAll(final Class<? extends AEntity> entityClazz) {
        return mongoOps.findAll(entityClazz);
    }// end of static method


}// end of class

