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
        builder = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleString() throws CardBuilderException {
        Article article = new ArticleBuilder("", dummyArticle.abstractContent).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyAbstractContent() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, "").build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleAndAbstractContent() throws CardBuilderException {
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
    public void hasErrorForNullHtmlString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.htmlContent(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyHtmlString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.htmlContent("");
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

    @Test(expected = CardBuilderException.class)
    public void nullMetaTagModel() throws CardBuilderException {
        Article article = new ArticleBuilder(null).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn(null);
        when(metaTagModel.getDescription()).thenReturn(null);

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getDescription()).thenReturn("");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyTitleString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getDescription()).thenReturn("<html><body></body></html>");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyImageUrlString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getDescription()).thenReturn("");

        Article article = new ArticleBuilder(metaTagModel).build();
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getDescription()).thenReturn("<html><body></body></html>");

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(article.getAbstractContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getDescription()).thenReturn("<html><body></body></html>");
        when(metaTagModel.getHtmlContent()).thenReturn("");
        when(metaTagModel.getAppLinkAndroid()).thenReturn(null);
        when(metaTagModel.getAppLinkIos()).thenReturn(null);

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(article.getAbstractContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
        assertNull(article.getMedia());
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

        Article article = new ArticleBuilder(metaTagModel).build();
        assertEquals(((Image) article.getMedia()).getImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(article.getHtmlContent(), "<html><body></body></html>");
        assertEquals(article.getTitle(), "BBC News Article");
        assertEquals(article.getAbstractContent(), "description");
    }

}
