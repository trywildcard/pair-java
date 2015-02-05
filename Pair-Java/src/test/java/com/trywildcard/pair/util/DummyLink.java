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
 * Created by karthiksenthil on 2/3/15.
 */
public class DummyLink {

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // minimal attributes
    public final String url = "http://spectrum.ieee.org/view-from-the-valley/at-work/tech-careers/massive-worldwide-layoff-underway-at-ibm";

    public final String title = "Massive WorldWide Layoff Underway at IBM";
    public final String description = "IBM layoffs started last month.  Details to follow.";
    public final String webUrl = "https://news.ycombinator.com/ibm-layoffs";
    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid = "android://applink";
    public final List<String> keywords = Lists.newArrayList("keyword1", "keyword2");

    public final Date publicationDate;

    public DummyLink() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        publicationDate = dateFormat.parse("2015-02-02");
    }
}
