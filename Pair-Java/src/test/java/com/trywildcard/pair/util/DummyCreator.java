package com.trywildcard.pair.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cmcewen on 2/2/15.
 */
public class DummyCreator {

    // minimal attributes
    public final String name = "Wildcard";
    public final String favicon = "http://www.trywildcard.com/images/favicon.ico";
    public final URL testFavicon;

    // extensive attributes
    public final String iosAppStoreUrl = "https://itunes.apple.com/us/app/wildcard-browse-better-mobile/id930047790";
    public final String androidAppStoreUrl = "https://play.google.com/store/apps/details?id=com.trywildcard.android";
    public final String url = "http://www.trywildcard.com";
    public final URL testUrl;

    public DummyCreator() throws MalformedURLException {
        testUrl = new URL("http://www.trywildcard.com");
        testFavicon = new URL("http://www.trywildcard.com/images/favicon.ico");
    }
}
