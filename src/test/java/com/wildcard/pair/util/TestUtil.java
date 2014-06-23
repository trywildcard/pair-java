package com.wildcard.pair.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestUtil {

    public final static int FLOAT_EXACT_COMPARISON_EPSILON = 0; // should be exact, no error since never computed. 

    /**
     * Given a filename, read in a test resource as a string.
     * @param fileName the name of the file to be read.
     * @return a string representation of the file contents.
     * @throws IOException
     */
    public static String readResourceAsString(String fileName) throws IOException{
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        Scanner s = new Scanner(is);
        s.useDelimiter("\\A");
        String resourceString = s.hasNext() ? s.next() : "";
        s.close();
        return resourceString;
    }

    /**
     * Get a configured instance of ObjectMapper for serialization.
     * @return the configured ObjectMapper.
     */
    public static ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return mapper;
    }
}
