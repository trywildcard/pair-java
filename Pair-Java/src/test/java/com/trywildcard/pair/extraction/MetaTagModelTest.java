package com.trywildcard.pair.extraction;

import com.trywildcard.pair.exception.CardBuilderException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.trywildcard.pair.extraction.MetaTagModel.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;


/**
 * Created by karthiksenthil on 11/2/14.
 */
public class MetaTagModelTest {


    private MetaTagModel metaTagModel;

    @Before
    public void setUp() throws CardBuilderException {

        Map<String, String> metaTags = new HashMap<>();
        metaTags.put(DESCRIPTION_DATA_KEY, "description");
        metaTags.put(HTML_DATA_KEY, "html");
        metaTags.put(TITLE_DATA_KEY, "title");
        metaTags.put(VIDEO_URL_DATA_KEY, "youtube.com");
        metaTags.put(VIDEO_HEIGHT_DATA_KEY, "240");
        metaTags.put(VIDEO_WIDTH_DATA_KEY, "240");
        metaTags.put(PRICE_DATA_KEY, "10");
        metaTags.put(IMAGE_URL_DATA_KEY, "image");
        metaTags.put(APP_LINK_ANDROID, "android");
        metaTags.put(APP_LINK_IOS, "ios");

        metaTagModel = new MetaTagModel(metaTags);
    }

    @Test(expected = CardBuilderException.class)
    public void testNull() throws CardBuilderException {
        MetaTagModel metaTagModel1 = new MetaTagModel(null);
    }

    @Test
    public void testNotThere() throws CardBuilderException {
        MetaTagModel metaTagModel1 = new MetaTagModel(Collections.EMPTY_MAP);
        assertEquals(metaTagModel1.getNumberOfMetaTags().intValue(), 0);
        assertNull(metaTagModel1.getTitle());
    }

    @Test
    public void testFields() throws CardBuilderException {
        assertEquals(metaTagModel.getTitle(), "title");
        assertEquals(metaTagModel.getPrice(), "10");
        assertEquals(metaTagModel.getDescription(), "description");
        assertEquals(metaTagModel.getAppLinkAndroid(), "android");
        assertEquals(metaTagModel.getAppLinkIos(), "ios");
        assertEquals(metaTagModel.getHtmlContent(), "html");
        assertEquals(metaTagModel.getImageUrl(), "image");
        assertEquals(metaTagModel.getVideoHeight(), "240");
        assertEquals(metaTagModel.getVideoWidth(), "240");
        assertEquals(metaTagModel.getVideoUrl(), "youtube.com");
        assertEquals(metaTagModel.getNumberOfMetaTags().intValue(), 10);
    }

}
