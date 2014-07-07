package com.trywildcard.pair.validation;

import com.trywildcard.pair.exception.CardBuilderException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class RequiredValidator {
    List<String> errors = new ArrayList<String>();

    boolean fail(String message) throws CardBuilderException {
        errors.add(message);
        throw new CardBuilderException(message);
    }

    List<String> getErrors() {
        return errors;
    }

    public boolean expect(boolean b, String message) throws CardBuilderException {
        if (b){
            return b;
        } else {
            return fail(message);
        }
    }
}
