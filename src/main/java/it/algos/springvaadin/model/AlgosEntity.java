package it.algos.springvaadin.model;

import com.vaadin.spring.annotation.SpringComponent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by gac on 07/07/17
 * Classi di tipo JavaBean
 * 1) le sottoclassi concrete devono avere un costruttore senza argomenti
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolenai al posto di get)
 * 3) le classi devono implementare l'interfaccia Serializable (tramite questa superclasse astratta)
 * 4) le classi non devono contenere nessun metodo per la gestione degli eventi
 */
@Entity
public abstract class AlgosEntity implements Serializable {

    @Id
    @GeneratedValue
    protected Long id;


    public Long getId() {
        return id;
    }// end of getter method


    public void setId(Long id) {
        this.id = id;
    }// end of setter method


}// end of entity abstract class
