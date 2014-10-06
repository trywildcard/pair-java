package com.trywildcard.pair.model.review;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;

import com.trywildcard.pair.util.DummyReview;
import com.trywildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class ReviewCardTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyReview dummyReview;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyReview = new DummyReview();
    }

    private void testMinimalCardAttributes(ReviewCard card){
        assertEquals("Article Title should match", dummyReview.title, card.getReview().getTitle());
        assertEquals("Article HtmlContent should match", dummyReview.htmlContent, card.getReview().getHtmlContent());

        assertEquals("Web url should match", dummyReview.webUrl, card.getWebUrl().toString());
    }

    private ReviewCard buildMinimalReviewCard() throws CardBuilderException {

        Review review = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent).build();

        ReviewCard reviewCard = new ReviewCard(review, dummyReview.webUrl);

        return reviewCard;
    }

    @Test
    public void testMinimalReviewCard() throws JsonProcessingException, CardBuilderException {
        ReviewCard card = buildMinimalReviewCard();
        testMinimalCardAttributes(card);
    }


    @Test
    public void testMinimalReviewWithMinimalConstructor() throws CardBuilderException {
        Review review = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent).build();
        ReviewCard reviewCard = new ReviewCard(review, dummyReview.webUrl);
        testMinimalCardAttributes(reviewCard);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {

        String inputString = TestUtil.readResourceAsString("example_review_card.json");
        ReviewCard fixtureCard = mapper.readValue(inputString,  ReviewCard.class);

        Review generatedReview = buildExtensiveReview();
        ReviewCard generatedCard = new ReviewCard(generatedReview, dummyReview.webUrl);

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {

        String inputString = TestUtil.readResourceAsString("example_minimal_review_card.json");
        ReviewCard fixtureCard = mapper.readValue(inputString,  ReviewCard.class);
        ReviewCard generatedCard = buildMinimalReviewCard();
        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullReview() throws CardBuilderException {
        ReviewCard card = new ReviewCard(null, dummyReview.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithBothNull() throws CardBuilderException {
        ReviewCard card = new ReviewCard(null, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullWebUrl() throws CardBuilderException {
        Review review = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent).build();
        ReviewCard card = new ReviewCard(review, null);
    }

    private Review buildExtensiveReview() throws CardBuilderException {
        ReviewBuilder builder = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent);

        builder.productName(dummyReview.productName);
        builder.rating(dummyReview.rating);
        builder.abstractContent(dummyReview.abstractContent);
        builder.byLine(dummyReview.byLine);
        builder.media(dummyReview.image);
        builder.publicationDate(dummyReview.publicationDate);
        builder.updatedDate(dummyReview.updatedDate);
        builder.source(dummyReview.source);
        builder.appLinkIos(dummyReview.appLinkIos);
        builder.appLinkAndroid(dummyReview.appLinkAndroid);

        return builder.build();
    }
}