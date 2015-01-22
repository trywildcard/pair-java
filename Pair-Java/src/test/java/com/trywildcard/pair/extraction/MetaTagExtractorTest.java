package com.trywildcard.pair.extraction;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 11/2/14.
 */
public class MetaTagExtractorTest {
    @Test
    public void testBadUrl() throws MalformedURLException, CardBuilderException{
        assertEquals(MetaTagExtractor.getMetaTags(new URL("http://dns.trywildcard23030203.com")).getNumberOfMetaTags().intValue(), 0);
    }

    @Test
    public void testTwitterTags() throws CardBuilderException, IOException {
        String html = TestUtil.readResourceAsString("metatags_twitter.html");
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(html);
        assertEquals(metaTagModel.getTitle(), "REI Halo +40 Sleeping Bag");
        assertEquals(metaTagModel.getDescription(), "REI Halo +40 Sleeping Bag - Women's Regular - Special Buy");
        assertEquals(metaTagModel.getImageUrl(), "http://ak-images3.jackthreads.com/v1/image/714713/size/jt-product_thumbnail");
        assertEquals(metaTagModel.getAppLinkIos(), "etsy://listing/128235512?ref=TwitterProductCard");
        assertEquals(metaTagModel.getAppLinkAndroid(), "etsy://listing/128235512?ref=TwitterProductCard");
    }

    @Test
    public void testOGTags() throws CardBuilderException, IOException {
        String html = TestUtil.readResourceAsString("metatags_opengraph.html");
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(html);
        assertEquals(metaTagModel.getTitle(), "Friend Smash Coin");
        assertEquals(metaTagModel.getDescription(), "Friend Smash Coins to purchase upgrades and items!");
        assertEquals(metaTagModel.getImageUrl(), "http://www.friendsmash.com/images/coin_600.png");
        assertEquals(metaTagModel.getImageHeight(), "369");
        assertEquals(metaTagModel.getImageWidth(), "553");
        assertEquals(metaTagModel.getPrice(), "0.30");
    }

    @Test
    public void testVideoTags() throws CardBuilderException, IOException {
        String html = TestUtil.readResourceAsString("metatags_video.html");
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(html);
        assertEquals(metaTagModel.getTitle(), "Meatball Making w The Meatball Shop");
        assertEquals(metaTagModel.getDescription(), "Join Chef and Co-Founder of The Meatball Shop, Dan Holzman, to learn how he makes meatballs");
        assertEquals(metaTagModel.getImageUrl(), "https://i.ytimg.com/vi/a5A_nVQYDfc/maxresdefault.jpg");
        assertEquals(metaTagModel.getAppLinkAndroid(), "http://www.youtube.com/watch?v=a5A_nVQYDfc");
        assertEquals(metaTagModel.getAppLinkIos(), "vnd.youtube://www.youtube.com/watch?v=a5A_nVQYDfc&feature=applinks");
        assertEquals(metaTagModel.getVideoUrl(), "http://www.youtube.com/v/a5A_nVQYDfc?autohide=1&version=3");
        assertEquals(metaTagModel.getVideoWidth(), "1280");
        assertEquals(metaTagModel.getVideoHeight(), "720");
    }

    @Test
    public void testDeepLinksTags() throws CardBuilderException, IOException {
        String html = TestUtil.readResourceAsString("metatags_deeplinks.html");
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(html);
        assertEquals(metaTagModel.getAppLinkAndroid(), "etsy://listing/128235512?ref=TwitterProductCard");
        assertEquals(metaTagModel.getAppLinkIos(), "etsy://listing/128235512?ref=applinks_ios");
    }

}
