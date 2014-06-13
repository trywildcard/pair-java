package com.wildcard.testUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestUtil {
    
    public static String readResourceAsString(String fileName) throws IOException{
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        Scanner s = new Scanner(is);
        s.useDelimiter("\\A");
        String resourceString = s.hasNext() ? s.next() : "";
        s.close();
        return resourceString;
    }
}
