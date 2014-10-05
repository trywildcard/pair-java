package com.trywildcard.pair.model.review;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyReview;
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
public class ReviewBuilderTest {
    private static DummyReview dummyReview;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyReview = new DummyReview();
    }

    private void testMinimalReviewAttributes(Review review){
        Assert.assertEquals("Name should match", dummyReview.title, review.getTitle());
        Assert.assertEquals("HtmlContent should match", dummyReview.htmlContent, review.getHtmlContent());
    }

    private Review buildMinimalReview() throws CardBuilderException {

        ReviewBuilder reviewBuilder = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent);

        return reviewBuilder.build();
    }

    @Test
    public void testMinimalReview() throws JsonProcessingException, CardBuilderException {
        Review review = buildMinimalReview();
        testMinimalReviewAttributes(review);
    }

    @Test
    public void testMinimalReviewWithMinimalConstructor() throws CardBuilderException {
        Review review = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent).build();
        testMinimalReviewAttributes(review);
    }

    private void testExtensiveReviewAttributes(Review review) throws MalformedURLException {
        testMinimalReviewAttributes(review);

        Assert.assertEquals("productName should match", dummyReview.productName, review.getProductName());
        Assert.assertEquals("productName should match", dummyReview.rating, review.getRating());

        Assert.assertEquals("Abstract should match", dummyReview.abstractContent, review.getAbstractContent());
        Assert.assertEquals("Publication Date should match", dummyReview.publicationDate, review.getPublicationDate());
        Assert.assertEquals("Updated Date should match", dummyReview.updatedDate, review.getUpdatedDate());


        Assert.assertEquals("By-Line should match", dummyReview.byLine, review.getByLine());
        Assert.assertEquals("Media should match", dummyReview.image, review.getMedia());
        Assert.assertEquals("Source should match", dummyReview.source, review.getSource());
        Assert.assertEquals("Source should match", dummyReview.appLinkAndroid, review.getAppLinkAndroid());
        Assert.assertEquals("Source should match", dummyReview.appLinkIos, review.getAppLinkIos());
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

    @Test
    public void testExtensiveReviewTest() throws IOException, URISyntaxException, CardBuilderException {
        Review review = buildExtensiveReview();
        testExtensiveReviewAttributes(review);
    }
}
