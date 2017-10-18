package it.algos.springvaadin.entity.log;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.spring.annotation.SpringComponent;
import it.algos.springvaadin.lib.LibField;

import java.util.Arrays;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 14-ott-2017
 * Time: 16:42
 */
public enum LogType {
    nuovo, edit, delete;


    public static String[] getValues() {
        String[] valori;
        LogType[] types = values();
        valori = new String[values().length];

        for (int k = 0; k < types.length; k++) {
            valori[k] = types[k].toString();
        }// end of for cycle

        return valori;
    }// end of static method

}// end of class