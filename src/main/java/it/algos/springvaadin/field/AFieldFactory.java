package it.algos.springvaadin.field;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.TextField;
import it.algos.springvaadin.lib.Cost;
import it.algos.springvaadin.presenter.AlgosPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: mer, 30-ago-2017
 * Time: 11:14
 */
@SpringComponent
public class AFieldFactory implements AIFieldFactory {

    @Autowired
    @Qualifier(Cost.FIELD_ID)
    private AField keyidFieldAutowired;

    @Autowired
    @Qualifier(Cost.FIELD_TEXT)
    private AField textFieldAutowired;

    @Autowired
    @Qualifier(Cost.FIELD_INTEGER)
    private AField integerFieldAutowired;

    @Autowired
    @Qualifier(Cost.FIELD_IMAGE)
    private AField imageFieldAutowired;

    @Autowired
    @Qualifier(Cost.FIELD_COMBO)
    private AField comboFieldAutowired;


    public AField crea(AFType type, String publicFieldName, AlgosPresenterImpl source,Object[] items) {
        AField field = null;

        try { // prova ad eseguire il codice
            switch (type) {
                case id:
                    field = keyidFieldAutowired.clone(publicFieldName, source);
                    break;
                case text:
                    field = textFieldAutowired.clone(publicFieldName, source);
                    break;
                case integer:
                    field = integerFieldAutowired.clone(publicFieldName, source);
                    break;
                case image:
                    field = imageFieldAutowired.clone(publicFieldName, source);
                    break;
                case combo:
                    field = comboFieldAutowired.clone(publicFieldName, source);
                    ((AComboField) field).fixCombo(items,false);
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return field;
    }// end of method

}// end of class

