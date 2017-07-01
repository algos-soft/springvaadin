package it.algos.springvaadin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by gac on 14/06/17
 * .
 */
/**
 * Classi di tipo JavaBean
 * <p>
 * 1) le sottoclassi concrete devono avere un costruttore senza argomenti
 * 2) le proprietà devono essere private e accessibili solo con get, set e is (usato per i boolenai al posto di get)
 * 3) le classi devono implementare l'interfaccia Serializable (tramite questa superclasse astratta)
 * 4) le classi non devono contenere nessun metodo per la gestione degli eventi
 */
public abstract class AlgosModel implements Serializable{

    @Id
    @GeneratedValue
    protected Long id;


    public Long getId() {
        return id;
    }// end of getter method

    public void setId(Long id) {
        this.id = id;
    }// end of setter method

}// end of class
