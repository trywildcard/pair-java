package com.trywildcard.pair.extraction;

import com.trywildcard.pair.exception.CardBuilderException;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 11/2/14.
 */
public class MetaTagExtractorTest {
    @Test
    public void testBadUrl() throws MalformedURLException, CardBuilderException{
        assertEquals(MetaTagExtractor.getMetaTags(new URL("http://trywildcard23030203.com")).getNumberOfMetaTags().intValue(), 0);
    }
}
