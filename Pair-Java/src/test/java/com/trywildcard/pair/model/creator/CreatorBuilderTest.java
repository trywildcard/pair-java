package com.trywildcard.pair.model.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyCreator;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * Created by cmcewen on 2/2/15.
 */
public class CreatorBuilderTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyCreator dummyCreator;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException, MalformedURLException {
        dummyCreator = new DummyCreator();
    }

    private void testMinimalCreatorAttributes(Creator creator){
        Assert.assertEquals("Name should match", dummyCreator.name, creator.getName());
        Assert.assertEquals("Favicon should match", dummyCreator.testFavicon, creator.getFavicon());
    }

    private Creator buildMinimalCreator() throws CardBuilderException {

        CreatorBuilder creatorBuilder = new CreatorBuilder(dummyCreator.name, dummyCreator.favicon);

        return creatorBuilder.build();
    }

    @Test
    public void testMinimalCreator() throws JsonProcessingException, CardBuilderException {
        Creator creator = buildMinimalCreator();
        testMinimalCreatorAttributes(creator);
    }

    @Test
    public void testMinimalCreatorWithMinimalConstructor() throws CardBuilderException {
        Creator creator = new CreatorBuilder(dummyCreator.name, dummyCreator.favicon).build();
        testMinimalCreatorAttributes(creator);
    }

    private void testExtensiveCardAttributes(Creator creator) throws MalformedURLException {
        testMinimalCreatorAttributes(creator);

        Assert.assertEquals("Ios App Store Url should match", dummyCreator.iosAppStoreUrl, creator.getIosAppStoreUrl());
        Assert.assertEquals("Android App Store Url should match", dummyCreator.androidAppStoreUrl, creator.getAndroidAppStoreUrl());
        Assert.assertEquals("creator Url should match", dummyCreator.testUrl, creator.getUrl());
    }


    public Creator buildExtensiveCreator() throws CardBuilderException {
        CreatorBuilder builder = new CreatorBuilder(dummyCreator.name, dummyCreator.favicon);

        builder.iosAppStoreUrl(dummyCreator.iosAppStoreUrl);
        builder.androidAppStoreUrl(dummyCreator.androidAppStoreUrl);
        builder.url(dummyCreator.url);

        return builder.build();
    }

    @Test
    public void testExtensiveCreatorTest() throws IOException, URISyntaxException, CardBuilderException {
        Creator creator = buildExtensiveCreator();
        testExtensiveCardAttributes(creator);
    }
}
