package it.algos.springvaadin.field;


/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 25-ott-2017
 * Time: 11:39
 */
public enum FieldAccessibility {
    allways,    //--sia Visible che Editable
    newOnly,    //--Visible e Editable per new e solo Visible per edit
    showOnly,   //--Visible ma non Editable sia per new che per edit
    never       //--Ne Visible ne Editable
}// end of enum class
