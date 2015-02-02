package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyAbstractCard;
import com.trywildcard.pair.util.DummyVideo;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoCardTest {
    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyVideo dummyVideo;
    private static DummyAbstractCard dummyAbstractCard;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyVideo = new DummyVideo();
        dummyAbstractCard = new DummyAbstractCard();
    }

    private void testMinimalVideoCardAttributes(VideoCard videoCard){
        Assert.assertEquals("Name should match", dummyVideo.title, videoCard.getMedia().getTitle());
        Assert.assertEquals("embeddedUrl should match", dummyVideo.embeddedURL, videoCard.getMedia().getEmbeddedUrl().toString());
        Assert.assertEquals("embeddedUrlHeight should match", dummyVideo.embeddedURLHeight,  videoCard.getMedia().getEmbeddedUrlHeight());
        Assert.assertEquals("embeddedUrlWidth should match", dummyVideo.embeddedURLWidth,  videoCard.getMedia().getEmbeddedUrlWidth());
    }


    private VideoCard buildMinimalVideoCard() throws CardBuilderException {

        Video video = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();

        VideoCard videoCard = new VideoCard(video, dummyVideo.webUrl);

        return videoCard;
    }

    @Test
    public void testMinimalVideoCard() throws JsonProcessingException, CardBuilderException {
        VideoCard card = buildMinimalVideoCard();
        testMinimalVideoCardAttributes(card);
    }

    @Test(expected = CardBuilderException.class)
    public void testNullKeywords() throws CardBuilderException {
        VideoCard card = buildMinimalVideoCard();
        card.setKeywords(null);
    }


    @Test
    public void testMinimalVideoCardWithMinimalConstructor() throws CardBuilderException {
        Video video = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
        VideoCard videoCard = new VideoCard(video, dummyVideo.webUrl);
        testMinimalVideoCardAttributes(videoCard);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_video_card.json");
        VideoCard fixtureCard = mapper.readValue(inputString,  VideoCard.class);
        Video generatedVideo = buildExtensiveVideo();
        VideoCard generatedCard = new VideoCard(generatedVideo, dummyVideo.webUrl);
        generatedCard.setKeywords(dummyAbstractCard.keywords);
        generatedCard.setAppLinkIos(dummyAbstractCard.appLinkIos);
        generatedCard.setAppLinkAndroid(dummyAbstractCard.appLinkAndroid);

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_minimal_video_card.json");
        VideoCard fixtureCard = mapper.readValue(inputString,  VideoCard.class);
        VideoCard generatedCard = buildMinimalVideoCard();

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullArticle() throws CardBuilderException {
        VideoCard card = new VideoCard(null, dummyVideo.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithBothNull() throws CardBuilderException {
        VideoCard card = new VideoCard(null, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullWebUrl() throws CardBuilderException {
        Video video = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
        VideoCard card = new VideoCard(video, null);
    }

    private Video buildExtensiveVideo() throws CardBuilderException {
        VideoBuilder builder = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth);

        builder.streamUrl(dummyVideo.streamURL);
        builder.streamContentType(dummyVideo.streamContentType);
        builder.posterImageUrl(dummyVideo.posterImageUrl);
        builder.description(dummyVideo.description);
        builder.creator(dummyVideo.creator);
        builder.publicationDate(dummyVideo.publicationDate);
        builder.source(dummyVideo.source);

        return builder.build();
    }

    @Test
    public void testBuildVideoCardWithWebUrl() throws CardBuilderException {
        VideoCard videoCard = new VideoCard("https://www.youtube.com/watch?v=0RFfrsABtQo");
        assertEquals(videoCard.getMedia().getTitle(), "Best of Phantom: Cavaliers Practice");
        assertTrue(videoCard.getMedia().getEmbeddedUrl().toString().startsWith("http://www.youtube.com/v/0RFfrsABtQo"));
        assertEquals(videoCard.getMedia().getEmbeddedUrlHeight(), new Integer(720));
        assertEquals(videoCard.getMedia().getEmbeddedUrlWidth(), new Integer(1280));
        assertEquals(videoCard.getMedia().getDescription(), "Take a look at the new-look Cleveland Cavaliers through the lens of the Phantom camera during a practice session. Visit nba.com/video for more highlights. Ab...");
        assertEquals(videoCard.getMedia().getPosterImageUrl().toString(), "https://i.ytimg.com/vi/0RFfrsABtQo/maxresdefault.jpg");
        assertEquals(videoCard.getAppLinkAndroid(), "http://www.youtube.com/watch?v=0RFfrsABtQo&feature=applinks");
        assertEquals(videoCard.getAppLinkIos(), "vnd.youtube://www.youtube.com/watch?v=0RFfrsABtQo&feature=applinks");
    }

}
