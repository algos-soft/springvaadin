package it.algos.springvaadin.entity.log;

/**
 * Created by gac on 22 ago 2015.
 * .
 */
public enum Livello {
    debug, info, warn, error;

    public Object getValue(){
        return this.toString();
    }
} // fine della Enumeration
