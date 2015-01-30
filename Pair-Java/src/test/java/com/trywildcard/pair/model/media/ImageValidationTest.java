package com.trywildcard.pair.model.media;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.article.Article;
import com.trywildcard.pair.model.article.ArticleBuilder;
import com.trywildcard.pair.util.DummyImage;
import com.trywildcard.pair.util.DummyVideo;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by karthiksenthil on 10/4/14.
 */
public class ImageValidationTest {

    Image image;
    Image imageToTest;
    DummyImage dummyImage;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyImage = new DummyImage();
        imageToTest = new Image(dummyImage.imageUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullImgUrlString() throws CardBuilderException {
        String test = null;
        image = new Image(test);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithInvalidImgUrlString() throws CardBuilderException {
        image = new Image("www.");
    }

    @Test
    public void isValidWithValidImgUrlString() throws CardBuilderException, MalformedURLException {
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg");
        assertEquals(image.getImageUrl(), new URL("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg"));
    }

    @Test
    public void isValidWithValidImgUrlButNullCaptionString() throws CardBuilderException, MalformedURLException {
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg", null);
        assertEquals(image.getImageUrl(), new URL("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg"));
        assertEquals("Errors size should match", 1, image.v.getErrors().size());
    }

    public void isValidWithValidImgUrlButInvalidCaptionString() throws CardBuilderException, MalformedURLException {
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg", "");
        assertEquals(image.getImageUrl(), new URL("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg"));
        assertEquals(image.getImageCaption(), null);
        assertEquals("Errors size should match", 1, image.v.getErrors().size());
    }

    public void isValidWithValidImgUrlButValidCaptionString() throws CardBuilderException, MalformedURLException {
        image = new Image("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg", "Image Caption");
        assertEquals(image.getImageUrl(), new URL("http://www.un.org/climatechange/summit/wp-content/uploads/sites/2/2013/09/climate_summit_2014.jpg"));
        assertEquals(image.getImageCaption(), "Image Caption");
        assertEquals(image.getType() , MediaType.IMAGE);
        assertEquals("Errors size should match", 0, image.v.getErrors().size());
    }

    @Test
    public void hasErrorForNullPublicationDate (){
        assertEquals("Errors size should match", 0, imageToTest.getErrors().size());
        imageToTest.setPublicationDate(null);
        assertEquals("Errors size should match", 1, imageToTest.getErrors().size());
    }

    @Test
    public void hasErrorForNullWidth (){
        assertEquals("Errors size should match", 0, imageToTest.getErrors().size());
        imageToTest.setWidth(null);
        assertEquals("Errors size should match", 1, imageToTest.getErrors().size());
    }

    @Test
    public void hasErrorForNullHeight (){
        assertEquals("Errors size should match", 0, imageToTest.getErrors().size());
        imageToTest.setHeight(null);
        assertEquals("Errors size should match", 1, imageToTest.getErrors().size());
    }

    @Test
    public void hasErrorForNullAuthor(){
        assertEquals("Errors size should match", 0, imageToTest.getErrors().size());
        imageToTest.setAuthor("");
        assertEquals("Errors size should match", 1, imageToTest.getErrors().size());
    }

    @Test
    public void hasErrorForNullTitle(){
        assertEquals("Errors size should match", 0, imageToTest.getErrors().size());
        imageToTest.setTitle("");
        assertEquals("Errors size should match", 1, imageToTest.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void nullMetaTagModel() throws CardBuilderException {
        MetaTagModel metaTagModel = null;
        Image image = new Image(metaTagModel);
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getImageUrl()).thenReturn(null);

        Image image = new Image(metaTagModel);
    }

    @Test
    public void inCompleteMetaTagModelInvalidWidth() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getImageUrl()).thenReturn(dummyImage.imageUrl);
        when(metaTagModel.getImageHeight()).thenReturn("1280");
        when(metaTagModel.getImageWidth()).thenReturn("h720");

        Image image = new Image(metaTagModel);
        assertEquals(image.getImageUrl().toString(), dummyImage.imageUrl);
    }

    @Test
    public void inCompleteMetaTagModelInvalidHeight() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getImageUrl()).thenReturn(dummyImage.imageUrl);
        when(metaTagModel.getImageHeight()).thenReturn("1280h");
        when(metaTagModel.getImageWidth()).thenReturn("720");

        Image image = new Image(metaTagModel);
        assertEquals(image.getImageUrl().toString(), dummyImage.imageUrl);
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getImageUrl()).thenReturn(dummyImage.imageUrl);

        Image image = new Image(metaTagModel);

        assertEquals(image.getImageUrl().toString(), dummyImage.imageUrl);
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Title");
        when(metaTagModel.getImageUrl()).thenReturn("http://flickr.com/image1");
        when(metaTagModel.getImageHeight()).thenReturn("1280");
        when(metaTagModel.getImageWidth()).thenReturn("720");
        when(metaTagModel.getDescription()).thenReturn("");
        when(metaTagModel.getAppLinkAndroid()).thenReturn(null);
        when(metaTagModel.getAppLinkIos()).thenReturn(null);

        Image image = new Image(metaTagModel);
        assertEquals(image.getImageUrl().toString(), "http://flickr.com/image1");
        assertEquals(image.getHeight(), new Integer(1280));
        assertEquals(image.getWidth(), new Integer(720));
        assertEquals(image.getTitle(), "Title");
        assertNull(image.getImageCaption());
    }

    @Test
    public void validMetaTagModelValidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("Flickr Image");
        when(metaTagModel.getImageUrl()).thenReturn("https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        when(metaTagModel.getImageHeight()).thenReturn("1280");
        when(metaTagModel.getImageWidth()).thenReturn("720");
        when(metaTagModel.getDescription()).thenReturn("Description");
        when(metaTagModel.getAppLinkAndroid()).thenReturn("android://youtube/1234");
        when(metaTagModel.getAppLinkIos()).thenReturn("ios://youtube/1234");

        Image image = new Image(metaTagModel);
        assertEquals(image.getImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(image.getHeight(), new Integer(1280));
        assertEquals(image.getWidth(), new Integer(720));
        assertEquals(image.getTitle(), "Flickr Image");
        assertEquals(image.getImageCaption(), "Description");
    }


}
