package com.trywildcard.pair.util;

import com.google.common.collect.Lists;
import com.trywildcard.pair.exception.CardBuilderException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by karthiksenthil on 1/22/15.
 */
public class DummyImage {

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // minimal attributes
    public final String imageUrl = "http://farm8.staticflickr.com/7555/15707309794_efd50ed163_z.jpg";

    public final String webUrl = "https://www.flickr.com/photos/pware/15707309794/in/explore-2015-01-21";

    // extensive attributes
    public final String imageCaption = "Hermit thrush Catharus guttatus  A very minor crop  Member of the Nature’s Spirit  Good Stewards of Nature  © 2014 Patricia Ware - All Rights Reserved";
    public final String title = "A Happy Thrush";
    public final Date publicationDate;
    public final String author = "Karthik S";
    public final Integer imageWidth = 640;
    public final Integer imageHeight = 426;

    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid = "android://applink";
    public final List<String> keywords = Lists.newArrayList("keyword1", "keyword2");

    public DummyImage() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        publicationDate = dateFormat.parse("2014-09-22");
    }
}
