package com.trywildcard.pair.util;

import com.google.common.collect.Lists;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;

import java.util.List;

/**
 * Created by karthiksenthil on 1/28/15.
 */
public class DummySummary {

    public final String title = "U.N. hosts Global Warming Summit";
    public final String description = "U.N. hosts Global Warming Summit in New York hosted by thousands.  Dignataries speak.";
    public final String webUrl = "http://www.huffingtonpost.com/2014/09/23/leonardo-dicaprio-un_n_5868718.html";
    public Image image;
    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid = "android://applink";
    public final List<String> keywords = Lists.newArrayList("keyword1", "keyword2");

    public DummySummary() throws CardBuilderException {
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg", "Climate Summit 2014");
    }

}
