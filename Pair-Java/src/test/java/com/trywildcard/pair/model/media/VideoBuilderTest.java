package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyVideo;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoBuilderTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyVideo dummyVideo;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyVideo = new DummyVideo();
    }

    private void testMinimalVideoAttributes(Video video){
        Assert.assertEquals("Name should match", dummyVideo.title, video.getTitle());
        Assert.assertEquals("embeddedUrl should match", dummyVideo.embeddedURL, video.getEmbeddedUrl().toString());
        Assert.assertEquals("embeddedUrlHeight should match", dummyVideo.embeddedURLHeight, video.getEmbeddedUrlHeight());
        Assert.assertEquals("embeddedUrlWidth should match", dummyVideo.embeddedURLWidth, video.getEmbeddedUrlWidth());
    }

    private Video buildMinimalVideo() throws CardBuilderException {

        VideoBuilder videoBuilder = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth);

        return videoBuilder.build();
    }

    @Test
    public void testMinimalVideo() throws JsonProcessingException, CardBuilderException {
        Video video = buildMinimalVideo();
        testMinimalVideoAttributes(video);
    }

    @Test
    public void testMinimalArticleWithMinimalConstructor() throws CardBuilderException {
        Video video = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
        testMinimalVideoAttributes(video);
    }

    private void testExtensiveVideoAttributes(Video video) throws MalformedURLException {
        testMinimalVideoAttributes(video);

        Assert.assertEquals("streamURL should match", dummyVideo.streamURL, video.getStreamUrl().toString());
        Assert.assertEquals("streamURL Content Type should match", dummyVideo.streamContentType, video.getStreamContentType());
        Assert.assertEquals("posterImage Url should match", dummyVideo.posterImageUrl, video.getPosterImageUrl().toString());
        Assert.assertEquals("Description should match", dummyVideo.description, video.getDescription());
        Assert.assertEquals("Publication Date should match", dummyVideo.publicationDate, video.getPublicationDate());
        Assert.assertEquals("Contributor should match", dummyVideo.creator, video.getAuthor());
        Assert.assertEquals("Source should match", dummyVideo.source, video.getSource());
    }


    private Video buildExtensiveVideo() throws CardBuilderException {
        VideoBuilder builder = new VideoBuilder( dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth);

        builder.streamUrl(dummyVideo.streamURL);
        builder.streamContentType(dummyVideo.streamContentType);
        builder.posterImageUrl(dummyVideo.posterImageUrl);
        builder.description(dummyVideo.description);
        builder.author(dummyVideo.creator);
        builder.publicationDate(dummyVideo.publicationDate);
        builder.source(dummyVideo.source);

        return builder.build();
    }

    @Test
    public void testExtensiveVideoTest() throws IOException, URISyntaxException, CardBuilderException {
        Video video = buildExtensiveVideo();
        testExtensiveVideoAttributes(video);
    }
}
