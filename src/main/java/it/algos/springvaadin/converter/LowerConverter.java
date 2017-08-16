package it.algos.springvaadin.converter;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class LowerConverter implements Converter<String, String> {

    @Override
    public Result<String> convertToModel(String value, ValueContext valueContext) {
            return Result.ok(value.toLowerCase());
    }// end of method

    @Override
    public String convertToPresentation(String value, ValueContext valueContext) {
        return value;
    }// end of method

}// end of class

