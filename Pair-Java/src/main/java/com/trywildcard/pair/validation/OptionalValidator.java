package com.trywildcard.pair.validation;

import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.exception.StrictCardBuilderException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class OptionalValidator {
    List<String> errors = new ArrayList<String>();
    boolean strictValidation = Pair.getStrictValidation();

    boolean fail(String message) {
        errors.add(message);
        if (strictValidation){
            throw new StrictCardBuilderException(message);
        }
        return false;
    }

    List<String> getErrors() {
        return errors;
    }

    public boolean expect(boolean b, String message) {
        if (b){
            return b;
        } else {
            return fail(message);
        }
    }
}
