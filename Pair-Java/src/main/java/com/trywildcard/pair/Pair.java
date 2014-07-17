package com.trywildcard.pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by michaelgarate on 7/2/14.
 */
public class Pair {
    private static Pair instance = null;
    private static Properties properties = new Properties();

    private Pair() {
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

    public static synchronized Pair getInstance(){
        if (instance == null) {
            instance = new Pair();
        }

        return instance;
    }


    public String getVersion(){
        return properties.getProperty("pairVersion");
    }

    public boolean getStrictValidation(){
        return Boolean.valueOf(properties.getProperty("strictValidation"));
    }

    public void setStrictValidation(boolean b){
        properties.setProperty("strictValidation", String.valueOf(b));
    }
}
