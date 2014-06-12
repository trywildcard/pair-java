package com.wildcard.model.util;

import java.util.List;

import com.wildcard.model.CardBuilderException;

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
}
