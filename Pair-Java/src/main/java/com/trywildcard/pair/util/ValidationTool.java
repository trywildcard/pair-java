package com.trywildcard.pair.util;

import com.trywildcard.pair.model.CardBuilderException;

import java.util.ArrayList;
import java.util.List;

/**
 * Static methods to assert validity of parameters.
 */
public class ValidationTool {

    public ValidationType REQUIRED = ValidationType.REQUIRED;
    public ValidationType OPTIONAL = ValidationType.OPTIONAL;

    List<String> errors = new ArrayList<String>();

    public ValidationTool(){

    }

    public boolean notNullOrEmpty(List<?> list, ValidationType vType, String message) {
        if (list == null || list.isEmpty()){
            return fail(vType, message);
        }

        return true;
    }
    
    public boolean notNullOrEmpty(String string, ValidationType vType, String message){
        if (string == null || string.isEmpty()){
            return fail(vType, message);
        }
        return true;
    }

    public boolean notNull(Object object, ValidationType vType, String message) {
        if (object == null){
            return fail(vType, message);
        }
        return true;
    }
    
    public boolean notEmpty(String string, ValidationType vType, String message){
        if (string == null){
            return true;
        }

        if (string.isEmpty()){
            return fail(vType, message);
        }
        return true;
    }

    public boolean notNegative(Integer integer, ValidationType vType, String message){
        if (integer == null){
            return true;
        }
        
        if (integer < 0){
            return fail(vType, message);
        }
        return true;
    }

    public boolean notNegative(Float integer, ValidationType vType, String message){
        if (integer == null){
            return true;
        }
        
        if (integer < 0){
            return fail(vType, message);
        }
        return true;
    }

    public boolean fail(ValidationType vType, String message) {
        switch(vType){
            case REQUIRED: throw new CardBuilderException(message);
            case OPTIONAL: {
                errors.add(message);
                return false;
            }
        }
        return false;
    }

    public List<String> getErrors(){
        return errors;
    }
}
