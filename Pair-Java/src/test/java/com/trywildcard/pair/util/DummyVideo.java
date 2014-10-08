package com.trywildcard.pair.util;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class DummyVideo {

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // minimal attributes
    public final String title = "Best of Phantom: Cavaliers Practice";
    public final String description = "Take a look at the new-look Cleveland Cavaliers through the lens of the Phantom camera during a practice session. " +
            "Visit nba.com/video for more highlights.About the NBA: The NBA is the premier professional basketball league in the United States and Canada. " +
            "The league is truly global, with games and programming in 215 countries and territories in 47 languages, as well as NBA rosters at the start of the 2013-14 season " +
            "featuring a record 92 international players from 39 countries and territories.";
    public final String webUrl = "https://www.youtube.com/watch?v=0RFfrsABtQo";

    // extensive attributes
    public final Date publicationDate;
    public final String source = "Youtube";
    public final String creator = "NBA";
    public final String embeddedURL = "https://www.youtube.com/embed/0RFfrsABtQo";
    public final String streamURL = "https://www.youtube.com/stream/0RFfrsABtQo";
    public final String streamContentType = "video/mp4";
    public final Integer embeddedURLWidth = 400;
    public final Integer embeddedURLHeight = 400;

    public final String posterImageUrl = "https://wildcard-branddata.s3.amazonaws.com/staging/youtube/bg_img.jpg";
    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid = "android://applink";

    public DummyVideo() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        publicationDate = dateFormat.parse("2014-09-22");
    }
}
