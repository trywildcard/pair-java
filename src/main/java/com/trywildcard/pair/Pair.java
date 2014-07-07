package com.trywildcard.pair;

/**
 * Created by michaelgarate on 7/2/14.
 */
public class Pair {
    public static final String VERSION = "0.0.1";

    public static boolean strictValidation = false;

    public static String getVersion(){
        return VERSION;
    }

    public static boolean getStrictValidation(){
        return strictValidation;
    }

    public static void setStrictValidation(boolean b){
        strictValidation = b;
    }
}
