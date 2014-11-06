package com.trywildcard.pair.util;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/**
 * Created by karthiksenthil on 11/2/14.
 */
public class HttpAgentTest {
    public static HttpAgent agent = new HttpAgent();

    @Test
    public void getHTTPTest() throws IOException, URISyntaxException {
        HttpAgent httpAgent = new HttpAgent();
        assertTrue(!httpAgent.get("http://www.trywildcard.com").isEmpty());
    }

    @Test(expected = UnknownHostException.class)
    public void getHTTPTestBadUrl() throws IOException, URISyntaxException {
        HttpAgent httpAgent = new HttpAgent();
        httpAgent.get("http://www.trywildcard232424.com");
    }
}
