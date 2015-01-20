package com.trywildcard.pair.model.media;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.util.DummyVideo;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test(expected = CardBuilderException.class)
    public void nullMetaTagModel() throws CardBuilderException {
        Video video = new VideoBuilder(null).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn(null);
        when(metaTagModel.getVideoUrl()).thenReturn(null);
        when(metaTagModel.getVideoHeight()).thenReturn(null);
        when(metaTagModel.getVideoWidth()).thenReturn(null);

        Video video = new VideoBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getVideoUrl()).thenReturn("");
        when(metaTagModel.getVideoHeight()).thenReturn("");
        when(metaTagModel.getVideoWidth()).thenReturn("");

        Video video = new VideoBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyTitleString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getVideoUrl()).thenReturn("");
        when(metaTagModel.getVideoHeight()).thenReturn("1280");
        when(metaTagModel.getVideoWidth()).thenReturn("720");

        Video video = new VideoBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelInvalidWidth() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getVideoUrl()).thenReturn("http://youtube.com");
        when(metaTagModel.getVideoHeight()).thenReturn("1280");
        when(metaTagModel.getVideoWidth()).thenReturn("h720");

        Video video = new VideoBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelInvalidHeight() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getVideoUrl()).thenReturn("http://youtube.com");
        when(metaTagModel.getVideoHeight()).thenReturn("1280h");
        when(metaTagModel.getVideoWidth()).thenReturn("720");

        Video video = new VideoBuilder(metaTagModel).build();
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getVideoUrl()).thenReturn("http://youtube.com");
        when(metaTagModel.getVideoHeight()).thenReturn("1280");
        when(metaTagModel.getVideoWidth()).thenReturn("720");

        Video video = new VideoBuilder(metaTagModel).build();
        assertEquals(video.getEmbeddedUrl().toString(), "http://youtube.com");
        assertEquals(video.getEmbeddedUrlHeight(), new Integer(1280));
        assertEquals(video.getEmbeddedUrlWidth(), new Integer(720));
        assertEquals(video.getTitle(), "Title");
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getVideoUrl()).thenReturn("http://youtube.com");
        when(metaTagModel.getVideoHeight()).thenReturn("1280");
        when(metaTagModel.getVideoWidth()).thenReturn("720");
        when(metaTagModel.getImageUrl()).thenReturn(null);
        when(metaTagModel.getDescription()).thenReturn("");
        when(metaTagModel.getAppLinkAndroid()).thenReturn(null);
        when(metaTagModel.getAppLinkIos()).thenReturn(null);

        Video video = new VideoBuilder(metaTagModel).build();
        assertEquals(video.getEmbeddedUrl().toString(), "http://youtube.com");
        assertEquals(video.getEmbeddedUrlHeight(), new Integer(1280));
        assertEquals(video.getEmbeddedUrlWidth(), new Integer(720));
        assertEquals(video.getTitle(), "Title");
        assertNull(video.getDescription());
        assertNull(video.getPosterImageUrl());
        assertNull(video.getAppLinkIos());
        assertNull(video.getAppLinkAndroid());
    }

    @Test
    public void validMetaTagModelValidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Youtube Video");
        when(metaTagModel.getImageUrl()).thenReturn("https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        when(metaTagModel.getVideoUrl()).thenReturn("http://youtube.com");
        when(metaTagModel.getVideoHeight()).thenReturn("1280");
        when(metaTagModel.getVideoWidth()).thenReturn("720");
        when(metaTagModel.getDescription()).thenReturn("Description");
        when(metaTagModel.getAppLinkAndroid()).thenReturn("android://youtube/1234");
        when(metaTagModel.getAppLinkIos()).thenReturn("ios://youtube/1234");

        Video video = new VideoBuilder(metaTagModel).build();
        assertEquals(video.getEmbeddedUrl().toString(), "http://youtube.com");
        assertEquals(video.getEmbeddedUrlHeight(), new Integer(1280));
        assertEquals(video.getEmbeddedUrlWidth(), new Integer(720));
        assertEquals(video.getTitle(), "Youtube Video");
        assertEquals(video.getPosterImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(video.getDescription(), "Description");
        assertEquals(video.getAppLinkIos(), "ios://youtube/1234");
        assertEquals(video.getAppLinkAndroid(), "android://youtube/1234");
    }
}
