package com.trywildcard.pair.util;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.model.media.Media;

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
    public final String htmlContent = "<p>Leonardo DiCaprio addressed world leaders assembled for the <a href=\"http://www.un.org/climatechange/summit/\" target=\"_hplink\">United Nations Climate Summit</a> early Tuesday morning, urging them to take action to address \"the greatest challenge of our existence on this planet.\"</p><p>\"As an actor, I pretend for a living. I play fictitious characters often solving fictitious problems. I believe mankind has looked at climate change in that same way,\" he said at the summit. \"My friends, " +
            "this body -- perhaps more than any other gathering in human history -- now faces this, but achievable task. You can make history... or be vilified by it.\"</p><p>UN Secretary-General Ban Ki-moon <a href=\"http://www.huffingtonpost.com/2014/09/16/leonardo-dicaprio-un-messenger_n_5830910.html?1411486895\" target=\"_hplink\">recently appointed DiCaprio</a> to serve as a <a href=\"http://www.un.org/apps/news/story.asp?NewsID=48762#.VCGVlytsvfs\" target=\"_hplink\">United Nations Messenger of Peace</a>, calling the actor a “new voice for climate advocacy.\" " +
            "Both Ban and DiCaprio participated in Sunday's <a href=\"http://www.huffingtonpost.com/2014/09/21/peoples-climate-march_n_5857902.html?utm_hp_ref=green\" target=\"_hplink\">400,000-strong People's Climate March</a> in New York City.</p><p>Take a look at DiCaprio's speech above. You can read a full transcription below.</p><blockquote><p>Thank you, Mr. Secretary-General, your excellencies, ladies and gentleman, and distinguished guests. I’m honored to be here today, I stand before you not as an expert but as a concerned citizen, one of the 400,000 people who " +
            "marched in the streets of New York on Sunday, and the billions of others around the world who want to solve our climate crisis.</p><p>As an actor I pretend for a living. I play fictitious characters often solving fictitious problems.</p><p>I believe mankind has looked at climate change in that same way: as if it were a fiction, as if pretending that climate change wasn’t real would somehow make it go away.</p><p>But I think we know better than that. Every week , we’re seeing new and undeniable climate events, evidence that accelerated climate change is here right " +
            "now. Droughts are intensifying, our oceans are acidifying, with methane plumes rising up from the ocean floor. We are seeing extreme weather events, and the West Antarctic and Greenland ice-sheets melting at unprecedented rates, decades ahead of scientific projections.</p><p>None of this is rhetoric, and none of it is hysteria. It is fact. The scientific community knows it, industry knows it, governments know it, even the United States military knows it. The Chief of the U.S. Navy’s Pacific Command, Admiral Samuel Locklear, recently said that climate change is our single " +
            "greatest security threat.</p><p>My Friends, this body - perhaps more than any other gathering in human history - now faces this difficult, but achievable task. You can make history... or be vilified by it.</p><p>To be clear, this is not about telling people to change their light bulbs or buy a hybrid car. This disaster has grown BEYOND the choices that individuals make. This is now about our industries, and governments around the world taking decisive, large-scale action.</p><p>Now must be our moment for action.</p><p>We need to put a pricetag on carbon emissions, and eliminate government subsidies for " +
            "oil, coal and gas companies. We need to end the free ride that industrial polluters have been given in the name of a free-market economy, they do not deserve our tax dollars, they deserve our scrutiny. For the economy itself will die if our eco-systems collapse.</p><p>The good news is that renewable energy is not only achievable but good economic policy.</p><p>This is not a partisan debate; it is a human one. Clean air and a livable climate are inalienable human rights. And solving this crisis is not a question of politics, it is a question of our own survival.</p><p>This is the most urgent of times, and" +
            " the most urgent of messages.</p><p>Honored delegates, leaders of the world, I pretend for a living. But you do not. </p><p>The people made their voices heard on Sunday around the world and the momentum will not stop. But now it is YOUR turn, the time to answer humankind's greatest challenge is now.</p><p>We beg of you to face it with courage. And honesty. Thank you.</p></blockquote>";

    // extensive attributes
    public final Date publicationDate;
    public final String source = "Associated Press";
    public final String byLine = "by Jans Koepling, Senior Editor";
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
