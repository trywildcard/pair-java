package com.trywildcard.pair.model.article;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummyArticle;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleBuilderValidationTest {

    DummyArticle dummyArticle;
    ArticleBuilder builder;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyArticle = new DummyArticle();
        builder = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleString() throws CardBuilderException {
        Article article = new ArticleBuilder("", dummyArticle.htmlContent).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyHtmlContent() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, "").build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleAndHtmlContent() throws CardBuilderException {
        Article article = new ArticleBuilder("", "").build();
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
    public void hasErrorForNullBylineString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.author(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyBylineString(){
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
    public void hasErrorForNullBreaking (){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.isBreaking(null);
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
        Article article = new ArticleBuilder(null).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn(null);
        when(metaTagModel.getHtmlContent()).thenReturn(null);

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getHtmlContent()).thenReturn("");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyTitleString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyImageUrlString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(article.getHtmlContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");
        when(metaTagModel.getDescription()).thenReturn("");
        when(metaTagModel.getAppLinkAndroid()).thenReturn(null);
        when(metaTagModel.getAppLinkIos()).thenReturn(null);

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(article.getHtmlContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
        assertNull(article.getMedia());
        assertNull(article.getAppLinkIos());
        assertNull(article.getAppLinkAndroid());
    }

    @Test
    public void validMetaTagModelValidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getImageUrl()).thenReturn("https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        when(metaTagModel.getHtmlContent()).thenReturn("<html><body></body></html>");
        when(metaTagModel.getAppLinkAndroid()).thenReturn("android://etsy/1234");
        when(metaTagModel.getAppLinkIos()).thenReturn("ios://etsy/1234");

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(((Image) article.getMedia()).getImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(article.getHtmlContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
        assertEquals(article.getAppLinkIos(), "ios://etsy/1234");
        assertEquals(article.getAppLinkAndroid(), "android://etsy/1234");
    }

}
