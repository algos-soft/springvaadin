package it.algos.springvaadin.validator;

import com.mongodb.MongoClient;
import com.sun.jdi.InterfaceType;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import it.algos.springvaadin.entity.stato.Stato;
import it.algos.springvaadin.entity.stato.StatoRepository;
import it.algos.springvaadin.lib.LibReflection;
import it.algos.springvaadin.lib.LibText;
import it.algos.springvaadin.model.AlgosEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import sun.rmi.rmic.iiop.ImplementationType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Controlla che il valore del campo unico non esista già
 */
public class AlgosUniqueValidator extends AbstractValidator<String> {

    private Class<? extends AlgosEntity> entityClazz;
    private String fieldName;
    private String dbName;

    private MongoClient mongo;
    private MongoOperations mongoOps;

    private String tagIni = " deve essere unico ed il valore ";
    private String tagEnd = " esiste già ";


    public AlgosUniqueValidator(final Class<? extends AlgosEntity> entityClazz, String fieldName) {
        super(fieldName);
        this.entityClazz = entityClazz;
        this.fieldName = fieldName;
        this.dbName = entityClazz.getSimpleName().toLowerCase();

        mongo = new MongoClient("localhost", 27017);
        mongoOps = new MongoTemplate(mongo, "test");
    }// end of constructor


    public ValidationResult apply(String value, ValueContext context) {
        boolean esiste = true;
Object alfa=context.getHasValue().get().getValue();
        try { // prova ad eseguire il codice
            AlgosEntity entity = mongoOps.findOne(
                    new Query(Criteria.where(fieldName).is(value)),
                    entityClazz, dbName);
            esiste = entity != null;
        } catch (Exception unErrore) { // intercetta l'errore
            int a = 87;
        }// fine del blocco try-catch

        if (value == null || value.equals("")) {
            return this.toResult(value, true);
        } else {
            if (esiste) {
                return this.toResult(value, false);
            } else {
                return this.toResult(value, true);
            }// end of if/else cycle
        }// end of if/else cycle
    }// end of method


    @Override
    protected String getMessage(String value) {
        String name = LibText.primaMaiuscola(fieldName);
        name = LibText.setRossoBold(name);
        value = LibText.setRossoBold(value);
        return name + tagIni + value + tagEnd;
    }// end of method

}// end of class
