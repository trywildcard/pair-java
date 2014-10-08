package com.trywildcard.pair.model.media;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.article.Article;
import com.trywildcard.pair.model.article.ArticleBuilder;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 10/4/14.
 */
public class ImageValidationTest {

    Image image;

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullImgUrlString() throws CardBuilderException {
        image = new Image(null);
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


}
