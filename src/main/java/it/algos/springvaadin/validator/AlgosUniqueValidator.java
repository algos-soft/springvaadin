package it.algos.springvaadin.validator;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import it.algos.springvaadin.lib.LibText;

import java.util.List;

/**
 * Controlla che il valore del campo unico non esista già
 */
public class AlgosUniqueValidator extends AbstractValidator<String> {

    private List<String> items;
    private String fieldName;
    private String tagIni = " deve essere unico ed il valore ";
    private String tagEnd = " esiste già ";


    public AlgosUniqueValidator(String fieldName, List<String> items) {
        super(fieldName);
        this.fieldName = fieldName;
        this.items = items;
    }// end of constructor


    public ValidationResult apply(String value, ValueContext context) {
        boolean esiste = items.contains(value);

        if (value == null || value.equals("")) {
            return this.toResult(value, true);
        } else {
            if (esiste) {
                return this.toResult(value, false);
            } else {
                return this.toResult(value, true);
            }// end of if/else cycle
        }// end of if/else cycle
    }// end of method


    @Override
    protected String getMessage(String value) {
        return fieldName + tagIni + LibText.setRossoBold(value) + tagEnd;
    }// end of method

}// end of class
