package com.trywildcard.pair.util;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by karthiksenthil on 10/4/14.
 */
public class DummyArticle {

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // minimal attributes
    public final String title = "U.N. hosts Global Warming Summit";
    public final String abstractContent = "U.N. hosts Global Warming Summit in New York hosted by thousands.  Dignataries speak.";
    public final String webUrl = "http://www.huffingtonpost.com/2014/09/23/leonardo-dicaprio-un_n_5868718.html";
    public final String htmlContent = "<p>Leonardo DiCaprio addressed world leaders assembled for the <a href=\"http://www.un.org/climatechange/summit/\" target=\"_hplink\">United Nations Climate Summit</a> early Tuesday morning, urging them to take action to address \"the greatest challenge of our existence on this planet.\"</p>";
    // extensive attributes
    public final Date publicationDate;
    public final String source = "Associated Press";
    public final String author = "Jans Koepling, Senior Editor";
    public final Date updatedDate;
    public Image image;
    public final Boolean isBreaking = Boolean.TRUE;

    public DummyArticle() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        publicationDate = dateFormat.parse("2014-09-22");
        updatedDate = dateFormat.parse("2014-09-23");
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg", "Climate Summit 2014");
    }

}
