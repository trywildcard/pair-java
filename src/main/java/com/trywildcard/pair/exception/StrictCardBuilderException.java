package com.trywildcard.pair.exception;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class StrictCardBuilderException extends RuntimeException {

    public StrictCardBuilderException(){
        super();
    }

    public StrictCardBuilderException(String message) {
        super(message);
    }

    public StrictCardBuilderException(String message, Throwable cause){
        super(message, cause);
    }

    public StrictCardBuilderException(Throwable cause){
        super(cause);
    }
}
