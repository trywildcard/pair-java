package com.trywildcard.pair.validation;

import com.trywildcard.pair.exception.CardBuilderException;

import java.util.ArrayList;
import java.util.List;

/**
 * Static methods to assert validity of parameters.
 */
public class ValidationTool extends ValidationHelpers {

    OptionalValidator optionalValidator = new OptionalValidator();
    RequiredValidator requiredValidator = new RequiredValidator();

    public ValidationTool(){

    }

    public boolean optional(boolean b, String message){
        return optionalValidator.expect(b, message);
    }

    public boolean required(boolean b, String message) throws CardBuilderException {
        return requiredValidator.expect(b, message);
    }

    public List<String> getErrors(){
        return optionalValidator.getErrors();
    }
}

