package it.algos.springvaadin.enumeration;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 14-ott-2017
 * Time: 16:42
 */
public enum EALogType {
    nuovo, edit, delete,versione;


    public static String[] getValues() {
        String[] valori;
        EALogType[] types = values();
        valori = new String[values().length];

        for (int k = 0; k < types.length; k++) {
            valori[k] = types[k].toString();
        }// end of for cycle

        return valori;
    }// end of static method

}// end of class
