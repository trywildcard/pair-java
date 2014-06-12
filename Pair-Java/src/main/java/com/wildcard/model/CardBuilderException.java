package com.wildcard.model;

public class CardBuilderException extends RuntimeException {

    /**
     * serialVersionUID generated by Eclipse
     */
    private static final long serialVersionUID = -9210262580105204438L;
    
    public CardBuilderException(){
        super();
    }
    
    public CardBuilderException(String message) {
        super(message);
    }
    
    public CardBuilderException(String message, Throwable cause){
        super(message, cause);
    }
    
    public CardBuilderException(Throwable cause){
        super(cause);
    }

}
