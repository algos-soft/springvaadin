package it.algos.springvaadin.model;

import com.vaadin.spring.annotation.SpringComponent;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * Created by gac on 07/07/17
 * Le sottoclassi concrete sono di tipo JavaBean
 * 1) la sottoclasse deve avere un costruttore senza argomenti
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolen al posto di get)
 * 3) la sottoclasse deve implementare l'interfaccia Serializable (lo fa in questa classe)
 * 4) la sottoclasse non deve contenere nessun metodo per la gestione degli eventi
 * <p>
 * Annotated with @Getter (Lombok) for automatic use of Getter
 * Sottoclasse annotated with @SpringComponent (obbligatorio)
 * Sottoclasse annotated with @Data (Lombok) for automatic use of Getter and Setter
 * Sottoclasse annotated with @NoArgsConstructor (Lombok) for JavaBean specifications
 * Sottoclasse annotated with @AllArgsConstructor (Lombok) per usare il costruttore completo nel Service
 */
@Getter
public abstract class AlgosEntity implements Serializable {


    /**
     * key property ObjectId
     * gestita direttamente da Mongo
     */
    public String id;


}// end of entity abstract class

