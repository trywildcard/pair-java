package com.trywildcard.pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by michaelgarate on 7/2/14.
 */
public class Pair {

    private static Properties properties = new Properties();

    public static void init(){
        InputStream input = null;

        try {
            input = Pair.class.getClassLoader().getResourceAsStream("config.properties");

            if (input != null){
                properties.load(input);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getVersion(){
        return properties.getProperty("pairVersion");
    }

    public static boolean getStrictValidation(){
        return Boolean.valueOf(properties.getProperty("strictValidation"));
    }

    public static void setStrictValidation(boolean b){
        properties.setProperty("strictValidation", String.valueOf(b));
    }
}
