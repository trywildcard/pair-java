package com.trywildcard.pair.model.review;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummyReview;
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
public class ReviewBuilderValidationTest {

    DummyReview dummyReview;
    ReviewBuilder builder;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyReview = new DummyReview();
        builder = new ReviewBuilder(dummyReview.title, dummyReview.htmlContent);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleString() throws CardBuilderException {
        Review review = new ReviewBuilder("", dummyReview.htmlContent).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyHtmlContent() throws CardBuilderException {
        Review review = new ReviewBuilder(dummyReview.title, "").build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleAndHtmlContent() throws CardBuilderException {
        Review review = new ReviewBuilder("", "").build();
    }

    @Test
    public void hasErrorForNullPublicationDate (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.publicationDate(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullUpdatedDate (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.updatedDate(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullAbstractString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.abstractContent(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyAbtractString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.abstractContent("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullAuthorString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.author(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyAuthorString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.author("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullMedia (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.media(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullProductName (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.productName(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullSourceString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.source(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullRating (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.rating(null);
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
        Review review = new ReviewBuilder(null).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn(null);
        when(metaTagModel.getHtmlContent()).thenReturn(null);

        Review review = new ReviewBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getHtmlContent()).thenReturn("");

        Review review = new ReviewBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyTitleString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");

        Review review = new ReviewBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyImageUrlString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("");

        Review review = new ReviewBuilder(metaTagModel).build();
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");

        Review review = new ReviewBuilder(metaTagModel).build();
        assertEquals(review.getHtmlContent(), "<html><body></body></html>");
        assertEquals(review.getTitle(), "BBC News Article");
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");
        when(metaTagModel.getDescription()).thenReturn("");
        when(metaTagModel.getAppLinkAndroid()).thenReturn(null);
        when(metaTagModel.getAppLinkIos()).thenReturn(null);

        Review review = new ReviewBuilder(metaTagModel).build();
        assertEquals(review.getHtmlContent(), "<html><body></body></html>");
        assertEquals(review.getTitle(), "BBC News Article");
        assertNull(review.getAbstractContent());
        assertNull(review.getAppLinkIos());
        assertNull(review.getAppLinkAndroid());
    }

    @Test
    public void validMetaTagModelValidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getImageUrl()).thenReturn("https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        when(metaTagModel.getDescription()).thenReturn("description");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");
        when(metaTagModel.getAppLinkAndroid()).thenReturn("android://etsy/1234");
        when(metaTagModel.getAppLinkIos()).thenReturn("ios://etsy/1234");

        Review review = new ReviewBuilder(metaTagModel).build();
        assertEquals(((Image) review.getMedia()).getImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(review.getHtmlContent(), "<html><body></body></html>");
        assertEquals(review.getTitle(), "BBC News Article");
        assertEquals(review.getAbstractContent(), "description");
        assertEquals(review.getAppLinkIos(), "ios://etsy/1234");
        assertEquals(review.getAppLinkAndroid(), "android://etsy/1234");
    }


}
