package it.algos.springvaadin.validator;

import com.vaadin.data.validator.EmailValidator;
import it.algos.springvaadin.lib.LibText;
import lombok.extern.slf4j.Slf4j;

/**
 * Project springvaadin
 * Created by Algos
 * User: gac
 * Date: sab, 21-ott-2017
 * Time: 15:10
 */
@Slf4j
public class AlgosEmailValidator extends EmailValidator {

    public AlgosEmailValidator(String fieldName) {
        super(LibText.setRossoBold(LibText.primaMaiuscola(fieldName)) + " doesn't look like a valid email address");
    }// end of constructor

}// end of class
