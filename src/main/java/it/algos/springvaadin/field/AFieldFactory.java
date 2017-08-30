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


    public AField crea(AFType type, String publicFieldName, AlgosPresenterImpl presenter) {
        AField field = null;

        try { // prova ad eseguire il codice
            switch (type) {
                case id:
                    field = keyidFieldAutowired.clone(publicFieldName, presenter);
                    break;
                case text:
                    field = textFieldAutowired.clone(publicFieldName, presenter);
                    break;
                case integer:
                    field = integerFieldAutowired.clone(publicFieldName, presenter);
                    break;
                default: // caso non definito
                    break;
            } // fine del blocco switch
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        return field;
    }// end of method

}// end of class

