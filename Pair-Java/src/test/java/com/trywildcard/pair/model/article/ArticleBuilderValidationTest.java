package com.trywildcard.pair.model.article;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyArticle;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

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

}
