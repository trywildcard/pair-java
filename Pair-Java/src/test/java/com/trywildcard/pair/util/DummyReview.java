package com.trywildcard.pair.util;

import com.google.common.collect.Lists;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.model.review.Rating;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class DummyReview {

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // minimal attributes
    public final String title = "LG G2 Review";
    public final String abstractContent = "There are a lot of smartphones out there now. You know this. To add to the confusion, many companies are now parading out multiple top-drawer phones:";
    public final String webUrl = "https://www.engadget.com/2013/09/09/lg-g2-review/";
    public final String htmlContent = "<div id=\"body\" score=\"33.75\"> <div class=\"copy post-body\" score=\"21.25\"> <div class=\"article-content\" score=\"82.5\">" +
            " <p class=\"image-container\"><img alt=\"LG G2 review\" src=\"http://www.blogcdn.com/www.engadget.com/media/2013/09/g2review-1378739225.jpg\" /></p> <p>There " +
            "are <a href=\"http://www.engadget.com/tag/smartphone,review/\">a lot of smartphones</a> out there now. You know this. To add to the confusion, many companies " +
            "are now parading out multiple top-drawer phones: think Samsung's Galaxy S and Note series, or the Xperia Z and Z Ultra from Sony. Even LG, whose new G2 flagship " +
            "I'm poring over this time around, has both the G and G Pro to tempt buyers. It's getting increasingly difficult to launch a smartphone with some standout feature, " +
            "something <em>more</em> than just <a href=\"http://www.engadget.com/2013/09/04/samsung-galaxy-note-3-preview/\">bigger screens</a> and faster processors.</p> <p>" +
            "For the G2, LG's decided to make a major change to the phone's physical layout -- in a bullet-point summary, it has buttons on the back.";
    // extensive attributes
    public final Date publicationDate;
    public final String source = "Engadget";
    public final String author = "Jans Koepling, Senior Editor";
    public final Date updatedDate;
    public Image image;
    public final String productName = "LG G2";
    public final Rating rating = new Rating(87f, 0f, 100f, 55);
    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid = "android://applink";
    public final List<String> keywords = Lists.newArrayList("keyword1", "keyword2");

    public DummyReview() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        publicationDate = dateFormat.parse("2014-09-22");
        updatedDate = dateFormat.parse("2014-09-23");
        image = new Image("http://www.blogcdn.com/www.engadget.com/media/2013/09/g2review-1378739225.jpg", "LG G2");
    }
}
