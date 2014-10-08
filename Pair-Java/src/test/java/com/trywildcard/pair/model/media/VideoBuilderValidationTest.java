package com.trywildcard.pair.model.media;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyVideo;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoBuilderValidationTest {
    DummyVideo dummyVideo;
    VideoBuilder builder;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyVideo = new DummyVideo();
        builder = new VideoBuilder(dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleString() throws CardBuilderException {
        Video video = new VideoBuilder("", dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyEmbedUrlContent() throws CardBuilderException {
        Video video = new VideoBuilder(dummyVideo.title, "", dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNegativeHeight() throws CardBuilderException {
        Video video = new VideoBuilder(dummyVideo.title, dummyVideo.embeddedURL, -1, dummyVideo.embeddedURLWidth).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNegativeWidth() throws CardBuilderException {
        Video video = new VideoBuilder(dummyVideo.title, dummyVideo.embeddedURL, dummyVideo.embeddedURLHeight, -1).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleAndEmbed() throws CardBuilderException {
        Video video = new VideoBuilder("", "", dummyVideo.embeddedURLHeight, dummyVideo.embeddedURLWidth).build();
    }

    @Test
    public void hasErrorForNullPublicationDate (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.publicationDate(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullDescription (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.description(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullContributorString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.creator(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyStreamUrl(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.streamUrl("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullStreamContentTypeString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.streamContentType(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullPoster (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.posterImageUrl("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullSourceString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.source(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptySourceString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.source("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullAppLinkIosString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkIos(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyAppLinkIosString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkIos("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullAppLinkAndroidString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkAndroid(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyAppLinkAndroidString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkAndroid("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }
}
