package it.algos.springvaadin.lib;

import com.sun.deploy.util.StringUtils;
import it.algos.springvaadin.model.AlgosModel;
import it.algos.springvaadin.presenter.AlgosPresenter;
import it.algos.springvaadin.view.AlgosView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by gac on 04/07/17
 */
public class LibSql {


    /**
     * Get the query string for INSERT
     */
    @Deprecated
    public static String getQueryInsert(String tableName, AlgosModel entity) {
        String query = "";
        String space = " ";

//        LinkedHashMap<String, Object> map = this.getBeanMap(entityBean);
//        String campi = StringUtils.join(map.keySet(), ",");
//        String valori = LibText.repeat("?", ",", map.size());
//        campi = LibText.setTonde(campi);
//        valori = "values" + LibText.setTonde(valori);

//        query += "INSERT INTO";
//        query += space;
//        query += tableName;
//        query += space;
//        query += campi;
//        query += space;
//        query += valori;

        return query;
    }// end of static method


    /**
     * Get the query string for INSERT
     */
    @Deprecated
    public static String getQueryInsert(String tableName, Collection keySet) {
        String query = "";
        String space = " ";

        String campi = StringUtils.join(keySet, ",");
        String valori = LibText.repeat("?", ",", keySet.size());
        campi = LibText.setTonde(campi);
        valori = "values" + LibText.setTonde(valori);

        query += "INSERT INTO";
        query += space;
        query += tableName;
        query += space;
        query += campi;
        query += space;
        query += valori;

        return query;
    }// end of static method


//    /**
//     * Get the query string for INSERT
//     *
//     * @param tableName   in cui inserire la nuova entity
//     * @param columnsName delle property
//     */
//    public static String getQueryInsert(String tableName, ArrayList columnsName) {
//        String query = "";
//        String virgola = ",";
//        String space = " ";
//
//        String campi = "";
//        for (String column : columnsName) {
//            campi += column;
//            campi += virgola;
//            campi += space;
//        }// end of for cycle
//        campi = LibText.levaCoda(campi, virgola);
//        campi = LibText.setTonde(campi);
//
//        query += "INSERT INTO";
//        query += space;
//        query += tableName;
//        query += space;
//        query += campi;
//        query += space;
//        query += repeatValue(columnsName.size());
//
//        return query;
//    }// end of static method


    /**
     * Get the query string for INSERT
     *
     * @param tableName   in cui inserire la nuova entity
     * @param columnsName delle property
     */
    public static String getQueryInsert(String tableName, String[] columnsName) {
        String query = "";
        String virgola = ",";
        String space = " ";

        String campi = "";
        for (String column : columnsName) {
            campi += column;
            campi += virgola;
            campi += space;
        }// end of for cycle
        campi = LibText.levaCoda(campi, virgola);
        campi = LibText.setTonde(campi);

        query += "INSERT INTO";
        query += space;
        query += tableName;
        query += space;
        query += campi;
        query += space;
        query += repeatValue(columnsName.length);

        return query;
    }// end of static method


    /**
     * Ripete n volte una stringa, con un separatore
     *
     * @param stringaBase    da ripetere
     * @param separatore     tra le ripetizioni
     * @param numRipetizioni della stringa base
     *
     * @return stringaBase ripetuta n volte, con n-1 separatori
     */
    public static String repeat(String stringaBase, String separatore, int numRipetizioni) {
        String stringaOut = "";

        for (int k = 0; k < numRipetizioni - 1; k++) {
            stringaOut += stringaBase;
            stringaOut += separatore;
        }// end of for cycle
        stringaOut += stringaBase;

        return stringaOut;
    }// end of static method


    /**
     * Ripete n volte una stringaBase='?', con un separatore=','
     * Aggiunge il tag VALUES
     * Inserisce il risultato dentro due parentesi tonde
     *
     * @param numRipetizioni del tag base
     *
     * @return stringaBase ripetuta n volte, con n-1 separatori; inserita dentro due parentesi tonde
     */
    public static String repeatValue(int numRipetizioni) {
        String stringaBase = "?";
        String separatore = ",";

        String stringaOut = repeat(stringaBase, separatore, numRipetizioni);

        return "VALUES " + LibText.setTonde(stringaOut);
    }// end of static method

}// end of static class
