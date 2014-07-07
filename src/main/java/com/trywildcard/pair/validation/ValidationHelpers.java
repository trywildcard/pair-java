package com.trywildcard.pair.validation;

import java.util.List;

/**
 * Created by michaelgarate on 7/7/14.
 */
public abstract class ValidationHelpers {

    public static boolean notNullOrEmpty(List<?> list) {
        if (list == null || list.isEmpty()){
            return false;
        }

        return true;
    }

    public static boolean notNullOrEmpty(String string){
        if (string == null || string.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean notNull(Object object) {
        if (object == null){
            return false;
        }
        return true;
    }

    public static boolean notEmpty(String string){
        if (string == null){
            return true;
        }

        if (string.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean notNegative(Integer integer){
        if (integer == null){
            return true;
        }

        if (integer < 0){
            return false;
        }
        return true;
    }

    public static boolean notNegative(Float integer){
        if (integer == null){
            return true;
        }

        if (integer < 0){
            return false;
        }
        return true;
    }
}
