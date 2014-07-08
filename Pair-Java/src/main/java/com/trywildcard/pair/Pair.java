package com.trywildcard.pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by michaelgarate on 7/2/14.
 */
public class Pair {

    private static Properties properties = new Properties();

    public static void init(){
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            properties.load(input);
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
