package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyAbstractCard;
import com.trywildcard.pair.util.DummyImage;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by karthiksenthil on 1/22/15.
 */
public class ImageCardTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyImage dummyImage;
    private static DummyAbstractCard dummyAbstractCard;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyImage = new DummyImage();
        dummyAbstractCard = new DummyAbstractCard();
    }

    private void testMinimalImageCardAttributes(ImageCard imageCard) {
        Assert.assertEquals("Name should match", dummyImage.imageUrl, imageCard.getMedia().getImageUrl().toString());
    }


    private ImageCard buildMinimalImageCard() throws CardBuilderException {

        Image image = new Image(dummyImage.imageUrl);
        ImageCard imageCard = new ImageCard(image);

        return imageCard;
    }

    @Test
    public void testMinimalVideoCard() throws JsonProcessingException, CardBuilderException {
        ImageCard card = buildMinimalImageCard();
        testMinimalImageCardAttributes(card);
    }

    @Test
    public void testNullKeywords() throws CardBuilderException {
        ImageCard card = buildMinimalImageCard();
        card.setKeywords(null);
        assertNull(card.getKeywords());
    }

    public void testNullAppLinkIos() throws CardBuilderException {
        ImageCard card = buildMinimalImageCard();
        card.setAppLinkIos(null);
        assertNull(card.getAppLinkIos());
    }

    public void testNullAppLinkAndroid() throws CardBuilderException {
        ImageCard card = buildMinimalImageCard();
        card.setAppLinkAndroid(null);
        assertNull(card.getAppLinkAndroid());
    }

    @Test
    public void testMinimalImageCardWithMinimalConstructor() throws CardBuilderException {
        Image image = new Image(dummyImage.imageUrl);
        ImageCard imageCard = new ImageCard(image);
        testMinimalImageCardAttributes(imageCard);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_image_card.json");
        ImageCard fixtureCard = mapper.readValue(inputString, ImageCard.class);
        Image generatedImage = buildExtensiveImage();
        ImageCard generatedCard = new ImageCard(generatedImage, dummyImage.webUrl);
        generatedCard.setKeywords(dummyAbstractCard.keywords);
        generatedCard.setAppLinkIos(dummyAbstractCard.appLinkIos);
        generatedCard.setAppLinkAndroid(dummyAbstractCard.appLinkAndroid);

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_minimal_image_card.json");
        ImageCard fixtureCard = mapper.readValue(inputString, ImageCard.class);
        ImageCard generatedCard = buildMinimalImageCard();

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullImage() throws CardBuilderException {
        ImageCard card = new ImageCard(null, dummyImage.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithBothNull() throws CardBuilderException {
        ImageCard card = new ImageCard(null, null);
    }

    public void isInvalidWithNullWebUrl() throws CardBuilderException {
        Image image = new Image(dummyImage.imageUrl);
        ImageCard card = new ImageCard(image, null);
        assertEquals(card.getErrors().size(), 1);
    }

    private Image buildExtensiveImage() throws CardBuilderException {
        Image image = new Image(dummyImage.imageUrl);

        image.setImageCaption(dummyImage.imageCaption);
        image.setWidth(dummyImage.imageWidth);
        image.setHeight(dummyImage.imageHeight);
        image.setPublicationDate(dummyImage.publicationDate);
        image.setAuthor(dummyImage.author);
        image.setTitle(dummyImage.title);

        return image;
    }

}
