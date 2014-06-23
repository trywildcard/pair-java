package com.wildcard.pair.util;

import java.util.List;

import com.wildcard.pair.model.CardBuilderException;

/**
 * Static methods to assert validity of parameters.
 */
public class ValidationTool {
    public static void notNullOrEmpty(List<?> list, String message){
        if (list == null || list.isEmpty()){
            throw new CardBuilderException(message);
        }
    }
    
    public static void notNullOrEmpty(String string, String message){
        if (string == null || string.isEmpty()){
            throw new CardBuilderException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null){
            throw new CardBuilderException(message);
        }
    }
    
    public static void notEmpty(String string, String message){
        if (string == null){
            return;
        }
        
        if (string.isEmpty()){
            throw new CardBuilderException(message);
        }
    }

    public static void notNegative(Integer integer, String message){
        if (integer == null){
            return;
        }
        
        if (integer < 0){
            throw new CardBuilderException(message);
        }
    }

    public static void notNegative(Float integer, String message){
        if (integer == null){
            return;
        }
        
        if (integer < 0){
            throw new CardBuilderException(message);
        }
    }
}
